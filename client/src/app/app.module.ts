import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UploadFormComponent } from './components/upload-form/upload-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { UploadService } from './services/upload.service';
import { DisplayresponseComponent } from './components/displayresponse/displayresponse.component';

@NgModule({
  declarations: [AppComponent, UploadFormComponent, DisplayresponseComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [UploadService],
  bootstrap: [AppComponent],
})
export class AppModule {}
