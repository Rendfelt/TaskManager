-- MySQL dump 10.13  Distrib 5.6.43, for Win64 (x86_64)
--
-- Host: localhost    Database: project_manager
-- ------------------------------------------------------
-- Server version	5.6.43

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

CREATE DATABASE `project_manager`
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects` (
  `id` varchar(40) NOT NULL,
  `userId` varchar(40) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_project_id` (`id`),
  KEY `FKixm2yykb90msu4oyyio3va8um` (`userId`),
  CONSTRAINT `FK_project_user_id` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  CONSTRAINT `FKixm2yykb90msu4oyyio3va8um` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES ('2423fc46-3dbb-404c-bd2b-9065bf4b5586','fd1bee5e-fb6b-42b8-b091-917ea658a4aa','Project_Name_104','Project_Description_225'),('48c61ec1-482b-44d6-bfb1-b14c10e50714','1fb5ed23-6d0e-4f6f-bb03-8552f54bcbbf','Project_Name_916','Project_Description_1'),('f67ba29b-8323-488c-b25d-2619e973a104','6748cab5-5972-4f95-9f96-55afb443a7f8','Project_Name_627','Project_Description_267');
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `id` varchar(40) NOT NULL,
  `userId` varchar(40) NOT NULL,
  `projectId` varchar(40) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_task_id` (`id`),
  KEY `FK_task_user_id` (`userId`),
  KEY `FK_task_project_id` (`projectId`),
  CONSTRAINT `FK_task_project_id` FOREIGN KEY (`projectId`) REFERENCES `projects` (`id`),
  CONSTRAINT `FK_task_user_id` FOREIGN KEY (`userId`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES ('0a67f16e-ea99-40ec-9634-fbe2188206e2','1fb5ed23-6d0e-4f6f-bb03-8552f54bcbbf','48c61ec1-482b-44d6-bfb1-b14c10e50714','Task_Name7','Task_Description_402'),('0f9d9aa7-b408-4659-bbac-37294f1dbcf6','fd1bee5e-fb6b-42b8-b091-917ea658a4aa','2423fc46-3dbb-404c-bd2b-9065bf4b5586','Task_Name759','Task_Description_402'),('39ba9092-3315-47a0-b8a9-24c4fd3ae9c5','fd1bee5e-fb6b-42b8-b091-917ea658a4aa','2423fc46-3dbb-404c-bd2b-9065bf4b5586','Task_Name333','Task_Description_698'),('39f62a18-7132-4d28-8871-400bd39cc320','fd1bee5e-fb6b-42b8-b091-917ea658a4aa','2423fc46-3dbb-404c-bd2b-9065bf4b5586','Task_Name669','Task_Description_914'),('4fb29522-7748-4c86-b101-4d4607722ec3','6748cab5-5972-4f95-9f96-55afb443a7f8','f67ba29b-8323-488c-b25d-2619e973a104','Task_Name820','Task_Description_609'),('5392255d-023c-4013-9688-f6c93c93549c','fd1bee5e-fb6b-42b8-b091-917ea658a4aa','2423fc46-3dbb-404c-bd2b-9065bf4b5586','Task_Name335','Task_Description_858'),('66a1f894-fd34-415e-9388-1ed48e6109cb','fd1bee5e-fb6b-42b8-b091-917ea658a4aa','2423fc46-3dbb-404c-bd2b-9065bf4b5586','Task_Name127','Task_Description_475'),('9560b849-1d68-47bb-a3a2-7e60c4dea8de','fd1bee5e-fb6b-42b8-b091-917ea658a4aa','2423fc46-3dbb-404c-bd2b-9065bf4b5586','Task_Name298','Task_Description_302'),('958077b6-4422-498d-ab2c-16d760eee598','6748cab5-5972-4f95-9f96-55afb443a7f8','f67ba29b-8323-488c-b25d-2619e973a104','Task_Name46','Task_Description_481'),('af2c49fd-b8bd-4e68-bef3-6233f5adec69','1fb5ed23-6d0e-4f6f-bb03-8552f54bcbbf','48c61ec1-482b-44d6-bfb1-b14c10e50714','Task_Name213','Task_Description_151'),('bd3bb0c5-d5c0-4d61-bb88-629918a39537','fd1bee5e-fb6b-42b8-b091-917ea658a4aa','2423fc46-3dbb-404c-bd2b-9065bf4b5586','Task_Name152','Task_Description_189');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` varchar(40) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UC_user_id` (`id`),
  UNIQUE KEY `ID_user_login` (`login`),
  UNIQUE KEY `UK_ow0gan20590jrb00upg3va2fn` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('1fb5ed23-6d0e-4f6f-bb03-8552f54bcbbf','login491','-482247052'),('23cc5b13-96cc-49a3-aad8-0a8c359d70ad','login980','-482247052'),('6748cab5-5972-4f95-9f96-55afb443a7f8','login421','-482247052'),('b6400229-db5a-42bb-b7f2-bc3f1058a525','login157','-482247052'),('fd1bee5e-fb6b-42b8-b091-917ea658a4aa','login642','-482247052');
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

-- Dump completed on 2019-01-28 16:08:55
