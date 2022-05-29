import { Gender } from "./gender";
import { MaritalStatus } from "./maritalStatus";

export interface Patient {
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
  practitioner: number;
  organization: number;
}
