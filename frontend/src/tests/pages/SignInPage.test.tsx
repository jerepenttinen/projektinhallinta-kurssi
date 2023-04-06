import SignInPage from "../../pages/SignInPage";
import { describe, test, expect } from "vitest";
import { render, screen } from "@testing-library/react";

describe("Signinpage", () => {
  test("should show signinpage", () => {
    const wrapper = render(<SignInPage />);
    expect(wrapper).toBeTruthy();

    const h2 = wrapper.container.querySelector("h2");
    expect(h2?.textContent).toBe("Kirjaudu");
    screen.debug();
  });
});
