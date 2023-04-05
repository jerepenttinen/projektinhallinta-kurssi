import SignupPage from "../../pages/SignupPage"
import { describe, test, expect } from "vitest"
import { render, screen } from "@testing-library/react";

describe('Signuppage', () => {
    test("should show signuppage", () => {
        const wrapper = render(<SignupPage/>);
        expect(wrapper).toBeTruthy();
;
        screen.debug();
    });
});