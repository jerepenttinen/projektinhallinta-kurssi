import { BsHandThumbsDown, BsHandThumbsUp, BsPrinter } from "react-icons/bs";
import {
  Badge,
  Button,
  ButtonGroup,
  Carousel,
  CarouselItem,
  Form,
  Stack,
} from "react-bootstrap";
import ReviewList from "../components/ReviewList";
import PageContainer from "../components/PageContainer";

const IngredientRow = () => {
  return (
    <Stack gap={2}>
      <Stack direction="horizontal" gap={3}>
        <div style={{ minWidth: 150 }}>
          <span style={{ float: "right" }}>Määrä</span>
        </div>
        <span>Nimi</span>
      </Stack>
    </Stack>
  );
};

const dummyReviewData: {
  id: number;
  name: string;
  comment: string;
  date: Date;
}[] = [
  {
    id: 1,
    name: "Erkki Esimerkki",
    comment: "Suorastaan herkullista!",
    date: new Date(2017, 4, 4),
  },
  {
    id: 2,
    name: "Matti Möttönen",
    comment: "Suorastaan oksettavaa!",
    date: new Date(2022, 2, 7),
  },
];

const RecipePage = () => {
  return (
    <PageContainer gap={3}>
      <h2 className="mb-0">Reseptin nimi</h2>
      <Stack direction="horizontal" gap={2}>
        <div
          className="bg-primary rounded-circle text-white d-flex align-items-center justify-content-center"
          style={{ width: 32, height: 32, fontSize: 10 }}
        >
          <span>Kuva</span>
        </div>
        <span>Etunimi Sukunimi</span>
      </Stack>
      <Stack direction="horizontal" className="justify-content-between">
        <Stack direction="horizontal" gap={2}>
          <Badge bg="secondary">Alkuruuat</Badge>
          <Badge bg="secondary">Alkuruuat</Badge>
        </Stack>
        <Button variant="secondary" className="rounded-0">
          <Stack direction="horizontal" gap={1}>
            <BsPrinter /> Tulosta
          </Stack>
        </Button>
      </Stack>

      <Carousel>
        <CarouselItem>
          <div
            className="d-flex bg-secondary bg-opacity-50 w-100 justify-content-center align-items-center"
            style={{ height: 400 }}
          >
            <h2>Kuvakaruselli</h2>
          </div>
        </CarouselItem>
        <CarouselItem>
          <div
            className="d-flex bg-secondary bg-opacity-50 w-100 justify-content-center align-items-center"
            style={{ height: 400 }}
          >
            <h2>Toka kuva</h2>
          </div>
        </CarouselItem>
      </Carousel>

      <Stack direction="horizontal" className="justify-content-between">
        <span>4 annosta | Valmistusaika 1 min</span>
        <Stack direction="horizontal" gap={2}>
          <BsHandThumbsUp />
          <span>1</span>
          <BsHandThumbsDown />
          <span>1</span>
          <span>50%</span>
        </Stack>
      </Stack>

      <h3>Raaka-aineet</h3>
      <IngredientRow />
      <IngredientRow />
      <IngredientRow />
      <IngredientRow />
      <IngredientRow />
      <IngredientRow />

      <h3>Ohjeet</h3>
      <ol>
        <li>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
          eiusmod tempor incididunt ut labore et aliqua.
        </li>
        <li>
          Proin fermentum leo vel orci porta non. Id leo in vitae turpis.
          Fermentum et sollicitudin ac orci phasellus.
        </li>
        <li>Tellus at urna condimentum mattis pellentesque id nibh tortor.</li>
        <li>
          Nisl tincidunt eget nullam non. Senectus et netus et malesuada fames.
        </li>
        <li>Tortor vitae purus faucibus ornare suspendisse.</li>
        <li>Ut ornare lectus sit amet est placerat in.</li>
        <li>Sed vulputate odio ut enim blandit volutpat maecenas.</li>
      </ol>

      <h3>Lisää arvostelu</h3>
      <div>
        <ButtonGroup>
          {/* TODO: use some component? */}
          <input
            type="radio"
            className="btn-check"
            name="like"
            id="like"
            defaultChecked
          />
          <label className="btn btn-outline-primary" htmlFor="like">
            <BsHandThumbsUp /> Tykkäsin
          </label>

          <input
            type="radio"
            className="btn-check"
            name="dislike"
            id="dislike"
          />
          <label className="btn btn-outline-primary" htmlFor="dislike">
            <BsHandThumbsDown /> En tykkänyt
          </label>
        </ButtonGroup>
      </div>

      <Form.Control as="textarea" rows={6} />
      <div>
        <Button variant="success">Julkaise</Button>
      </div>

      <h3>Arvostelut</h3>
      <ReviewList reviews={dummyReviewData} />
    </PageContainer>
  );
};

export default RecipePage;
