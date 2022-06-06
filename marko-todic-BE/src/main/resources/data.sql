INSERT INTO `examination` (`id`, `diagnosis`, `end_date`, `identifier`, `priority`, `start_date`, `status`, `organization_id`, `patient_id`, `service_type_id`) VALUES
(1, 'I33-2b', '2022-05-27 14:00:00', 'EXAM02', 'RUSH_REPORT', '2022-05-27 13:00:00', 'PLANNED', 2, 1, 10),
(2, 'S-3a', '2022-05-31 02:30:00', 'EXAM03', 'ROUTINE', '2022-05-31 02:00:00', 'PLANNED', 2, 2, 5),
(3, 'g43', '2022-06-03 19:20:00', 'EXAM04', 'ROUTINE', '2022-06-03 19:00:00', 'ENTERED_IN_ERROR', 6, 3, 7),
(4, 's52-a', '2022-06-05 19:00:00', 'EXAM05', 'TIMING_CRITICAL', '2022-06-05 15:00:00', 'IN_PROGRESS', 6, 4, 17);

-- --------------------------------------------------------

INSERT INTO `examination_practitioner` (`examination_id`, `practitioner_id`) VALUES
(1, 2),
(1, 1),
(2, 1),
(3, 5),
(4, 5);

-- --------------------------------------------------------

INSERT INTO `organization` (`id`, `active`, `address`, `email`, `identifier`, `name`, `phone`, `type`) VALUES
(1, b'1', '976 Paris Hill Ave. Aberdeen', 'organization1@mail.com', 'ORG012222', 'Ultra Care House', '213123122', 'HOSPITAL'),
(2, b'1', '8866 Ridge Street New Albany', 'organization2@mail.com', 'ORG0123', 'Care & Cure Hospital', '213123122', 'HOSPITAL'),
(3, b'1', '70 Warren Circle Davenport', 'organization3@mail.com', 'ORG04', 'Newlife hospital', '41221', 'HOSPITAL'),
(4, b'1', '8715 Illinois Court Parkville', 'organization4@mail.com', 'ORG99', 'Rejuvenate', '34134213324', 'INSURANCE_COMPANY'),
(5, b'1', '8289 Marsh Lane Gloucester', 'organization5@mail.com', 'ORG062', 'Good Planet', '1232213', 'INSURANCE_COMPANY'),
(6, b'1', '973 West Cambridge St. Apt 1 Hillsborough', 'organization6@mail.com', 'ORG07', 'YouHeal Hospital', '2131231', 'OTHER'),
(7, b'0', '9786 Beech Rd. South Bend', 'organization7@mail.com', 'ORG08', 'Harmony', '34134324', 'EDUCATION_INSTITUTE'),
(8, b'1', '788 East Virginia Rd. Braintree', 'organization8@mail.com', 'ORG09', 'NorthMark', '34134324', 'EDUCATION_INSTITUTE'),
(9, b'1', '9255 Adams Street Auburndale', 'organization9@mail.com', 'ORG10', 'Wake up Medicare', '34134213324', 'HOSPITAL'),
(10, b'1', '861 Devonshire Lane Downers Grove', 'organization10@mail.com', 'ORG11', 'Willow Green Hospital', '34134213324', 'INSURANCE_COMPANY'),
(11, b'1', '7076 Indian Spring Court West Fargo', 'organization11@gmail.com', 'ORG12', 'Maple heart care', '12312231132', 'EDUCATION_INSTITUTE'),
(12, b'0', '374 Santa Clara Ave. Canton', 'organization12@gmail.com', 'ORG13', 'Al Shadow care', '123312321', 'CLINICAL_RESEARCH'),
(13, b'1', '78 Peninsula Ave. Sterling', 'organization13@gmail.com', 'ORG14', 'Mr.Nine’s Hospital', '123124124', 'OTHER'),
(14, b'1', 'GoldFish Hospital', 'organization14@gmail.com', 'ORG01234', 'GoldFish Hospital', '123124121', 'CLINICAL_RESEARCH'),
(15, b'1', '20 Shore Road Pomona', 'organization15@gmail.com', 'ORG012299', 'Twin Tulip Hospital', '21312312', 'CLINICAL_RESEARCH');

-- --------------------------------------------------------

