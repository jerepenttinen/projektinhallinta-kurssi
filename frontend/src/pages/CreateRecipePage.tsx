import "bootstrap/dist/css/bootstrap.min.css";
import { Badge, Button, Form, Stack } from "react-bootstrap";
import { SortableList } from "../components/SortableList";
import {
  BsGripHorizontal,
  BsGripVertical,
  BsPlus,
  BsTrash,
  BsX,
} from "react-icons/bs";
import DropImages from "../components/DropImages";
import "./CreateRecipePage.css";
import { postNewRecipe, getCategories } from "../api/Recipes";
import { useFieldArray, useForm } from "react-hook-form";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useRef } from "react";
import { blobToBase64 } from "../utils/blob";
import { useMutation, useQuery } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";

function SortingRow(props: {
  children: React.ReactElement;
  onTrash: () => void;
}) {
  return (
    <Stack direction="horizontal" className="justify-content-between">
      <Stack direction="horizontal" gap={3} className="flex-grow-1 text-break">
        <BsGripHorizontal />
        {props.children}
      </Stack>
      <Button variant="link" className="text-secondary" onClick={props.onTrash}>
        <BsTrash />
      </Button>
    </Stack>
  );
}

// TODO: Lisää virhe viestejä
const createRecipeFormValidator = z.object({
  name: z.string(),
  ingredients: z.array(
    z.object({
      ingredient: z.string(),
      quantity: z.string(),
    }),
  ),
  instructions: z.array(
    z.object({
      instruction: z.string(),
    }),
  ),
  images: z.array(
    z.object({
      blob: z.instanceof(Blob),
    }),
  ),
  categories: z.array(
    z.object({
      category: z.string(),
    }),
  ),
});

function AddIngredient(props: {
  onAdd: (item: { ingredient: string; quantity: string }) => void;
}) {
  const ingredient = useRef<HTMLInputElement>(null);
  const quantity = useRef<HTMLInputElement>(null);

  return (
    <Stack direction="horizontal" gap={2}>
      <Form.Control type="text" placeholder="Raaka-aine" ref={ingredient} />
      <Form.Control type="text" placeholder="Määrä" ref={quantity} />
      <Button
        variant="success"
        className="hstack"
        onClick={() => {
          props.onAdd({
            ingredient: ingredient.current!.value,
            quantity: quantity.current!.value,
          });
        }}
      >
        <BsPlus size={24} /> Lisää
      </Button>
    </Stack>
  );
}

function AddInstruction(props: {
  onAdd: (item: { instruction: string }) => void;
}) {
  const instruction = useRef<HTMLInputElement>(null);

  return (
    <Stack direction="horizontal" gap={2}>
      <Form.Control type="text" placeholder="Uusi vaihe" ref={instruction} />
      <Button
        variant="success"
        className="hstack gap-1"
        onClick={() => {
          props.onAdd({ instruction: instruction.current!.value });
        }}
      >
        <BsPlus size={24} /> Lisää
      </Button>
    </Stack>
  );
}

