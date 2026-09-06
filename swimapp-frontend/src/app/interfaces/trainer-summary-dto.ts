import { CourseDetailDto } from "./course-detail-dto";

export interface TrainerSummaryDto {
  trainerId: number;
  firstName: string;
  lastName: string;
  courses: CourseDetailDto[] | null;
}
