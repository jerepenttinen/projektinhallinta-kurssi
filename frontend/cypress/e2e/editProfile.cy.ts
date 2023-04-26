import { faker } from "@faker-js/faker";

describe("Profile spec", () => {
  it("Change profile description", () => {
    const user = {
      userName: faker.internet.userName(),
      firstName: faker.name.firstName(),
      lastName: faker.name.lastName(),
      email: faker.internet.email(),
      password: faker.internet.password(10),
    };
    cy.signup(
      user.userName,
      user.firstName,
      user.lastName,
      user.email,
      user.password,
    );
    const text = faker.lorem.lines();
    cy.login(user.email, user.password);
    cy.get("#edit-profile-link").click();
    cy.get("#textArea").type(text);
    cy.get("#profile-submit").click();
    cy.wait(500);
    cy.get("#profile-link").click();
    cy.url().should("include", `http://localhost:5173/profile/`);
    //cy.get("#user-description").contains(text);
    

  })
});