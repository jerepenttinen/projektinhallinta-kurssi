import { BsHandThumbsDown, BsHandThumbsUp, BsPrinter } from "react-icons/bs";
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
import { useQueries } from "@tanstack/react-query";
import { useParams } from "react-router-dom";
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

const RecipePage = () => {
  const { id } = useParams();

  const [recipeQuery, reviewsQuery] = useQueries({
    queries: [
      {
        queryKey: ["recipes", id],
        queryFn: () => GetRecipeById(id ?? ""),
        enabled: typeof id === "string",
      },
      {
        queryKey: ["recipes", id, "reviews"],
        queryFn: () => GetReviewByRecipeId(id ?? ""),
        enabled: typeof id === "string",
      },
    ],
  });

  return (
    <PageContainer gap={3}>
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
        <CarouselItem>
          <div
            className="d-flex bg-secondary bg-opacity-50 w-100 justify-content-center align-items-center"
            style={{ height: 400 }}
          >
            <h2>Kuvakaruselli</h2>
          </div>
        </CarouselItem>
        <CarouselItem>
          <div
            className="d-flex bg-secondary bg-opacity-50 w-100 justify-content-center align-items-center"
            style={{ height: 400 }}
          >
            <h2>Toka kuva</h2>
          </div>
        </CarouselItem>
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
        {recipeQuery.data?.instructions.map((instruction, i) => {
          return <li key={i}>{instruction}</li>;
        })}
      </ol>

      <h3>Lisää arvostelu</h3>
      <div>
        <ButtonGroup>
          {/* TODO: use some component? */}
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

      <h3>Arvostelut</h3>
      {/* <ReviewList reviews={reviews} /> */}
    </PageContainer>
  );
};

export default RecipePage;
