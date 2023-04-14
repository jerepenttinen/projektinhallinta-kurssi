import { Stack } from "react-bootstrap";
import { BsHandThumbsDown, BsHandThumbsUp, BsPrinter } from "react-icons/bs";
import { ReviewType } from "../Types";

export interface Props {
  review: ReviewType | null;
}

const ReviewItem = ({ review }: Props) => {
  return (
    <Stack className="w-100" direction="horizontal" gap={2}>
      <div
        className="bg-primary rounded-circle text-white d-flex align-items-center justify-content-center"
        style={{ width: 48, height: 48, fontSize: 12 }}
      >
        <span>Kuva</span>
      </div>

      <Stack gap={2}>
        <Stack direction="horizontal" className="justify-content-between">
          <span>{review?.userId}</span>
          <Stack className="float-right" direction="horizontal" gap={2}>
            <BsHandThumbsUp />
            <time>
              {review?.created.getDate()}.{review?.created.getMonth()}?.
              {review?.created.getFullYear()}
            </time>
          </Stack>
        </Stack>
        <span>{review?.content}</span>
      </Stack>
    </Stack>
  );
};

export default ReviewItem;