INSERT INTO `patient` (`id`, `active`, `address`, `birth_date`, `deceased`, `email`, `gender`, `identifier`, `marital_status`, `name`, `phone`, `surname`, `organization_id`, `practitioner_id`) VALUES
(1, b'1', '3 Sierra St. Holbrook', '1945-06-01', b'0', 'deangelo@mail.com', 'MALE', 'PAE02', 'MARRIED', 'Deangelo', '34134213324', 'Mann', 2, 1),
(2, b'1', '660 West Fairground Avenue Newtown', '1988-07-21', b'0', 'caroline@gmail.com', 'FEMALE', 'PAE01', 'ANNULLED', 'Caroline', '213124124', 'Gerson', 2, 1),
(3, b'0', '9733 N. Creekside Street Elkton', '1998-12-22', b'0', 'jasmine@gmail.com', 'FEMALE', 'PAE03', 'NEVER_MARRIED', 'Jasmine', '142141241', 'Long', 6, 5),
(4, b'1', '9948 Buckingham St. Franklin', '1998-02-08', b'0', 'jorge@gmail.com', 'MALE', 'PAE04', 'DIVORCED', 'Jorge', '154152124', 'Walls', 6, 5);

-- --------------------------------------------------------

INSERT INTO `practitioner` (`id`, `active`, `address`, `birth_date`, `email`, `gender`, `identifier`, `name`, `phone`, `qualification`, `surname`, `organization_id`) VALUES
(1, b'1', '350 Grant St. Loveland', '1991-10-03', 'john@mail.com', 'MALE', 'PRC01', 'John', '2121321132', 'DOCTOR_OF_MEDICINE', 'Doe', 2),
(2, b'1', '894 Linden Lane Stow', '1974-01-22', 'michael@mail.com', 'OTHER', 'PRC02', 'Michael', '123123231', 'NURSE_PRACTITIONER', 'Elle', 2),
(3, b'0', '7479 West Livingston Street Flushing', '1965-04-22', 'nicolas@gmail.com', 'MALE', 'PRC03', 'Nicolas', '13214214', 'DOCTOR_OF_PHARMACY', 'Agner', 8),
(4, b'1', '384 New St. Cuyahoga Falls', '1988-04-24', 'email@gmail.com', 'UNKNOWN', 'PRC04', 'Aliya', '1312414', 'MEDICAL_ASSISTANT', 'Downs', NULL),
(5, b'1', '56 Albany Ave. East Stroudsburg', '1997-08-05', 'jody@gmail.com', 'FEMALE', 'PRC05', 'Jody', '41224142', 'DOCTOR_OF_MEDICINE', 'Suele', 6),
(6, b'1', '368 Halifax Rd. Shelbyville', '1993-03-31', 'lara@gmail.com', 'FEMALE', 'PRC06', 'Lara', '124824851', 'EMERGENCY_MEDICAL_TECHNICIAN', 'Lucas', 14);

-- --------------------------------------------------------


INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_PRACTITIONER'),
(3, 'ROLE_PATIENT');

-- --------------------------------------------------------


INSERT INTO `service_type` (`id`, `name`) VALUES
(1, 'Aged Care Assessment'),
(2, 'Aged Residential Care'),
(3, 'Acupuncture'),
(4, 'Bowen Therapy'),
(5, 'Blood Donation'),
(6, 'Family Planning'),
(7, 'Immunization'),
(8, 'Optometry'),
(9, 'Osteopathy'),
(10, 'Physiotherapy'),
(11, 'Podiatry'),
(12, 'Endodontic'),
(13, 'Dental'),
(14, 'Oral Surgery'),
(15, 'Dermatology'),
(16, 'Psychology'),
(17, 'Emergency Medical');

-- --------------------------------------------------------

INSERT INTO `user` (`id`, `email`, `password`) VALUES
(1, 'admin@mail.com', '$2a$10$DEIFl6LmHnYf2AxWcWxyReFIQCVr3ryORP5MdiRTrk9BNNMk/Azg6'),
(2, 'jody@gmail.com', '$2a$10$7WKliOn4BNsOTQt1sLk8KelnqM7KH0BGwJGceYohqhZsOIpmWnoKi');

-- --------------------------------------------------------


INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
(1, 1),
(2, 3);

