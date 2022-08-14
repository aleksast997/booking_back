/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 10.4.18-MariaDB : Database - udemy-hibernate
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`udemy-hibernate` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `udemy-hibernate`;

/*Table structure for table `booking` */

DROP TABLE IF EXISTS `booking`;

CREATE TABLE `booking` (
  `idUser` bigint(20) NOT NULL,
  `idEvent` bigint(20) NOT NULL,
  `idTicketsCategory` bigint(20) DEFAULT NULL,
  `amount` bigint(20) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `idTicketsCategory_event` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idUser`,`idEvent`),
  KEY `sdsd` (`idEvent`),
  KEY `s` (`idTicketsCategory_event`),
  KEY `fk_ticketsCategory` (`idTicketsCategory`,`idTicketsCategory_event`),
  CONSTRAINT `FK81lk1eknh2kyskih5b9t2e07u` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKponpe5cqvycn6rk1tycnl09ye` FOREIGN KEY (`idEvent`) REFERENCES `event` (`idEvent`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_ticketsCategory` FOREIGN KEY (`idTicketsCategory`, `idTicketsCategory_event`) REFERENCES `ticketscategory` (`idTicketsCategory`, `idEvent`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `booking` */

insert  into `booking`(`idUser`,`idEvent`,`idTicketsCategory`,`amount`,`price`,`idTicketsCategory_event`) values 
(39,160,2,5,10000,160);

/*Table structure for table `event` */

DROP TABLE IF EXISTS `event`;

CREATE TABLE `event` (
  `idEvent` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(55) DEFAULT NULL,
  `description` varchar(55) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `idEventType` bigint(55) DEFAULT NULL,
  `idPlace` bigint(20) DEFAULT NULL,
  `idUser` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idEvent`),
  KEY `FKhod5ml6s60vvje70rfjofl6df` (`idEventType`),
  KEY `fk_place` (`idPlace`),
  KEY `fk_event_user` (`idUser`),
  CONSTRAINT `fk_event_type` FOREIGN KEY (`idEventType`) REFERENCES `eventtype` (`idEventType`) ON UPDATE CASCADE,
  CONSTRAINT `fk_event_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON UPDATE CASCADE,
  CONSTRAINT `fk_place` FOREIGN KEY (`idPlace`) REFERENCES `place` (`idPlace`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8mb4;

/*Data for the table `event` */

insert  into `event`(`idEvent`,`name`,`description`,`date`,`idEventType`,`idPlace`,`idUser`) values 
(156,'Neki novi dogadjaj 2',' asda','2022-01-27',1,2,40),
(157,'Neki novi dogadjaj 22',' sdad','2022-02-04',1,1,40),
(158,'Izmena imena',' adfads','2022-02-04',1,1,40),
(159,'Crvena Zvezda vs Partizan','Uzivajte uz najbolju kosarku 19:00','2022-02-21',2,1,40),
(160,'Zdravko Colic',' Koncert','2022-02-22',1,2,41),
(162,'Novi naziv dogajdja',' Opis dogadjaja','2022-02-22',1,2,40);

/*Table structure for table `eventtype` */

DROP TABLE IF EXISTS `eventtype`;

CREATE TABLE `eventtype` (
  `idEventType` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idEventType`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `eventtype` */

insert  into `eventtype`(`idEventType`,`description`,`name`) values 
(1,'Live music event','Concert'),
(2,'Sporting event','Sport'),
(3,'Movie theater event','Movie'),
(4,'Theater event','Theater play');

/*Table structure for table `place` */

DROP TABLE IF EXISTS `place`;

CREATE TABLE `place` (
  `idPlace` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(55) DEFAULT NULL,
  `maxCapacity` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPlace`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `place` */

insert  into `place`(`idPlace`,`name`,`maxCapacity`) values 
(1,'Arena',15000),
(2,'Sava Centar',4500),
(3,'Narodno pozoriste',700),
(4,'Bioskop sala 1',100),
(5,'Bioskop sala 2',150),
(6,'Hala Pionir',5000);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `idRole` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`idRole`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `role` */

insert  into `role`(`idRole`,`name`) values 
(1,'organizer'),
(2,'customer');

/*Table structure for table `ticketscategory` */

DROP TABLE IF EXISTS `ticketscategory`;

CREATE TABLE `ticketscategory` (
  `idTicketsCategory` bigint(20) NOT NULL AUTO_INCREMENT,
  `idEvent` bigint(20) NOT NULL,
  `name` varchar(55) DEFAULT NULL,
  `description` varchar(55) DEFAULT NULL,
  `maxSeats` int(11) DEFAULT NULL,
  `occupiedSeats` int(11) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idTicketsCategory`,`idEvent`),
  KEY `fk_event` (`idEvent`),
  CONSTRAINT `fk_event` FOREIGN KEY (`idEvent`) REFERENCES `event` (`idEvent`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ticketscategory` */

insert  into `ticketscategory`(`idTicketsCategory`,`idEvent`,`name`,`description`,`maxSeats`,`occupiedSeats`,`price`) values 
(1,156,'dasd','asdas',4,0,2),
(1,157,'sadsd','asdas',6,0,7),
(1,158,'asdasd','asdasd',10,0,5),
(1,159,'Sever','Ulaz sa severne strane.',3500,0,800),
(1,160,'Stajanje','Stajanje',2000,0,1500),
(1,162,'Tip 1','opis ulaznice',1000,0,1000),
(2,158,'cvxcvxc','xcvx',15,0,10),
(2,159,'Jug','Ulaz sa juzne strane.',3500,0,800),
(2,160,'Sedenje','Sedenje',2000,5,2000),
(3,159,'Istok','Ulaz sa istocne strane.',4000,0,1000),
(3,160,'Loza','Loza',20,0,5000),
(4,159,'Zapad','Ulaz sa zapadne strane.',4000,0,1200);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `idUser` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(55) DEFAULT NULL,
  `password` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user` */

insert  into `user`(`idUser`,`name`,`password`) values 
(39,'Aleksa','$2a$10$v7ZQIIzJM57MNgIuGnoYLOGk3digCBeC8agHk8c5K7pA5Gfox6LTq'),
(40,'organizer','$2a$10$M4EQr0.NBAbt8SneICBLLeSIFNb/WiKNz6jmFJTrWLfoLDDgITXui'),
(41,'organizer2','$2a$10$60vldtaaFAzyGUQsK34zKObka0jqJXehnJz3nCk2kP80w8KCR8BYq'),
(42,'customer','$2a$10$xef5BOEl1VzO7Zld0uYifODjp5ZhUftpDw291z/lUjPca72B/ygM6'),
(43,'customer2','$2a$10$am5Rc6z49DDVLoIRcLs4K.3qG7ZiJmBZW4P5XHcUiL4odODgVFLE6');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `idUser` bigint(20) NOT NULL,
  `idRole` bigint(20) NOT NULL,
  PRIMARY KEY (`idUser`,`idRole`),
  KEY `user/role/role` (`idRole`),
  CONSTRAINT `user/role/role` FOREIGN KEY (`idRole`) REFERENCES `role` (`idRole`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user/role/user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_role` */

insert  into `user_role`(`idUser`,`idRole`) values 
(39,2),
(40,1),
(41,1),
(42,2),
(43,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
