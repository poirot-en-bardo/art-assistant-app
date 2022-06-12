import { AbstractControl } from '@angular/forms';

export class PasswordValidation {

  public static passwordCheck() {
    return (control: AbstractControl) => {
      //TODO change regex
      const passwordRegex = '^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$';
      const passwordValue = control.value;
      if (!passwordValue) {
        return null;
      }
      return passwordValue.match(passwordRegex) ? null : {'passwordCheck' : true};
    }
  }
}
