-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: nearmypark
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `parking`
--

DROP TABLE IF EXISTS `parking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `alternate_mobile` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `vehicle_type` varchar(255) DEFAULT NULL,
  `map_link` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `booked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking`
--

LOCK TABLES `parking` WRITE;
/*!40000 ALTER TABLE `parking` DISABLE KEYS */;
INSERT INTO `parking` VALUES (1,'melvanakkambadi','9361816131','chengam','9489809040',500,'9047811140','4 Wheeler Small','https://maps.app.goo.gl/NUjKr7W7QsrQiyep6',12.8765,80.2451,1),(2,'rv nagar','9361816131','tiruvannamalai','9489809040',50,'9047811146','2 Wheeler','https://maps.app.goo.gl/NUjKr7W7QsrQiyep6',12.8765,80.2451,1),(3,'rv nagar','9361816131','tiruvannamalai','9489809040',50,'9047811146','2 Wheeler','https://maps.app.goo.gl/NUjKr7W7QsrQiyep6',12.8765,80.2451,1),(4,'rv nagar','9361816131','tiruvannamalai','9489809040',50,'9047811146','2 Wheeler','',12.8765,80.2451,1),(5,'mvb','8529647103','tiruvannamalai','9045456632',23,'9047811146','4 Wheeler Big','',12.8765,80.2451,1),(6,'mvb','8529647103','tiruvannamalai','9045456632',231,'9047811146','2 Wheeler','https://maps.app.goo.gl/NUjKr7W7QsrQiyep6',NULL,NULL,0),(7,'neelambur','8529647103','chennai','78945612300',510,'9047811140','Heavy Vehicle','https://maps.app.goo.gl/NUjKr7W7QsrQiyep6',13.0798,80.2469,0),(8,'andipatti','9047811140','chengam','9043266329',52,'9361816131','2 Wheeler','https://maps.app.goo.gl/NUjKr7W7QsrQiyep6',10.992309,76.994018,0),(9,'andipatti','9047811140','chengam','9043266329',520,'9361816131','4 Wheeler Small','https://maps.app.goo.gl/NUjKr7W7QsrQiyep6',NULL,NULL,0),(10,'vettavalm','8956485621','tiruvannamalai','8974561236',200,'9047811146','4 Wheeler Small','https://maps.app.goo.gl/NUjKr7W7QsrQiyep',NULL,NULL,0);
/*!40000 ALTER TABLE `parking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_request`
--

DROP TABLE IF EXISTS `parking_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parking_request` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `owner_id` varchar(255) DEFAULT NULL,
  `parking_id` bigint DEFAULT NULL,
  `requester_id` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `from_date` varchar(255) DEFAULT NULL,
  `to_date` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `from_time` varchar(255) DEFAULT NULL,
  `to_time` varchar(255) DEFAULT NULL,
  `cancellation_reason` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_request`
--

LOCK TABLES `parking_request` WRITE;
/*!40000 ALTER TABLE `parking_request` DISABLE KEYS */;
INSERT INTO `parking_request` VALUES (25,'9047811146',4,'9047811146','REJECTED','2026-05-15','2026-05-16',NULL,NULL,NULL,NULL,NULL),(27,'9361816131',8,'9047811146','PENDING','2026-05-17','2026-05-31',NULL,NULL,NULL,NULL,NULL),(28,'9047811146',4,'9047811146','ACCEPTED','2026-05-15','2026-05-16',NULL,NULL,NULL,NULL,NULL),(29,'9047811146',6,'9047811146','ACCEPTED','2026-05-15','2026-05-16',NULL,'14:41','15:41',NULL,NULL),(30,'9047811146',6,'9047811146','ACCEPTED','2026-05-15','2026-05-15',NULL,'14:43','14:43',NULL,NULL),(31,'9047811146',6,'9047811146','ACCEPTED','2026-05-15','2026-05-16',NULL,'15:00','15:00',NULL,NULL),(32,'9047811146',6,'9047811146','REJECTED','2026-05-15','2026-05-16',NULL,'15:07','15:07',NULL,NULL),(33,'9047811146',6,'9047811146','ACCEPTED','2026-05-15','2026-05-16',NULL,'15:10','15:10',NULL,NULL),(34,'9047811146',5,'9047811146','ACCEPTED','2026-05-15','2026-05-16',NULL,'15:20','15:20',NULL,NULL),(35,'9361816131',9,'9047811146','PENDING','2026-05-15','2026-05-23',NULL,'16:57','17:58',NULL,NULL),(36,'9047811146',2,'9047811146','ACCEPTED','2026-05-15','2026-05-15',NULL,'18:29','19:29',NULL,NULL),(37,'9047811146',2,'9047811146','ACCEPTED','2026-05-15','2026-05-15',NULL,'18:32','22:32',NULL,NULL),(38,'9047811146',6,'9047811146','ACCEPTED','2026-05-24','2026-05-25',NULL,'21:37','21:37',NULL,NULL),(39,'9047811146',10,'9047811140','ACCEPTED','2026-05-24','2026-05-25',NULL,'21:51','21:52',NULL,NULL),(40,'9047811146',3,'9047811146','CANCELLED_BY_DRIVER','2026-05-31','2026-06-04',NULL,'10:38','10:38',NULL,NULL),(41,'9047811146',4,'9047811146','CANCELLED_BY_DRIVER','2026-05-31','2026-05-31',NULL,'11:15','11:15',NULL,NULL),(42,'9047811146',3,'9047811146','CANCELLED_BY_OWNER','2026-05-31','2026-05-31',NULL,'11:35','11:35',NULL,NULL),(43,'9047811146',10,'9047811146','CANCELLED_BY_OWNER','2026-05-31','2026-05-31',NULL,'12:00','12:00',NULL,NULL),(44,'9047811146',10,'9047811146','CANCELLED_BY_DRIVER','2026-05-31','2026-05-31',NULL,'12:02','12:02','change plan',NULL),(45,'9047811146',10,'9047811146','CANCELLED_BY_DRIVER','2026-05-31','2026-05-31',NULL,'12:05','12:05','othr',NULL),(46,'9047811146',10,'9047811146','REJECTED','2026-05-31','2026-05-31',NULL,'12:05','12:05',NULL,NULL),(47,'9047811146',4,'9047811146','EXPIRED','2026-05-31','2026-05-31',NULL,'12:22','12:24',NULL,'2026-05-31 12:24:18'),(48,'9047811146',2,'9047811146','CANCELLED_BY_DRIVER','2026-05-31','2026-05-31',NULL,'12:37','12:37','change plan','2026-05-31 12:37:18'),(49,'9047811146',3,'9047811146','ACCEPTED','2026-05-31','2026-05-31',NULL,'12:37','12:37',NULL,'2026-05-31 12:37:38'),(50,'9047811146',2,'9047811146','ACCEPTED','2026-05-31','2026-05-31',NULL,'19:35','19:35',NULL,'2026-05-31 19:35:23'),(51,'9047811146',2,'9047811146','EXPIRED','2026-05-31','2026-05-31',NULL,'19:38','19:38',NULL,'2026-05-31 19:38:30');
/*!40000 ALTER TABLE `parking_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'santhoshkumare004@gmail.com','santhosh','12345','9047811140','owner'),(2,'GH@gmail.com','sara','123','9047811146','DRIVER'),(3,'santhoshkumar1004@gmail.com','sarath','7789','9047811147','DRIVER'),(4,'mani123@gmail.com','mani','1234','9361816131','DRIVER');
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

-- Dump completed on 2026-05-31 20:23:15
