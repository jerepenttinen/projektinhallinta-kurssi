import CarouselContainer from "../components/CarouselContainer";
import ReviewList from "../components/ReviewList";
import RecipeCard from "../components/RecipeCard";
import PageContainer from "../components/PageContainer";
import RecipeList from "../components/RecipeList";
import { Figure } from "react-bootstrap";
import { useEffect, useState } from "react";
import { RecipeType, UserType } from "../Types";
import { GetUser } from "../api/Users";
import { GetUserRecipes } from "../api/Recipes";

interface ContactProps {
  name: string | undefined;
  description: string | undefined;
}
const ProfileContacts = ({ name, description }: ContactProps) => {
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
        <h3>{name}</h3>
        <p>{description}</p>
      </div>
    </div>
  );
};

const ProfilePage = () => {
  const [userRecipes, setUserRecipes] = useState<RecipeType[] | null>(null);
  const [user, setUser] = useState<UserType | null>(null);
  const getUserId = () => {
    const siteUrl = window.location.href;
    const urlArray = siteUrl.split("/");
    return urlArray[urlArray.length - 1];
  };

  const update = async () => {
    setUser(await GetUser(getUserId()));
    setUserRecipes(await GetUserRecipes(getUserId()));
  };

  useEffect(() => {
    update();
  }, []);
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

  return (
    <PageContainer gap={3}>
      <ProfileContacts name={user?.username} description={user?.description} />
      <h4>Reseptit</h4>
      <RecipeList recipes={userRecipes} />
      <h4>Kokoelmat</h4>
      <h5>Aamupalat</h5>
      <CarouselContainer showDots={true}>
        {recipes.map((recipe) => (
          <RecipeCard key={recipe.id} header={recipe.header} id={recipe.id} />
        ))}
      </CarouselContainer>
    </PageContainer>
  );
};

export default ProfilePage;
