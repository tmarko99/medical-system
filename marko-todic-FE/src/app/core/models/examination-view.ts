import { Organization } from "./organization";
import { Patient } from "./patient";
import { Practitioner } from "./practitioner";
import { Priority } from "./priority";
import { ServiceType } from "./service-type";
import { Status } from "./status";

export interface ExaminationView {
    id: number;
    identifier: string;
    status: Status;
    serviceType: ServiceType;
    priority: Priority;
    startDate: Date;
    endDate: Date;
    diagnosis: string;
    organization: Organization;
    practitioners: Practitioner[];
    patient: Patient;
}
