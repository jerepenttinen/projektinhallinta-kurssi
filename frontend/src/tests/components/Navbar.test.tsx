import Navbar from "../../components/Navbar";
import { describe, test, expect } from "vitest";
import { render, screen } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";

describe("Navbar", () => {
  test("should show navbar", () => {
    const navbar = render(
      <BrowserRouter>
        <Navbar />
      </BrowserRouter>,
    );

    expect(navbar).toBeTruthy();
    expect(screen.getByText("Keittokirja")).toBeInTheDocument();
    expect(screen.getByText("Etusivu")).toBeInTheDocument();
    expect(screen.getByText("Haku")).toBeInTheDocument();

    screen.debug();
  });
});
