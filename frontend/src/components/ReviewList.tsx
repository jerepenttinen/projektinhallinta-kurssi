import { Review } from "./ReviewItem";
import { Stack } from "react-bootstrap";
import { z } from "zod";
import { recipeReview } from "../api/validators";

const ReviewList = (props: { reviews: z.infer<typeof recipeReview>[] }) => {
  return (
    <Stack gap={3}>
      {props.reviews.map((review) => {
        return <Review key={review.id} review={review} />;
      })}
    </Stack>
  );
};

export default ReviewList;
