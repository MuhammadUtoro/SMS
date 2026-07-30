import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ParentRegistrationRequestDTO } from '../../interfaces/parent-registration-request-dto';
import { ParentRegistrationResponseDTO } from '../../interfaces/parent-registration-response-dto';

@Injectable({
  providedIn: 'root',
})
export class ParentService {
  registerUrl = 'http://localhost:8080/parents/register';
  private http: HttpClient = inject(HttpClient);

  registerParent(dto: ParentRegistrationRequestDTO) {
    return this.http.post<ParentRegistrationResponseDTO>(
      this.registerUrl, dto
    );
  }
}
