import { BsHandThumbsDown, BsHandThumbsUp } from "react-icons/bs";
import {
  Badge,
  Button,
  ButtonGroup,
  Carousel,
  CarouselItem,
  Form,
  Stack,
} from "react-bootstrap";
import ReviewList from "../components/ReviewList";
import PageContainer from "../components/PageContainer";
import { GetRecipeById } from "../api/Recipes";
import { GetReviewByRecipeId } from "../api/Reviews";
import { useQuery } from "@tanstack/react-query";
import { useParams } from "react-router-dom";
import { Suspense } from "react";
import { Base64Image } from "../components/Base64Image";

interface IngredientRowProps {
  quantity: string;
  name: string;
}

const IngredientRow = ({ name, quantity }: IngredientRowProps) => {
  return (
    <Stack gap={2}>
      <Stack direction="horizontal" gap={3}>
        <div style={{ minWidth: 150 }}>
          <span style={{ float: "right" }}> {quantity} </span>
        </div>
        <span>{name}</span>
      </Stack>
    </Stack>
  );
};

const ReviewSection = () => {
  const { id } = useParams();
  const reviewsQuery = useQuery({
    queryKey: ["recipes", id, "reviews"],
    queryFn: () => GetReviewByRecipeId(id ?? ""),
    enabled: typeof id === "string",
    suspense: true,
  });

  return (
    <Stack gap={3} as="section">
      <h3>Arvostelut</h3>
      <Suspense fallback={<p>Ladataan...</p>}>
        {reviewsQuery.data ? <ReviewList reviews={reviewsQuery.data} /> : null}
      </Suspense>
    </Stack>
  );
};

const CreateReviewSection = () => {
  return (
    <Stack gap={3} as="form">
      <h3 className="mb-0">Lisää arvostelu</h3>
      <div>
        <ButtonGroup>
          <input
            type="radio"
            className="btn-check"
            name="like"
            id="like"
            defaultChecked
          />
          <label className="btn btn-outline-primary" htmlFor="like">
            <BsHandThumbsUp /> Tykkäsin
          </label>

          <input
            type="radio"
            className="btn-check"
            name="dislike"
            id="dislike"
          />
          <label className="btn btn-outline-primary" htmlFor="dislike">
            <BsHandThumbsDown /> En tykkänyt
          </label>
        </ButtonGroup>
      </div>

      <Form.Control as="textarea" rows={6} />
      <div>
        <Button variant="success">Julkaise</Button>
      </div>
    </Stack>
  );
};

const RecipeSection = () => {
  const { id } = useParams();

  const recipeQuery = useQuery({
    queryKey: ["recipes", id],
    queryFn: () => GetRecipeById(id ?? ""),
    enabled: typeof id === "string",
    suspense: true,
  });

  return (
    <Stack gap={3} as="section">
      <h2 className="mb-0">{recipeQuery.data?.recipeName}</h2>
      <Stack direction="horizontal" gap={2}>
        <div
          className="bg-primary rounded-circle text-white d-flex align-items-center justify-content-center"
          style={{ width: 32, height: 32, fontSize: 10 }}
        >
          <span>Kuva</span>
        </div>
        <span>Etunimi Sukunimi</span>
      </Stack>

      <Stack direction="horizontal" gap={2}>
        <Badge bg="secondary">Alkuruuat</Badge>
        <Badge bg="secondary">Alkuruuat</Badge>
      </Stack>

      <Carousel>
        {recipeQuery.data?.images.map((image, i) => (
          <CarouselItem>
            <div
              className="d-flex w-100 justify-content-center align-items-center"
              style={{ height: 400 }}
            >
              <Base64Image
                id={recipeQuery.data.id.toString() + i}
                image={image}
              />
            </div>
          </CarouselItem>
        ))}
      </Carousel>

      {/* <Stack direction="horizontal" className="justify-content-end">
        <Stack direction="horizontal" gap={2}>
          <BsHandThumbsUp />
          <span>1</span>
          <BsHandThumbsDown />
          <span>1</span>
          <span>50%</span>
        </Stack>
      </Stack> */}

      <h3>Raaka-aineet</h3>
      <div>
        {recipeQuery.data?.ingredients.map((ingredient) => (
          <IngredientRow
            key={ingredient.id}
            name={ingredient.ingredient}
            quantity={ingredient.quantity}
          />
        ))}
      </div>
      <h3>Ohjeet</h3>
      <ol>
        {recipeQuery.data?.instructions.map((instruction, i) => (
          <li key={i}>{instruction}</li>
        ))}
      </ol>
    </Stack>
  );
};

const RecipePage = () => {
  return (
    <PageContainer gap={3}>
      <Suspense>
        <RecipeSection />
        <CreateReviewSection />
        <ReviewSection />
      </Suspense>
    </PageContainer>
  );
};

export default RecipePage;
