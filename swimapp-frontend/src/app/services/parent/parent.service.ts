import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ParentRegistrationRequestDTO } from '../../interfaces/parent-registration-request-dto';
import { ParentRegistrationResponseDTO } from '../../interfaces/parent-registration-response-dto';

@Injectable({
  providedIn: 'root',
})
export class ParentService {
  registerUrl = 'http://localhost:8080/parents/register';
  profileUrl = 'http://localhost:8080/parents/me';
  private http: HttpClient = inject(HttpClient);

  registerParent(dto: ParentRegistrationRequestDTO) {
    return this.http.post<ParentRegistrationResponseDTO>(
      this.registerUrl, dto
    );
  }
  getMyProfile() {
    return this.http.get(this.profileUrl);
  }
}
