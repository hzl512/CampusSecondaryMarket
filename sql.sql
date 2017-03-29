/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.28 : Database - db_campus_secondary_market
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_campus_secondary_market` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_campus_secondary_market`;

/*Table structure for table `t_buys` */

DROP TABLE IF EXISTS `t_buys`;

CREATE TABLE `t_buys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usersID` int(11) DEFAULT NULL,
  `buysName` varchar(500) DEFAULT NULL,
  `buysPrice` varchar(100) DEFAULT NULL,
  `buysAddress` varchar(2000) DEFAULT NULL,
  `buysDetail` varchar(2000) DEFAULT NULL,
  `buysPhone` varchar(500) DEFAULT NULL,
  `buysAddTime` varchar(100) DEFAULT NULL,
  `buysStatus` int(11) DEFAULT NULL,
  `buysQQ` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `t_buys` */

insert  into `t_buys`(`id`,`usersID`,`buysName`,`buysPrice`,`buysAddress`,`buysDetail`,`buysPhone`,`buysAddTime`,`buysStatus`,`buysQQ`) values (1,1,'男士自行车','￥100','校内','男士自行车','123456','2016/08/03 22:37',0,123456),(2,1,'123','123','123','123','123','2016/08/27 17:52',0,123),(3,1,'test','123','xiaonei','test','12312312','2016/08/27 17:54',0,1231231231),(4,1,'人民币','0.001','校内','求购人民币','123456','2016/12/20 14:55',0,123456),(5,1,'共享mi美女','￥1000','这里','美女','15859967033','2017/03/17 17:02',0,8635249);

/*Table structure for table `t_category` */

DROP TABLE IF EXISTS `t_category`;

CREATE TABLE `t_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(500) DEFAULT NULL,
  `categoryRemark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `t_category` */

insert  into `t_category`(`id`,`categoryName`,`categoryRemark`) values (1,'校园代步','校园代步'),(2,'手机','手机'),(3,'电脑','电脑'),(4,'数码配件','数码配件'),(5,'数码','数码'),(6,'电器','电器'),(7,'运动健身','运动健身'),(8,'衣服鞋帽','衣服鞋帽'),(9,'图书教材','图书教材'),(10,'租赁','租赁'),(11,'生活娱乐','生活娱乐'),(12,'其他','其他');

/*Table structure for table `t_commodity` */

DROP TABLE IF EXISTS `t_commodity`;

CREATE TABLE `t_commodity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usersID` int(11) DEFAULT NULL,
  `commodityImageUrl` varchar(2000) DEFAULT NULL,
  `commodityName` varchar(500) DEFAULT NULL,
  `commodityDetail` varchar(2000) DEFAULT NULL,
  `commodityAddress` varchar(1000) DEFAULT NULL,
  `commodityPrice` varchar(100) DEFAULT NULL,
  `categoryID` int(11) DEFAULT NULL,
  `commodityBargain` int(11) DEFAULT NULL,
  `commodityPhone` int(11) DEFAULT NULL,
  `commodityQQ` int(11) DEFAULT NULL,
  `commodityAddTime` varchar(100) DEFAULT NULL,
  `commodityStatus` int(11) DEFAULT NULL,
  `commodityViews` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `t_commodity` */

insert  into `t_commodity`(`id`,`usersID`,`commodityImageUrl`,`commodityName`,`commodityDetail`,`commodityAddress`,`commodityPrice`,`categoryID`,`commodityBargain`,`commodityPhone`,`commodityQQ`,`commodityAddTime`,`commodityStatus`,`commodityViews`) values (1,1,'goodsPicture/1195001359937.jpg','test','test','校内','￥50',11,0,123456,123456,'2016/08/06',0,17),(2,1,'goodsPicture/1195001141953.jpg','test1','test1','校内','￥20',1,0,123456,123456,'2016/08/06',0,11),(3,1,'goodsPicture/1195001612265.jpg','test2','test2','校内','￥100',3,1,123456,123456,'2016/08/06 18:01',0,4),(4,1,'goodsPicture/1472262819568.jpg','test1test1','test1test1test1test1','校内','￥20',1,0,123456,123456,'2016/08/14',0,7),(5,1,'goodsPicture/1473329082583.png','test1','test2','test3','￥50',1,0,123456,123456,'2016/09/08 18:04',0,5),(6,1,'goodsPicture/1473329360921.png','我的商品','test2','test3','￥50',1,0,123456,123456,'2016/09/08 18:09',0,3),(7,1,'goodsPicture/1473388275327.png','我的商品','test2','test3','￥50',1,0,123456,123456,'2016/09/09 10:31',0,5),(8,1,'goodsPicture/1473400782259.png','我的商品','test2','test3','￥50',1,0,123456,123456,'2016/09/09 13:59',0,5),(9,1,'goodsPicture/1473400923896.jpg','我的商品','test2','test3','￥50',1,0,123456,123456,'2016/09/09 14:02',0,8),(10,1,'goodsPicture/1473403720364.jpg','测试','小额贷款','中国','￥50',2,1,123456,123456,'2016/09/09 14:48',0,34),(11,1,'goodsPicture/1482135501410.jpg','测试','测试','中国','￥56',7,0,123456,123456,'2016/10/09',0,13),(12,1,'goodsPicture/1482135488210.jpg','测试题','测试','校内','￥100',10,0,123456,123456,'2016/12/14',0,3),(13,1,'goodsPicture/1482136247544.jpg','测试欧','Xxxx','校内','￥500',6,0,123456,123456,'2016/12/19 16:30',0,21),(14,1,'goodsPicture/1482216812732.jpg','特殊','啦啦啦','校外','￥100',3,0,123456,123456,'2016/12/20 14:53',0,5),(15,1,'goodsPicture/1490173105060.jpg','就是测试咯','你馁','中国','￥42000',2,0,123456,123456,'2017/03/22 16:58',0,3);

