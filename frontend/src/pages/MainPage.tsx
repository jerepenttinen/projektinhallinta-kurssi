import "bootstrap/dist/css/bootstrap.min.css";
import Container from "react-bootstrap/Container";
import FrontPageRecipe from "../components/FrontPageRecipe";
import HorizontalRecipeList from "../components/HorizontalRecipeList"

const dummyData: { id: number, header: string }[] = [
        { id:1, header: "Kinkkukiusaus" },
        { id:2, header: "Lihamureke" },
        { id:3, header: "Makaronilaatikko" },
        { id:4, header: "Hernekeitto" },
        { id:5, header: "Jauhelihakastike" },
        { id:7, header: "Jauhelihakastike2" },
        { id:8, header: "Jauhelihakastike3" },
        { id:9, header: "Jauhelihakastike4" },
    ]

const MainPage = () => {

  return (
    <Container>
      <h1 className="mt-5 mb-4">Reseptejä</h1>
      <Container>
        <HorizontalRecipeList imageSize={3} fontSize={25} data={dummyData}/>
      </Container>
    </Container>
  );
};
export default MainPage;
