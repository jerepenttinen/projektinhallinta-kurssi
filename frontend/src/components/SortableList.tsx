import {
  DndContext,
  DragEndEvent,
  PointerSensor,
  closestCenter,
  useSensor,
  useSensors,
} from "@dnd-kit/core";
import {
  SortableContext,
  useSortable,
  verticalListSortingStrategy,
} from "@dnd-kit/sortable";
import { CSS } from "@dnd-kit/utilities";

type Identifiable = {
  id: string;
};

type SortableItemProps = Identifiable & {
  children: React.ReactElement;
};

function SortableItem(props: SortableItemProps) {
  const { attributes, listeners, setNodeRef, transform, transition } =
    useSortable({ id: props.id });

  const style = {
    transform: CSS.Transform.toString(transform),
    transition,
  };

  return (
    <div ref={setNodeRef} style={style} {...attributes} {...listeners}>
      {props.children}
    </div>
  );
}

export function SortableList<T extends Identifiable>(props: {
  render: (item: T, index: number) => React.ReactElement;
  move: (indexA: number, indexB: number) => void;
  items: T[];
}) {
  const sensors = useSensors(
    useSensor(PointerSensor, {
      activationConstraint: {
        distance: 8,
      },
    }),
  );

  return (
    <DndContext
      collisionDetection={closestCenter}
      onDragEnd={handleDragEnd}
      sensors={sensors}
    >
      <SortableContext
        items={props.items}
        strategy={verticalListSortingStrategy}
      >
        {props.items.map((item, index) => (
          <SortableItem key={item.id} id={item.id}>
            {props.render(item, index)}
          </SortableItem>
        ))}
      </SortableContext>
    </DndContext>
  );

  function handleDragEnd(event: DragEndEvent) {
    const { active, over } = event;

    if (over !== null && active.id !== over?.id) {
      const ids = props.items.map((item) => item.id);
      const activeIndex = ids.indexOf(active.id.toString());
      const overIndex = ids.indexOf(over.id.toString());
      props.move(activeIndex, overIndex);
    }
  }
}
