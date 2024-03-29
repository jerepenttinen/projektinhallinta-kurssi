import { faker } from "@faker-js/faker";

describe('Authentication spec', () => {
  it('Signup', () => {
    cy.visit('http://localhost:5173/signup');
    cy.get('#username').type(faker.internet.userName());
    cy.get('#firstname').type(faker.name.firstName());
    cy.get('#lastname').type(faker.name.lastName());
    cy.get('#email').type(faker.internet.email());
    cy.get('#password').type(faker.internet.password(10));
    cy.get('#signup').click();
    cy.url().should("include", "/signin");
  });

  it('Can login', () => {
    const user = {
      userName: faker.internet.userName(),
      firstName: faker.name.firstName(),
      lastName: faker.name.lastName(),
      email: faker.internet.email(),
      password: faker.internet.password(10)
    }

    cy.visit('http://localhost:5173/signup');
    cy.get('#username').type(user.userName);
    cy.get('#firstname').type(user.firstName);
    cy.get('#lastname').type(user.lastName);
    cy.get('#email').type(user.email);
    cy.get('#password').type(user.password);
    cy.get('#signup').click();
    cy.url().should("include", "/signin");

    cy.get('#email').type(user.email);
    cy.get('#password').type(user.password);
    cy.get('#signin').click();
    cy.url().should("eq", "http://localhost:5173/");
  });

  it('Password too short', () => {
    cy.visit('http://localhost:5173/signup');
    cy.get('#username').type(faker.internet.userName());
    cy.get('#firstname').type(faker.name.firstName());
    cy.get('#lastname').type(faker.name.lastName());
    cy.get('#email').type(faker.internet.email());
    cy.get('#password').type(faker.internet.password(5));
    cy.get('#signup').click();
    cy.contains("Salasanan tulee olla vähintään 8 merkkiä pitkä");
  });
  it('invalid credentials', () => {
    cy.visit('http://localhost:5173/signin');
    cy.get('#email').type(faker.internet.email());
    cy.get('#password').type(faker.internet.password(10));
    cy.get('#signin').click();
    cy.contains("Virheelliset kirjautumistiedot");
  });
})