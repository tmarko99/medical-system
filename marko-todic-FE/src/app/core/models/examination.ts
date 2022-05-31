import { Priority } from "./priority";
import { ServiceType } from "./service-type";
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
  practitoners: number[];
  patient: number;
}
