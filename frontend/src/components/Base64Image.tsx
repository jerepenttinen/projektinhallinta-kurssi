import { useQuery } from "@tanstack/react-query";
import { Figure } from "react-bootstrap";

async function base64ToUrl(base64Data: string) {
  const res = await fetch("data:image/jpeg;base64," + base64Data);
  const blob = await res.blob();
  return window.URL.createObjectURL(blob);
}

/**
 * Give image is base64 string
 */
export function Base64Image(props: {
  image: string;
  id: string;
  rounded?: boolean;
}) {
  const imageUrl = useQuery([props.id], () => base64ToUrl(props.image));
  return <Figure.Image src={imageUrl.data} alt="" rounded={props.rounded} />;
}
