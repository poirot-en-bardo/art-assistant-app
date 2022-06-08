
import { Injectable } from '@angular/core';

@Injectable()
export class FileChangeBaseComponent {
  public file: any;
  public isLoading = false;
  fileType: string;
  picturePreview: any;

  public fileChangeEvent($event: any): void {
    this.isLoading = true;
    const imageType = /image.*/;
    const reader = new FileReader();
    if ($event.target.files && $event.target.files.length > 0) {
      const file = $event.target.files[0] || $event.dataTransfer.files[0];
      if (file.type.match(imageType)) {
        reader.readAsDataURL(file);
        reader.onload = () => {
          this.file = reader.result;
          this.picturePreview = reader.result;
          this.isLoading = false;
        }
      }
    }
  }
}
