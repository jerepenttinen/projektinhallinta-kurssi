import { Stack } from "react-bootstrap";
import { BsHandThumbsDown, BsHandThumbsUp } from "react-icons/bs";
import { recipeReview, user } from "../api/validators";
import { z } from "zod";
import dayjs from "dayjs";
import { Link } from "react-router-dom";
import { Base64Image } from "./Base64Image";
import { Avatar } from "./Avatar";

export const Review = ({
  review,
  reviewer,
}: {
  review: z.infer<typeof recipeReview>;
  reviewer?: z.infer<typeof user>;
}) => {
  return (
    <Stack className="w-100" direction="horizontal" gap={2}>
      <Avatar size="m">
        {typeof reviewer !== "undefined" && reviewer.image !== null ? (
          <Base64Image id={`user-${reviewer.id}`} image={reviewer.image} />
        ) : null}
      </Avatar>

      <Stack gap={2}>
        <Stack direction="horizontal" className="justify-content-between">
          <Link to={`/profile/${review.userId}`}>{reviewer?.username}</Link>
          <Stack className="float-right" direction="horizontal" gap={2}>
            {review.upvote ? <BsHandThumbsUp /> : <BsHandThumbsDown />}
            <time>{dayjs(review.created).format("DD.MM.YYYY")}</time>
          </Stack>
        </Stack>
        <span>{review.content}</span>
      </Stack>
    </Stack>
  );
};
