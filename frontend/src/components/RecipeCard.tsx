import "bootstrap/dist/css/bootstrap.min.css";
import Figure from "react-bootstrap/Figure";
import { Link } from "react-router-dom";

export interface Props {
  header: string;
  id: number;
}

const RecipeCard = ({ header, id }: Props) => {
  const url = "/recipes/" + id;
  return (
    <Link to={url} className="text-black">
      <Figure className="mb-0">
        <Figure.Image
          src="https://4.bp.blogspot.com/-ecGN1GaoT-g/Uz20paShlSI/AAAAAAAAAcc/mc7Or69LsNw/s1600/veghamp2.jpg"
          rounded
        />
        <p className="text-break">{header}</p>
      </Figure>
    </Link>
  );
};

export default RecipeCard;
