import { Stack } from "react-bootstrap";
import { BsHandThumbsDown, BsHandThumbsUp } from "react-icons/bs";
import { recipeReview, user } from "../api/validators";
import { z } from "zod";
import dayjs from "dayjs";
import { Link } from "react-router-dom";
import { Base64Image } from "./Base64Image";

export const Review = ({
  review,
  reviewer,
}: {
  review: z.infer<typeof recipeReview>;
  reviewer?: z.infer<typeof user>;
}) => {
  return (
    <Stack className="w-100" direction="horizontal" gap={2}>
      <div
        className="bg-primary rounded-circle text-white d-flex align-items-center justify-content-center"
        style={{ width: 48, height: 48, fontSize: 12 }}
      >
        {typeof reviewer !== "undefined" && reviewer.image !== null ? (
          <Base64Image id={`user-${reviewer.id}`} image={reviewer.image} />
        ) : (
          <span></span>
        )}
      </div>

      <Stack gap={2}>
        <Stack direction="horizontal" className="justify-content-between">
          <Link to={`/profile/${review.userId}`}>{reviewer?.username}</Link>
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
