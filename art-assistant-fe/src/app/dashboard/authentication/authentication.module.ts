import { NgModule } from '@angular/core';
import { AuthenticationRoutingModule } from './authentication-routing.module';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { AuthenticationComponent } from './authentication.component';
import { SharedModule } from '../../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthenticationService } from '../../shared/services/authentication.service';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    SignInComponent,
    SignUpComponent,
    AuthenticationComponent
  ],
  imports: [
    AuthenticationRoutingModule,
    SharedModule,
    ReactiveFormsModule,
    CommonModule
  ],
  providers: [AuthenticationService]
})
export class AuthenticationModule {}
