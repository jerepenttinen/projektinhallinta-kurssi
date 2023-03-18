import Col from 'react-bootstrap/Col';
import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import Figure from "react-bootstrap/Figure";
import { Link } from "react-router-dom";
import "./css/FrontPageRecipe.css"

export interface Props{
    header?: string,
    fontSize: number,
    marginX: string
}
const FrontPageRecipe = ({ header, fontSize, marginX }: Props) => {
    return(
        <div className="mx-3">

            <Link to='/'>
                <Figure className="selector">
                    <Figure.Image className="selector" src='https://4.bp.blogspot.com/-ecGN1GaoT-g/Uz20paShlSI/AAAAAAAAAcc/mc7Or69LsNw/s1600/veghamp2.jpg'
                        rounded
                    />
                    <p className=" fw-bold text-center text-break text-dark" style={{fontSize:fontSize}} >
                        {header}
                    </p>
                </Figure>
            </Link>



        </div>
    );
}

export default FrontPageRecipe;