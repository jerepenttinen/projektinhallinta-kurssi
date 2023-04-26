import ProfileSettingsPage from "../../pages/ProfileSettingsPage";
import { describe, test, expect } from "vitest";
import { render, screen } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";

describe("Profilesettings page", () => {
  test("should show Profilesettings page", () => {
    const wrapper = render(
      <BrowserRouter>
        <ProfileSettingsPage />
      </BrowserRouter>,
    );
    expect(wrapper).toBeTruthy();

    const h2 = wrapper.container.querySelector("h2");
    expect(h2?.textContent).toBe("Asetukset");
    const topContainer = screen.getByTestId("top-container");
    expect(topContainer).toBeTruthy();
    screen.debug();
  });
});
