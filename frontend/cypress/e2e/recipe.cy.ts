import { faker } from "@faker-js/faker";

describe("Recipe spec", () => {
  const user = {
    userName: faker.internet.userName(),
    firstName: faker.name.firstName(),
    lastName: faker.name.lastName(),
    email: faker.internet.email(),
    password: faker.internet.password(10),
  };
  let recipe = "";
  it("Create a recipe", () => {
    
    cy.signup(
      user.userName,
      user.firstName,
      user.lastName,
      user.email,
      user.password,
    );
    cy.login(user.email, user.password);
    const recipeNames = [
      "Kinkkukiusaus",
      "Sämpylä",
      "Makaronilaatikko",
      "Kaurapuuro",
      "Kiisseli",
    ];
    const recipeIngredients = [
      "Vehnäjauho",
      "Vesi",
      "Kananmuna",
      "Maito",
      "Makaroni",
      "Kinkku",
      "Peruna",
    ];
    const recipeInstructions = [
      "Keitä perunat",
      "Paista kinkku",
      "Lisää maito",
      "Lisää vesi",
      "Lisää kananmunat",
      "Pese perunat",
      "Lisää jauhot",
      "Keitä makaronit",
    ];
    const recipeCategories = [
      "Pääruuat",
      "Alkuruuat",
      "Jälkiruoat",
      "Juomat",
      "Keitot",
      "Pizzat",
      "Välipalat",
      "Salaatit",
    ];

    cy.get("#new-recipe-link").click();
    recipe = faker.helpers.arrayElement(recipeNames)
    cy.get("#recipe-name").type(recipe);
    cy.get("#recipe-ingredient").type(
      faker.helpers.arrayElement(recipeIngredients),
    );
    cy.get("#recipe-quantity").type(faker.random.numeric() + " g");
    cy.get("#recipe-submit-ingredient").click();

    cy.get("#recipe-ingredient")
      .clear()
      .type(faker.helpers.arrayElement(recipeIngredients));
    cy.get("#recipe-quantity")
      .clear()
      .type(faker.random.numeric() + " g");
    cy.get("#recipe-submit-ingredient").click();

    cy.get("#recipe-ingredient")
      .clear()
      .type(faker.helpers.arrayElement(recipeIngredients));
    cy.get("#recipe-quantity")
      .clear()
      .type(faker.random.numeric() + " g");
    cy.get("#recipe-submit-ingredient").click();

    cy.get("#recipe-instructions").type(
      faker.helpers.arrayElement(recipeInstructions),
    );
    cy.get("#recipe-submit-instruction").click();

    cy.get("#recipe-instructions")
      .clear()
      .type(faker.helpers.arrayElement(recipeInstructions));
    cy.get("#recipe-submit-instruction").click();

    cy.get("#recipe-instructions")
      .clear()
      .type(faker.helpers.arrayElement(recipeInstructions));
    cy.get("#recipe-submit-instruction").click();

    cy.get("#recipe-instructions")
      .clear()
      .type(faker.helpers.arrayElement(recipeInstructions));
    cy.get("#recipe-submit-instruction").click();

    cy.get("#recipe-category").select(
      faker.helpers.arrayElement(recipeCategories),
    );

    cy.get("#recipe-submit").click();

    cy.url().should("include", `http://localhost:5173/recipes/`);

  });
  it("Add a review", () => {
    const text = faker.lorem.lines(1);
    const options = ["like", "dislike"];
    cy.visit("http://localhost:5173/signin")
    cy.login(user.email, user.password);
    cy.get("#profile-link").click();
    cy.get("#recipe-link").click();
    cy.get(`#${faker.helpers.arrayElement(options)}`).click({force: true});
    cy.get("#review-textarea").type(text);
    cy.get("#submit-review").click();
    cy.get("#review-list").contains(text);
  });
  it("Search a review", () => {

    cy.visit("http://localhost:5173/signin")
    cy.login(user.email, user.password);
    cy.get("#search-link").click();
    cy.get("#search-input").type(recipe);
    cy.get("#search-submit").click();
    cy.get("#recipe-link").click();
    cy.url().should("include", `http://localhost:5173/recipes/`);    
  });
});
