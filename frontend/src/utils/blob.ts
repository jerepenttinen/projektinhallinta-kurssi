export async function blobToBase64(blob: Blob): Promise<string> {
  const reader = new FileReader();
  reader.readAsDataURL(blob);
  return new Promise((resolve, reject) => {
    reader.onloadend = () => {
      if (reader.result) {
        resolve(reader.result.toString().split(",")[1]);
      } else {
        reject();
      }
    };
  });
}
