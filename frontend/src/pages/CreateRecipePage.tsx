import "bootstrap/dist/css/bootstrap.min.css";
import { useState } from "react";
import { Button, Form, FormGroup, Stack } from "react-bootstrap";
import { SortableList } from "../components/SortableList";
import { BsGripHorizontal, BsTrash } from "react-icons/bs";

function SortingRow(props: { text: string; onTrash: () => void }) {
  return (
    <Stack direction="horizontal" className="justify-content-between">
      <Stack direction="horizontal" gap={3} className="flex-grow-1 text-break">
        <BsGripHorizontal />
        <span>{props.text}</span>
      </Stack>
      <Button
        variant="link"
        className="text-secondary"
        style={{ zIndex: 1000 }}
        onClick={props.onTrash}
      >
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
                text={item.ingredient + item.quantity}
                onTrash={() => {
                  setIngredients((ingredients) =>
                    ingredients.filter((ig) => ig.id !== item.id),
                  );
                }}
              />
            )}
          />
        </Stack>
      </FormGroup>
      <FormGroup>
        <Form.Label>Ohjeet</Form.Label>
        <Stack gap={3}>
          <SortableList
            items={instructions}
            setItems={setInstructions}
            renderItem={(item) => (
              <SortingRow
                text={item.instruction}
                onTrash={() => {
                  setInstructions((instructions) =>
                    instructions.filter((ig) => ig.id !== item.id),
                  );
                }}
              />
            )}
          />
        </Stack>
      </FormGroup>
    </Form>
  );
};

export default CreateRecipePage;
