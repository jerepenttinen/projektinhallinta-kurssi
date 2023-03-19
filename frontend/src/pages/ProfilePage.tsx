import CarouselContainer from "../components/CarouselContainer";
import ReviewList from "../components/ReviewList";
import RecipeCard from "../components/RecipeCard";
import PageContainer from "../components/PageContainer";
import { Figure } from "react-bootstrap";

const ProfileContacts = () => {
  return (
    <div className="d-flex align-items-top">
      <div className="d-inline-block h-25">
        <Figure>
          <Figure.Image
            src="https://secure.gravatar.com/avatar/5586197d3539ebe07272af21926b496f?s=1920&d=mm&r=g"
            className="rounded-circle me-4"
            style={{ height: 128 }}
          />
        </Figure>
      </div>
      <div className="d-inline-block">
        <h3>Etunimi Sukunimi</h3>
        <p>Kuvaus</p>
      </div>
    </div>
  );
};

const ProfileRecipeList = () => {
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

const ProfilePage = () => {
  const recipes = [
    { id: 1, header: "Kinkkukiusaus" },
    { id: 2, header: "Lihamureke" },
    { id: 3, header: "Makaronilaatikko" },
    { id: 4, header: "Hernekeitto" },
    { id: 5, header: "Jauhelihakastike" },
    { id: 7, header: "Jauhelihakastike2" },
    { id: 8, header: "Jauhelihakastike3" },
    { id: 9, header: "Jauhelihakastike4" },
  ];

  const reviews = [
    {
      id: 1,
      name: "Hernekeitto",
      comment: "Suorastaan herkullista!",
      date: new Date(2017, 4, 4),
    },
    {
      id: 2,
      name: "Makaronilaatikko",
      comment: "Suorastaan oksettavaa!",
      date: new Date(2022, 2, 7),
    },
  ];

  return (
    <PageContainer gap={3}>
      <ProfileContacts />
      <h4>Reseptit</h4>
      <ProfileRecipeList />
      <h4>Kokoelmat</h4>
      <h5>Aamupalat</h5>
      <CarouselContainer showDots={true}>
        {recipes.map((recipe) => (
          <RecipeCard key={recipe.id} header={recipe.header} />
        ))}
      </CarouselContainer>
      <ReviewList reviews={reviews} />
    </PageContainer>
  );
};

export default ProfilePage;
