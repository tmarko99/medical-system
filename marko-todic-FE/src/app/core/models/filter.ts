import { Priority } from "./priority";
import { Status } from "./status";

export interface Filter {
  organization: number;
  patient: number;
  serviceType: number;
  status: Status;
  priority: Priority;
}
