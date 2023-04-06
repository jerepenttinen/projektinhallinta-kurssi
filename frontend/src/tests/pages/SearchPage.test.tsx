import SearchPage from "../../pages/SearchPage";
import { describe, test, expect } from "vitest";
import { render, screen } from "@testing-library/react";

describe("Searchpage", () => {
  test("should show searchpage", () => {
    const wrapper = render(<SearchPage />);
    expect(wrapper).toBeTruthy();

    const h1 = wrapper.container.querySelector("h1");
    expect(h1?.textContent).toBe("Haku");
    screen.debug();
  });
});
