import ReviewItem from "./ReviewItem";
import { Stack } from "react-bootstrap";

interface Props {
  reviews: { id: number; name: string; comment: string; date: Date }[];
}

const ReviewList = ({ reviews }: Props) => {
  return (
    <>
      <h4>Kommentit</h4>
      <Stack direction="vertical" gap={3}>
        {reviews.map((review) => {
          return <ReviewItem key={review.id} review={review} />;
        })}
      </Stack>
    </>
  );
};

export default ReviewList;