import RecipeCard from "./RecipeCard";
import { z } from "zod";
import { recipeCard } from "../api/validators";

const RecipeList = ({ recipes }: { recipes: z.infer<typeof recipeCard>[] }) => {
  return (
    <div className="row row-cols-3" id="recipe-list">
      {recipes ? (
        recipes.map((recipe) => (
          <RecipeCard
            key={recipe.id}
            header={recipe.recipeName}
            id={recipe.id}
            image={recipe.image}
          />
        ))
      ) : (
        <p>Ei reseptejä!</p>
      )}
    </div>
  );
};

export default RecipeList;
