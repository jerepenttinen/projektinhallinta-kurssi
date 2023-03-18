import Carousel from "react-multi-carousel";
import "react-multi-carousel/lib/styles.css";
import FrontPageRecipe from "./FrontPageRecipe"
const responsive = {
  superLargeDesktop: {
    // the naming can be any, depends on you.
    breakpoint: { max: 4000, min: 3000 },
    items: 5
  },
  desktop: {
    breakpoint: { max: 3000, min: 1024 },
    items: 3
  },
  tablet: {
    breakpoint: { max: 1024, min: 464 },
    items: 2
  },
  mobile: {
    breakpoint: { max: 464, min: 0 },
    items: 1
  }
};

export interface Props{
    header?: string,
    fontSize: number,
    data: { id: number; header: string; }[],
    showDots?: boolean,
}
const HorizontalRecipeList = ({ header, fontSize, data, showDots }: Props) => {
    const recipeList = data.map((recipe) => {
        return(
            <FrontPageRecipe key={recipe.id} header={recipe.header} fontSize={fontSize}/>
        );
    })
    return(
        <>
            <h3 className="my-3">{header}</h3>
            <Carousel className=""
              swipeable={true}
              draggable={true}
              showDots={showDots}
              responsive={responsive}
              infinite={true}
              autoPlaySpeed={0}
              keyBoardControl={true}
              transitionDuration={0}
              containerClass="carousel-container"
              dotListClass="custom-dot-list-style"
              itemClass="carousel-item-padding-40-px"
            >
                {recipeList}
            </Carousel>
        </>
    );
};


export default HorizontalRecipeList;