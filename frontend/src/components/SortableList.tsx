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
  arrayMove,
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

type SortableListProps<T extends Identifiable> = {
  items: T[];
  setItems: React.Dispatch<React.SetStateAction<T[]>>;
  renderItem: (item: T) => React.ReactElement;
};

export function SortableList<T extends Identifiable>({
  items,
  setItems,
  renderItem,
}: SortableListProps<T>) {
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
      <SortableContext items={items} strategy={verticalListSortingStrategy}>
        {items.map((item) => (
          <SortableItem key={item.id} id={item.id}>
            {renderItem(item)}
          </SortableItem>
        ))}
      </SortableContext>
    </DndContext>
  );

  function handleDragEnd(event: DragEndEvent) {
    const { active, over } = event;

    if (over !== null && active.id !== over?.id) {
      setItems((items) => {
        const ids = items.map((item) => item.id);
        const activeIndex = ids.indexOf(active.id.toString());
        const overIndex = ids.indexOf(over.id.toString());
        return arrayMove(items, activeIndex, overIndex);
      });
    }
  }
}
