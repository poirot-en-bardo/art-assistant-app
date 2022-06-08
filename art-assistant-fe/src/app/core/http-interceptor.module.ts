import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor } from './interceptor/user-interceptor';
import { NgModule } from '@angular/core';

@NgModule({
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true }]
})
export class HttpInterceptorModule {
}
