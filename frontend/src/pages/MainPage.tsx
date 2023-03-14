import "bootstrap/dist/css/bootstrap.min.css";
import Container from "react-bootstrap/Container";
import { Stack } from "react-bootstrap";
import FrontPageRecipe from "../components/FrontPageRecipe";


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

const MainPage = () => {

  const recipeList = dummyData.map((recipe) => {
      return(
          <FrontPageRecipe key={recipe.id} header={recipe.header} xs={3}/>
      );
  });

  return (
    <Container>
      <h1 className="mt-5 mb-4">Reseptejä</h1>
      <Container>
        <Stack className="flex-nowrap overflow-scroll gap-2" direction="horizontal">
            {recipeList}
        </Stack>
      </Container>
    </Container>
  );
};
export default MainPage;

