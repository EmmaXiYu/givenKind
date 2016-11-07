-- MySQL dump 10.13  Distrib 5.5.50, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: givenkind
-- ------------------------------------------------------
-- Server version	5.5.50-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Item` (
  `ItemType` varchar(31) NOT NULL,
  `itemId` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateExpires` datetime DEFAULT NULL,
  `itemName` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `user_UserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`itemId`),
  KEY `FK_fg9jk3uk8sr38s2p5x4r18oi7` (`user_UserId`),
  CONSTRAINT `FK_fg9jk3uk8sr38s2p5x4r18oi7` FOREIGN KEY (`user_UserId`) REFERENCES `tblUserLogin` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Item_tblItemCategory`
--

DROP TABLE IF EXISTS `Item_tblItemCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Item_tblItemCategory` (
  `Item_itemId` bigint(20) NOT NULL,
  `itemCategories_CategoryId` bigint(20) NOT NULL,
  KEY `FK_jrutguq794sjh75xvkwr2ae46` (`itemCategories_CategoryId`),
  KEY `FK_4jgv555jeifvtjrrf88gf8ym8` (`Item_itemId`),
  CONSTRAINT `FK_4jgv555jeifvtjrrf88gf8ym8` FOREIGN KEY (`Item_itemId`) REFERENCES `Item` (`itemId`),
  CONSTRAINT `FK_jrutguq794sjh75xvkwr2ae46` FOREIGN KEY (`itemCategories_CategoryId`) REFERENCES `tblItemCategory` (`CategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item_tblItemCategory`
--

LOCK TABLES `Item_tblItemCategory` WRITE;
/*!40000 ALTER TABLE `Item_tblItemCategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `Item_tblItemCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblActiveTransactions`
--

DROP TABLE IF EXISTS `tblActiveTransactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblActiveTransactions` (
  `ActiveTransactionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `DonorItemId` bigint(20) DEFAULT NULL,
  `DonorProfileId` bigint(20) DEFAULT NULL,
  `NpProfileId` bigint(20) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `StatusCategoryId` bigint(20) DEFAULT NULL,
  `WishItemId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ActiveTransactionId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblActiveTransactions`
--

LOCK TABLES `tblActiveTransactions` WRITE;
/*!40000 ALTER TABLE `tblActiveTransactions` DISABLE KEYS */;
INSERT INTO `tblActiveTransactions` VALUES (4,NULL,1,2,1,3,4),(5,NULL,1,2,2,1,4),(6,NULL,1,2,1,1,4),(7,11,17,2,3,2,NULL),(10,NULL,28,2,1,1,7),(14,18,26,30,1,4,NULL),(15,NULL,31,30,1,1,12),(16,NULL,1,2,1,4,14),(17,NULL,1,2,2,3,15),(20,22,1,2,2,2,NULL),(21,NULL,42,44,2,1,22),(25,25,50,53,2,3,NULL);
/*!40000 ALTER TABLE `tblActiveTransactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblAdminUserLogon`
--

DROP TABLE IF EXISTS `tblAdminUserLogon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblAdminUserLogon` (
  `UserId` bigint(20) NOT NULL,
  PRIMARY KEY (`UserId`),
  KEY `FK_t3vsg8mwp4vpr2ibr0f302wsf` (`UserId`),
  CONSTRAINT `FK_t3vsg8mwp4vpr2ibr0f302wsf` FOREIGN KEY (`UserId`) REFERENCES `tblUserLogin` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblAdminUserLogon`
--

LOCK TABLES `tblAdminUserLogon` WRITE;
/*!40000 ALTER TABLE `tblAdminUserLogon` DISABLE KEYS */;
INSERT INTO `tblAdminUserLogon` VALUES (18),(34);
/*!40000 ALTER TABLE `tblAdminUserLogon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblApprovalStatus`
--

DROP TABLE IF EXISTS `tblApprovalStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblApprovalStatus` (
  `StatusId` bigint(20) NOT NULL AUTO_INCREMENT,
  `StatusName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`StatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblApprovalStatus`
--

LOCK TABLES `tblApprovalStatus` WRITE;
/*!40000 ALTER TABLE `tblApprovalStatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `tblApprovalStatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblCompletedTransactions`
--

DROP TABLE IF EXISTS `tblCompletedTransactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblCompletedTransactions` (
  `CompletedTransactionId` bigint(20) NOT NULL AUTO_INCREMENT,
  `DonorItemId` bigint(20) DEFAULT NULL,
  `DonorProfileId` bigint(20) DEFAULT NULL,
  `NpProfileId` bigint(20) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `WishlistItemId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`CompletedTransactionId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblCompletedTransactions`
--

LOCK TABLES `tblCompletedTransactions` WRITE;
/*!40000 ALTER TABLE `tblCompletedTransactions` DISABLE KEYS */;
INSERT INTO `tblCompletedTransactions` VALUES (1,NULL,1,2,1,2),(2,NULL,1,2,1,3),(3,NULL,1,2,3,7),(4,8,1,2,2,NULL),(5,13,28,30,1,NULL),(6,19,28,29,1,NULL),(7,NULL,42,2,1,15),(8,25,50,54,5,NULL),(9,25,50,54,5,NULL);
/*!40000 ALTER TABLE `tblCompletedTransactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblDonorListItem`
--

DROP TABLE IF EXISTS `tblDonorListItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblDonorListItem` (
  `DonorListItemId` bigint(20) NOT NULL AUTO_INCREMENT,
  `ItemCondition` varchar(255) DEFAULT NULL,
  `DateExpires` datetime DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `FairMarketValue` double DEFAULT NULL,
  `ItemName` varchar(255) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `user_UserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`DonorListItemId`),
  KEY `FK_im3jjnusxn9vf45tukvmachg2` (`user_UserId`),
  CONSTRAINT `FK_im3jjnusxn9vf45tukvmachg2` FOREIGN KEY (`user_UserId`) REFERENCES `tblUserLogin` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblDonorListItem`
--

LOCK TABLES `tblDonorListItem` WRITE;
/*!40000 ALTER TABLE `tblDonorListItem` DISABLE KEYS */;
INSERT INTO `tblDonorListItem` VALUES (2,'New','2016-08-31 00:00:00','Test',0.01,'Test Item',1,1),(3,'New','2016-08-05 00:00:00','Test',10,'Sample Test',2,1),(4,'Good','2016-08-11 00:00:00','Things to cook with',5,'Test item 2',3,1),(7,'good','2016-08-12 00:00:00','test',0.05,'Test4',1,1),(8,'Good','2016-08-15 00:00:00','Livingroom Lamps',20,'Lamp',1,1),(10,'brh','2016-09-02 00:00:00','rgt',0,'erggerg4',1,16),(11,'Good','2016-09-01 00:00:00','Good but tray has faded',25,'High Chair',4,17),(12,'new ','2016-09-18 00:00:00','Crate & Barrel williamson chair set',22,'chairs',4,26),(14,'used','2016-09-18 00:00:00','ikea queen bed',250,'bed',1,26),(15,'Average','2016-10-18 00:00:00','4 chairs used for Dinnette table',100,'ChairTest1',4,31),(16,'new','2016-09-18 00:00:00','round table seats 6',45,'table',1,26),(17,'new','2016-09-18 00:00:00','table for 4',0,'table',1,26),(18,'avg','2016-09-24 00:00:00','TableTest new dinnette',250,'TableTest1',1,26),(21,'good','2016-10-11 00:00:00','32\"',0.05,'TV',10,1),(22,'good','2016-10-01 00:00:00','Floor lamp',5.03,'Lamp',20,1),(23,'good','2016-10-01 00:00:00','Fair',29.98,'Play Gym',11,1),(24,'like new','2016-10-01 00:00:00','Good',32,'table',30,1),(25,'Used','2017-01-17 00:00:00','Excellent',8,'Baby books',1,50),(26,'Used','2016-10-25 00:00:00','Excellent',200,'TV',14,50),(27,'New','2017-01-05 00:00:00','Good',20,'Tiles',10,51),(28,'used','2016-10-28 00:00:00','Fair',100,'Sofa',6,52),(29,'good','2016-10-31 00:00:00','Fair',100,'table',5,1),(30,'Used','2017-02-23 00:00:00','Fair',200,'Fridge',2,50),(31,'fair','2016-11-25 00:00:00','Good',300,'macbook',1,1),(32,'good','2016-10-31 00:00:00','Very Good',0,'chair',1,1),(33,'Used','2017-02-23 00:00:00','Excellent',500,'Fridge',2,50),(34,'Used','2016-11-30 00:00:00','Good',30,'Printers',5,51),(35,'good','2017-02-08 00:00:00','Excellent',250,'phone',2,NULL),(36,'good','2017-01-08 00:00:00','Excellent',50,'table',3,52),(37,'good','2016-11-24 00:00:00','Excellent',10,'Infant dress',23,52);
/*!40000 ALTER TABLE `tblDonorListItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblDonorListItem_tblItemCategory`
--

DROP TABLE IF EXISTS `tblDonorListItem_tblItemCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblDonorListItem_tblItemCategory` (
  `tblDonorListItem_DonorListItemId` bigint(20) NOT NULL,
  `itemCategories_CategoryId` bigint(20) NOT NULL,
  KEY `FK_oqnocmnlbmkx5jymxoaiww1kv` (`itemCategories_CategoryId`),
  KEY `FK_j8f4eeqwglmktbsy826tq5br6` (`tblDonorListItem_DonorListItemId`),
  CONSTRAINT `FK_j8f4eeqwglmktbsy826tq5br6` FOREIGN KEY (`tblDonorListItem_DonorListItemId`) REFERENCES `tblDonorListItem` (`DonorListItemId`),
  CONSTRAINT `FK_oqnocmnlbmkx5jymxoaiww1kv` FOREIGN KEY (`itemCategories_CategoryId`) REFERENCES `tblItemCategory` (`CategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblDonorListItem_tblItemCategory`
--

LOCK TABLES `tblDonorListItem_tblItemCategory` WRITE;
/*!40000 ALTER TABLE `tblDonorListItem_tblItemCategory` DISABLE KEYS */;
INSERT INTO `tblDonorListItem_tblItemCategory` VALUES (3,1),(3,2),(3,3),(4,3),(7,2),(8,14),(10,3),(2,2),(11,5),(11,14),(12,14),(14,14),(15,14),(16,13),(17,13),(18,2),(26,11),(27,3),(27,7),(27,18),(28,14),(29,13),(31,11),(32,14),(30,3),(33,3),(34,21),(21,3),(22,11),(23,5),(24,6),(25,1),(25,2),(25,3),(25,4),(25,5),(25,6),(35,1),(35,2),(35,3),(35,4),(35,7),(35,11),(36,12),(36,13),(36,14),(36,15),(37,4),(37,5),(37,6),(37,7),(37,8),(37,9);
/*!40000 ALTER TABLE `tblDonorListItem_tblItemCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblDonorUserLogon`
--

DROP TABLE IF EXISTS `tblDonorUserLogon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblDonorUserLogon` (
  `UserId` bigint(20) NOT NULL,
  PRIMARY KEY (`UserId`),
  KEY `FK_2grv5xo1brevtxs9hkfkdiyih` (`UserId`),
  CONSTRAINT `FK_2grv5xo1brevtxs9hkfkdiyih` FOREIGN KEY (`UserId`) REFERENCES `tblUserLogin` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblDonorUserLogon`
--

LOCK TABLES `tblDonorUserLogon` WRITE;
/*!40000 ALTER TABLE `tblDonorUserLogon` DISABLE KEYS */;
INSERT INTO `tblDonorUserLogon` VALUES (1),(8),(12),(14),(16),(17),(21),(26),(28),(31),(32),(33),(36),(38),(41),(42),(45),(47),(50),(51),(52),(56);
/*!40000 ALTER TABLE `tblDonorUserLogon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblItemCategory`
--

DROP TABLE IF EXISTS `tblItemCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblItemCategory` (
  `CategoryId` bigint(20) NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(255) DEFAULT NULL,
  `CategoryDescription` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CategoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblItemCategory`
--

LOCK TABLES `tblItemCategory` WRITE;
/*!40000 ALTER TABLE `tblItemCategory` DISABLE KEYS */;
INSERT INTO `tblItemCategory` VALUES (1,'Airline Miles and Hotel Points','N/A'),(2,'Animal/Wildlife','N/A'),(3,'Appliances','N/A'),(4,'Arts and Craft Materials','N/A'),(5,'Baby Items','N/A'),(6,'Books','N/A'),(7,'Building Materials','N/A'),(8,'Clothing-Adults','N/A'),(9,'Clothing-Children','N/A'),(10,'Decor','N/A'),(11,'Electronics','N/A'),(12,'Food','N/A'),(13,'Furniture-Office','N/A'),(14,'Furniture-Residential','N/A'),(15,'Kitchen','N/A'),(16,'Linens/Bedding','N/A'),(17,'Medical Supplies and Equipment','N/A'),(18,'Monetary Donation','N/A'),(19,'Music','N/A'),(20,'Office Space','N/A'),(21,'Office Supplies and Equipment','N/A'),(22,'Other','N/A'),(23,'Personal Care','N/A'),(24,'Sports','N/A'),(25,'Storage','N/A'),(26,'Towels','N/A');
/*!40000 ALTER TABLE `tblItemCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblNonProfitCategory`
--

DROP TABLE IF EXISTS `tblNonProfitCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblNonProfitCategory` (
  `CategoryId` bigint(20) NOT NULL AUTO_INCREMENT,
  `CategoryDescription` varchar(255) DEFAULT NULL,
  `CategoryName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CategoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblNonProfitCategory`
--

LOCK TABLES `tblNonProfitCategory` WRITE;
/*!40000 ALTER TABLE `tblNonProfitCategory` DISABLE KEYS */;
INSERT INTO `tblNonProfitCategory` VALUES (1,'N/A','Animals'),(2,'N/A','Arts, Culture and Humanties'),(3,'N/A','Children'),(4,'N/A','Education'),(5,'N/A','Environment'),(6,'N/A','Health'),(7,'N/A','Human Services'),(8,'N/A','International Benefit'),(9,'N/A','Public Benefit'),(10,'N/A','Religious/Spiritual'),(11,'N/A','Other');
/*!40000 ALTER TABLE `tblNonProfitCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblNonProfitUserLogon`
--

DROP TABLE IF EXISTS `tblNonProfitUserLogon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblNonProfitUserLogon` (
  `UserId` bigint(20) NOT NULL,
  PRIMARY KEY (`UserId`),
  KEY `FK_sqa9whu70dtqgs3dshgq3crsd` (`UserId`),
  CONSTRAINT `FK_sqa9whu70dtqgs3dshgq3crsd` FOREIGN KEY (`UserId`) REFERENCES `tblUserLogin` (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblNonProfitUserLogon`
--

LOCK TABLES `tblNonProfitUserLogon` WRITE;
/*!40000 ALTER TABLE `tblNonProfitUserLogon` DISABLE KEYS */;
INSERT INTO `tblNonProfitUserLogon` VALUES (2),(3),(4),(13),(15),(18),(23),(24),(25),(27),(29),(30),(34),(35),(37),(39),(40),(43),(44),(46),(48),(49),(53),(54),(55);
/*!40000 ALTER TABLE `tblNonProfitUserLogon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblNonProfitUserLogon_tblNonProfitCategory`
--

DROP TABLE IF EXISTS `tblNonProfitUserLogon_tblNonProfitCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblNonProfitUserLogon_tblNonProfitCategory` (
  `tblNonProfitUserLogon_UserId` bigint(20) NOT NULL,
  `categories_CategoryId` bigint(20) NOT NULL,
  PRIMARY KEY (`tblNonProfitUserLogon_UserId`,`categories_CategoryId`),
  UNIQUE KEY `UK_18oqqarn202lhuwpjl3ox6ccv` (`categories_CategoryId`),
  KEY `FK_18oqqarn202lhuwpjl3ox6ccv` (`categories_CategoryId`),
  KEY `FK_rtkt8h81yxhshu7kde01yl0q7` (`tblNonProfitUserLogon_UserId`),
  CONSTRAINT `FK_rtkt8h81yxhshu7kde01yl0q7` FOREIGN KEY (`tblNonProfitUserLogon_UserId`) REFERENCES `tblNonProfitUserLogon` (`UserId`),
  CONSTRAINT `FK_18oqqarn202lhuwpjl3ox6ccv` FOREIGN KEY (`categories_CategoryId`) REFERENCES `tblNonProfitCategory` (`CategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblNonProfitUserLogon_tblNonProfitCategory`
--

LOCK TABLES `tblNonProfitUserLogon_tblNonProfitCategory` WRITE;
/*!40000 ALTER TABLE `tblNonProfitUserLogon_tblNonProfitCategory` DISABLE KEYS */;
INSERT INTO `tblNonProfitUserLogon_tblNonProfitCategory` VALUES (2,1),(3,2),(3,3),(3,4),(15,6),(13,8),(4,11);
/*!40000 ALTER TABLE `tblNonProfitUserLogon_tblNonProfitCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblPasswordReset`
--

DROP TABLE IF EXISTS `tblPasswordReset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblPasswordReset` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdAt` datetime DEFAULT NULL,
  `uniqueResetKey` varchar(255) DEFAULT NULL,
  `tblUserLogin_UserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_3xd9s5vf2oet0n9yqxrr07752` (`tblUserLogin_UserId`),
  CONSTRAINT `FK_3xd9s5vf2oet0n9yqxrr07752` FOREIGN KEY (`tblUserLogin_UserId`) REFERENCES `tblUserLogin` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblPasswordReset`
--

LOCK TABLES `tblPasswordReset` WRITE;
/*!40000 ALTER TABLE `tblPasswordReset` DISABLE KEYS */;
INSERT INTO `tblPasswordReset` VALUES (2,'2016-10-10 02:47:25','65b010d1-ca92-4a72-b30c-189ec4de33f6',2),(5,'2016-10-10 04:38:22','30def4cd-0d44-4a9b-bdd2-8e48a4c27dbc',4),(6,'2016-10-10 04:38:24','75263121-0b8c-49fa-a4bd-51a923c1441a',4),(7,'2016-10-10 05:24:56','24d2dd2a-c625-4903-b9b7-7586b202b7d1',4);
/*!40000 ALTER TABLE `tblPasswordReset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblProfile`
--

DROP TABLE IF EXISTS `tblProfile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblProfile` (
  `ProfileId` bigint(20) NOT NULL AUTO_INCREMENT,
  `EIN` varchar(255) DEFAULT NULL,
  `ModifiedBy` varchar(255) DEFAULT NULL,
  `AddressLine1` varchar(255) DEFAULT NULL,
  `AddressLine2` varchar(255) DEFAULT NULL,
  `ApprovalStatus` varchar(255) DEFAULT NULL,
  `City` varchar(255) DEFAULT NULL,
  `ContactEmail` varchar(255) DEFAULT NULL,
  `Country` varchar(255) DEFAULT NULL,
  `FullName` varchar(255) DEFAULT NULL,
  `IsPickupServiceAvailable` tinyint(1) DEFAULT NULL,
  `LogoLocation` varchar(255) DEFAULT NULL,
  `MissionStatement` varchar(255) DEFAULT NULL,
  `ModifiedDate` datetime DEFAULT NULL,
  `OrganizationName` varchar(255) DEFAULT NULL,
  `Phone1` varchar(255) DEFAULT NULL,
  `Phone2` varchar(255) DEFAULT NULL,
  `Phone3` varchar(255) DEFAULT NULL,
  `TravelDistance` double DEFAULT NULL,
  `WebSiteUrl` varchar(255) DEFAULT NULL,
  `ZipCode` varchar(255) DEFAULT NULL,
  `state_StateId` bigint(20) DEFAULT NULL,
  `user_UserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ProfileId`),
  KEY `FK_plgx32oarc19chnv06vhfkkik` (`state_StateId`),
  KEY `FK_sbb8hdiyjxilbpis7s6omqu6x` (`user_UserId`),
  CONSTRAINT `FK_sbb8hdiyjxilbpis7s6omqu6x` FOREIGN KEY (`user_UserId`) REFERENCES `tblUserLogin` (`UserId`),
  CONSTRAINT `FK_plgx32oarc19chnv06vhfkkik` FOREIGN KEY (`state_StateId`) REFERENCES `tblState` (`StateId`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblProfile`
--

LOCK TABLES `tblProfile` WRITE;
/*!40000 ALTER TABLE `tblProfile` DISABLE KEYS */;
INSERT INTO `tblProfile` VALUES (1,NULL,'iamadonor@test.com','Test Address2','','Approved','test','iamadonor@test.com',NULL,'Test User',NULL,NULL,NULL,'2016-08-03 19:49:50',NULL,'1234',NULL,NULL,NULL,'','43235',35,1),(2,'123555555','iamanp@test.com','test','','Pending Approval','test','test@test.com',NULL,'test person ',1,NULL,'test statement','2016-08-04 15:23:35','test','1238888888',NULL,NULL,45,'test.com','43235',35,2),(3,'123456789','iamanptest@test.com','test','','Pending Approval','New York','Test@test.com',NULL,'Test',1,NULL,'Test','2016-08-04 18:35:12','Test Org','123123111',NULL,NULL,1,'Test','10000',32,3),(4,'123456789','mikefadhl@gmail.com','2323 NW 188th Ave','','Pending Approval','Hillsboro','mikefadhl@gmail.com',NULL,'Me',0,NULL,'MeMyselfAndINon','2016-08-28 15:22:06','MeMyselfAndINon','9712174809',NULL,NULL,NULL,'not yet','97124',37,4),(8,NULL,'EmmaTest@gmail.com','2272 s Archer Apt1','Chicago','Approved','Chicago',NULL,NULL,'EmmaTest@gmail.com',NULL,NULL,NULL,'2016-08-29 21:08:47',NULL,NULL,NULL,NULL,NULL,NULL,'60616',13,8),(12,NULL,'mikedonor@gmail.com','2323 NW 188th Ave','','Approved','Hillsboro',NULL,NULL,'MIke',NULL,NULL,NULL,'2016-08-30 01:14:10',NULL,NULL,NULL,NULL,NULL,NULL,'97124',37,12),(13,'123456789','xiyu332012@gmail.com','2272 s Archer Apt1','Chicago','Pending Approval','Chicago','xiyu332012@gmail.com',NULL,'Emma Xi',1,NULL,'test Organization','2016-08-30 04:43:43','test Organization','1234567898',NULL,NULL,5,'test Organization.com','60616',13,13),(14,NULL,'kittyp@test.com','123 Try this lane','','Approved','Development',NULL,NULL,'Emily',NULL,NULL,NULL,'2016-08-31 03:29:20',NULL,NULL,NULL,NULL,NULL,NULL,'60089',3,14),(15,'121234567','testnp@test.com','1234 try this ','','Pending Approval','Buffalo Grove','Emily.petway@gmail.com',NULL,'Emily P',0,NULL,'to test this site','2016-08-31 03:34:05','testtest','770-361-0802',NULL,NULL,NULL,'www.test.com','60089',13,15),(16,NULL,'testDonor@gmail.com','2272 s Archer Apt1','Chicago','Approved','Chicago',NULL,NULL,'testDonor@gmail.com',NULL,NULL,NULL,'2016-09-02 19:57:13',NULL,NULL,NULL,NULL,NULL,NULL,'60616',13,16),(17,NULL,'maddy@petway.com','1234 our house','','Approved','BG',NULL,NULL,'Maddy',NULL,NULL,NULL,'2016-09-10 22:31:50',NULL,NULL,NULL,NULL,NULL,NULL,'60089',13,17),(18,'N/A','admin@gmail.com','admin address','Chicago','Approved','Chicago','admin@gmail.com','N/A','admin',NULL,NULL,NULL,'2016-08-16 19:41:53',NULL,NULL,NULL,NULL,NULL,NULL,'60616',13,18),(21,NULL,'padonor@test.com','17262 erfjfj','','Approved','Pittsburgh',NULL,NULL,'padonor',NULL,NULL,NULL,'2016-09-17 17:26:26',NULL,NULL,NULL,NULL,NULL,NULL,'15237',38,21),(23,'123432156','panp@test.com','39465 Paseo Padre Parkway','Suite 1200','Pending Approval','Fremont','panp@test.com',NULL,'CHris',0,NULL,'PANP','2016-09-17 17:56:44','PANP','2342342345',NULL,NULL,NULL,'PNP','94538',5,23),(24,'123456789','NYNP@test.com','17262 erfjfj','','Pending Approval','Pittsburgh','nynp@test.com',NULL,'test',0,NULL,'sdf','2016-09-17 21:23:35','Hurix','1234567890',NULL,NULL,45,'dsfs','15237',38,24),(25,'123457878','MINP@test.com','34 perry highway','','Pending Approval','winston','MINP@test.com',NULL,'MN',0,NULL,'fdgdghdcfg','2016-09-17 21:27:03','dfgd','3454546577',NULL,NULL,78,'www.MNC.com','15237',21,25),(26,NULL,'dom0198@gmail.com','1509 N Campbell Avenue','Unit 2N','Approved','Chicago','dom0198@gmail.com',NULL,'Domonique McClendon Hollins',NULL,NULL,NULL,'2016-09-18 16:47:02',NULL,'6463200793',NULL,NULL,NULL,'','60622',13,26),(27,'123456789','nkshop88@gmail.com','123 Learning Street','','Pending Approval','Chicago','nkshop88@gmail.com',NULL,'Natalie Keable',1,NULL,'We collect and donate school supplies to local families in need.','2016-09-18 16:49:51','Back to School','8477787842',NULL,NULL,20,'backtoschool.com','60661',13,27),(28,NULL,'pswartzer@gmail.com','3940 North Southport Avenue','#2F','Approved','Chicago','pswartzer@gmail.com',NULL,'Patrick Swartzer',NULL,NULL,NULL,'2016-09-18 16:50:03',NULL,'7734398640',NULL,NULL,NULL,'Www.facebook.com','60613',13,28),(29,'135246790','toys4kids@gmail.com','123 Kidway Drive','','Pending Approval','Seattle','imagineme@gmail.com',NULL,'Imagine Me',0,NULL,'providing items for kids to expand their imagination and creativity','2016-09-18 16:50:11','Toys4Kids','7909746421',NULL,NULL,25,'www.t4k.org','12345',47,29),(30,'123456789','katie@petway.com','katie\'s house','','Pending Approval','Buffalo Grove','katie@petway.com',NULL,'Katie',1,NULL,'Katie is awesome','2016-09-18 16:50:52','Katie','1231231234',NULL,NULL,50,'www.sweetkatiep.com','60089',13,30),(31,NULL,'prp0121@yahoo.com','1234 My Street','','Approved','Palatine',NULL,NULL,'Pranav NDcafe1',NULL,NULL,NULL,'2016-09-18 16:52:42',NULL,NULL,NULL,NULL,NULL,NULL,'12345',13,31),(32,NULL,'sweetiepygemini@aol.com','456 Main Street','','Approved','Chicago',NULL,NULL,'Natalie Keable',NULL,NULL,NULL,'2016-09-18 17:20:11',NULL,NULL,NULL,NULL,NULL,NULL,'60661',13,32),(33,NULL,'testLogin@gmail.com','2272 s Archer Apt1','Chicago','Approved','Chicago',NULL,NULL,'testLogin@gmail.com',NULL,NULL,NULL,'2016-09-19 19:32:08',NULL,NULL,NULL,NULL,NULL,NULL,'60616',13,33),(34,'123456789','admin@admin.com','admin@admin.com','','Pending Approval','Chicago','admin@admin.com',NULL,'admin@admin.com',0,NULL,'admin@admin.com','2016-09-19 20:26:15','admin@admin.com','1234567898',NULL,NULL,NULL,'admin@admin.com','60616',13,34),(35,'123456789','goodnp@test.com','17262 erfjfj','','Pending Approval','Pittsburgh','goodnp@test.com',NULL,'test',0,NULL,'fdgdg','2016-09-21 02:57:20','Hurix','2342342345',NULL,NULL,56,'www.MNC.com','15237',38,35),(36,NULL,'gooddonor@test.com','1305 Cherrington Pkwy  Building 200','','Approved','charlotte','gooddonor@test.com',NULL,'good',NULL,NULL,NULL,'2016-09-21 02:59:47',NULL,'2342342345',NULL,NULL,NULL,'','34567',26,36),(37,'123456789','RichNp@test.com','123 west view','','Pending Approval','richmond','RichNp@test.com',NULL,'Rich',0,NULL,'sdfdfd','2016-09-22 18:47:38','SSS','2342342345',NULL,NULL,NULL,'www.richnp.com','23456',46,37),(38,NULL,'Emily@givenkind.org','1 lets do this','','Approved','Gurnee','Emily@givenkind.org',NULL,'Emily',NULL,NULL,NULL,'2016-09-27 03:59:17',NULL,'1231234123',NULL,NULL,NULL,NULL,'60031',13,38),(39,'456789034','nphome@test.com','17262 erfjfj','','Pending Approval','Pittsburgh','nphome@test.com',NULL,'test',1,NULL,'good','2016-09-29 15:57:43','New','2343433433',NULL,NULL,30,'www.nphome.com','23451',38,39),(40,'234561990','npoffice@test.com','23 teal road','','Pending Approval','richmond','npoffice@test.com',NULL,'mike',0,NULL,'goodwell','2016-09-29 16:00:27','old','2356758978',NULL,NULL,NULL,'www.npoffice.com','34567',46,40),(41,NULL,'donorhome@test.com','78 cherry prky','','Approved','charlotte','donorhome@test.com',NULL,'donor',NULL,NULL,NULL,'2016-09-29 16:02:09',NULL,'2344545421',NULL,NULL,NULL,NULL,'89999',26,41),(42,NULL,'donor1@aa','450 main street','','Approved','sunnyvale','donor1@aa',NULL,'donor1',NULL,NULL,NULL,'2016-10-07 01:24:18',NULL,'1234567891',NULL,NULL,NULL,NULL,'94085',5,42),(43,'123987456','np@test','123 first street','','Pending Approval','Nashua','test1@test',NULL,'test1',1,NULL,'test123','2016-10-08 04:32:43','test','89012345678',NULL,NULL,10,'test','03060',29,43),(44,'123456789','np1@test','12 first street','','Pending Approval','nashua','contact1@test',NULL,'contact1',1,NULL,'mission','2016-10-08 04:45:22','test','12345609788888888888',NULL,NULL,NULL,'test','03060',29,44),(45,NULL,'michael.fadhl@nike.com','2323 NW 188th Ave','','Approved','Hillsboro','michael.fadhl@nike.com',NULL,'Michael',NULL,NULL,NULL,'2016-10-10 02:56:35',NULL,'9712174809',NULL,NULL,NULL,NULL,'97124',37,45),(46,'123456789','np2@test','#123 Main *****street','','Pending Approval','######','test123@test',NULL,'test1',1,NULL,'test','2016-10-14 02:43:55','test','2488751234444444444',NULL,NULL,12,'test.com','12345',3,46),(47,NULL,'donor12@aa','36789 san pedro dr','','Approved','fremont','donor12@aa',NULL,'donor',NULL,NULL,NULL,'2016-10-15 00:39:41',NULL,'21444444444444444444',NULL,NULL,NULL,NULL,'94555',5,47),(48,'131232132','dfgdfg@dfgdfg.com','1707 Teal Trace','','Pending Approval','Pittsburgh','dfgdfg@dfgdfg.com',NULL,'dfgdfg',0,NULL,'xfgdf','2016-10-16 01:09:28','fgdgd','123242423433534',NULL,NULL,NULL,'','12345',38,48),(49,'123456778','newnp@gmail.com','123 west view','','Pending Approval','richmond','newnp@gmail.com',NULL,'Rich',0,NULL,'fdg','2016-10-16 02:00:19','newnp','2483459971',NULL,NULL,NULL,'','12345',46,49),(50,NULL,'donor1@test.com','11 Main street','','Approved','phoenix','donor1@test.com',NULL,'donor1',NULL,NULL,NULL,'2016-10-22 04:44:02',NULL,'1234567888',NULL,NULL,NULL,NULL,'85283',3,50),(51,NULL,'donor2@test.com',' Second street','Apt#1256','Approved','denver','donor2@test.com',NULL,'Donor2',NULL,NULL,NULL,'2016-10-22 05:28:04',NULL,'34512378960',NULL,NULL,NULL,NULL,'80201',6,51),(52,NULL,'donor3@test.com','Address 1','','Approved','Jacksonville','donor3@test.com',NULL,'donor3',NULL,NULL,NULL,'2016-10-22 05:41:07',NULL,'234890123678',NULL,NULL,NULL,NULL,'32099',9,52),(53,'123555555','np1@test.com','123 Main street','','Pending Approval','minneapolis','nonprofit1@test.com',NULL,'Test Person1',0,NULL,'Mission Statement','2016-10-23 17:23:47','Non Profit1','21356734908',NULL,NULL,NULL,'','55450',23,53),(54,'213456789','np2@test.com','fifth street','','Pending Approval','Nashville','contact2@test.com',NULL,'contact2',1,NULL,'Mission ','2016-10-23 18:01:46','Non Profit2','3421894567',NULL,NULL,100,'','37115',42,54),(55,'231245568','np3@test.com','Main sreet','','Pending Approval','Cleveland','contact3@test.com',NULL,'Contact3',1,NULL,'Mission','2016-10-23 18:31:29','Non Profit 3','3412894522',NULL,NULL,250,'www.website.com','44105',35,55),(56,NULL,'testuserfntestuserln@gmail.com','123 main street','','Approved','tempe','testuserfntestuserln@gmail.com',NULL,'qwe',NULL,NULL,NULL,'2016-10-31 17:41:54',NULL,'54978',NULL,NULL,NULL,NULL,'85283',3,56);
/*!40000 ALTER TABLE `tblProfile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblProfile_tblNonProfitCategory`
--

DROP TABLE IF EXISTS `tblProfile_tblNonProfitCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblProfile_tblNonProfitCategory` (
  `tblProfile_ProfileId` bigint(20) NOT NULL,
  `categories_CategoryId` bigint(20) NOT NULL,
  KEY `FK_fff9g6g7wbankm6mo11j7a6gv` (`categories_CategoryId`),
  KEY `FK_al3p394oaopviv6vg04xn11nn` (`tblProfile_ProfileId`),
  CONSTRAINT `FK_al3p394oaopviv6vg04xn11nn` FOREIGN KEY (`tblProfile_ProfileId`) REFERENCES `tblProfile` (`ProfileId`),
  CONSTRAINT `FK_fff9g6g7wbankm6mo11j7a6gv` FOREIGN KEY (`categories_CategoryId`) REFERENCES `tblNonProfitCategory` (`CategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblProfile_tblNonProfitCategory`
--

LOCK TABLES `tblProfile_tblNonProfitCategory` WRITE;
/*!40000 ALTER TABLE `tblProfile_tblNonProfitCategory` DISABLE KEYS */;
INSERT INTO `tblProfile_tblNonProfitCategory` VALUES (23,7),(24,2),(24,3),(25,10),(27,3),(27,4),(27,7),(30,1),(30,2),(30,3),(29,3),(34,11),(35,2),(35,3),(37,3),(39,1),(39,2),(40,2),(43,1),(43,3),(43,5),(44,2),(44,3),(44,4),(46,2),(46,4),(48,3),(48,4),(49,3),(55,3),(55,4),(55,5),(54,4),(54,6),(54,11),(2,1),(2,2),(2,3),(53,5),(53,6),(53,7);
/*!40000 ALTER TABLE `tblProfile_tblNonProfitCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblRole`
--

DROP TABLE IF EXISTS `tblRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblRole` (
  `RoleId` bigint(20) NOT NULL AUTO_INCREMENT,
  `RoleDescription` varchar(255) DEFAULT NULL,
  `RoleName` varchar(255) DEFAULT NULL,
  `user_UserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`RoleId`),
  KEY `FK_dpnvtew05lqyulp23v0opdbbk` (`user_UserId`),
  CONSTRAINT `FK_dpnvtew05lqyulp23v0opdbbk` FOREIGN KEY (`user_UserId`) REFERENCES `tblUserLogin` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblRole`
--

LOCK TABLES `tblRole` WRITE;
/*!40000 ALTER TABLE `tblRole` DISABLE KEYS */;
INSERT INTO `tblRole` VALUES (1,'Admin','ROLE_ADMIN',1),(2,'NonProfit','ROLE_NONPROFIT',2),(3,'Donor','ROLE_DONOR',3),(4,'Volunteer','ROLE_VOLUNTEER',4);
/*!40000 ALTER TABLE `tblRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblState`
--

DROP TABLE IF EXISTS `tblState`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblState` (
  `StateId` bigint(20) NOT NULL AUTO_INCREMENT,
  `StateAbbr` varchar(255) DEFAULT NULL,
  `StateName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`StateId`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblState`
--

LOCK TABLES `tblState` WRITE;
/*!40000 ALTER TABLE `tblState` DISABLE KEYS */;
INSERT INTO `tblState` VALUES (1,'AL','Alabama'),(2,'AK','Alaska'),(3,'AZ','Arizona'),(4,'AR','Arkansas'),(5,'CA','California'),(6,'CO','Colorado'),(7,'CT','Connecticut'),(8,'DE','Delaware'),(9,'FL','Florida'),(10,'GA','Georgia'),(11,'HI','Hawaii'),(12,'ID','Idaho'),(13,'IL','Illinois'),(14,'IN','Indiana'),(15,'IA','Iowa'),(16,'KS','Kansas'),(17,'KY','Kentucky'),(18,'LA','Louisiana'),(19,'ME','Maine'),(20,'MD','Maryland'),(21,'MA','Massachusetts'),(22,'MI','Michigan'),(23,'MN','Minnesota'),(24,'MS','Mississippi'),(25,'MO','Missouri'),(26,'MT','Montana'),(27,'NE','Nebraska'),(28,'NV','Nevada'),(29,'NH','New Hampshire'),(30,'NJ','New Jersey'),(31,'NM','New Mexico'),(32,'NY','New York'),(33,'NC','North Carolina'),(34,'ND','North Dakota'),(35,'OH','Ohio'),(36,'OK','Oklahoma'),(37,'OR','Oregon'),(38,'PA','Pennsylvania'),(39,'RI','Rhode Island'),(40,'SC','South Carolina'),(41,'SD','South Dakota'),(42,'TN','Tennessee'),(43,'TX','Texas'),(44,'UT','Utah'),(45,'VT','Vermont'),(46,'VA','Virginia'),(47,'WA','Washington'),(48,'WV','West Virginia'),(49,'WI','Wisconsin'),(50,'WY','Wyoming');
/*!40000 ALTER TABLE `tblState` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblStatusCategory`
--

DROP TABLE IF EXISTS `tblStatusCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblStatusCategory` (
  `StatusCategoryId` bigint(20) NOT NULL AUTO_INCREMENT,
  `StatusCategoryName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`StatusCategoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblStatusCategory`
--

LOCK TABLES `tblStatusCategory` WRITE;
/*!40000 ALTER TABLE `tblStatusCategory` DISABLE KEYS */;
INSERT INTO `tblStatusCategory` VALUES (1,'Donor Requested'),(2,'NP Requested'),(3,'Accepted'),(4,'In Transit');
/*!40000 ALTER TABLE `tblStatusCategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblUserLogin`
--

DROP TABLE IF EXISTS `tblUserLogin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblUserLogin` (
  `UserType` varchar(31) NOT NULL,
  `UserId` bigint(20) NOT NULL AUTO_INCREMENT,
  `CreatedDate` datetime DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT NULL,
  `LastLoggedInDate` datetime DEFAULT NULL,
  `LoginId` varchar(255) DEFAULT NULL,
  `ModifiedBy` varchar(255) DEFAULT NULL,
  `ModifiedDate` datetime DEFAULT NULL,
  `PasswordHash` varchar(255) DEFAULT NULL,
  `profile_ProfileId` bigint(20) DEFAULT NULL,
  `role_RoleId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`UserId`),
  KEY `FK_kmjlcda4qusgxwubjb89mf8hv` (`profile_ProfileId`),
  KEY `FK_qgpm7fm3l9vixfggt2j5rbhyg` (`role_RoleId`),
  CONSTRAINT `FK_qgpm7fm3l9vixfggt2j5rbhyg` FOREIGN KEY (`role_RoleId`) REFERENCES `tblRole` (`RoleId`),
  CONSTRAINT `FK_kmjlcda4qusgxwubjb89mf8hv` FOREIGN KEY (`profile_ProfileId`) REFERENCES `tblProfile` (`ProfileId`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblUserLogin`
--

LOCK TABLES `tblUserLogin` WRITE;
/*!40000 ALTER TABLE `tblUserLogin` DISABLE KEYS */;
INSERT INTO `tblUserLogin` VALUES ('D',1,'2016-08-03 19:49:50',1,'2016-08-03 19:49:50','iamadonor@test.com','iamadonor@test.com',NULL,'$2a$12$/KA7aBVy01v2GWKMqjYnLemZRV4XV5BgJufk6Rfp6YoBlNFwk.PxC',1,3),('N',2,'2016-08-04 15:23:35',1,'2016-08-04 15:23:35','iamanp@test.com','iamanp@test.com',NULL,'$2a$12$AdZkCyIitbz95qpAavlS6.e9hlBx2LZeiQ.yyJNEGi88mZbjTImY.',2,2),('N',3,'2016-08-04 18:35:12',1,'2016-08-04 18:35:12','iamanptest@test.com','iamanptest@test.com',NULL,'$2a$12$a1j/ocWaPMvGxdAJ1OHW3OBaW3n855solb8HHfdil6h.aP58vaiym',3,NULL),('N',4,'2016-08-28 15:22:06',1,'2016-08-28 15:22:06','mikefadhl@gmail.com','mikefadhl@gmail.com',NULL,'$2a$12$gyDX891su3P4p75EblpQIOGNAPoG5xc7h/H01pXwFX4swbO0A/eeC',4,NULL),('D',8,'2016-08-29 21:08:48',1,'2016-08-29 21:08:48','EmmaTest@gmail.com','EmmaTest@gmail.com',NULL,'$2a$12$tJK98Y3PQkzYHzOtt0bKLeUsnuewh5FB/UB10xuoqQk26dtRteUs2',8,3),('D',12,'2016-08-30 01:14:11',1,'2016-08-30 01:14:11','mikedonor@gmail.com','mikedonor@gmail.com',NULL,'$2a$12$vBM1Gwy5pdlz1SC6rqddEuViBArmk7sEv10FbaJVGGXdYTFkprMMO',12,3),('N',13,'2016-08-30 04:43:43',1,'2016-08-30 04:43:43','xiyu332012@gmail.com','xiyu332012@gmail.com',NULL,'$2a$12$KF2H2sAo4UBxJi82hqEHDO6Z9j1AQBxvmjxMK6ePJqhyF4X/6.e9i',13,2),('D',14,'2016-08-31 03:29:21',1,'2016-08-31 03:29:21','kittyp@test.com','kittyp@test.com',NULL,'$2a$12$36Oefq5AxcsQjWcfFXDjjeBG6n3tLf9RcYs5PNb.GpYSPEuI7qsPq',14,3),('N',15,'2016-08-31 03:34:05',1,'2016-08-31 03:34:05','testnp@test.com','testnp@test.com',NULL,'$2a$12$YRGk5TkVbEu7v3ZGx25xjuOlnoOc50vzZ9PlcaSMTb4UEZti8E/v.',15,2),('D',16,'2016-09-02 19:57:14',1,'2016-09-02 19:57:14','testDonor@gmail.com','testDonor@gmail.com',NULL,'$2a$12$uQpeCDyuzdmGNSmSQqW8UuM1Ow2WiyUNla7qqy5b4wwE6r2JJBEYa',16,3),('D',17,'2016-09-10 22:31:51',1,'2016-09-10 22:31:51','maddy@petway.com','maddy@petway.com',NULL,'$2a$12$64MD8RyTq3QvyE89ZsIOmuE1eYLj7BhIEJ4NaOFBCn8lYqLh/5cDe',17,3),('A',18,'2016-08-16 19:41:53',1,'2016-08-16 19:41:53','admin@gmail.com','admin@gmail.com','0000-00-00 00:00:00','$2a$12$wP56YheE4GIg/ihLGmJS7eluyYC76S6BmxGKogwUcAUfxwjID1yCO',18,1),('D',21,'2016-09-17 17:26:27',1,'2016-09-17 17:26:27','padonor@test.com','padonor@test.com',NULL,'$2a$12$ks7r5lAo/okMjecQmZ0YX.oYnmvV3e3jKo.ZlUpO9JdFd8ylmP67K',21,3),('N',23,'2016-09-17 17:56:44',1,'2016-09-17 17:56:44','panp@test.com','panp@test.com',NULL,'$2a$12$drmVGWtEGnE1Ehg9W/g/Ze/VoHXmHeHxFozLCNGP/LgDrtk380ro2',23,2),('N',24,'2016-09-17 21:23:35',1,'2016-09-17 21:23:35','NYNP@test.com','NYNP@test.com',NULL,'$2a$12$x5xyBcAb3tLjAAom4xwC7ORm4X99ubzj5XI4miJ9bHMg4RizzGmo6',24,2),('N',25,'2016-09-17 21:27:03',1,'2016-09-17 21:27:03','MINP@test.com','MINP@test.com',NULL,'$2a$12$Zfz2lSQXHKxPXm85AKkpUOoLm1X95NHvD5L8LuVjb/D2GE7oRLl.e',25,2),('D',26,'2016-09-18 16:47:03',1,'2016-09-18 16:47:03','dom0198@gmail.com','dom0198@gmail.com',NULL,'$2a$12$z2vCIObbshIfTwLut.D3BuJKfIHb6hbpKPldSqWP51/Ht20DfpmB2',26,3),('N',27,'2016-09-18 16:49:51',1,'2016-09-18 16:49:51','nkshop88@gmail.com','nkshop88@gmail.com',NULL,'$2a$12$39IWrFZJesPxDCx47cNHsOhf55OFfnbuXl8oxTj8qhr1sjN6ymfjS',27,2),('D',28,'2016-09-18 16:50:04',1,'2016-09-18 16:50:04','pswartzer@gmail.com','pswartzer@gmail.com',NULL,'$2a$12$k0JLU/o8x6mR2aP/He9Cn.4GCoVZeEi1Tuiku2J0GHYFW5tLHkuQG',28,3),('N',29,'2016-09-18 16:50:11',1,'2016-09-18 16:50:11','toys4kids@gmail.com','toys4kids@gmail.com',NULL,'$2a$12$OMGimSS8gF8Y.GzJ9R15LeXSrMbqMEMSfui/6iE87sUGqaYqIVuJG',29,2),('N',30,'2016-09-18 16:50:52',1,'2016-09-18 16:50:52','katie@petway.com','katie@petway.com',NULL,'$2a$12$sJmiu.fmkzOVtzb86oTdNO2CDLDYsxblu6Njvxr/aWfnHe7onrpWa',30,2),('D',31,'2016-09-18 16:52:43',1,'2016-09-18 16:52:43','prp0121@yahoo.com','prp0121@yahoo.com',NULL,'$2a$12$QCsI5qlLljC4ga/u/9Fwf.rVUb84vv/zNOI4FYFpmeX7rGRu8JksC',31,3),('D',32,'2016-09-18 17:20:12',1,'2016-09-18 17:20:12','sweetiepygemini@aol.com','sweetiepygemini@aol.com',NULL,'$2a$12$6lhUCkWBjasEza38rCX6OOZzVuH72ASGnqzdiixr2ooyOOBR5.Yei',32,3),('D',33,'2016-09-19 19:32:09',1,'2016-09-19 19:32:09','testLogin@gmail.com','testLogin@gmail.com',NULL,'$2a$12$wP56YheE4GIg/ihLGmJS7eluyYC76S6BmxGKogwUcAUfxwjID1yCO',33,3),('N',34,'2016-09-19 20:26:15',1,'2016-09-19 20:26:15','admin@admin.com','admin@admin.com',NULL,'$2a$12$RxZLHwkUUl488G9bIE.8qO5t0abip2p6e1gwddkl2zmSOay9u5fHu',34,1),('N',35,'2016-09-21 02:57:20',1,'2016-09-21 02:57:20','goodnp@test.com','goodnp@test.com',NULL,'$2a$12$7D.2Ek5PyxgCvR9mwl7Q/.LOwPtp7HoXqjiGT0nousGmoJxc0j7Pm',35,2),('D',36,'2016-09-21 02:59:47',1,'2016-09-21 02:59:47','gooddonor@test.com','gooddonor@test.com',NULL,'$2a$12$Qx0v7RnIOOD6JUgsJ7LZGODeMCJwR8gQq4x1Krdpm6RG4r6iKLqGC',36,3),('N',37,'2016-09-22 18:47:38',1,'2016-09-22 18:47:38','RichNp@test.com','RichNp@test.com',NULL,'$2a$12$cpzaDOmSUcnIOsL7PEbxte2ISH.75gRPGjjhV8ridIMNzYvvlhvru',37,2),('D',38,'2016-09-27 03:59:18',1,'2016-09-27 03:59:18','Emily@givenkind.org','Emily@givenkind.org',NULL,'$2a$12$8tQ0v6cOPMKCRPvRyut6teOXL1UC588YO22KK59RKAXSw8/W4o7V6',38,3),('N',39,'2016-09-29 15:57:43',1,'2016-09-29 15:57:43','nphome@test.com','nphome@test.com',NULL,'$2a$12$LdQXM585mQzr7kQ0ABbSYuc8lwcBuBJIPFYsq.G0//SIC56lyRpja',39,2),('N',40,'2016-09-29 16:00:27',1,'2016-09-29 16:00:27','npoffice@test.com','npoffice@test.com',NULL,'$2a$12$bpG1cc0vTp8PXjYtHIQ/eOBPGymVLdP84BfMmVl7ejUs1X9dGIJoC',40,2),('D',41,'2016-09-29 16:02:10',1,'2016-09-29 16:02:10','donorhome@test.com','donorhome@test.com',NULL,'$2a$12$1lsI2.HJwLxdHUGFOhc0a.CUmV1IUSTe4f2k9V826vqMwFcv60le2',41,3),('D',42,'2016-10-07 01:24:19',1,'2016-10-07 01:24:19','donor1@aa','donor1@aa',NULL,'$2a$12$AHnCXP8J7IaAZcPpgAETmOod7kFOQfnNuk0Jwi30HCi7toEW0X5y6',42,3),('N',43,'2016-10-08 04:32:43',1,'2016-10-08 04:32:43','np@test','np@test',NULL,'$2a$12$BR55YQRGgTriA12iE4f9IOYrY3AN0O/Wld1RTtPDYkXk/9Gd.4hza',43,2),('N',44,'2016-10-08 04:45:22',1,'2016-10-08 04:45:22','np1@test','np1@test',NULL,'$2a$12$ClIIbOkYtu.AG1po3eLi4.gN.aPhfND41arz/LKsnsIL/0v/6CfRG',44,2),('D',45,'2016-10-10 02:56:36',1,'2016-10-10 02:56:36','michael.fadhl@nike.com','michael.fadhl@nike.com',NULL,'$2a$12$dNDe/J17EpYnLPwXE/3pbOqaPSp2H9Zsh19zC5rtX3ejGh03JkVGG',45,3),('N',46,'2016-10-14 02:43:55',1,'2016-10-14 02:43:55','np2@test','np2@test',NULL,'$2a$12$y/Rdl1jBpiUGAmJA61ua6.32pIRPPv8WGTD6hlJCVzjK8jmg8WnVa',46,2),('D',47,'2016-10-15 00:39:42',1,'2016-10-15 00:39:42','donor12@aa','donor12@aa',NULL,'$2a$12$BUKj1NaFQgy8U/Bv1UKk7.swVtgfY4i5EJAWymH8k1Y.Zyc5jq2kS',47,3),('N',48,'2016-10-16 01:09:28',1,'2016-10-16 01:09:28','dfgdfg@dfgdfg.com','dfgdfg@dfgdfg.com',NULL,'$2a$12$kFDVqRu8g93eOXHuMaFaS.O3sL6zWtcoljlj9t7TjRpL0POSZNNty',48,2),('N',49,'2016-10-16 02:00:19',1,'2016-10-16 02:00:19','newnp@gmail.com','newnp@gmail.com',NULL,'$2a$12$P2KiFtrX4mVqS6njHTHP5eGgwS3EWUZ92FdmXxCk/WULoLEipxwG.',49,2),('D',50,'2016-10-22 04:44:03',1,'2016-10-22 04:44:03','donor1@test.com','donor1@test.com',NULL,'$2a$12$WUJD3XN4GBYI5mgz1vxJh.dV5SzPKBBEA2zOxl4DzdIvPrkkDNEvq',50,3),('D',51,'2016-10-22 05:28:05',1,'2016-10-22 05:28:05','donor2@test.com','donor2@test.com',NULL,'$2a$12$x5gxBZ6BnrGTvfH08XvyRedunaAesbWKND35K.DkL08OvG6YmToEi',51,3),('D',52,'2016-10-22 05:41:07',1,'2016-10-22 05:41:07','donor3@test.com','donor3@test.com',NULL,'$2a$12$Ku79AU1FVs.6dtkfrfzjxum8IaUIrN.GBOAIaZvBIt4uOBVMgOmPS',52,3),('N',53,'2016-10-23 17:23:47',1,'2016-10-23 17:23:47','np1@test.com','np1@test.com',NULL,'$2a$12$PB9LZaVSAqT5kfgdO09vfup3d3WYcsUugpcec3Sq67ilD42j8tHfq',53,2),('N',54,'2016-10-23 18:01:46',1,'2016-10-23 18:01:46','np2@test.com','np2@test.com',NULL,'$2a$12$LdanWuliwnneJDm3x.VhtONPAtBbrojbEKrY67HroON0KzoA6Hp.e',54,2),('N',55,'2016-10-23 18:31:29',1,'2016-10-23 18:31:29','np3@test.com','np3@test.com',NULL,'$2a$12$ozOSlN9pNB1vjHygX1b7h.rVMS0sOxg4uj.LCKm1c1lB7L8KGTBGi',55,2),('D',56,'2016-10-31 17:41:55',1,'2016-10-31 17:41:55','testuserfntestuserln@gmail.com','testuserfntestuserln@gmail.com',NULL,'$2a$12$CPdwF15MuwXXDJqr4TXOueMMJF87ySo.rqzU8QWgqfd/cnIK4Ka1m',56,3);
/*!40000 ALTER TABLE `tblUserLogin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblWishlistItem`
--

DROP TABLE IF EXISTS `tblWishlistItem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblWishlistItem` (
  `WishlistItemId` bigint(20) NOT NULL AUTO_INCREMENT,
  `DateExpires` datetime DEFAULT NULL,
  `Impact` varchar(255) DEFAULT NULL,
  `ItemName` varchar(255) DEFAULT NULL,
  `Notes` varchar(255) DEFAULT NULL,
  `QuantityDesired` int(11) DEFAULT NULL,
  `nonProfitUserLogon_UserId` bigint(20) DEFAULT NULL,
  `Comments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`WishlistItemId`),
  KEY `FK_2tke1lnya15xm62ug9pa0cbcq` (`nonProfitUserLogon_UserId`),
  CONSTRAINT `FK_2tke1lnya15xm62ug9pa0cbcq` FOREIGN KEY (`nonProfitUserLogon_UserId`) REFERENCES `tblNonProfitUserLogon` (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblWishlistItem`
--

LOCK TABLES `tblWishlistItem` WRITE;
/*!40000 ALTER TABLE `tblWishlistItem` DISABLE KEYS */;
INSERT INTO `tblWishlistItem` VALUES (1,'2016-08-18 00:00:00','Test','Test Item Edit','Example',2,2,NULL),(2,'2016-08-11 00:00:00','Test','Test Item for Search','Example',2,2,NULL),(3,'2016-08-01 00:00:00','3','Test Wish List Item','Sample Wishlist',3,2,NULL),(4,'2016-08-12 00:00:00','A home for our company mascot','Fish Tank','12\" x 12\" square fish tank; glass; comes with lid',1,2,NULL),(6,'2016-10-04 00:00:00','nothing','table','furniture',4,2,NULL),(7,'2016-09-30 00:00:00','yes','table','20*10',1,2,NULL),(8,'2017-06-14 00:00:00','give children a safe place to play','playmat','Floor mat for children to play on/interact ',10,29,NULL),(9,'2016-09-30 00:00:00','Helping kids prepare to go back to school.','Backpacks','Children\'s backpacks',15,27,NULL),(11,'2016-09-30 00:00:00','Support community programs','Notebooks','School notebooks',15,27,NULL),(12,'2016-09-19 00:00:00','General Operations','Table','A table with a drawer',1,30,NULL),(14,'2016-10-19 00:00:00','Support general operations','test1','testdesc',1,2,'Test Comments'),(15,'2016-10-31 00:00:00','Support community programs','carseat','child carseat',9,2,'to provide car seat for needed families'),(18,'2016-10-27 00:00:00','Support general operations','asdf','app',4,2,''),(20,'2018-01-24 00:00:00','Support general operations','table','table',5,44,''),(21,'2016-11-26 00:00:00','Support general operations','shoe','shoe',11,44,''),(22,'2016-10-31 00:00:00','Support general operations','crib','crib',5,44,''),(23,'2016-10-01 00:00:00','Support community programs','table','table',20,43,''),(24,'2016-12-30 00:00:00','Support community programs','tiles','building materials',5,53,'To remodel'),(25,'2016-11-16 00:00:00','Support general operations','Fridge','kitchen appliance',1,53,'yes'),(26,'2017-03-16 00:00:00','Support community programs','lamp','lamps needed for kitchen',21,54,'yes'),(27,'2017-07-13 00:00:00','Support general operations','table','rectangular table',6,54,'yes'),(28,'2016-12-15 00:00:00','Support general operations','Fridge','Stainless Steel',5,55,'Yes'),(29,'2017-07-12 00:00:00','Support general operations','Play Gym','Play mat',23,55,'Helps kids'),(31,'2016-11-17 00:00:00','Support general operations','Pillow','Rectangular Pillows',20,53,'Helps people'),(32,'2016-12-16 00:00:00','Support community programs','Washer','Full size washer',2,55,'Helps the People'),(33,'2017-01-12 00:00:00','Support community programs','Air Conditioner','9000 ',10,54,'Supports community\r\n'),(34,'2017-01-26 00:00:00','Support community programs','Table','Rectangular table',10,2,'Yes'),(35,'2016-12-08 00:00:00','Support general operations','chair','Wooden chair',10,2,'Yes');
/*!40000 ALTER TABLE `tblWishlistItem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblWishlistItem_tblItemCategory`
--

DROP TABLE IF EXISTS `tblWishlistItem_tblItemCategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tblWishlistItem_tblItemCategory` (
  `tblWishlistItem_WishlistItemId` bigint(20) NOT NULL,
  `wishlistItemCategories_CategoryId` bigint(20) NOT NULL,
  KEY `FK_d0dcl0ed85r9md5brmp7wrpwa` (`wishlistItemCategories_CategoryId`),
  KEY `FK_rd17b8uuc8kbjjs2rne2c9i3p` (`tblWishlistItem_WishlistItemId`),
  CONSTRAINT `FK_rd17b8uuc8kbjjs2rne2c9i3p` FOREIGN KEY (`tblWishlistItem_WishlistItemId`) REFERENCES `tblWishlistItem` (`WishlistItemId`),
  CONSTRAINT `FK_d0dcl0ed85r9md5brmp7wrpwa` FOREIGN KEY (`wishlistItemCategories_CategoryId`) REFERENCES `tblItemCategory` (`CategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblWishlistItem_tblItemCategory`
--

LOCK TABLES `tblWishlistItem_tblItemCategory` WRITE;
/*!40000 ALTER TABLE `tblWishlistItem_tblItemCategory` DISABLE KEYS */;
INSERT INTO `tblWishlistItem_tblItemCategory` VALUES (2,4),(4,2),(3,4),(7,13),(9,22),(11,21),(12,11),(12,12),(12,13),(8,4),(8,5),(8,7),(8,10),(14,22),(15,5),(18,3),(22,5),(23,1),(23,13),(23,14),(20,10),(20,11),(20,12),(20,13),(20,23),(21,24),(24,7),(25,3),(26,15),(27,13),(29,5),(31,16),(32,3),(28,1),(28,2),(28,3),(28,15),(1,1),(1,2),(1,3),(6,10),(6,11),(6,12),(6,13),(33,3),(33,18),(33,22),(34,13),(34,14),(35,13),(35,14),(35,15),(35,16),(35,17),(35,18);
/*!40000 ALTER TABLE `tblWishlistItem_tblItemCategory` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-06  2:56:52
