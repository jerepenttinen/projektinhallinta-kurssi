import DropImages from "../components/DropImages";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { Suspense, useEffect, useState } from "react";
import { useMutation } from "@tanstack/react-query";
import { useAuthentication } from "../AuthenticationContext";
import { GetUser, UpdateUserDetails } from "../api/Users";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";
import { blobToBase64 } from "../utils/blob";
import { Avatar } from "../components/Avatar";
import PageContainer from "../components/PageContainer";

const profileSettingsFormValidator = z.object({
  image: z.instanceof(Blob),
  description: z.string(),
});

const ProfileSettingsPage = () => {
  const { user } = useAuthentication();

  const updateDetailsMutation = useMutation(UpdateUserDetails);
  const [image, setImage] = useState<Blob | null>(null);

  const onImageDropped = (buffer: Uint8Array) => {
    const blob = new Blob([buffer]);
    setValue("image", blob);
    setImage(blob);
  };

  const { register, handleSubmit, setValue } = useForm<
    z.infer<typeof profileSettingsFormValidator>
  >({
    resolver: zodResolver(profileSettingsFormValidator),
  });

  useEffect(() => {
    if (user) {
      (async () => {
        const details = await GetUser(user.profileId.toString());
        if (details.image) {
          const res = await fetch("data:image/jpeg;base64," + details.image);
          const blob = await res.blob();
          setValue("image", blob);
          setImage(blob);
        }
        if (details.description) {
          setValue("description", details.description);
        }
      })();
    }
  }, [user, setValue]);

  const imageUrl = image !== null ? URL.createObjectURL(image) : null;

  return (
    <Suspense fallback={<p>Ladataan...</p>}>
      <PageContainer>
        <Form
          className="vstack gap-3"
          onSubmit={handleSubmit(async (data) => {
            updateDetailsMutation.mutate({
              id: user!.id,
              description: data.description,
              image: await blobToBase64(data.image),
            });
          })}
        >
          <h2>Asetukset</h2>
          <div className="hstack gap-3" data-testid="top-container">
            <Avatar size="l">
              {imageUrl ? <img src={imageUrl} alt="" /> : null}
            </Avatar>
            <DropImages onImageDropped={onImageDropped} />
          </div>
          <Form.Group controlId="textArea">
            <Form.Control
              as="textarea"
              placeholder="Kuvauksen muokkaus"
              rows={6}
              {...register("description")}
            />
          </Form.Group>
          <Button variant="primary" size="lg" type="submit" id="profile-submit">
            Tallenna
          </Button>
        </Form>
      </PageContainer>
    </Suspense>
  );
};

export default ProfileSettingsPage;
