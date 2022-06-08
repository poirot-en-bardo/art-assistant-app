import { AbstractControl } from '@angular/forms';

export class PasswordValidation {

  public static passwordCheck() {
    return (control: AbstractControl) => {
      //TODO change regex
      const passwordRegex = '(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%.,*#?&^_-]).{8,}';
      const passwordValue = control.value;
      if (!passwordValue) {
        return null;
      }
      return passwordValue.match(passwordRegex) ? null : {'passwordCheck' : true};
    }
  }
}
