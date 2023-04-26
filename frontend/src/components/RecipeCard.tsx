import Figure from "react-bootstrap/Figure";
import { Link } from "react-router-dom";
import { Base64Image } from "./Base64Image";
import "./RecipeCard.css";

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
          <Figure.Image src="https://4.bp.blogspot.com/-ecGN1GaoT-g/Uz20paShlSI/AAAAAAAAAcc/mc7Or69LsNw/s1600/veghamp2.jpg" />
        )}
      </Figure>
      <p className="text-break">{header}</p>
    </Link>
  );
};

export default RecipeCard;
