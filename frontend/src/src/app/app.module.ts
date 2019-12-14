import {NgModule} from "@angular/core";
import {HeaderComponent} from "./header/header.component";
import {AppComponent} from "./app.component";
import {SectionComponent} from "./section/section.component";
import {HomeComponent} from "./section/home/home.component";
import {LoginComponent} from "./section/login/login.component";
import {RegisterComponent} from "./section/register/register.component";
import {PasswordResetComponent} from "./section/login/password-reset/password-reset.component";
import {FooterComponent} from "./footer/footer.component";
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "./app-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {UserModule} from "./section/user/user.module";
import {httpInterceptorProvider} from "./section/auth/auth-interceptor";
import {AbstractsModule} from "./section/abstracts/abstracts.module";
import {FieldErrorDisplayModule} from "./section/field-error-display/field-error-display.module";
import {AdminModule} from "./section/admin/admin.module";
import { BreadCrumbComponent } from './bread-crumb/bread-crumb.component';
import { ChangePasswordComponent } from './section/login/change-password/change-password.component';
import { TitleComponent } from './title/title.component';
import {FaConfig, FaIconLibrary} from "@fortawesome/angular-fontawesome";
import {faAngleDown, faAngleUp} from "@fortawesome/free-solid-svg-icons";
import { ExportComponent } from './export/export.component';
import {FieldSuccessfulDisplayModule} from "./section/field-successfull-display/field-successful-display.module";


@NgModule({
  declarations: [
    HeaderComponent,
    AppComponent,
    SectionComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    PasswordResetComponent,
    FooterComponent,
    BreadCrumbComponent,
    ChangePasswordComponent,
    TitleComponent,
    ExportComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgbModule,
    UserModule,
    AbstractsModule,
    FieldErrorDisplayModule,
    AdminModule,
    FieldSuccessfulDisplayModule
  ],
  providers: [
    httpInterceptorProvider,
    FaIconLibrary,
    FaConfig
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(private library: FaIconLibrary) {
    library.addIcons(faAngleDown, faAngleUp);
  }
}
