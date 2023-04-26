import { Review } from "./ReviewItem";
import { Stack } from "react-bootstrap";
import { z } from "zod";
import { recipeReview, user } from "../api/validators";

const ReviewList = (props: {
  reviews: z.infer<typeof recipeReview>[];
  users: Record<number, z.infer<typeof user>>;
}) => {
  return (
    <Stack gap={3} id="review-list">
      {props.reviews.map((review) => {
        return (
          <Review
            key={review.id}
            review={review}
            reviewer={props.users[review.userId]}
          />
        );
      })}
    </Stack>
  );
};

export default ReviewList;
