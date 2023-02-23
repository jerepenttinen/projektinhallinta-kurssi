import {useCallback} from 'react'
import Button from 'react-bootstrap/Button';

import {useDropzone} from 'react-dropzone'

const FileDropzone = () => {

    const onDrop = useCallback((acceptedFiles: File[]) => {
        acceptedFiles.forEach((file) => {
            const reader = new FileReader()

            reader.onabort = () => console.log('file reading was aborted');
            reader.onerror = () => console.log('file reading has failed');
            reader.onload = () => {
                // Do whatever you want with the file contents
                const binaryStr = reader.result
                console.log(binaryStr)
            }
            reader.readAsArrayBuffer(file)
        })
    }, [])
    const dropzoneSettings = {
        onDrop,
        accept: {
            'image/png': ['.png'],
            'text/html': ['.html', '.htm']
        },
        noClick: true,
    }
    const {getRootProps, getInputProps, isDragActive, open} = useDropzone(dropzoneSettings);

    return(
        <div {...getRootProps()} className="p-2 border-secondary text-center"
          style={{minHeight:'100%', minWidth:'100%', borderStyle:'dashed', borderWidth:2}}>
              <input {...getInputProps()}/>
              {
                isDragActive ?
                  <p className="">Tiputa kuva</p> :
                  <p className="">Raahaa kuva tai lisää painamalla</p>
              }
              <Button variant="outline-secondary" type="button" onClick={open}>
                Lisää kuva
              </Button>
        </div>
    );
}

export default FileDropzone;