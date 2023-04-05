import RecipePage from "../../pages/RecipePage";
import { describe, test, expect } from "vitest";
import { render, screen } from "@testing-library/react";

describe("Recipe page", () => {
  test("should show recipepage", () => {
    const wrapper = render(<RecipePage />);
    expect(wrapper).toBeTruthy();

    const h2 = wrapper.container.querySelector("h2");
    expect(h2?.textContent).toBe("Reseptin nimi");
    screen.debug();
  });
});
