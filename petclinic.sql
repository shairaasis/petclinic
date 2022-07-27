-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: petclinic
-- ------------------------------------------------------
-- Server version	8.0.29

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
  `contact_no` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `account_id_UNIQUE` (`account_id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `account_type_id_idx` (`account_type_id`),
  CONSTRAINT `account_type_id` FOREIGN KEY (`account_type_id`) REFERENCES `account_types` (`account_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (4,3,'asd','5f4dcc3b5aa765d61d8327deb882cf99','Asd','Qwerty','Leyte','asd@try.com','09950114419'),(9,3,'john','5f4dcc3b5aa765d61d8327deb882cf99','John','Doe','Samar','john@gmail.com','0920337728'),(15,3,'client','e00cf25ad42683b3df678c61f42c6bda','Client','One','manila','admin1@gmail.com','09453115526'),(21,2,'ab','900150983cd24fb0d6963f7d28e17f72','Jane','Doe','aaabbb','ab@gmail.com','676766'),(24,1,'admin1','e00cf25ad42683b3df678c61f42c6bda','Admin','One','Leyte','admin1@gmail.com','09652444432'),(28,2,'vet1','9d5f433b59b4e5359f2c0d9cafed1d60','Vet','Name','Leyte','vet1@gmail.com','09652445521'),(30,2,'vet2','4cd7f1eb0e0c365e2ab010066222516c','Vet','Two','Leyte','vet2@gmail.com','09950114419'),(44,3,'user','ee11cbb19052e40b07aac0ca060c23ee','Jay','Reyes','s','jon@gmail.com','09453115526'),(45,3,'shaira','482a53a7536571834cfa06881073227b','Shaira','Asis','Dulag, Leyte','asisshaira99@gmail.com','09950114419'),(46,2,'vet','2fe585b232995699edf68a537f91d31d','Rose','Tan','Cebu City','vet@gmail.com','09453115526'),(48,2,'dave','1610838743cc90e3e4fdda748282d9b8','Dave','Tan','Leyte','john@gmail.com','09453115526'),(49,3,'monic','b632f02d9f6ffe28aa27db8c6b0314cc','Monica','Asis','Leyte','monica@gmail.com','09453115526'),(51,3,'gap','df9bcbd6578a1e49c06b7ec2874f9e23','Gap','Py','Leyte','gap@gmail.com','09652445521'),(52,3,'jaya','ce9689abdeab50b5bee3b56c7aadee27','Jaya','Jo','Leyte','jaya@gmail.com','09453115526');
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
  `schedule` datetime NOT NULL,
  `status` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`appointment_id`),
  UNIQUE KEY `appointment_id_UNIQUE` (`appointment_id`),
  UNIQUE KEY `schedule_unique` (`schedule`),
  KEY `customer_id_idx` (`customer_id`),
  KEY `pet_id_idx` (`pet_id`),
  KEY `veterinarian_id_idx` (`veterinarian_id`),
  KEY `service_idx` (`service`),
  CONSTRAINT `customer_id` FOREIGN KEY (`customer_id`) REFERENCES `accounts` (`account_id`),
  CONSTRAINT `pet_id` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`pet_id`),
  CONSTRAINT `service` FOREIGN KEY (`service`) REFERENCES `services` (`service_id`),
  CONSTRAINT `veterinarian_id` FOREIGN KEY (`veterinarian_id`) REFERENCES `accounts` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (9,15,13,21,2,'2022-07-30 10:00:00',NULL),(63,45,21,21,20,'2022-07-23 08:00:00','approved'),(86,44,22,46,2,'2022-07-13 07:35:00','approved'),(87,44,22,46,2,'2022-07-16 02:00:00','pending');
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pets`
--

LOCK TABLES `pets` WRITE;
/*!40000 ALTER TABLE `pets` DISABLE KEYS */;
INSERT INTO `pets` VALUES (3,9,NULL,'sasasa','2021-04-18','MalShih','Brown-White'),(4,9,NULL,'sososo','2010-01-01','MalShih','Brown-Black'),(5,9,NULL,'sasasa1','2010-01-01','MalShih','sad'),(6,9,NULL,'sasasa1','2010-01-01','MalShih','sad'),(8,9,NULL,'fg','2010-01-12','fg','dfhd'),(9,9,NULL,'vcccc','2014-03-26','cbxb','sfg'),(10,9,NULL,'dfsdgf','2010-01-20','fdgdf','fghg'),(11,9,NULL,'asasa','2010-01-15','ddddddd','dddddddddddd'),(13,15,NULL,'Gab','2016-01-21','Husky','Bl'),(17,15,NULL,'Max','2016-01-14','MalShih','Brown-White'),(19,44,NULL,'Mini','2010-01-14','MalShih','Brown-White'),(21,45,NULL,'Prince','2015-01-01','Aspin','Brown'),(22,44,NULL,'Mix','2014-01-15','Husky','Brown-White'),(23,44,NULL,'Max','2011-01-12','Husky','Brown-White');
/*!40000 ALTER TABLE `pets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_availability`
--

DROP TABLE IF EXISTS `schedule_availability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schedule_availability` (
  `schedule_id` int NOT NULL AUTO_INCREMENT,
  `veterinarian_id` int NOT NULL,
  `date` date NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `schedule_availability` varchar(45) NOT NULL,
  PRIMARY KEY (`schedule_id`),
  UNIQUE KEY `schedule_id_UNIQUE` (`schedule_id`),
  KEY `veterinarian_id` (`veterinarian_id`),
  CONSTRAINT `schedule_availability_ibfk_1` FOREIGN KEY (`veterinarian_id`) REFERENCES `accounts` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_availability`