/*Table structure for table `t_departments` */

DROP TABLE IF EXISTS `t_departments`;

CREATE TABLE `t_departments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departmentsName` varchar(500) DEFAULT NULL,
  `departmentsRemark` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_departments` */

insert  into `t_departments`(`id`,`departmentsName`,`departmentsRemark`) values (1,'经济与管理学院','经济与管理学院'),(2,'机械与运载学院','机械与运载学院'),(3,'信息与控制学院','信息与控制学院'),(4,'艺术与传媒学院','艺术与传媒学院'),(5,'能源与水利学院','能源与水利学院'),(6,'生命工程学院','生命工程学院'),(7,'应用本科学院','应用本科学院');

/*Table structure for table `t_manage` */

DROP TABLE IF EXISTS `t_manage`;

CREATE TABLE `t_manage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `manageName` varchar(500) DEFAULT NULL,
  `managePassWord` varchar(500) DEFAULT NULL,
  `manageRemark` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_manage` */

insert  into `t_manage`(`id`,`manageName`,`managePassWord`,`manageRemark`) values (1,'admin','admin','超级管理员');

/*Table structure for table `t_profession` */

DROP TABLE IF EXISTS `t_profession`;

CREATE TABLE `t_profession` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `departmentsID` int(11) DEFAULT NULL,
  `professionName` varchar(500) DEFAULT NULL,
  `professionRemark` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

/*Data for the table `t_profession` */

insert  into `t_profession`(`id`,`departmentsID`,`professionName`,`professionRemark`) values (1,1,'市场营销','市场营销专业'),(2,1,'工商管理','工商管理专业'),(3,1,'会计学','会计学专业'),(4,1,'金融学','金融学专业'),(5,1,'国际经济与贸易','国际经济与贸易专业'),(6,1,'物流管理','物流管理专业'),(7,1,'网络营销方向','网络营销方向专业'),(8,1,'酒店管理专业','酒店管理'),(9,1,'人力资源管理','人力资源管理专业'),(10,1,'农林经济管理','农林经济管理专业'),(11,1,'农村区域发展','农村区域发展专业'),(12,2,'机械电子工程','机械电子工程专业'),(13,2,'机械设计制造及其自动化专业','机械设计制造及其自动化'),(14,2,'材料成型及控制工程','材料成型及控制工程专业'),(15,2,'车辆工程','车辆工程专业'),(16,2,'交通运输','交通运输专业'),(17,2,'汽车服务工程','汽车服务工程专业'),(18,2,'工业设计','工业设计专业'),(19,3,'计算机科学与技术','计算机科学与技术专业'),(20,3,'软件工程','软件工程专业'),(21,3,'物联网工程','物联网工程专业'),(22,3,'电子信息工程','电子信息工程专业'),(23,3,'通信工程','通信工程专业'),(24,3,'自动化专业','自动化'),(25,3,'电气工程及其自动化','电气工程及其自动化专业'),(26,3,'轨道交通信号与控制','轨道交通信号与控制专业'),(27,3,'建筑电气与智能化','建筑电气与智能化专业'),(28,4,'传播设计类','传播设计类专业'),(29,4,'形态规划设计类','形态规划设计类专业'),(30,4,'动画专业','动画专业'),(31,4,'视觉传达设计','视觉传达设计专业'),(32,4,'工艺美术','工艺美术专业'),(33,4,'风景园林','风景园林专业'),(34,4,'表演专业','表演专业'),(35,4,'产品设计','产品设计专业'),(36,4,'环境设计','环境设计专业'),(37,5,'弹药工程与爆炸技术','弹药工程与爆炸技术专业'),(38,5,'特种能源技术与工程','特种能源技术与工程专业'),(39,5,'水利水电工程','水利水电工程专业'),(40,5,'农业水利工程','农业水利工程专业'),(41,5,'工业工程','工业工程专业'),(42,5,'环境工程','环境工程专业'),(43,5,'安全工程','安全工程专业'),(44,5,'测绘工程','测绘工程专业'),(45,6,'设施农业科学与工程','设施农业科学与工程专业'),(46,6,'园艺专业（专升本）','园艺专业（专升本）'),(47,6,'食品科学与工程','食品科学与工程专业'),(48,6,'食品质量与安全','食品质量与安全专业'),(49,6,'生物工程','生物工程专业'),(50,6,'动物医学','动物医学专业'),(51,6,'动物科学','动物科学专业'),(52,6,'园林专业','园林专业'),(53,6,'农学专业','农学专业'),(54,6,'园艺专业','园艺专业'),(55,6,'植物保护','植物保护专业'),(56,7,'艺术设计（专升本）','艺术设计（专升本）'),(57,7,'会计学（专升本）','会计学（专升本）专业'),(58,7,'动物医学（专升本）','动物医学（专升本）专业'),(59,7,'机械设计制造及其自动化（专升本）','机械设计制造及其自动化（专升本）专业'),(60,7,'计算机科学与技术（专升本）','计算机科学与技术（专升本）专业'),(61,7,'物流管理（专科）','物流管理（专科）专业'),(62,7,'电气自动化技术（专科）','电气自动化技术（专科）专业'),(63,7,'工商企业管理（专升本）','工商企业管理（专升本）专业'),(64,7,'工商企业管理（专科）','工商企业管理（专科）专业'),(65,7,'会计电算化（专科）','会计电算化（专科）专业'),(66,7,'机械制造与自动化（专科）','机械制造与自动化（专科）专业'),(67,7,'计算机网络技术（专科）','计算机网络技术（专科）专业'),(68,7,'艺术设计（本科）','艺术设计（本科）专业'),(69,7,'动物医学（本科）','动物医学（本科）专业'),(70,7,'会计学（本科）','会计学（本科）专业'),(71,7,'工商企业管理（本科）','工商企业管理（本科）专业'),(72,7,'机械设计制造及其自动化（本科）','机械设计制造及其自动化（本科）专业'),(73,7,'计算机科学与技术（本科）','计算机科学与技术（本科）专业');

/*Table structure for table `t_update` */

DROP TABLE IF EXISTS `t_update`;

CREATE TABLE `t_update` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `versionName` varchar(500) DEFAULT NULL,
  `versionNumber` varchar(500) DEFAULT NULL,
  `forcedUpdate` int(11) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `url` varchar(2000) DEFAULT NULL,
  `createTime` varchar(500) DEFAULT NULL,
  `size` varchar(2000) DEFAULT NULL,
  `apkSize` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_update` */

