import PageContainer from "../components/PageContainer";
import { useRef } from "react";
import RecipeList from "../components/RecipeList";
import Dropdown from "react-bootstrap/Dropdown";
import "bootstrap/dist/css/bootstrap.min.css";
import { useEffect, useState } from "react";
import { RecipeType } from "../Types";
import { GetRecipes } from "../api/Recipes";
const SearchPage = () => {
  const selectRef = useRef<HTMLSelectElement | null>(null);
  const [recipes, setRecipes] = useState<RecipeType[]>([]);

  const updateRecipes = async () => {
    setRecipes(await GetRecipes());
  };

  useEffect(() => {
    updateRecipes();
  }, []);
  return (
    <PageContainer gap={3}>
      <h1>Haku</h1>
      <div className="input-group">
        <input
          type="search"
          className="form-control rounded"
          placeholder="Reseptin nimi"
          aria-label="Search"
          aria-describedby="search-addon"
        />
        <button type="button" className="btn btn-primary">
          Hae
        </button>
      </div>
      <Dropdown>
        <Dropdown.Toggle
          split
          variant="light border border-dark wid text-start"
          id="dropdown-split-basic"
        >
          Kategoria
        </Dropdown.Toggle>

        <Dropdown.Menu>
          <Dropdown.Item href="#/action-1">Pääruoat</Dropdown.Item>
          <Dropdown.Item href="#/action-2">Jälkiruoat</Dropdown.Item>
          <Dropdown.Item href="#/action-3">Välipalat</Dropdown.Item>
        </Dropdown.Menu>
      </Dropdown>
      <RecipeList recipes={recipes} />
    </PageContainer>
  );
};
export default SearchPage;
