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
INSERT INTO `employee` VALUES (1,'20230706','e10adc3949ba59abbe56e057f20f883e','12345678910','女','管理员','123456789101112130',1),(2,'张文迪','e10adc3949ba59abbe56e057f20f883e','15151515151','男','后厨','181818181818181818',1),(678224360,'管理员','3b712de48137572f3849aabd5666a4e3','15677773333','女','管理员','159765200369422251',1),(1124066311,'测试4','202cb962ac59075b964b07152d234b70','15688881111','女','服务员','410777555623945501',0),(1216383252,'测试','81dc9bdb52d04dc20036dbd8313ed055','15677778888','男','后厨','410777555623945502',0),(1741638572,'测试2','e10adc3949ba59abbe56e057f20f883e','15799996666','女','服务员','410777555623945502',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (10,'测试','C:\\Users\\BricePC\\Desktop\\diancan\\src\\main\\resources\\static\\image\\hot.jpg','2023070601',19.00,1,'配菜',1,1,1),(11,'1','http://10.132.5.219:8080/food/c3ac06faf88a4cb59f5d1eb774689b66_hot.jpg','111',100.00,1,'配菜',1,0,0),(12,'1','blob:http://localhost:8081/696464be-7e9e-4708-83ae-67730dce1dc5','1',1.00,1,'配菜',1,1,1),(13,'1','blob:http://localhost:8081/3609481c-395d-4829-a26b-c7405937d48d','1',1.00,1,'配菜',1,1,1),(200,'干炒牛河','http://10.132.5.219:8080/food/4c86ba055f6047db963e8c5d85c442de_niuhe2.jpg','很多油',114.00,0,'粉',1,0,0),(201,'白粥','http://10.132.5.219:8080/food/8a54ebcf1a8a4446a6c69adcfa5d29f1_baizhou.jpg','很少米',4.00,1,'粥',0,0,1),(202,'湿炒牛河','http://10.132.5.219:8080/food/183058c8fe674521acbaad1dad2079e8_niuhe.jpg','超级多油',15.00,0,'粉',1,0,0),(203,'榨菜','http://10.132.5.219:8080/food/4f33365e055d4609b8bf7e7ed5edfa6d_zhacai.jpg','超级下粥',0.50,1,'配菜',0,0,0),(204,'油焖大虾','http://10.132.5.219:8080/food/9db990ccbe2f482fbd1305385d1b19d1_daxia.jpeg','听说很好吃',45.90,0,'硬菜',0,0,0),(205,'葱烧海参','http://10.132.5.219:8080/food/643571c9d92d44ddaf5d1a168afaa944_haishen.jpg','不喜欢吃葱的别点',59.50,0,'硬菜',0,0,0),(206,'0707','http://10.132.5.219:8080/food/6319d2f6fc5e4d1e98521eb937609b1b_food4.jpg','0707',26.00,1,'粉',1,0,0),(207,'070702','blob:http://localhost:8081/ebf4866c-74e1-4b4c-9114-9d765a5d0a09','11',0.00,1,'配菜',1,1,1);
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
INSERT INTO `order_detail` VALUES (1676159990336880641,1,200,'干炒牛河','http://10.132.5.219:8080/food/4c86ba055f6047db963e8c5d85c442de_niuhe2.jpg',114.51,1,'很多香菜',0);
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
INSERT INTO `orders` VALUES (1,1,1,'2023-07-04 15:40:18',50.10,1,'哈哈真好吃'),(2,4,1,'2023-07-04 15:42:26',130.50,2,'测试数据');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tables`
--

DROP TABLE IF EXISTS `tables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tables` (
  `tnum` int(10) NOT NULL AUTO_INCREMENT,
  `tstatus` tinyint(4) NOT NULL DEFAULT '0',
  `eid` int(20) DEFAULT NULL,
  PRIMARY KEY (`tnum`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tables`
--

LOCK TABLES `tables` WRITE;
/*!40000 ALTER TABLE `tables` DISABLE KEYS */;
INSERT INTO `tables` VALUES (1,1,NULL),(2,0,NULL),(3,1,NULL);
/*!40000 ALTER TABLE `tables` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2000920485 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'test','11111111111','男','','2023-06-28 15:14:36'),(33923379,'幽底蓝鲸',NULL,NULL,NULL,'2023-07-06 09:22:41'),(202197340,'张文迪001',NULL,NULL,'2ssss2s11a5a','2023-07-05 15:12:27'),(306365562,'jingming',NULL,NULL,'oVRuh5KVm_hh5Ve19igxUxDVzNS0','2023-07-05 16:03:45'),(412720407,'幽底蓝鲸',NULL,NULL,NULL,'2023-07-05 17:35:53'),(470050412,'幽底蓝鲸',NULL,NULL,NULL,'2023-07-05 17:30:31'),(498612667,'幽底蓝鲸',NULL,NULL,NULL,'2023-07-05 17:32:24'),(604945717,'幽底蓝鲸',NULL,NULL,NULL,'2023-07-05 17:31:47'),(789366060,'a',NULL,NULL,NULL,'2023-07-04 17:13:09'),(1096217441,'我',NULL,NULL,NULL,'2023-07-05 15:08:08'),(1121096541,'幽底蓝鲸',NULL,NULL,NULL,'2023-07-05 17:30:07'),(1138119826,'幽底蓝鲸',NULL,NULL,NULL,'2023-07-05 17:29:37'),(1164611487,'张文迪001',NULL,NULL,'strfasfdfsdg','2023-07-05 15:09:20'),(1209944061,'吴彦组2',NULL,NULL,'wuyanzu','2023-07-05 15:18:07'),(1305040571,'的',NULL,NULL,NULL,'2023-07-05 15:07:16'),(1476365982,'xiaoxiao',NULL,NULL,'123456','2023-07-05 17:34:17'),(1491630384,'张文迪002',NULL,NULL,'lkjhgfdskjhgfd123hgfds45','2023-07-05 15:16:14'),(1818934336,'幽底蓝鲸',NULL,NULL,NULL,'2023-07-05 17:17:08'),(1879747528,'幽底蓝鲸',NULL,NULL,NULL,'2023-07-05 17:21:36'),(1935993629,'jingming',NULL,NULL,NULL,'2023-07-05 15:52:19'),(1945539343,'环境',NULL,NULL,NULL,'2023-07-05 15:18:40'),(1982553818,'幽底蓝鲸',NULL,NULL,NULL,'2023-07-05 17:15:47'),(2000920484,'幽底蓝鲸',NULL,NULL,NULL,'2023-07-05 17:20:33');
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

-- Dump completed on 2023-07-07 10:31:39
