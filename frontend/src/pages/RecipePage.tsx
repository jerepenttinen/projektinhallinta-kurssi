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
import { CreateReviewForRecipe, GetReviewByRecipeId } from "../api/Reviews";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { useParams } from "react-router-dom";
import { Suspense } from "react";
import { Base64Image } from "../components/Base64Image";
import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { AxiosError } from "axios";

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

  const usersQuery = useQuery({
    queryKey: ["recipes", id, "reviews", "users"],
    queryFn: () =>
      GetMultipleUsers(reviewsQuery.data!.map((review) => review.userId)),
    enabled:
      typeof reviewsQuery.data !== "undefined" && reviewsQuery.data?.length > 0,
  });

  return (
    <Stack gap={3} as="section">
      <h3>Arvostelut</h3>
      <Suspense fallback={<p>Ladataan...</p>}>
        {reviewsQuery.data && usersQuery.data ? (
          <ReviewList reviews={reviewsQuery.data} users={usersQuery.data} />
        ) : null}
      </Suspense>
    </Stack>
  );
};

const ReviewSummarySection = () => {
  const { id } = useParams();
  const reviewsQuery = useQuery({
    queryKey: ["recipes", id, "reviews"],
    queryFn: () => GetReviewByRecipeId(id ?? ""),
    enabled: typeof id === "string",
  });

  const reviewSummary = reviewsQuery.data?.reduce<{ up: number; down: number }>(
    (acc, cur) => {
      return {
        up: cur.upvote ? acc.up + 1 : acc.up,
        down: !cur.upvote ? acc.down + 1 : acc.down,
      };
    },
    {
      up: 0,
      down: 0,
    },
  );

  const total = (reviewSummary?.down ?? 0) + (reviewSummary?.up ?? 0);
  const percentage =
    total === 0 ? "0" : (((reviewSummary?.up ?? 0) / total) * 100).toFixed(0);

  return (
    <Stack direction="horizontal" className="justify-content-end">
      <Stack direction="horizontal" gap={2}>
        <BsHandThumbsUp />
        <span>{reviewSummary?.up ?? 0}</span>
        <BsHandThumbsDown />
        <span>{reviewSummary?.down ?? 0}</span>
        <span>{percentage}%</span>
      </Stack>
    </Stack>
  );
};

const createReviewFormValidator = z.object({
  vote: z.enum(["upvote", "downvote"]),
  content: z.string(),
});

const CreateReviewSection = () => {
  const { id } = useParams();

  const { register, handleSubmit, setError, formState } = useForm<
    z.infer<typeof createReviewFormValidator>
  >({
    resolver: zodResolver(createReviewFormValidator),
  });

  const createReviewMutation = useMutation(CreateReviewForRecipe);
  const queryClient = useQueryClient();

  if (typeof id !== "string") {
    return null;
  }

  return (
    <Form
      className="vstack gap-3"
      onSubmit={handleSubmit((data) => {
        createReviewMutation.mutate(
          {
            recipeId: Number(id),
            content: data.content,
            upvote: data.vote === "upvote",
          },
          {
            onError(error) {
              if (error instanceof AxiosError) {
                setError("root", {
                  type: "custom",
                  message: error.response?.data.message,
                });
              }
            },
            onSuccess() {
              queryClient.invalidateQueries(["recipes", id, "reviews"]);
            },
          },
        );
      })}
    >
      <h3 className="mb-0">Lisää arvostelu</h3>
      <div>
        <ButtonGroup>
          <input
            type="radio"
            className="btn-check"
            id="like"
            value="upvote"
            defaultChecked
            {...register("vote")}
          />
          <label className="btn btn-outline-primary" htmlFor="like">
            <BsHandThumbsUp /> Tykkäsin
          </label>

          <input
            type="radio"
            className="btn-check"
            id="dislike"
            value="downvote"
            {...register("vote")}
          />
          <label className="btn btn-outline-primary" htmlFor="dislike">
            <BsHandThumbsDown /> En tykkänyt
          </label>
        </ButtonGroup>
      </div>

      <Form.Control as="textarea" rows={6} {...register("content")} />

      {formState.errors.root && (
        <span role="alert" className="text-danger">
          {formState.errors.root.message}
        </span>
      )}

      <div>
        <Button variant="success" type="submit">
          Julkaise
        </Button>
      </div>
    </Form>
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

  const creatorQuery = useQuery({
    queryKey: ["recipes", id, "creator"],
    queryFn: () => GetUser(recipeQuery.data!.creatorId.toString()),
    enabled: typeof recipeQuery.data !== "undefined",
    suspense: true,
  });

  return (
    <Stack gap={3} as="section">
      <h2 className="mb-0">{recipeQuery.data?.recipeName}</h2>
      <Suspense>
        <Stack direction="horizontal" gap={2}>
          <div
            className="bg-primary rounded-circle text-white d-flex align-items-center justify-content-center"
            style={{ width: 32, height: 32, fontSize: 10 }}
          >
            {typeof creatorQuery.data !== "undefined" &&
            creatorQuery.data.image !== null ? (
              <Base64Image
                id={`user-${creatorQuery.data.id}`}
                image={creatorQuery.data.image}
              />
            ) : (
              <span></span>
            )}
          </div>
          <Link to={`/profile/${recipeQuery.data?.creatorId}`}>
            {creatorQuery.data?.username}
          </Link>
        </Stack>
      </Suspense>

      <Stack direction="horizontal" gap={2}>
        {recipeQuery.data?.categories.map((category) => (
          <Badge bg="secondary" key={category}>
            {category}
          </Badge>
        ))}
      </Stack>

      <Carousel>
        {recipeQuery.data?.images.map((image, i) => (
          <CarouselItem key={i}>
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

      <ReviewSummarySection />

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
