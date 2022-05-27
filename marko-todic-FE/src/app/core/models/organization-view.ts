import { OrganizationType } from "./organizationType";

export interface OrganizationView {
  id: number;
  identifier: string;
  active: boolean;
  type: OrganizationType;
  name: string;
  address: string;
  phone: string;
  email: string;
  practitioners: any[];
  patients: any[];
  examinations: any[];
}
