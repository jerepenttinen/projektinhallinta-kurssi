import Carousel from "react-multi-carousel";
import "react-multi-carousel/lib/styles.css";

const responsive = {
  superLargeDesktop: {
    // the naming can be any, depends on you.
    breakpoint: { max: 4000, min: 3000 },
    items: 5,
  },
  desktop: {
    breakpoint: { max: 3000, min: 1024 },
    items: 3,
  },
  tablet: {
    breakpoint: { max: 1024, min: 464 },
    items: 2,
  },
  mobile: {
    breakpoint: { max: 464, min: 0 },
    items: 1,
  },
};

interface Props {
  showDots?: boolean;
  children: React.ReactElement[];
}

const CarouselContainer = ({ children, showDots }: Props) => {
  return (
    <Carousel
      swipeable={false}
      draggable={false}
      showDots={showDots}
      responsive={responsive}
      infinite={true}
      slidesToSlide={1}
      autoPlay={true}
      autoPlaySpeed={2500}
      customTransition={"transform 400ms ease-in-out"}
      keyBoardControl={true}
      transitionDuration={0}
      containerClass="carousel-container"
      dotListClass="custom-dot-list-style"
      itemClass="carousel-item-padding-40-px"
      sliderClass="hstack gap-3"
    >
      {children}
    </Carousel>
  );
};

export default CarouselContainer;
