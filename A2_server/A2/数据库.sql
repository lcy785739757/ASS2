/*
SQLyog Ultimate v12.4.3 (64 bit)
MySQL - 5.5.49 : Database - mlearnserver
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mlearnserver` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mlearnserver`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `courseID` int(11) NOT NULL AUTO_INCREMENT,
  `courseName` varchar(100) DEFAULT NULL,
  `teacherName` varchar(100) DEFAULT NULL,
  `courseUrl` varchar(100) DEFAULT NULL,
  `courseAbstract` varchar(100) DEFAULT NULL,
  `courseType` int(11) DEFAULT NULL,
  `teacherID` int(11) DEFAULT NULL,
  `detailInfo` text,
  PRIMARY KEY (`courseID`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`courseID`,`courseName`,`teacherName`,`courseUrl`,`courseAbstract`,`courseType`,`teacherID`,`detailInfo`) values 
(1,'数据库应用系统综合实践','刘铎','res/course/1/cover.jpg','YF801',1,1,'数据库配套实践课\r\n肝就完了，哦里给'),
(2,'数据库系统','冯凤娟','res/course/2/cover.jpg','SX205',1,2,'《数据库系统》课程是为软件工程及相关专业本科二年级开设的核心专业课，旨在于培养学生掌握数据库系统的基本概念、建模思想和规范化的设计方法，了解数据库技术的最新发展动态，使之具有独立设计并实现数据库应用系统的能力，具备运行与维护数据库系统的技能；增强检索英文科技文献和阅读英文专业技术资料的能力，提高学生国际交流水平和自主学习国外先进技术的能力，将学生培养成具有国际竞争力的精英型软件人才。'),
(3,'操作系统','张迪','res/course/3/cover.jpg','YF306',1,4,'操作系统是软件工程专业学生必修的基础课程，也是软件工程专业主干课程。它理论性比较强，其内容综合了操作系统的基本结构和基础理论、设计思想和方法等，主要讲述操作系统的基本概念、基本原理及其实现技术，包括处理器管理、进程管理、存储器管理、文件管理、输入输出系统等内容。通过该课程的学习使学生能更好地理解和掌握操作系统的基本原理、结构和设计方法，理解用户与计算机系统的交互手段以及操作系统的工作过程，为学生后续学习和研究奠定坚实专业基础。'),
(4,'数据结构','严蔚敏','res/course/4/cover.jpg','YF404',1,3,'数据结构数据结构数\r\n据结构数据结构数据结构\r\n数据结构数据结构64654564\r\n数据结构数据结构数据结构\r\n数据结数据结构数据结构数据结构数据结构数据结构数据结构数据结构64654564数据结\r\n构数据结构数据结构数\r\n据结构数据结构数据结构561565构数据结构数据结构561565'),
(7,'c++','张三','res/course/7/cover.jpg','SY309',NULL,1,NULL),
(8,'java','李四','res/course/8/cover.jpg','DQ206',NULL,2,NULL),
(9,'软件知识法规','yuliya','res/course/9/cover.jpg','SY311',NULL,3,NULL),
(10,'线代辅导讲义','聂大大','res/course/10/cover.jpg','SY311',NULL,4,NULL);

/*Table structure for table `coursematerial` */

DROP TABLE IF EXISTS `coursematerial`;

CREATE TABLE `coursematerial` (
  `resID` int(11) NOT NULL AUTO_INCREMENT,
  `courseID` int(11) DEFAULT NULL,
  `publishTime` datetime DEFAULT NULL,
  `resTitle` varchar(100) DEFAULT NULL,
  `resUrl` varchar(100) DEFAULT NULL,
  `teacherID` int(11) DEFAULT NULL,
  `size` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`resID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `coursematerial` */

INSERT INTO `mlearnserver`.`coursematerial` (`resID`, `courseID`, `publishTime`, `resTitle`, `resUrl`, `teacherID`, `size`) VALUES ('1', '1', '2019-12-20 00:00:00', 'video.mp4', 'res/course/1/vtest.mp4', '1', '2842130');


/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `sex` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `signature` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `logintime` datetime DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`userID`,`username`,`password`,`sex`,`email`,`photo`,`signature`,`type`,`logintime`) values 
(1,'小埋','123','女','123@qq.com','res/user/123@qq.com/pic.jpg','真正的小埋',NULL,NULL),
(2,'路飞','123','男','456@qq.com','res/user/456@qq.com/pic.jpg',NULL,NULL,NULL),
(7,'王大锤','123','男','a@qq.com','res/user/a@qq.com/pic.jpg','我叫王大锤',0,NULL),
(9,'马云','123',NULL,'c@qq.com','res/user/c@qq.com/pic.jpg','我对钱不感兴趣',0,NULL),
(10,'芳心纵火犯','123',NULL,'d@qq.com','res/user/d@qq.com/pic.jpg','北大也还行',0,NULL),
(12,'阿拉蕾','123',NULL,'f@qq.com','res/user/f@qq.com/pic.jpg',NULL,0,NULL),
(13,'周杰伦','123',NULL,'g@qq.com','res/user/g@qq.com/pic.jpg','哎哟不错哟',0,NULL),
(16,'林俊杰','123',NULL,'p@qq.com','res/user/p@qq.com/pic.jpg',NULL,0,NULL),
(17,'我不是马化腾','123','男','mht@qq.com','res/user/mht@qq.com/pic.jpg','想一想，不充钱，你能变得更强吗？',0,NULL),
(19,'彭于晏','123','男','pyy@qq.com','res/user/pyy@qq.com/pic.jpg','清除心中的不可以，告诉自己我可以',0,NULL),
(21,'雷军','123','男','leijun@qq.com','res/user/leijun@qq.com/pic.jpg','Are you ok？',0,NULL);

/*Table structure for table `studentcourse` */

DROP TABLE IF EXISTS `studentcourse`;

CREATE TABLE `studentcourse` (
  `relationID` int(11) NOT NULL AUTO_INCREMENT,
  `courseID` int(11) DEFAULT NULL,
  `studentID` int(11) DEFAULT NULL,
  `studentGrade` int(11) DEFAULT NULL,
  `studentAnswer` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`relationID`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Data for the table `studentcourse` */


