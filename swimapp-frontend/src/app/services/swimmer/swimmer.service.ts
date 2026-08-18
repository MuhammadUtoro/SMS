import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CreateSwimmerDto } from '../../interfaces/create-swimmer-dto';

@Injectable({
  providedIn: 'root',
})
export class SwimmerService {
  createSwimmerUrl = 'http://localhost:8080/swimmers';

  private http: HttpClient = inject(HttpClient);

  createSwimmer(dto: CreateSwimmerDto) {
    return this.http.post<CreateSwimmerDto>(this.createSwimmerUrl, dto);
  }
}
