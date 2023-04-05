import ProfilePage from "../../pages/ProfilePage";
import { describe, test, expect } from "vitest";
import { render, screen } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";

describe("Profilepage", () => {
  test("should show Profilepage", () => {
    const wrapper = render(
      <BrowserRouter>
        <ProfilePage />
      </BrowserRouter>,
    );
    expect(wrapper).toBeTruthy();

    const h4 = wrapper.container.querySelector("h4");
    expect(h4?.textContent).toBe("Reseptit");

    screen.debug();
  });
});
