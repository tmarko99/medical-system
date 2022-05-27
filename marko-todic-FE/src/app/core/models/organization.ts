import { OrganizationType } from "./organizationType";

export interface Organization {
  id: number;
  identifier: string;
  active: boolean;
  type: OrganizationType;
  name: string;
  address: string;
  phone: string;
  email: string;
}
