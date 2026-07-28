import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export interface ParentRegistrationRequestDTO {
  email: string;
  firstName: string;
  lastName: string;
  username: string;
  password: string;
}

@Injectable({
  providedIn: 'root',
})
export class ParentService {
  registerUrl = 'http://localhost:8080/parents/register';
  private http: HttpClient = inject(HttpClient);

  registerParent(dto: ParentRegistrationRequestDTO) {
    return this.http.post(this.registerUrl, dto);
  }
}
