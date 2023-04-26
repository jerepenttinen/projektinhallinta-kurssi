/// <reference types="cypress" />
// ***********************************************
// This example commands.ts shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//

// -- This is a parent command --
Cypress.Commands.add("login", (email, password) => {
  cy.get("#email").type(email);
  cy.get("#password").type(password);
  cy.get("#signin").click();
  cy.url().should("eq", "http://localhost:5173/");
});
Cypress.Commands.add(
  "signup",
  (userName, firstName, lastName, email, password) => {
    cy.visit("http://localhost:5173/signup");
    cy.get("#username").type(userName);
    cy.get("#firstname").type(firstName);
    cy.get("#lastname").type(lastName);
    cy.get("#email").type(email);
    cy.get("#password").type(password);
    cy.get("#signup").click();
    cy.url().should("include", "/signin");
  },
);
//
//
// -- This is a child command --
// Cypress.Commands.add('drag', { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add('dismiss', { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite('visit', (originalFn, url, options) => { ... })
//
declare namespace Cypress {
  interface Chainable {
    login(email: string, password: string): Chainable<void>;
    signup(
      userName: string,
      firstName: string,
      lastName: string,
      email: string,
      password: string,
    ): Chainable<void>;
    //drag(subject: string, options?: Partial<TypeOptions>): Chainable<Element>
    //dismiss(subject: string, options?: Partial<TypeOptions>): Chainable<Element>
    //visit(originalFn: CommandOriginalFn, url: string, options: Partial<VisitOptions>): Chainable<Element>
  }
}
