import { Priority } from "./priority";
import { ServiceType } from "./service-type";
import { Status } from "./status";

export interface Filter {
  organization: number;
  patient: number;
  serviceType: number;
  status: Status;
  priority: Priority;
}
