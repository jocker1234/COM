import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {LoginComponent} from "./section/login/login.component";
import {RegisterComponent} from "./section/register/register.component";
import {PasswordResetComponent} from "./section/login/password-reset/password-reset.component";


const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'sign',
    component: RegisterComponent
  },
  {
    path: 'reset',
    component: PasswordResetComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
