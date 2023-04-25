import { useCallback } from "react";
import { useDropzone } from "react-dropzone";

type Props = {
  onImageDropped: (buf: Uint8Array) => void;
};

export default function DropImages({ onImageDropped }: Props) {
  const onDrop = useCallback(
    (acceptedFiles: File[]) => {
      for (const file of acceptedFiles) {
        const reader = new FileReader();

        reader.onerror = (err) => console.error(err);
        reader.onload = () => {
          if (reader.result instanceof ArrayBuffer) {
            onImageDropped(new Uint8Array(reader.result));
          } else {
            console.error(reader.result);
          }
        };

        reader.readAsArrayBuffer(file);
      }
    },
    [onImageDropped],
  );

  const { getRootProps, getInputProps } = useDropzone({
    onDrop,
    accept: {
      "image/jpeg": [".jpeg", ".jpg"],
    },
  });

  return (
    <div
      {...getRootProps()}
      className="rounded vstack bg-white"
      style={{
        border: "1px dashed var(--bs-border-color)",
        borderStyle: "dashed",
      }}
    >
      <div className="mx-auto vstack py-5 gap-2">
        <span style={{ userSelect: "none" }}>
          Raahaa kuva tai lisää painamalla tästä
        </span>
        <input {...getInputProps()} />
      </div>
    </div>
  );
}
