-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: petclinic
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account_types`
--

DROP TABLE IF EXISTS `account_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account_types` (
  `account_type_id` int NOT NULL AUTO_INCREMENT,
  `account_type` varchar(255) NOT NULL,
  PRIMARY KEY (`account_type_id`),
  UNIQUE KEY `idaccount_types_UNIQUE` (`account_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_types`
--

LOCK TABLES `account_types` WRITE;
/*!40000 ALTER TABLE `account_types` DISABLE KEYS */;
INSERT INTO `account_types` VALUES (1,'admin'),(2,'veterinarian'),(3,'customer');
/*!40000 ALTER TABLE `account_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `account_id` int NOT NULL AUTO_INCREMENT,
  `account_type_id` int NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `contact_no` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `account_id_UNIQUE` (`account_id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `account_type_id_idx` (`account_type_id`),
  CONSTRAINT `account_type_id` FOREIGN KEY (`account_type_id`) REFERENCES `account_types` (`account_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (24,1,'admin1','e00cf25ad42683b3df678c61f42c6bda','Super','Admin','Manila','mad.dinar25@gmail.com','123456789'),(54,2,'vet1','f2930c16c1335ba781ba1e5bc69038f6','Shaira','Asis','Leyte','shairaasis99@gmail.com','123456877'),(55,1,'admin2','c84258e9c39059a89ab77d846ddab909','Super','Admin2','Leyte','asisshaira99@gmail.com','1234569878'),(79,3,'demo123','62cc2d8b4bf2d8728120d052163a77df','Mike','Miller','Cebu','madzoldyck925@gmail.com','09494669508'),(94,3,'mads123','9ef058bc09015cc9a75ee2235184f96b','dnadn','nfsuanf','fasnofansfo','mad.dinar25@gmail.com','+639494669508'),(95,3,'asd123','20917c851c4a54f2a054390dac9085b7','mads','dinar','manila','mad.dinar25@gmail.com','+639494669508'),(96,3,'madzzz123','c7aa32c54daf50e109e4f8994518435a','mads','dinar','manila','mad.dinar25@gmail.com','+639494669508'),(97,3,'madzz123','c7aa32c54daf50e109e4f8994518435a','mads','dinar','manila','mad.dinar25@gmail.com','+639494669508'),(98,3,'new123','68a0099b3f45357798639a30c5fe3154','asfbai','fasfuobnao','fnasuofnao','mad.dinar25@gmail.com','+639494669508'),(99,3,'newest123','17aebcf118df0151d3575db353733283','asnfuas','fnasofnaosn','fnoasfnaon','mad.dinar25@gmail.com','+639494669508'),(100,3,'client2','2c66045d4e4a90814ce9280272e510ec','asufja','nfoasfna','nfoasfnao','asndoa@gmail.com','1231244'),(101,3,'client1','a165dd3c2e98d5d607181d0b87a4c66b','Mads','Dinar','Manila','mad.dinar25@gmail.com','+639494669508'),(102,3,'newmads123','ed1bb6592fe85e90d15a44d443213aca','asfhoah','fnoasfnao','fasniofna','mad.dinar25@gmail.com','+639494669508'),(103,3,'lasttry123','d017c28b7b8b2a77736321c22a0027f8','nmasolfnan','asnfonao','fnsafonaof','mad.dinar25@gmail.com','+639494669508'),(104,3,'superlast123','25d1699bcece6f5550684f75d54fdcea','Mad','Dinar','afasfasd','mad.dinar25@gmail.com','+639494669508'),(105,3,'lastnapo123','c1b74a19e9b2ffb4c52d55606ebf81b2','Last','Po','Rizal','madzoldyck925@gmail.com','+639494669508');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `appointment_id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `pet_id` int NOT NULL,
  `veterinarian_id` int DEFAULT NULL,
  `service` int NOT NULL,
  `schedule` date DEFAULT NULL,
  `timeID` int NOT NULL,
  `status` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`appointment_id`),
  UNIQUE KEY `appointment_id_UNIQUE` (`appointment_id`),
  KEY `customer_id_idx` (`customer_id`),
  KEY `pet_id_idx` (`pet_id`),
  KEY `veterinarian_id_idx` (`veterinarian_id`),
  KEY `service_idx` (`service`),
  KEY `timeID` (`timeID`),
  CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`timeID`) REFERENCES `time` (`timeID`),
  CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `accounts` (`account_id`),
  CONSTRAINT `pet_id` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`pet_id`),
  CONSTRAINT `service` FOREIGN KEY (`service`) REFERENCES `services` (`service_id`),
  CONSTRAINT `veterinarian_id` FOREIGN KEY (`veterinarian_id`) REFERENCES `accounts` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (172,101,28,54,2,'2022-09-16',4,'pending'),(173,105,29,54,20,'2022-09-14',6,'pending');
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pets`
--

DROP TABLE IF EXISTS `pets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pets` (
  `pet_id` int NOT NULL AUTO_INCREMENT,
  `owner_id` int NOT NULL,
  `health_record_id` int DEFAULT NULL,
  `pet_name` varchar(255) NOT NULL,
  `date_of_birth` date NOT NULL,
  `breed` varchar(255) NOT NULL,
  `coat_color` varchar(255) NOT NULL,
  PRIMARY KEY (`pet_id`),
  UNIQUE KEY `pet_id_UNIQUE` (`pet_id`),
  KEY `owner_id` (`owner_id`),
  CONSTRAINT `owner_id` FOREIGN KEY (`owner_id`) REFERENCES `accounts` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pets`
--

LOCK TABLES `pets` WRITE;
/*!40000 ALTER TABLE `pets` DISABLE KEYS */;
INSERT INTO `pets` VALUES (27,100,NULL,'Meow','2010-01-01','breedless','coatless'),(28,101,NULL,'Super Pet','2010-01-01','Super Breed','Super Color'),(29,105,NULL,'Hachiko','2009-01-16','Shiba','Brown');
/*!40000 ALTER TABLE `pets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `service_id` int NOT NULL AUTO_INCREMENT,
  `service` varchar(255) NOT NULL,
  `fee` int NOT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (2,'Consultation',450),(20,'Vaccination',500),(25,'Grooming',300);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time`
--

DROP TABLE IF EXISTS `time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time` (
  `timeID` int NOT NULL AUTO_INCREMENT,
  `time` time DEFAULT NULL,
  PRIMARY KEY (`timeID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time`
--

LOCK TABLES `time` WRITE;
/*!40000 ALTER TABLE `time` DISABLE KEYS */;
INSERT INTO `time` VALUES (1,'08:00:00'),(2,'09:00:00'),(3,'10:00:00'),(4,'11:00:00'),(5,'13:00:00'),(6,'14:00:00'),(7,'15:00:00'),(8,'16:00:00');
/*!40000 ALTER TABLE `time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tokens` (
  `token` varchar(255) NOT NULL,
  PRIMARY KEY (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokens`
--

LOCK TABLES `tokens` WRITE;
/*!40000 ALTER TABLE `tokens` DISABLE KEYS */;
INSERT INTO `tokens` VALUES ('08dd17cd49a842db89ac533283707653'),('0913b2b3767844cdb3e1fb4b7319d117'),('2e6ead0fc10e45ebaed77b887eb25f14'),('3010bef8d5d1440fa744f7fcb6d5430b'),('3d42d58925fe4a64ac0e83182486c7ce'),('42b505f38b3640a19bff4e3505d07228'),('4c91e5cf8f24405dba8cd3760080a1cf'),('4eaf344c6d0b4737a83da2b7917f7b50'),('58b94d45dc67491fb88596a651b58782'),('61d8fdf9e08f4d6d8202aaab91492d12'),('6f9f381577d04aa380675b852a1b8ac1'),('8a982ab53aab4fb3aeb926fce7cb3f6e'),('950dcc7017aa4a0e8b308d8570c928bf'),('addbe39cc9c345c384a75c5c90932661'),('be03899e8ebf490f8221bd7e8d068984'),('c49a9bfc9e1b4f76acc7e58c6e2b5d8c'),('e820c9e5b5ff425988ec2ab9f0f93ebc'),('febfe82fe00e4826b9c56ae5c4e7dbb2'),('ffe70075515a4da08d41d08b6843a6c7');
/*!40000 ALTER TABLE `tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification`
--

DROP TABLE IF EXISTS `verification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verification` (
  `ver_id` int NOT NULL AUTO_INCREMENT,
  `account_id` int NOT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'Pending',
  `code` int NOT NULL,
  PRIMARY KEY (`ver_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification`
--

LOCK TABLES `verification` WRITE;
/*!40000 ALTER TABLE `verification` DISABLE KEYS */;
INSERT INTO `verification` VALUES (1,24,'Verified',6908),(2,54,'Verified',9954),(3,55,'Verified',5567),(12,79,'Verified',9283),(13,80,'Pending',4893),(14,81,'Pending',4768),(15,82,'Pending',9666),(16,83,'Pending',9790),(17,84,'Pending',9504),(18,85,'Pending',4845),(19,86,'Pending',8861),(20,87,'Verified',8215),(21,88,'Pending',1753),(22,89,'Pending',9412),(23,90,'Pending',8029),(24,91,'Pending',4556),(25,92,'Pending',5173),(26,94,'Pending',7820),(27,95,'Pending',7494),(28,96,'Pending',2310),(29,97,'Pending',2373),(30,98,'Verified',6565),(31,99,'Verified',8411),(32,101,'Verified',4167),(33,102,'Pending',6222),(34,103,'Verified',3280),(35,104,'Verified',2592),(36,105,'Verified',7416);
/*!40000 ALTER TABLE `verification` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-14 16:05:57
