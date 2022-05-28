import { Gender } from "./gender";
import { Qualification } from "./qualification";

export interface PractitionerView {
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
  qualification: Qualification;
  patients: any[];
  examinations: any[];
  organization: number;
}
