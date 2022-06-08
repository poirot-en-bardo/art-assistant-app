import { Injectable } from '@angular/core';

@Injectable()
export class LocalStorageService {

  public setItem(localStorageKey: string, object: any) {
    if (object) {
      localStorage.setItem(localStorageKey, JSON.stringify(object));
    }
  }

  public getItem(key: string) {
    const data = localStorage.getItem(key);
    if (data) {
      return JSON.parse(data);
    }
    return null;
  }

  public removeItem(key: string) {
    localStorage.removeItem(key);
  }
}
