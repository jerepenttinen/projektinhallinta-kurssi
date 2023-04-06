import { test, expect } from "vitest";
import { render, screen } from "@testing-library/react";
import App from "./App";
import { BrowserRouter } from "react-router-dom";

describe('App', () => {

    test("Should render app", () => {
      render(<BrowserRouter> <App /></BrowserRouter>);
      screen.debug();
    });
})