export default function CreateRecipePage() {
  const categoriesQuery = useQuery(["categories"], getCategories);
  const newRecipeMutation = useMutation(postNewRecipe);
  const navigate = useNavigate();

  const { register, control, handleSubmit } = useForm<
    z.infer<typeof createRecipeFormValidator>
  >({
    resolver: zodResolver(createRecipeFormValidator),
  });

  const ingredients = useFieldArray({
    control,
    name: "ingredients",
  });

  const instructions = useFieldArray({
    control,
    name: "instructions",
  });

  const images = useFieldArray({
    control,
    name: "images",
  });

  const categories = useFieldArray({
    control,
    name: "categories",
  });

  return (
    <Form
      className="py-4 px-3 container vstack gap-3"
      onSubmit={handleSubmit(async (data) => {
        newRecipeMutation.mutate(
          {
            recipeName: data.name,
            categories: data.categories.map((item) => item.category),
            images: await Promise.all(
              data.images.map((image) => blobToBase64(image.blob)),
            ),
            ingredients: data.ingredients,
            instructions: data.instructions.map(
              (instruction) => instruction.instruction,
            ),
          },
          {
            onSuccess(data) {
              navigate(`/recipes/${data.id}`);
            },
          },
        );
      })}
    >
      <h2 className="mb-0">Lisää uusi resepti</h2>
      <Form.Group>
        <Form.Label>Reseptin nimi</Form.Label>
        <Form.Control
          type="text"
          placeholder="Reseptin nimi"
          {...register("name")}
        />
      </Form.Group>
      <Form.Group>
        <Form.Label>Raaka-aineet</Form.Label>
        <Stack gap={3}>
          <SortableList
            move={ingredients.move}
            items={ingredients.fields}
            render={(field, index) => (
              <SortingRow
                onTrash={() => {
                  ingredients.remove(index);
                }}
              >
                <span className="user-select-none">
                  {field.quantity} {field.ingredient}
                </span>
              </SortingRow>
            )}
          />
          <AddIngredient onAdd={(item) => ingredients.append(item)} />
        </Stack>
      </Form.Group>
      <Form.Group>
        <Form.Label>Ohjeet</Form.Label>
        <Stack gap={3} as="ol" className="ps-0">
          <SortableList
            items={instructions.fields}
            move={instructions.move}
            render={(item, index) => (
              <SortingRow
                onTrash={() => {
                  instructions.remove(index);
                }}
              >
                <li className="ms-3 ps-1">{item.instruction}</li>
              </SortingRow>
            )}
          />
          <AddInstruction onAdd={(item) => instructions.append(item)} />
        </Stack>
      </Form.Group>
      <Form.Group>
        <Form.Label>Kategoriat</Form.Label>
        <Form.Select
          onChange={(e) => {
            const select = e.currentTarget;
            if (select.selectedIndex === 0) {
              return;
            }

            categories.append({ category: select.value });
            select.selectedIndex = 0;
          }}
        >
          <option>Lisää kategoria</option>
          {categoriesQuery.data
            ?.filter(
              (category) =>
                !categories.fields
                  .map((field) => field.category)
                  .includes(category),
            )
            .map((category) => (
              <option key={category}>{category}</option>
            ))}
        </Form.Select>
        <Stack direction="horizontal" gap={2} className="mt-3">
          {categories.fields.map((category, index) => (
            <Badge bg="secondary" key={category.id}>
              {category.category}{" "}
              <Button
                variant="link"
                className="text-white p-0 align-baseline"
                onClick={() => categories.remove(index)}
              >
                <BsX />
              </Button>
            </Badge>
          ))}
        </Stack>
      </Form.Group>
      <Form.Group>
        <Form.Label>Kuvat</Form.Label>
        <Stack direction="horizontal" gap={3} className="mb-3">
          <SortableList
            items={images.fields}
            move={images.move}
            render={(image, index) => {
              const url = URL.createObjectURL(image.blob);
              return (
                <div key={image.id} className="position-relative">
                  <img
                    src={url}
                    alt={image.id}
                    style={{ objectFit: "cover", width: 100, height: 100 }}
                  />

                  <div className="hover">
                    <div className="bg-light opacity-50 w-100 h-100 position-absolute top-0"></div>
                    <BsGripVertical className="position-absolute start-50 top-50 translate-middle" />
                    <Button
                      variant="danger"
                      className="position-absolute top-0 end-0 p-0"
                      style={{ width: 24, height: 24 }}
                      onClick={() => images.remove(index)}
                    >
                      <BsX />
                    </Button>
                  </div>
                </div>
              );
            }}
          />
        </Stack>
        <DropImages
          onImageDropped={(buf) => images.append({ blob: new Blob([buf]) })}
        />
      </Form.Group>
      <div>
        <Button variant="success" size="lg" type="submit">
          Julkaise
        </Button>
      </div>
    </Form>
  );
}
