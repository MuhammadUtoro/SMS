import { Injectable, inject} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TrainerRegistrationDTO } from '../../interfaces/trainer-registration-request-dto';


@Injectable({
  providedIn: 'root',
})
export class TrainerService {
  registerUrl = 'http://localhost:8080/trainers/register'

  private http: HttpClient = inject(HttpClient);

  registerTrainer(dto: TrainerRegistrationDTO) {
    return this.http.post<TrainerRegistrationDTO>(this.registerUrl, dto);
  }
}
