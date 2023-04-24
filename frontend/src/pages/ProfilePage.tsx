import CarouselContainer from "../components/CarouselContainer";
import RecipeCard from "../components/RecipeCard";
import PageContainer from "../components/PageContainer";
import RecipeList from "../components/RecipeList";
import { Figure } from "react-bootstrap";
import { Suspense } from "react";
import { GetUser } from "../api/Users";
import { GetUserRecipes } from "../api/Recipes";
import { useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";

const ProfileContactsSection = () => {
  const { uid } = useParams();

  const userQuery = useQuery({
    queryKey: ["user", uid],
    queryFn: () => GetUser(uid ?? ""),
    enabled: typeof uid === "string",
  });

  return (
    <Suspense>
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
          <h3>{userQuery.data?.username}</h3>
          <p>{userQuery.data?.description}</p>
        </div>
      </div>
    </Suspense>
  );
};

const ProfilePage = () => {
  const { uid } = useParams();

  const userRecipesQuery = useQuery({
    queryKey: ["user", uid, "recipes"],
    queryFn: () => GetUserRecipes(uid ?? ""),
    enabled: typeof uid === "string",
  });

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
      <ProfileContactsSection />
      <h4>Reseptit</h4>
      <Suspense>
        {userRecipesQuery.data ? (
          <RecipeList recipes={userRecipesQuery.data} />
        ) : null}
      </Suspense>
      <h4>Kokoelmat</h4>
      <h5>Aamupalat</h5>
      <CarouselContainer showDots={true}>
        {recipes.map((recipe) => (
          <RecipeCard
            key={recipe.id}
            header={recipe.header}
            id={recipe.id}
            image={null}
          />
        ))}
      </CarouselContainer>
    </PageContainer>
  );
};

export default ProfilePage;
