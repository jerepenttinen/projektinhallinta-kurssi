import "bootstrap/dist/css/bootstrap.min.css";
import Figure from "react-bootstrap/Figure";
import { Link } from "react-router-dom";
import { Base64Image } from "./Base64Image";

export interface Props {
  header: string;
  id: number;
  image: string | null;
}

const RecipeCard = ({ header, id, image }: Props) => {
  return (
    <Link to={`/recipes/${id}`} className="text-black">
      <Figure className="mb-0">
        {image ? (
          <Base64Image id={id.toString()} image={image} />
        ) : (
          <Figure.Image
            src="https://4.bp.blogspot.com/-ecGN1GaoT-g/Uz20paShlSI/AAAAAAAAAcc/mc7Or69LsNw/s1600/veghamp2.jpg"
            rounded
          />
        )}
        <p className="text-break">{header}</p>
      </Figure>
    </Link>
  );
};

export default RecipeCard;
