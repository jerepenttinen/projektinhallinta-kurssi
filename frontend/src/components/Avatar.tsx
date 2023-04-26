import "./Avatar.css";

const sizes = {
  s: 32,
  m: 48,
  l: 128,
} as const;

export function Avatar(props: {
  size: "s" | "m" | "l";
  children: React.ReactElement | null;
}) {
  const size = sizes[props.size];

  return (
    <div
      className="avatar bg-primary"
      style={{
        width: size,
        height: size,
      }}
    >
      {props.children}
    </div>
  );
}
