import { Priority } from "./priority";
import { Status } from "./status";

export interface Examination {
  id: number;
  identifier: string;
  status: Status;
  serviceType: number | string;
  priority: Priority;
  startDate: Date;
  endDate: Date;
  diagnosis: string;
  organization: number;
  practitioners: number[];
  patient: number;
}