--

LOCK TABLES `schedule_availability` WRITE;
/*!40000 ALTER TABLE `schedule_availability` DISABLE KEYS */;
INSERT INTO `schedule_availability` VALUES (1,21,'2022-07-30','08:00:00','09:00:00','AVAILABLE');
/*!40000 ALTER TABLE `schedule_availability` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (2,'Consultation',450),(20,'Vaccination',500);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tokens`
--

DROP TABLE IF EXISTS `tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tokens` (
  `token` varchar(255) NOT NULL,
  UNIQUE KEY `token_UNIQUE` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tokens`
--

LOCK TABLES `tokens` WRITE;
/*!40000 ALTER TABLE `tokens` DISABLE KEYS */;
INSERT INTO `tokens` VALUES ('1e65a24cfd9d4c34a10786e2baf59487'),('24fe134823984df6a3ff4a40f4ea7e7a'),('2ddd1a8984344a23ac39cacd4f055822'),('2f72d224414a4e95a7021e69e05e8760'),('40642ac958b14cd69090438cc7a56384'),('91e344575da142b1b19b2c7a7a6157a0'),('9e5be2db5bb34b54b7226fe566b4306d'),('a2818fec3afa483cb72bd71dad185884'),('bc1ca5be5f914a84a4683e3c35fbb28a'),('c32944090d4e40a085c95e40048c8781'),('cc420aece830469893e7d07033c0b2b8'),('d25b44e63dbf449fbdf19939a94a7b8a'),('d701483a71c44b1d9cea03ed14405d39'),('da3b68b0102749d58ff428b89827d005'),('de688afff4574cbba2d985afcedd5f3a'),('e1cc1b12d1584c8d84950fac0c07f327'),('e6b8c2a0f2ae4e60a3b9ebcb6fddde20'),('e92283b723094af0bef2fd088b58e4de'),('ea3ef09c31fe485d8404bbc99d7b4569'),('eb8c0daf90db4bf6a55b03fc6e3ac776');
/*!40000 ALTER TABLE `tokens` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-26 22:08:32
