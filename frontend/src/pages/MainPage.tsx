import "bootstrap/dist/css/bootstrap.min.css";
import Container from "react-bootstrap/Container";
import RecipeCard from "../components/RecipeCard";
import CarouselContainer from "../components/CarouselContainer";
import PageContainer from "../components/PageContainer";
import { useEffect, useState } from "react";
import { RecipeType } from "../Types";
import { GetRecipes } from "../api/Recipes";

const MainPage = () => {
  const [recipes, setRecipes] = useState<RecipeType[]>([]);

  const updateRecipes = async () => {
    setRecipes(await GetRecipes());
  };

  useEffect(() => {
    updateRecipes();
  }, []);
  return (
    <PageContainer gap={3}>
      <h3>Reseptejä</h3>
      <Container>
        {recipes[0] ? (
          <CarouselContainer showDots={true}>
            {recipes.map((recipe) => {
              return (
                <RecipeCard
                  key={recipe.id}
                  header={recipe.recipeName}
                  id={recipe.id}
                />
              );
            })}
          </CarouselContainer>
        ) : (
          <p>Reseptejä ei löytynyt!</p>
        )}
      </Container>
    </PageContainer>
  );
};
export default MainPage;
