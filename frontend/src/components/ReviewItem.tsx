import { Stack } from "react-bootstrap";
import { BsHandThumbsDown, BsHandThumbsUp } from "react-icons/bs";
import { recipeReview } from "../api/validators";
import { z } from "zod";
import dayjs from "dayjs";

export const Review = ({
  review,
}: {
  review: z.infer<typeof recipeReview>;
}) => {
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
          <span>{review.userId}</span>
          <Stack className="float-right" direction="horizontal" gap={2}>
            <BsHandThumbsUp />
            <time>{dayjs(review.created).format("DD.MM.YYYY")}</time>
          </Stack>
        </Stack>
        <span>{review.content}</span>
      </Stack>
    </Stack>
  );
};
