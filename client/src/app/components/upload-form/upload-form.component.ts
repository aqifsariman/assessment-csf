import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { lastValueFrom } from 'rxjs';
import { UploadService } from 'src/app/services/upload.service';

@Component({
  selector: 'app-upload-form',
  templateUrl: './upload-form.component.html',
  styleUrls: ['./upload-form.component.css'],
})
export class UploadFormComponent implements OnInit {
  @ViewChild('file') archiveFile!: ElementRef;
  form!: FormGroup;
  response$!: Promise<any>;

  constructor(
    private fb: FormBuilder,
    private uploadSVC: UploadService,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.form = this.createForm();
  }

  createForm() {
    return this.fb.group({
      name: this.fb.control<string>('', [Validators.required]),
      title: this.fb.control<string>('', [Validators.required]),
      comments: this.fb.control<string>(''),
      archive: this.fb.control(''),
    });
  }

  upload() {
    const formData = new FormData();
    //@ts-ignore
    formData.set('name', this.form.get('archive').value);
    formData.set('file', this.archiveFile.nativeElement.files[0]);
    console.log('Form Value:', this.form.value);
    console.log('Uploaded Zip Name: ', formData.get('name'));
    console.log('Uploaded Zip File: ', formData.get('file'));

    // lastValueFrom(this.http.post('/upload', formData));
  }
}

// upload() {
//   constformData= new FormData();
//   formData.set(‘name', this.form.get(‘image-file’).value);
//   ...
//   formData.set('file', this.imageFile.nativeElement.files[0]);
//   firstVaulueFrom(
//   this.http.post('http://localhost:8080/upload', formData)
//   ).then(() => { ... })
//   .catch((error) => { ... })
//   }
