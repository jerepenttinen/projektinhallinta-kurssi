import MainPage from "../../pages/MainPage"
import { describe, test, expect } from "vitest"
import { render, screen } from "@testing-library/react";

describe('Mainpage', () => {
    test("should show mainpage", () => {
        const wrapper = render(<MainPage/>);
        expect(wrapper).toBeTruthy();

        const h3 = wrapper.container.querySelector("h3");
        expect(h3?.textContent).toBe("Reseptejä");

        screen.debug();
    });
});