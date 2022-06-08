import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'firstKey'})
export class FirstKeyPipe implements PipeTransform {
  transform(object: any): string | null {
    const objectKeys = Object.keys(object);
    if (objectKeys.length > 0) {
      return objectKeys[0];
    }
    return null;
  }
}
