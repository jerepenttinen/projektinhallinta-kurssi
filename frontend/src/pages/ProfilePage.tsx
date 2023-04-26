import PageContainer from "../components/PageContainer";
import RecipeList from "../components/RecipeList";
import { Suspense } from "react";
import { GetUser } from "../api/Users";
import { GetUserRecipes } from "../api/Recipes";
import { useParams } from "react-router-dom";
import { useQuery } from "@tanstack/react-query";
import { Base64Image } from "../components/Base64Image";
import { Avatar } from "../components/Avatar";

const ProfileContactsSection = () => {
  const { uid } = useParams();

  const userQuery = useQuery({
    queryKey: ["user", uid],
    queryFn: () => GetUser(uid ?? ""),
    enabled: typeof uid === "string",
  });

  return (
    <Suspense>
      <div className="hstack gap-3">
        <Avatar size="l">
          {userQuery.data && userQuery.data.image ? (
            <Base64Image
              id={`user-${userQuery.data.id}`}
              image={userQuery.data.image}
            />
          ) : null}
        </Avatar>
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

  return (
    <PageContainer gap={3}>
      <ProfileContactsSection />
      <h4>Reseptit</h4>
      <>
        {userRecipesQuery.data ? (
          <RecipeList recipes={userRecipesQuery.data} />
        ) : null}
      </>
    </PageContainer>
  );
};

export default ProfilePage;
