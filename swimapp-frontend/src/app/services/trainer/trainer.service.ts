import { Injectable, inject} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TrainerRegistrationDTO } from '../../interfaces/trainer-registration-request-dto';
import { TrainerSummaryDto } from '../../interfaces/trainer-summary-dto';



@Injectable({
  providedIn: 'root',
})
export class TrainerService {
  registerUrl = 'http://localhost:8080/trainers/register';
  profileUrl = 'http://localhost:8080/trainers/me';
  getTrainersList = 'http://localhost:8080/trainers';
  private http: HttpClient = inject(HttpClient);

  getAllTrainers() {
    return this.http.get<TrainerSummaryDto[]>(this.getTrainersList);
  }

  registerTrainer(dto: TrainerRegistrationDTO) {
    return this.http.post<TrainerRegistrationDTO>(this.registerUrl, dto);
  }

  getMyProfile() {
    return this.http.get<TrainerSummaryDto>(this.profileUrl);
  }
}
