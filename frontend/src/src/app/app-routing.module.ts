import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {LoginComponent} from "./section/login/login.component";
import {RegisterComponent} from "./section/register/register.component";
import {PasswordResetComponent} from "./section/login/password-reset/password-reset.component";
import {HomeComponent} from "./section/home/home.component";
import {AuthGuard} from "./section/auth/auth-guard";
import {ChangePasswordComponent} from "./section/login/change-password/change-password.component";


const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    data: {
      breadcrumb: 'Login'
    },
  },
  {
    path: 'sign',
    component: RegisterComponent,
    data: {
      breadcrumb: 'Registration'
    },
  },
  {
    path: 'forgot',
    component: PasswordResetComponent,
    data: {
      breadcrumb: 'Remind password'
    },
  },
  {
    path: 'reset',
    component: ChangePasswordComponent,
    data: {
      breadcrumb: 'Change password'
    },
  },
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'user',
    loadChildren: () => import('./section/user/user.module').then(value => value.UserModule),
    data: {
      authorities: ['ROLE_ACTIVE_PARTICIPANT', 'ROLE_ADMIN'],
      breadcrumb: 'User'
    },
    canActivate: [AuthGuard]
  },
  {
    path: 'abstracts',
    loadChildren: () => import('./section/abstracts/abstracts.module').then(value => value.AbstractsModule),
    data: {
      authorities: ['ROLE_ACTIVE_PARTICIPANT'],
      breadcrumb: 'Abstracts'
    },
    canActivate: [AuthGuard]
  },
  {
    path: 'admin',
    loadChildren: () => import('./section/admin/admin.module').then(value => value.AdminModule),
    data: {
      authorities: ['ROLE_ADMIN'],
      breadcrumb: 'Admin'
    },
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
