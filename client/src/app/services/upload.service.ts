import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UploadService {
  constructor(private http: HttpClient) {}
}

// export class SearchService {
//   constructor(private http: HttpClient) {}

//   getSearchResults(query: string): Promise<any> {
//     const params = new HttpParams().set('query', query);

//     return lastValueFrom(this.http.get<any>('/api/search', { params }));
//   }
// }
