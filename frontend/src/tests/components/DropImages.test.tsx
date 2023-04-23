import DropImages from "../../components/DropImages";
import { describe, test, expect } from "vitest";
import { render, screen } from "@testing-library/react";

describe("DropImages", () => {
  test("should show DropImages component", () => {
    const wrapper = render(<DropImages onImageDropped={() => {}} />);
    expect(wrapper).toBeTruthy();

    const span = wrapper.container.querySelector("span");
    expect(span?.textContent).toBe("Raahaa kuva tai lisää painamalla tästä");

    screen.debug();
  });
});
