export class ImageUtils {
  static extractImage(file: any) {
    if (file) {
      return file.split(",").pop();
    }
    return '';
  }

  static appendImageType(mediaArray: string, mediaType: string = 'png') {
    return `data:image/${mediaType};base64,${mediaArray}`;
  }
}
