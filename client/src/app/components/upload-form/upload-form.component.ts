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
  results!: String;

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
    formData.set('name', this.form.get('name')?.value);
    formData.set('title', this.form.get('title')?.value);
    formData.set('comments', this.form.get('comments')?.value);
    formData.set('file', this.archiveFile.nativeElement.files[0]);

    // this.http.post('/upload', formData);
    this.uploadSVC.getBundleId(formData).then((val) => {
      console.log(val);
    });

    // this.router.navigate(['/bundle/' +]);
  }
}
