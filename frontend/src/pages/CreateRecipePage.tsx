import "bootstrap/dist/css/bootstrap.min.css";
import { useState } from "react";
import { Button, Form, FormGroup, Stack } from "react-bootstrap";
import { SortableList } from "../components/SortableList";
import { BsGripHorizontal, BsPlus, BsTrash } from "react-icons/bs";

function SortingRow(props: {
  render: () => React.ReactElement;
  onTrash: () => void;
}) {
  return (
    <Stack direction="horizontal" className="justify-content-between">
      <Stack direction="horizontal" gap={3} className="flex-grow-1 text-break">
        <BsGripHorizontal />
        {props.render()}
      </Stack>
      <Button variant="link" className="text-secondary" onClick={props.onTrash}>
        <BsTrash />
      </Button>
    </Stack>
  );
}

const CreateRecipePage = () => {
  const [ingredients, setIngredients] = useState([
    { id: "1", ingredient: "Peruna", quantity: "1 kg" },
    { id: "2", ingredient: "Liha", quantity: "500 g" },
    { id: "3", ingredient: "Maito", quantity: "200 g" },
  ]);

  const [instructions, setInstructions] = useState([
    { id: "1", instruction: "Keitä peruna" },
    { id: "2", instruction: "Paista liha" },
    { id: "3", instruction: "Lisää maito" },
  ]);

  return (
    <Form
      className="py-4 px-3 container vstack gap-3"
      style={{ maxWidth: 768 }}
    >
      <h2 className="mb-0">Lisää uusi resepti</h2>
      <FormGroup>
        <Form.Label>Reseptin nimi</Form.Label>
        <Form.Control type="text" placeholder="Reseptin nimi" />
      </FormGroup>
      <FormGroup>
        <Form.Label>Raaka-aineet</Form.Label>
        <Stack gap={3}>
          <SortableList
            items={ingredients}
            setItems={setIngredients}
            renderItem={(item) => (
              <SortingRow
                render={() => (
                  <span className="user-select-none">
                    {item.quantity} {item.ingredient}
                  </span>
                )}
                onTrash={() => {
                  setIngredients((ingredients) =>
                    ingredients.filter((ig) => ig.id !== item.id),
                  );
                }}
              />
            )}
          />
        </Stack>
        <Stack direction="horizontal" className="my-3" gap={2}>
          <Form.Control type="text" placeholder="Määrä" />
          <Form.Control type="text" placeholder="Raaka-aine" />
          <Button variant="success" className="hstack">
            <BsPlus size={24} /> Lisää
          </Button>
        </Stack>
      </FormGroup>
      <FormGroup>
        <Form.Label>Ohjeet</Form.Label>
        <ol className="vstack gap-3 ps-0 mb-0">
          <SortableList
            items={instructions}
            setItems={setInstructions}
            renderItem={(item) => (
              <SortingRow
                render={() => <li className="ms-3">{item.instruction}</li>}
                onTrash={() => {
                  setInstructions((instructions) =>
                    instructions.filter((ig) => ig.id !== item.id),
                  );
                }}
              />
            )}
          />
        </ol>
        <Stack direction="horizontal" className="my-3" gap={2}>
          <Form.Control type="text" placeholder="Uusi vaihe" />
          <Button variant="success" className="hstack gap-1">
            <BsPlus size={24} /> Lisää
          </Button>
        </Stack>
      </FormGroup>
    </Form>
  );
};

export default CreateRecipePage;
