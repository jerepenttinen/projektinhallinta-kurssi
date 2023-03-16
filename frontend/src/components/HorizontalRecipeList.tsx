import FrontPageRecipe from "./FrontPageRecipe"
import { Stack } from "react-bootstrap";


export interface Props{
    header?: string,
    imageSize: number,
    fontSize: number,
    data: { id: number; header: string; }[]
}

const HorizontalRecipeList = ({ header, imageSize, fontSize, data }: Props) => {

    const recipeList = data.map((recipe) => {
          return(
              <FrontPageRecipe key={recipe.id} header={recipe.header} xs={imageSize} fontSize={fontSize}/>
          );
    });

    return (
        <>
            <h3>{header}</h3>
            <Stack className="flex-nowrap overflow-scroll gap-2" direction="horizontal">
              {recipeList}
            </Stack>
        </>
    );
}

export default HorizontalRecipeList;