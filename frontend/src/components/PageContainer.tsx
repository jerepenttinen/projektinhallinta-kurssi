import { Stack } from "react-bootstrap";

type Props = {
  children: React.ReactElement[] | undefined | React.ReactElement;
  gap?: 0 | 1 | 2 | 3 | 4 | 5;
};

export default function PageContainer({ children, gap }: Props) {
  return (
    <Stack className="py-4 px-3 container" gap={gap} style={{ maxWidth: 768 }}>
      {children}
    </Stack>
  );
}
