import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ParentRegistrationRequestDTO } from '../../interfaces/parent-registration-request-dto';
import { ParentRegistrationResponseDTO } from '../../interfaces/parent-registration-response-dto';
import { ParentSummaryDto } from '../../interfaces/parent-summary-dto';
import { SwimmerSummaryDto } from '../../interfaces/swimmer-summary-dto';
import { UpdateParentInfoDto } from '../../interfaces/update-parent-info-dto';


@Injectable({
  providedIn: 'root',
})
export class ParentService {
  registerUrl = 'http://localhost:8080/parents/register';
  profileUrl = 'http://localhost:8080/parents/me';
  updateProfileUrl = 'http://localhost:8080/parents/me';
  getMySwimmersUrl = 'http://localhost:8080/parents/me/swimmers';
  private http: HttpClient = inject(HttpClient);

  registerParent(dto: ParentRegistrationRequestDTO) {
    return this.http.post<ParentRegistrationResponseDTO>(
      this.registerUrl, dto
    );
  }

  getMyProfile() {
    return this.http.get<ParentSummaryDto>(this.profileUrl);
  }

  updateMyProfile(dto: UpdateParentInfoDto) {
    return this.http.put<UpdateParentInfoDto>(
      this.updateProfileUrl, dto
    );
  }

  getMySwimmers() {
    return this.http.get<SwimmerSummaryDto[]>(this.getMySwimmersUrl);
  }

}
