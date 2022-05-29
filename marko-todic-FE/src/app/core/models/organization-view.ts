import { OrganizationType } from "./organizationType";
import { Patient } from "./patient";
import { Practitioner } from "./practitioner";

export interface OrganizationView {
  id: number;
  identifier: string;
  active: boolean;
  type: OrganizationType;
  name: string;
  address: string;
  phone: string;
  email: string;
  practitioners: Practitioner[];
  patients: Patient[];
  examinations: any[];
}
