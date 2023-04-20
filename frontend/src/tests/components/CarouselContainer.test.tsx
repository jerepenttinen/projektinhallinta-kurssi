import CarouselContainer from "../../components/CarouselContainer";
import RecipeCard from "../../components/RecipeCard";
import { describe, test, expect } from "vitest";
import { render, screen } from "@testing-library/react";

const dummyData = [
  { id: 1, header: "Kinkkukiusaus" },
  { id: 2, header: "Lihamureke" },
];
const data = dummyData.map((recipe) => (
  <RecipeCard key={recipe.id} id={recipe.id} header={recipe.header} />
));
describe("Carousel container", () => {
  test("should show carousel container", () => {
    const carouselContainer = render(
      <CarouselContainer>{data}</CarouselContainer>,
    );

    expect(carouselContainer).toBeTruthy();

    screen.debug();
  });
});
