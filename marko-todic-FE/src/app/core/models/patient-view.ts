import { Gender } from "./gender";
import { MaritalStatus } from "./maritalStatus";
import { Organization } from "./organization";
import { Practitioner } from "./practitioner";

export interface PatientView {
  id: number;
  identifier: string;
  active: boolean;
  name: string;
  surname: string;
  gender: Gender;
  birthDate: Date;
  address: string;
  phone: string;
  email: string;
  deceased: boolean;
  maritalStatus: MaritalStatus;
  practitioner: Practitioner;
  examinations: any[];
  organization: Organization;
}
