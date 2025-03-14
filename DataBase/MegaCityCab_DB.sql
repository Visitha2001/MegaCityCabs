-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: signup
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `rides`
--

DROP TABLE IF EXISTS `rides`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rides` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_location` varchar(255) NOT NULL,
  `end_location` varchar(255) NOT NULL,
  `customer_username` varchar(255) NOT NULL,
  `rider_username` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `length_of_ride` double DEFAULT NULL,
  `ride_status` enum('REQUESTED','ASSIGNED','ACCEPTED','IN_PROGRESS','COMPLETED','CANCELLED') DEFAULT 'REQUESTED',
  `vehicle_type` enum('CAR','SUV','BIKE','VAN') DEFAULT NULL,
  `vehicle_plate_number` varchar(50) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `ride_started_at` datetime DEFAULT NULL,
  `ride_ended_at` datetime DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rides`
--

LOCK TABLES `rides` WRITE;
/*!40000 ALTER TABLE `rides` DISABLE KEYS */;
INSERT INTO `rides` VALUES (1,'Location A','Location B','customer1','rider1',20.00,5,'COMPLETED','CAR','ABC123','1234567890','2025-03-08 15:22:59','2025-03-08 15:22:59','2025-03-08 09:52:58'),(5,'kandy','matale','visitha nirmal','rider2',2500.00,25,'COMPLETED','CAR','C12345','0781262257','2025-03-11 18:32:03','2025-03-11 18:34:47','2025-03-11 12:51:11'),(6,'kandy','matale','visitha nirmal',NULL,1800.00,12,'REQUESTED','SUV',NULL,NULL,NULL,NULL,'2025-03-12 09:21:21'),(7,'kandy','matale','visitha nirmal','rider2',1200.00,12,'COMPLETED','CAR','','','2025-03-12 14:59:17','2025-03-12 14:59:43','2025-03-12 09:27:45');
/*!40000 ALTER TABLE `rides` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile` varchar(50) NOT NULL,
  `NIC` varchar(15) NOT NULL,
  `address` varchar(100) NOT NULL,
  `role` varchar(50) NOT NULL,
  `vehicleType` varchar(100) DEFAULT NULL,
  `plateNumber` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `NIC` (`NIC`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (37,'visitha nirmal','123','visitha2001@gmail.com','0781262257','200122703830','No. 99/3, Kahawaththa , Ambathanna','customer','',''),(38,'admin','123','admin123@gmail.com','0781262257','200122703831','No. 99/3, Kahawaththa , Ambathanna','admin',NULL,NULL),(41,'rider2','123','rider2@gmail.com','0781262257','200111112345','No. 99/3, Kahawaththa , Ambathanna','rider','Car','C12345'),(42,'testuser','password','testuser@example.com','1234567890','123456789V','123 Test St','customer','Car','XYZ123');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-14 13:15:02
