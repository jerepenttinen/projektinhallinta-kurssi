import "bootstrap/dist/css/bootstrap.min.css";
import { useRef, useState } from "react";
import { Badge, Button, Form, Stack } from "react-bootstrap";
import { SortableList } from "../components/SortableList";
import { BsGripHorizontal, BsPlus, BsTrash, BsX } from "react-icons/bs";
import DropImages from "../components/DropImages";

const uuid = () => URL.createObjectURL(new Blob([])).substring(-36);

function SortingRow(props: {
  render: () => React.ReactElement;
  onTrash: () => void;
}) {
  return (
    <Stack direction="horizontal" className="justify-content-between">
      <Stack direction="horizontal" gap={3} className="flex-grow-1 text-break">
        <BsGripHorizontal />
        {props.render()}
      </Stack>
      <Button variant="link" className="text-secondary" onClick={props.onTrash}>
        <BsTrash />
      </Button>
    </Stack>
  );
}

const CreateRecipePage = () => {
  const [ingredients, setIngredients] = useState([
    { id: "1", ingredient: "Peruna", quantity: "1 kg" },
    { id: "2", ingredient: "Liha", quantity: "500 g" },
    { id: "3", ingredient: "Maito", quantity: "200 g" },
  ]);

  const [instructions, setInstructions] = useState([
    { id: "1", instruction: "Keitä peruna" },
    { id: "2", instruction: "Paista liha" },
    { id: "3", instruction: "Lisää maito" },
  ]);

  const [images, setImages] = useState<{ id: string; blob: Blob }[]>([]);

  const quantityRef = useRef<HTMLInputElement | null>(null);
  const ingredientRef = useRef<HTMLInputElement | null>(null);
  const stepRef = useRef<HTMLInputElement | null>(null);

  return (
    <Form
      className="py-4 px-3 container vstack gap-3"
      style={{ maxWidth: 768 }}
    >
      <h2 className="mb-0">Lisää uusi resepti</h2>
      <Form.Group>
        <Form.Label>Reseptin nimi</Form.Label>
        <Form.Control type="text" placeholder="Reseptin nimi" />
      </Form.Group>
      <Form.Group>
        <Form.Label>Raaka-aineet</Form.Label>
        <Stack gap={3}>
          <SortableList
            items={ingredients}
            setItems={setIngredients}
            renderItem={(item) => (
              <SortingRow
                render={() => (
                  <span className="user-select-none">
                    {item.quantity} {item.ingredient}
                  </span>
                )}
                onTrash={() => {
                  setIngredients((ingredients) =>
                    ingredients.filter((ig) => ig.id !== item.id),
                  );
                }}
              />
            )}
          />
        </Stack>
        <Stack direction="horizontal" className="mt-3" gap={2}>
          <Form.Control
            type="text"
            placeholder="Raaka-aine"
            ref={ingredientRef}
          />
          <Form.Control type="text" placeholder="Määrä" ref={quantityRef} />
          <Button
            variant="success"
            className="hstack"
            onClick={() =>
              setIngredients((ingredients) =>
                ingredients.concat({
                  id: uuid(),
                  ingredient: ingredientRef.current?.value ?? "",
                  quantity: quantityRef.current?.value ?? "",
                }),
              )
            }
          >
            <BsPlus size={24} /> Lisää
          </Button>
        </Stack>
      </Form.Group>
      <Form.Group>
        <Form.Label>Ohjeet</Form.Label>
        <ol className="vstack gap-3 ps-0 mb-0">
          <SortableList
            items={instructions}
            setItems={setInstructions}
            renderItem={(item) => (
              <SortingRow
                render={() => <li className="ms-3 ps-1">{item.instruction}</li>}
                onTrash={() => {
                  setInstructions((instructions) =>
                    instructions.filter((ig) => ig.id !== item.id),
                  );
                }}
              />
            )}
          />
        </ol>
        <Stack direction="horizontal" className="mt-3" gap={2}>
          <Form.Control type="text" placeholder="Uusi vaihe" ref={stepRef} />
          <Button
            variant="success"
            className="hstack gap-1"
            onClick={() =>
              setInstructions((instructions) =>
                instructions.concat({
                  id: uuid(),
                  instruction: stepRef.current?.value ?? "",
                }),
              )
            }
          >
            <BsPlus size={24} /> Lisää
          </Button>
        </Stack>
      </Form.Group>
      {/* TODO */}
      <Form.Group>
        <Form.Label>Kategoriat</Form.Label>
        <Form.Select>
          <option></option>
          <option>Välipalat</option>
        </Form.Select>
        <Stack direction="horizontal" gap={2} className="mt-3">
          <Badge bg="secondary">
            Alkuruuat <BsX />
          </Badge>
          <Badge bg="secondary">
            Kalaruuat <BsX />
          </Badge>
        </Stack>
      </Form.Group>
      <Form.Group>
        <Form.Label>Kuvat</Form.Label>
        <Stack direction="horizontal" gap={3}>
          <SortableList
            items={images}
            setItems={setImages}
            renderItem={(image) => {
              const url = URL.createObjectURL(image.blob);
              return (
                <div key={image.id} className="position-relative">
                  <img
                    src={url}
                    alt={image.id}
                    style={{ objectFit: "cover", width: 100, height: 100 }}
                  />
                  <Button
                    variant="danger"
                    className="position-absolute top-0 end-0 p-0"
                    style={{ width: 24, height: 24 }}
                    onClick={() =>
                      setImages((images) =>
                        images.filter(({ id }) => id !== image.id),
                      )
                    }
                  >
                    <BsX />
                  </Button>
                </div>
              );
            }}
          />
        </Stack>
        <DropImages
          onImageDropped={(buf) =>
            setImages((images) =>
              images.concat({ id: uuid(), blob: new Blob([buf]) }),
            )
          }
        />
      </Form.Group>
      <div>
        <Button variant="success" size="lg">
          Julkaise
        </Button>
      </div>
    </Form>
  );
};

export default CreateRecipePage;
