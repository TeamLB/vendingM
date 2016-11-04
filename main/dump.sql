-- MySQL dump 10.15  Distrib 10.0.17-MariaDB, for Win32 (AMD64)
--
-- Host: jycom.asuscomm.com    Database: vending
-- ------------------------------------------------------
-- Server version	5.7.11

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
-- Table structure for table `bstock`
--

DROP TABLE IF EXISTS `bstock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bstock` (
  `Bname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Bstock` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bstock`
--

LOCK TABLES `bstock` WRITE;
/*!40000 ALTER TABLE `bstock` DISABLE KEYS */;
INSERT INTO `bstock` VALUES ('5000won',4),('1000won',29),('500won',43),('100won',67),('50won',25),('10won',12);
/*!40000 ALTER TABLE `bstock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pstock`
--

DROP TABLE IF EXISTS `pstock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pstock` (
  `Pname` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `Pimage` longtext COLLATE utf8_unicode_ci NOT NULL,
  `Pprice` int(10) unsigned NOT NULL,
  `Pstock` int(10) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pstock`
--

LOCK TABLES `pstock` WRITE;
/*!40000 ALTER TABLE `pstock` DISABLE KEYS */;
INSERT INTO `pstock` VALUES ('白いサワー','../img/p1.jpg',500,32),('もも','../img/p2.jpg',1000,24),('りんご','../img/p3.jpg',500,12),('レモンサワー','../img/p4.jpg',1500,5),('はちみつレモン','../img/p5.jpg',500,16),('ぶどうサワー','../img/p6.jpg',1000,8),('梅酒サワー','../img/p7.jpg',500,22),('アイスティーサワー','../img/p8.jpg',1000,32),('カシスとオレンジ','../img/p9.jpg',1500,16),('ワインサワー','../img/p10.jpg',2000,5);
/*!40000 ALTER TABLE `pstock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesgraph`
--

DROP TABLE IF EXISTS `salesgraph`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salesgraph` (
  `product` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `sold` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesgraph`
--

LOCK TABLES `salesgraph` WRITE;
/*!40000 ALTER TABLE `salesgraph` DISABLE KEYS */;
INSERT INTO `salesgraph` VALUES ('もも',1);
/*!40000 ALTER TABLE `salesgraph` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salesvolume`
--

DROP TABLE IF EXISTS `salesvolume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `salesvolume` (
  `product` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `sold` int(10) unsigned NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salesvolume`
--

LOCK TABLES `salesvolume` WRITE;
/*!40000 ALTER TABLE `salesvolume` DISABLE KEYS */;
INSERT INTO `salesvolume` VALUES ('もも',1,'2015-05-17 10:56:29');
/*!40000 ALTER TABLE `salesvolume` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-17 11:00:17
