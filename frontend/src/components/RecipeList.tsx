import RecipeCard from "./RecipeCard";
import { RecipeType } from "../Types";
import { useState } from "react";

interface Props {
  recipes: RecipeType[] | null;
}
const RecipeList = ({ recipes }: Props) => {
  return (
    <div className="row row-cols-3">
      {recipes ? (
        recipes.map((recipe) => (
          <RecipeCard
            key={recipe.id}
            header={recipe.recipeName}
            id={recipe.id}
          />
        ))
      ) : (
        <p>Ei reseptejä!</p>
      )}
    </div>
  );
};

export default RecipeList;
