import RecipeCard from "../components/RecipeCard";
import CarouselContainer from "../components/CarouselContainer";
import PageContainer from "../components/PageContainer";
import { getRecipes } from "../api/Recipes";
import { useQuery } from "@tanstack/react-query";
import { Suspense } from "react";

const MainPage = () => {
  const recipesQuery = useQuery(["recipes"], getRecipes, {
    suspense: true,
  });

  return (
    <PageContainer gap={3}>
      <h3>Reseptejä</h3>
      <Suspense fallback={<p>Ladataan</p>}>
        {recipesQuery.data && recipesQuery.data.length !== 0 ? (
          <CarouselContainer showDots={true}>
            {recipesQuery.data.map((recipe) => {
              return (
                <RecipeCard
                  key={recipe.id}
                  header={recipe.recipeName}
                  id={recipe.id}
                  image={recipe.image}
                />
              );
            })}
          </CarouselContainer>
        ) : (
          <p>Reseptejä ei löytynyt!</p>
        )}
      </Suspense>
    </PageContainer>
  );
};
export default MainPage;
