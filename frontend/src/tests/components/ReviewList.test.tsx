import ReviewList from "../../components/ReviewList"
import { describe, test, expect } from "vitest"
import { render, screen } from "@testing-library/react";

const dummyReviewData: {
  id: number;
  name: string;
  comment: string;
  date: Date;
}[] = [
  {
    id: 1,
    name: "Erkki Esimerkki",
    comment: "Suorastaan herkullista!",
    date: new Date(2017, 4, 4),
  },
  {
    id: 2,
    name: "Matti Möttönen",
    comment: "Suorastaan oksettavaa!",
    date: new Date(2022, 2, 7),
  },
];

describe('ReviewList', () => {
    test("should show review list", () => {
        const reviewList = render(<ReviewList reviews={dummyReviewData}/>);
        const h4 = reviewList.container.querySelector("h4");

        expect(reviewList).toBeTruthy();
        expect(h4?.textContent).toBe("Kommentit");
        expect(screen.getByText("Erkki Esimerkki")).toBeInTheDocument();
        expect(screen.getByText("Suorastaan herkullista!")).toBeInTheDocument();
        expect(screen.getByText("Matti Möttönen")).toBeInTheDocument();
        expect(screen.getByText("Suorastaan oksettavaa!")).toBeInTheDocument();
        expect(screen.getByText("4.4.2017")).toBeInTheDocument();
        expect(screen.getByText("7.2.2022")).toBeInTheDocument();

        screen.debug();
    });
});