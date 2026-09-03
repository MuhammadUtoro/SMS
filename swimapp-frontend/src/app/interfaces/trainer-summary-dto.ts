import { CourseDetailDto } from "./course-detail-dto";

export interface TrainerSummaryDto {
  firstName: string;
  lastName: string;
  courses: CourseDetailDto[] | null;
}
