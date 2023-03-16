import FrontPageRecipe from "./FrontPageRecipe"
import {useState} from "react"


const dummyData: { id: number, header: string }[] = [
        { id:1, header: "Kinkkukiusaus" },
        { id:2, header: "Lihamureke" },
        { id:3, header: "Makaroonilaatikko" },
        { id:4, header: "Hernekeitto" },
        { id:5, header: "Jauhelihakastike" },
        { id:7, header: "Jauhelihakastike2" },
        { id:8, header: "Jauhelihakastike3" },
        { id:9, header: "Jauhelihakastike4" },
    ]
const ProfileRecipeList = () => {
    const [recipes, setRecipes] = useState<{id: number, header: string}[]>(dummyData);

    const recipeList = recipes.map((recipe) => {
        return(
            <div key={recipe.id} className="d-inline-block me-4 align-middle" style={{width:"250px"}}>
                <FrontPageRecipe header={recipe.header} fontSize={25}/>
            </div>
        );
    });

    return(
        <div className="text-center "style={{}}>
            {recipeList}
        </div>
    )
}

export default ProfileRecipeList;