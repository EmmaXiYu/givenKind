SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `givenkind`.`tblNonProfitCategory`
-- -----------------------------------------------------
START TRANSACTION;
USE `givenkind`;
INSERT INTO `givenkind`.`tblNonProfitCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (1, 'Animals', 'N/A');
INSERT INTO `givenkind`.`tblNonProfitCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (2, 'Arts', 'N/A');
INSERT INTO `givenkind`.`tblNonProfitCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (3, 'Children', 'N/A');
INSERT INTO `givenkind`.`tblNonProfitCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (4, 'Education', 'N/A');
INSERT INTO `givenkind`.`tblNonProfitCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (5, 'Environment', 'N/A');
INSERT INTO `givenkind`.`tblNonProfitCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (6, 'Health', 'N/A');
INSERT INTO `givenkind`.`tblNonProfitCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (7, 'Human Services', 'N/A');
INSERT INTO `givenkind`.`tblNonProfitCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (8, 'International Benefit', 'N/A');
INSERT INTO `givenkind`.`tblNonProfitCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (9, 'Public Benefit', 'N/A');
INSERT INTO `givenkind`.`tblNonProfitCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (10, 'Religious/Spiritual', 'N/A');
INSERT INTO `givenkind`.`tblNonProfitCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (11, 'Other', 'N/A');

COMMIT;


-- -----------------------------------------------------
-- Data for table `givenkind`.`tblRole`
-- -----------------------------------------------------
START TRANSACTION;
USE `givenkind`;
INSERT INTO `givenkind`.`tblRole` (`RoleId`, `RoleName`, `RoleDescription`) VALUES (1, 'Admin', 'N/A');
INSERT INTO `givenkind`.`tblRole` (`RoleId`, `RoleName`, `RoleDescription`) VALUES (2, 'NonProfit', 'N/A');
INSERT INTO `givenkind`.`tblRole` (`RoleId`, `RoleName`, `RoleDescription`) VALUES (3, 'Donor', 'N/A');
INSERT INTO `givenkind`.`tblRole` (`RoleId`, `RoleName`, `RoleDescription`) VALUES (4, 'Volunteer', 'N/A');

COMMIT;


-- -----------------------------------------------------
-- Data for table `givenkind`.`tblState`
-- -----------------------------------------------------
START TRANSACTION;
USE `givenkind`;
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (1, 'AL', 'Alabama');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (2, 'AK', 'Alaska');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (3, 'AZ', 'Arizona');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (4, 'AR', 'Arkansas');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (5, 'CA', 'California');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (6, 'CO', 'Colorado');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (7, 'CT', 'Connecticut');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (8, 'DE', 'Delaware');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (9, 'FL', 'Florida');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (10, 'GA', 'Georgia');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (11, 'HI', 'Hawaii');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (12, 'ID', 'Idaho');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (13, 'IL', 'Illinois');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (14, 'IN', 'Indiana');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (15, 'IA', 'Iowa');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (16, 'KS', 'Kansas');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (17, 'KY', 'Kentucky');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (18, 'LA', 'Louisiana');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (19, 'ME', 'Maine');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (20, 'MD', 'Maryland');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (21, 'MA', 'Massachusetts');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (22, 'MI', 'Michigan');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (23, 'MN', 'Minnesota');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (24, 'MS', 'Mississippi');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (25, 'MO', 'Missouri');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (26, 'MT', 'Montana');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (27, 'NE', 'Nebraska');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (28, 'NV', 'Nevada');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (29, 'NH', 'New Hampshire');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (30, 'NJ', 'New Jersey');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (31, 'NM', 'New Mexico');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (32, 'NY', 'New York');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (33, 'NC', 'North Carolina');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (34, 'ND', 'North Dakota');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (35, 'OH', 'Ohio');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (36, 'OK', 'Oklahoma');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (37, 'OR', 'Oregon');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (38, 'PA', 'Pennsylvania');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (39, 'RI', 'Rhode Island');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (40, 'SC', 'South Carolina');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (41, 'SD', 'South Dakota');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (42, 'TN', 'Tennessee');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (43, 'TX', 'Texas');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (44, 'UT', 'Utah');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (45, 'VT', 'Vermont');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (46, 'VA', 'Virginia');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (47, 'WA', 'Washington');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (48, 'WV', 'West Virginia');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (49, 'WI', 'Wisconsin');
INSERT INTO `givenkind`.`tblState` (`StateId`, `StateAbbr`, `StateName`) VALUES (50, 'WY', 'Wyoming');

COMMIT;

-- -----------------------------------------------------
-- Data for table `givenkind`.`tblItemCategory`
-- -----------------------------------------------------
START TRANSACTION;
USE `givenkind`;
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (1, 'Airline Miles and Hotel Points', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (2, 'Animal/Wildlife', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (3, 'Appliances', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (4, 'Arts and Craft Materials', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (5, 'Baby Items', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (6, 'Books', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (7, 'Building Materials', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (8, 'Clothing-Adults', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (9, 'Clothing-Children', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (10, 'Decor', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (11, 'Electronics', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (12, 'Food', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (13, 'Furniture-Office', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (14, 'Furniture-Residential', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (15, 'Kitchen', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (16, 'Linens/Bedding', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (17, 'Medical Supplies and Equipment', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (18, 'Monetary Donation', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (19, 'Music', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (20, 'Office Space', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (21, 'Office Supplies and Equipment', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (22, 'Other', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (23, 'Personal Care', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (24, 'Sports', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (25, 'Storage', 'N/A');
INSERT INTO `givenkind`.`tblItemCategory` (`CategoryId`, `CategoryName`, `CategoryDescription`) VALUES (26, 'Towels', 'N/A');

COMMIT;

-- -----------------------------------------------------
-- Data for table `givenkind`.`tblStatusCategory`
-- -----------------------------------------------------
START TRANSACTION;
USE `givenkind`;
INSERT INTO `givenkind`.`tblStatusCategory` (`StatusCategoryId`,`StatusCategoryName`) VALUES (1, 'Donor Requested');
INSERT INTO `givenkind`.`tblStatusCategory` (`StatusCategoryId`,`StatusCategoryName`) VALUES (2, 'NP Requested');
INSERT INTO `givenkind`.`tblStatusCategory` (`StatusCategoryId`,`StatusCategoryName`) VALUES (3, 'Accepted');
INSERT INTO `givenkind`.`tblStatusCategory` (`StatusCategoryId`,`StatusCategoryName`) VALUES (4, 'In Transit');
COMMIT;