insert  into `t_update`(`id`,`versionName`,`versionNumber`,`forcedUpdate`,`description`,`url`,`createTime`,`size`,`apkSize`) values (1,'1.0.1','2',0,'1：就是测试更新啦\r\n2：就是测试更新啦\r\n2：就是测试更新啦','http://192.168.1.226:8080/file/csma.apk','Tue Mar 21 16:43:35 CST 2017','6',NULL),(2,'1.0.1','3',0,'1：测试\r\n2：测试\r\n3：测试','http://192.168.1.226:8080/files/csma.apk','2017-03-21 17:06:49','6',NULL),(3,'1.0.1','1',0,'1：测试啊\r\n2：你说呢\r\n3：就是爽','http://192.168.1.226:8080/CampusSecondaryMarket/files/CSMA1.0.1.apk','2017-03-23 15:02:09','2.40M',NULL),(4,'1.0.2','2',0,'1：测试更新\r\n2：测试更新\r\n3：测试更新','http://192.168.1.226:8080/CampusSecondaryMarket/files/CSMA1.0.2.apk','2017-03-28 11:45:17','2.40M',2519496);

/*Table structure for table `t_users` */

DROP TABLE IF EXISTS `t_users`;

CREATE TABLE `t_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usersName` varchar(500) DEFAULT NULL,
  `usersPwd` varchar(500) DEFAULT NULL,
  `usersAddTime` varchar(100) DEFAULT NULL,
  `usersNickName` varchar(500) DEFAULT NULL,
  `usersPhone` varchar(500) DEFAULT NULL,
  `usersQQ` varchar(500) DEFAULT NULL,
  `departmentsID` int(11) DEFAULT NULL,
  `professionID` int(11) DEFAULT NULL,
  `usersGrade` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_users` */

insert  into `t_users`(`id`,`usersName`,`usersPwd`,`usersAddTime`,`usersNickName`,`usersPhone`,`usersQQ`,`departmentsID`,`professionID`,`usersGrade`) values (1,'test','123456','2016/08/02 23:09','test','123456','123456',1,1,2015),(2,'test1','123456','2017/03/15 17:37','不知道','15859967086','82654316',2,18,17);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
