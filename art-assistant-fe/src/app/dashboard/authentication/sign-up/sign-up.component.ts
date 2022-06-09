import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SIGN_UP_FORM } from '../authentication.config';
import { AuthenticationService } from '../../../shared/services/authentication.service';
import { Router } from '@angular/router';
import { PasswordValidation } from '../../../shared/validators/password-validation';
import { AuthenticationRoutesConstants } from '../../../shared/constants/authentication-routes.constants';
import { AuthorisationEnum } from '../../../core/constants/authorisation.constants';
import { LocalStorageService } from '../../../core/services/local-storage.service';
import { GetLoggedUser } from '../../../shared/redux/user/user.action';
import { Store } from '@ngxs/store';
import { ShowErrorToaster } from '../../../shared/redux/toaster/toaster.action';

@Component({
  selector: 'app-authentication',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {
  signUpForm: FormGroup;
  SIGN_UP_FORM = SIGN_UP_FORM;
  passwordTextType = "password";

  constructor(private store: Store, private formBuilder: FormBuilder, private localStorageService: LocalStorageService, private authenticationService: AuthenticationService, private router: Router) {
  }

  ngOnInit(): void {
    this.buildForm();
  }

  public buildForm(): void {
    this.signUpForm = this.formBuilder.group({
      [SIGN_UP_FORM.FIRSTNAME]: ['', [
        Validators.required,
        Validators.minLength(3)
      ]],
      [SIGN_UP_FORM.LASTNAME]: ['', [
        Validators.required,
        Validators.minLength(3)
      ]],
      [SIGN_UP_FORM.EMAIL]: ['', [
        Validators.required,
        Validators.email,
        Validators.minLength(3)
      ]],
      [SIGN_UP_FORM.PASSWORD]: ['', [
        Validators.required,
        Validators.minLength(3),
        PasswordValidation.passwordCheck()
      ]],
    });
  }

  public signUp(): void {
    const user = {
      ...this.signUpForm.getRawValue(),
      roles: ["GUEST", "USER"]
    };
    this.authenticationService.signUp(user).subscribe(
      (sessionId) => {
        this.localStorageService.setItem(AuthorisationEnum.SESSION_ID, sessionId.sessionId);
        this.router.navigate([AuthenticationRoutesConstants.HOME]);
        this.store.dispatch(new GetLoggedUser());
        setTimeout(() => window.location.reload(), 600);
      },
      () => {
        this.store.dispatch(new ShowErrorToaster());
      });
  }

  togglePasswordTextType() {
    if (this.passwordTextType === 'password') {
      this.passwordTextType = 'text'
    } else {
      this.passwordTextType = 'password'
    }
  }
}
