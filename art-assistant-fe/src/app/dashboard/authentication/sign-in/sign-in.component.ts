import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SIGN_IN_FORM } from '../authentication.config';
import { AuthenticationService } from '../../../shared/services/authentication.service';
import { Router } from '@angular/router';
import { AuthenticationRoutesConstants } from '../../../shared/constants/authentication-routes.constants';
import { AuthorisationEnum } from '../../../core/constants/authorisation.constants';
import { LocalStorageService } from "../../../core/services/local-storage.service";
import { Store } from '@ngxs/store';
import { ShowErrorToaster } from '../../../shared/redux/toaster/toaster.action';
import { GetLoggedUser } from '../../../shared/redux/user/user.action';

@Component({
  selector: 'app-authentication',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {
  signInForm: FormGroup;
  SIGN_IN_FORM = SIGN_IN_FORM;
  passwordTextType = "password";

  constructor(private store: Store, private formBuilder: FormBuilder, private localStorageService: LocalStorageService, private authenticationService: AuthenticationService, private router: Router) {
  }

  ngOnInit(): void {
    this.buildForm();
  }

  public buildForm(): void {
    this.signInForm = this.formBuilder.group({
      [SIGN_IN_FORM.EMAIL]: ['', [
        Validators.required,
        Validators.email,
        Validators.minLength(3)
      ]],
      [SIGN_IN_FORM.PASSWORD]: ['', [
        Validators.required,
        Validators.minLength(3),
      ]],
    });
  }

  public signIn(): void {
    const user = {
      ...this.signInForm.getRawValue()
    };
    this.signInForm.controls[SIGN_IN_FORM.PASSWORD].setValue('');
    this.authenticationService.signIn(user).subscribe(
      (tokenDetails) => {
        this.localStorageService.setItem(AuthorisationEnum.JWT, tokenDetails.accessToken);
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
