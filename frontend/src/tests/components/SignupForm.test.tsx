import SignupForm from "../../components/SignupForm"
import { describe, test, expect } from "vitest"
import { render, screen } from "@testing-library/react";

describe('SignupForm', () => {
    test("should show signup form", () => {
        const wrapper = render(<SignupForm/>);
        expect(wrapper).toBeTruthy();

        const h2 = wrapper.container.querySelector("h2");
        expect(h2?.textContent).toBe("Rekisteröidy");
        expect(screen.queryByPlaceholderText("Käyttäjänimi")).toBeInTheDocument();
        expect(screen.queryByPlaceholderText("Etunimi")).toBeInTheDocument();
        expect(screen.queryByPlaceholderText("Sukunimi")).toBeInTheDocument();
        expect(screen.queryByPlaceholderText("Sähköposti")).toBeInTheDocument();
        expect(screen.queryByPlaceholderText("Salasana")).toBeInTheDocument();

        screen.debug();
    });
});