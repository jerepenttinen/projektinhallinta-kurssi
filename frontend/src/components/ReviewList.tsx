import ReviewItem from "./ReviewItem"
import { Stack } from "react-bootstrap";

export interface Props{
    review: { id:number; name: string; comment: string; date: Date; }[]
}


const ReviewList = ({ reviews } : Props) => {

    const reviewList = reviews.map((review) => {
        return(
            <ReviewItem key={review.id} review={review}/>
        );
    });

    return(
        <>
            <h3>Kommentit</h3>
            <Stack direction="vertical" gap={3}>
                {reviewList}
            </Stack>
        </>
    );
};

export default ReviewList;