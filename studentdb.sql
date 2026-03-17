-- MySQL dump 10.13  Distrib 5.7.37, for Win64 (x86_64)
--
-- Host: localhost    Database: studentdb
-- ------------------------------------------------------
-- Server version	5.7.37-log

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
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `course_id` char(8) NOT NULL COMMENT '课程编号',
  `course_name` varchar(100) NOT NULL COMMENT '课程名称',
  `credit` decimal(3,1) NOT NULL COMMENT '学分',
  `hours` int(11) NOT NULL COMMENT '课时',
  `department_id` int(11) NOT NULL COMMENT '开课系',
  `teacher_id` int(11) NOT NULL COMMENT '授课教师',
  `classroom` varchar(50) DEFAULT NULL COMMENT '教室',
  `schedule` varchar(100) DEFAULT NULL COMMENT '上课时间',
  `academic_year` varchar(20) DEFAULT NULL COMMENT '学年',
  `semester` enum('春季','秋季') DEFAULT NULL COMMENT '学期',
  PRIMARY KEY (`course_id`),
  KEY `department_id` (`department_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `departments` (`department_id`),
  CONSTRAINT `courses_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('CS101','数据结构',4.0,64,1,1,'A201','周一1-2节，周三3-4节','2023-2024','秋季'),('CS102','数据库原理',3.5,56,1,2,'A202','周二1-2节，周四3-4节','2023-2024','秋季'),('CS103','算法设计与分析',3.0,48,1,3,'A203','周五1-4节','2023-2024','秋季'),('MA301','数学分析',5.0,80,3,7,'C301','周一5-6节，周三5-6节，周五1-2节','2023-2024','秋季'),('MA302','高等代数',4.5,72,3,8,'C302','周二5-6节，周四5-6节','2023-2024','秋季'),('MA303','解析几何',3.0,48,3,9,'C303','周五3-6节','2023-2024','秋季'),('ST201','概率论',4.0,64,2,4,'B101','周一3-4节，周三1-2节','2023-2024','秋季'),('ST202','数理统计',3.5,56,2,5,'B102','周二3-4节，周四1-2节','2023-2024','秋季'),('ST203','统计软件应用',2.5,40,2,6,'B103','周五5-8节','2023-2024','秋季');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departments` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(50) NOT NULL COMMENT '系名称',
  `office_location` varchar(100) DEFAULT NULL COMMENT '办公室位置',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `head_name` varchar(20) DEFAULT NULL COMMENT '系主任姓名',
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (1,'信息与计算科学系','学院楼A栋201','020-12345671','张教授'),(2,'统计学系','学院楼B栋102','020-12345672','李教授'),(3,'数学与应用数学系','学院楼C栋301','020-12345673','王教授');
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_courses`
--

DROP TABLE IF EXISTS `student_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_courses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` char(12) NOT NULL COMMENT '学号',
  `course_id` char(8) NOT NULL COMMENT '课程编号',
  `enrollment_date` date NOT NULL COMMENT '选课日期',
  `score` decimal(5,2) DEFAULT NULL COMMENT '成绩',
  `status` enum('选修中','已通过','未通过','退课') DEFAULT '选修中' COMMENT '选课状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_id` (`student_id`,`course_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `student_courses_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`) ON DELETE CASCADE,
  CONSTRAINT `student_courses_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_courses`
--

LOCK TABLES `student_courses` WRITE;
/*!40000 ALTER TABLE `student_courses` DISABLE KEYS */;
INSERT INTO `student_courses` VALUES (1,'202011010101','CS101','2023-09-01',85.50,'已通过'),(2,'202011010101','CS102','2023-09-01',92.00,'已通过'),(3,'202011010101','ST201','2023-09-01',78.50,'已通过'),(4,'202011010102','CS101','2023-09-01',90.00,'已通过'),(5,'202011010102','CS103','2023-09-01',88.00,'已通过'),(6,'202011010102','MA301','2023-09-01',76.50,'已通过'),(7,'202011010103','CS102','2023-09-01',82.00,'已通过'),(8,'202011010103','ST202','2023-09-01',95.00,'已通过'),(9,'202011010103','MA302','2023-09-01',89.50,'已通过'),(10,'202011010104','CS101','2023-09-01',91.50,'已通过'),(11,'202011010104','CS103','2023-09-01',84.00,'已通过'),(12,'202011010104','ST203','2023-09-01',79.00,'已通过'),(13,'202011010105','CS102','2023-09-01',87.50,'已通过'),(14,'202011010105','ST201','2023-09-01',93.00,'已通过'),(15,'202011010105','MA303','2023-09-01',81.00,'已通过'),(16,'202011020101','ST201','2023-09-01',88.50,'已通过'),(17,'202011020101','ST202','2023-09-01',92.50,'已通过'),(18,'202011020101','CS101','2023-09-01',76.00,'已通过'),(19,'202011020102','ST201','2023-09-01',94.00,'已通过'),(20,'202011020102','ST203','2023-09-01',89.00,'已通过'),(21,'202011020102','MA301','2023-09-01',82.50,'已通过'),(22,'202011020103','ST202','2023-09-01',85.50,'已通过'),(23,'202011020103','CS102','2023-09-01',91.00,'已通过'),(24,'202011020103','MA302','2023-09-01',78.00,'已通过'),(25,'202011020104','ST201','2023-09-01',90.50,'已通过'),(26,'202011020104','ST203','2023-09-01',87.00,'已通过'),(27,'202011020104','CS103','2023-09-01',83.50,'已通过'),(28,'202011020105','ST202','2023-09-01',96.00,'已通过'),(29,'202011020105','MA301','2023-09-01',89.50,'已通过'),(30,'202011020105','MA303','2023-09-01',77.00,'已通过'),(31,'202011030101','MA301','2023-09-01',93.50,'已通过'),(32,'202011030101','MA302','2023-09-01',88.00,'已通过'),(33,'202011030101','ST201','2023-09-01',84.50,'已通过'),(34,'202011030102','MA301','2023-09-01',97.00,'已通过'),(35,'202011030102','MA303','2023-09-01',91.50,'已通过'),(36,'202011030102','CS101','2023-09-01',79.00,'已通过'),(37,'202011030103','MA302','2023-09-01',86.50,'已通过'),(38,'202011030103','ST202','2023-09-01',92.00,'已通过'),(39,'202011030103','CS102','2023-09-01',80.50,'已通过'),(40,'202011030104','MA301','2023-09-01',89.00,'已通过'),(41,'202011030104','MA303','2023-09-01',94.50,'已通过'),(42,'202011030104','ST203','2023-09-01',87.00,'已通过'),(43,'202011030105','MA302','2023-09-01',95.50,'已通过'),(44,'202011030105','CS103','2023-09-01',82.00,'已通过'),(45,'202011030105','ST201','2023-09-01',90.00,'已通过');
/*!40000 ALTER TABLE `student_courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `student_id` char(12) NOT NULL COMMENT '学号',
  `student_name` varchar(20) NOT NULL COMMENT '学生姓名',
  `gender` enum('男','女') NOT NULL COMMENT '性别',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  `department_id` int(11) NOT NULL COMMENT '所属系',
  `enrollment_date` date NOT NULL COMMENT '入学日期',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `address` varchar(200) DEFAULT NULL COMMENT '家庭住址',
  `status` enum('在读','休学','退学','毕业') DEFAULT '在读' COMMENT '学籍状态',
  PRIMARY KEY (`student_id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `students_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `departments` (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES ('202011010101','张三','男','2002-05-10',1,'2020-09-01','13800138101','zhangsan@example.com','广东省广州市天河区','在读'),('202011010102','李四','女','2001-08-15',1,'2020-09-01','13800138102','lisi@example.com','广东省广州市番禺区','在读'),('202011010103','王五','男','2002-03-20',1,'2020-09-01','13800138103','wangwu@example.com','广东省深圳市南山区','在读'),('202011010104','赵六','女','2001-11-05',1,'2020-09-01','13800138104','zhaoliu@example.com','广东省珠海市香洲区','在读'),('202011010105','钱七','男','2002-01-30',1,'2020-09-01','13800138105','qianqi@example.com','广东省佛山市南海区','在读'),('202011020101','孙八','女','2002-04-12',2,'2020-09-01','13800138106','sunba@example.com','广东省东莞市','在读'),('202011020102','周九','男','2001-07-25',2,'2020-09-01','13800138107','zhoujiu@example.com','广东省中山市','在读'),('202011020103','吴十','女','2002-02-18',2,'2020-09-01','13800138108','wushi@example.com','广东省惠州市','在读'),('202011020104','郑十一','男','2001-09-08',2,'2020-09-01','13800138109','zhengshiyi@example.com','广东省汕头市','在读'),('202011020105','王十二','女','2002-06-22',2,'2020-09-01','13800138110','wangshier@example.com','广东省江门市','在读'),('202011030101','刘十三','男','2002-03-15',3,'2020-09-01','13800138111','liushisan@example.com','广东省湛江市','在读'),('202011030102','陈十四','女','2001-10-28',3,'2020-09-01','13800138112','chenshisi@example.com','广东省茂名市','在读'),('202011030103','林十五','男','2002-01-05',3,'2020-09-01','13800138113','linshiwu@example.com','广东省肇庆市','在读'),('202011030104','黄十六','女','2001-12-20',3,'2020-09-01','13800138114','huangshiliu@example.com','广东省清远市','在读'),('202011030105','杨十七','男','2002-07-30',3,'2020-09-01','13800138115','yangshiqi@example.com','广东省揭阳市','在读');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teachers` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_name` varchar(20) NOT NULL COMMENT '教师姓名',
  `gender` enum('男','女') NOT NULL COMMENT '性别',
  `title` varchar(20) DEFAULT NULL COMMENT '职称',
  `department_id` int(11) NOT NULL COMMENT '所属系',
  `hire_date` date NOT NULL COMMENT '入职日期',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  PRIMARY KEY (`teacher_id`),
  KEY `department_id` (`department_id`),
  CONSTRAINT `teachers_ibfk_1` FOREIGN KEY (`department_id`) REFERENCES `departments` (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
INSERT INTO `teachers` VALUES (1,'张明','男','教授',1,'2010-08-15','13800138001','zhangming@example.com'),(2,'李华','女','副教授',1,'2015-03-20','13800138002','lihua@example.com'),(3,'王强','男','讲师',1,'2018-09-01','13800138003','wangqiang@example.com'),(4,'刘芳','女','教授',2,'2009-06-10','13800138004','liufang@example.com'),(5,'陈伟','男','副教授',2,'2014-02-15','13800138005','chenwei@example.com'),(6,'黄静','女','讲师',2,'2019-08-20','13800138006','huangjing@example.com'),(7,'周涛','男','教授',3,'2008-09-01','13800138007','zhoutao@example.com'),(8,'吴敏','女','副教授',3,'2013-07-15','13800138008','wumin@example.com'),(9,'郑杰','男','讲师',3,'2020-03-10','13800138009','zhengjie@example.com');
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-19 22:34:14
