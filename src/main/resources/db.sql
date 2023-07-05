CREATE DATABASE  IF NOT EXISTS `diancan` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `diancan`;
-- MySQL dump 10.13  Distrib 5.7.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: diancan
-- ------------------------------------------------------
-- Server version	5.7.41-log

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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `eid` int(20) NOT NULL,
  `name` varchar(10) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `phone` varchar(11) NOT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `role` varchar(5) DEFAULT NULL,
  `idnum` varchar(18) NOT NULL,
  `estatus` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`eid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'admin','e10adc3949ba59abbe56e057f20f883e','12345678910','女','管理员','123456789101112131',1),(2,'张文迪','e10adc3949ba59abbe56e057f20f883e','15151515151','男','后厨','181818181818181818',1),(678224360,'管理员','e10adc3949ba59abbe56e057f20f883e','15677773333','女','管理员','159765200369422251',1),(1124066311,'测试4','202cb962ac59075b964b07152d234b70','15688881111','女','服务员','410777555623945501',0),(1216383252,'测试','81dc9bdb52d04dc20036dbd8313ed055','15677778888','男','后厨','410777555623945502',0),(1741638572,'测试2','e10adc3949ba59abbe56e057f20f883e','15799996666','女','服务员','410777555623945502',0);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(20) NOT NULL,
  `fimage` varchar(100) DEFAULT NULL,
  `finfo` varchar(200) NOT NULL,
  `fprice` decimal(10,2) NOT NULL,
  `fstatus` tinyint(2) NOT NULL DEFAULT '1',
  `ftype` varchar(20) NOT NULL,
  `hot` tinyint(4) DEFAULT '0',
  `sweet` tinyint(4) DEFAULT '0',
  `temp` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=369102851 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (200,'干炒牛河',NULL,'很多油',114.51,1,'粉',1,0,0),(201,'白粥',NULL,'很少米',4.00,1,'粥',0,0,1),(202,'湿炒牛河',NULL,'超级多油',15.00,1,'粉',1,0,0),(203,'榨菜',NULL,'超级下粥',0.50,0,'配菜',0,0,0),(204,'油焖大虾',NULL,'听说很好吃',45.90,1,'硬菜',0,0,0),(205,'葱烧海参',NULL,'不喜欢吃葱的别点',59.50,1,'硬菜',0,0,0),(369102850,'京酱肉丝','c/c/c/','testing',30.00,1,'不知道',1,1,1);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_detail` (
  `odid` bigint(20) NOT NULL,
  `oid` int(20) DEFAULT NULL,
  `fid` int(11) NOT NULL,
  `odname` varchar(20) DEFAULT NULL,
  `odimage` varchar(100) DEFAULT NULL,
  `odprice` decimal(10,2) DEFAULT NULL,
  `odcount` tinyint(8) DEFAULT NULL,
  `taste` varchar(45) DEFAULT NULL,
  `odstatus` tinyint(8) NOT NULL DEFAULT '0',
  PRIMARY KEY (`odid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1676159990336880641,1,200,'干炒牛河','c/c/c/',114.51,1,'很多香菜',0);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `oid` int(20) NOT NULL,
  `ostatus` tinyint(8) NOT NULL,
  `uid` int(20) DEFAULT NULL,
  `createtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `oprice` decimal(10,2) DEFAULT NULL,
  `tnum` int(20) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,1,1,'2023-07-04 15:40:18',50.10,1,'哈哈真好吃'),(2,1,1,'2023-07-04 15:42:26',130.50,2,'测试数据');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table`
--

DROP TABLE IF EXISTS `table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `table` (
  `tnum` int(10) NOT NULL AUTO_INCREMENT,
  `tstatus` tinyint(4) NOT NULL DEFAULT '0',
  `eid` int(20) DEFAULT NULL,
  PRIMARY KEY (`tnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table`
--

LOCK TABLES `table` WRITE;
/*!40000 ALTER TABLE `table` DISABLE KEYS */;
/*!40000 ALTER TABLE `table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uid` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `openid` varchar(100) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`uid`),
  UNIQUE KEY `openid_UNIQUE` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=1945539344 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'test','11111111111','男','','2023-06-28 15:14:36'),(202197340,'张文迪001',NULL,NULL,'2ssss2s11a5a','2023-07-05 15:12:27'),(789366060,'a',NULL,NULL,NULL,'2023-07-04 17:13:09'),(1096217441,'我',NULL,NULL,NULL,'2023-07-05 15:08:08'),(1164611487,'张文迪001',NULL,NULL,'strfasfdfsdg','2023-07-05 15:09:20'),(1209944061,'吴彦组2',NULL,NULL,'wuyanzu','2023-07-05 15:18:07'),(1305040571,'的',NULL,NULL,NULL,'2023-07-05 15:07:16'),(1491630384,'张文迪002',NULL,NULL,'lkjhgfdskjhgfd123hgfds45','2023-07-05 15:16:14'),(1945539343,'环境',NULL,NULL,NULL,'2023-07-05 15:18:40');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-05 15:34:07
