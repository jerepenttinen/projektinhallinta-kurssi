import Figure from "react-bootstrap/Figure";
import { Link } from "react-router-dom";
import { Base64Image } from "./Base64Image";
import "./RecipeCard.css";
import { MissingImage } from "./MissingImage";

export interface Props {
  header: string;
  id: number;
  image: string | null;
}

const RecipeCard = ({ header, id, image }: Props) => {
  return (
    <Link to={`/recipes/${id}`} className="text-decoration-none text-reset">
      <Figure className="mb-0 recipe-card">
        {image ? (
          <Base64Image id={id.toString()} image={image} />
        ) : (
          <MissingImage />
        )}
      </Figure>
      <p className="text-break">{header}</p>
    </Link>
  );
};

export default RecipeCard;
