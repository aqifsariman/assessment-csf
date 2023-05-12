import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UploadFormComponent } from './components/upload-form/upload-form.component';

const routes: Routes = [
  { path: '', component: UploadFormComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
