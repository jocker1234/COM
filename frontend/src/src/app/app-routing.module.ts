import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {LoginComponent} from "./section/login/login.component";
import {RegisterComponent} from "./section/register/register.component";
import {PasswordResetComponent} from "./section/login/password-reset/password-reset.component";
import {HomeComponent} from "./section/home/home.component";
import {AuthGuard} from "./section/auth/auth-guard";


const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'home',
    component: HomeComponent
  },
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
  {
    path: 'user',
    loadChildren: () => import('./section/user/user.module').then(value => value.UserModule),
    data: {
      authorities: ['ROLE_ACTIVE_PARTICIPANT', 'ROLE_ADMIN']
    },
    canActivate: [AuthGuard]
  },
  {
    path: 'abstracts',
    loadChildren: () => import('./section/abstracts/abstracts.module').then(value => value.AbstractsModule),
    data: {
      authorities: ['ROLE_ACTIVE_PARTICIPANT']
    },
    canActivate: [AuthGuard]
  },
  {
    path: 'admin',
    loadChildren: () => import('./section/admin/admin.module').then(value => value.AdminModule),
    data: {
      authorities: ['ROLE_ADMIN']
    },
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
