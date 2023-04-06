import RecipeCard from "./RecipeCard";

const RecipeList = () => {
    const recipes = [
        { id: 1, header: "Kinkkukiusaus" },
        { id: 2, header: "Lihamureke" },
        { id: 3, header: "Makaroonilaatikko" },
        { id: 4, header: "Hernekeitto" },
        { id: 5, header: "Jauhelihakastike" },
        { id: 7, header: "Jauhelihakastike2" },
        { id: 8, header: "Jauhelihakastike3" },
        { id: 9, header: "Jauhelihakastike4" },
    ];

    return (
        <div className="row row-cols-3">
            {recipes.map((recipe) => (
                <RecipeCard key={recipe.id} header={recipe.header} />
            ))}
        </div>
    );
};

export default RecipeList;