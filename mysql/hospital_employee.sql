-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hospital
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `ID` varchar(50) NOT NULL,
  `Role` varchar(5) DEFAULT NULL,
  `FName` varchar(30) DEFAULT NULL,
  `LName` varchar(30) DEFAULT NULL,
  `DutySlot` time DEFAULT NULL,
  `Age` int DEFAULT NULL,
  `StartDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `Role` (`Role`),
  CONSTRAINT `Employee_ibfk_1` FOREIGN KEY (`Role`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('A101','A','Ayush','Singh','23:00:00',40,'2015-02-23 17:30:00'),('A113','A','Raj','Verma','11:30:00',30,'2020-12-28 07:56:07'),('D101','D','Priyal','Patil','10:00:00',35,'2018-02-23 02:30:00'),('D112','D','James','Halpert','08:00:00',34,'2015-12-22 02:30:00'),('EMP72HGZR','A','Aadwik','Lakar',NULL,50,'2021-08-14 18:30:00'),('EMPA2PTSA','A','Aadwik','Lakar',NULL,50,'2021-08-14 18:30:00'),('EMPGVW7X9','D','Abc','Def',NULL,28,'2021-04-30 18:30:00'),('EMPJGV5QB','R','Abc','Def',NULL,28,'2021-04-30 18:30:00'),('EMPMTX4MT','A','Aadwik','Lakar',NULL,50,'2021-08-14 18:30:00'),('EMPRRBRCD','D','Aadwik','Lakar',NULL,50,'2021-08-14 18:30:00'),('R101','R','Ashwin','Roy','08:00:00',28,'2015-07-09 01:30:00'),('R111','R','Nikita','Bhatiya','07:00:00',22,'2020-12-28 08:32:05');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-15 18:51:45
