import PageContainer from "../components/PageContainer";
import RecipeList from "../components/RecipeList";
import { SearchRecipes } from "../api/Recipes";
import { useQuery } from "@tanstack/react-query";
import { useRef } from "react";
import { useSearchParams } from "react-router-dom";

const searchKey = "q";

function createSearchParams(term: string) {
  return term.length > 0
    ? new URLSearchParams([[searchKey, term]])
    : new URLSearchParams();
}

const SearchPage = () => {
  const [searchParams, setSearchParams] = useSearchParams();
  const searchBox = useRef<HTMLInputElement | null>(null);
  const search = searchParams.get(searchKey) ?? "";
  const recipesQuery = useQuery(["search", search], () =>
    SearchRecipes(search),
  );

  return (
    <PageContainer gap={3}>
      <h1>Haku</h1>
      <form
        className="input-group"
        onSubmit={(e) => {
          e.preventDefault();
          const term = searchBox.current!.value;
          setSearchParams(createSearchParams(term));
        }}
      >
        <input
          type="search"
          className="form-control"
          placeholder="Reseptin nimi"
          aria-label="Search"
          aria-describedby="search-addon"
          ref={searchBox}
          defaultValue={search}
        />
        <button type="submit" className="btn btn-primary">
          Hae
        </button>
      </form>
      <>
        {recipesQuery.data ? <RecipeList recipes={recipesQuery.data} /> : null}
      </>
    </PageContainer>
  );
};
export default SearchPage;
