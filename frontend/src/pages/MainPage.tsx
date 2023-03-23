import "bootstrap/dist/css/bootstrap.min.css";
import Container from "react-bootstrap/Container";
import RecipeCard from "../components/RecipeCard";
import CarouselContainer from "../components/CarouselContainer";
import PageContainer from "../components/PageContainer";

const dummyData = [
  { id: 1, header: "Kinkkukiusaus" },
  { id: 2, header: "Lihamureke" },
  { id: 3, header: "Makaronilaatikko" },
  { id: 4, header: "Hernekeitto" },
  { id: 5, header: "Jauhelihakastike" },
  { id: 7, header: "Jauhelihakastike2" },
  { id: 8, header: "Jauhelihakastike3" },
  { id: 9, header: "Jauhelihakastike4" },
];

const MainPage = () => {
  return (
    <PageContainer gap={3}>
      <h3>Reseptejä</h3>
      <Container>
        <CarouselContainer showDots={true}>
          {dummyData.map((recipe) => {
            return <RecipeCard key={recipe.id} header={recipe.header} />;
          })}
        </CarouselContainer>
      </Container>
    </PageContainer>
  );
};
export default MainPage;
