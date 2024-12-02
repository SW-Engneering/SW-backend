-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: sportsmatch
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,         -- 사용자 ID (Primary Key)
  `team_id` int DEFAULT NULL,                    -- 팀 ID (Foreign Key)
  `passwd` varchar(50) NOT NULL,                -- 비밀번호
  `user_name` varchar(50) NOT NULL,             -- 사용자 이름
  `phone_number` varchar(15) NOT NULL,          -- 전화번호
  `nickname` varchar(50) NOT NULL,              -- 닉네임
  `regist_date` datetime DEFAULT CURRENT_TIMESTAMP, -- 등록 날짜
  `ban_yn` tinyint(1) DEFAULT '0',              -- 차단 여부
  `location` varchar(255) DEFAULT NULL,         -- 위치
  `age` int DEFAULT NULL,                       -- 나이
  `sex` char(1) DEFAULT NULL,                   -- 성별
  `position` varchar(255) DEFAULT NULL,         -- 포지션
  `profile_image` varchar(255) DEFAULT NULL,    -- 프로필 이미지
  `post_created_time` datetime(6) DEFAULT NULL, -- 게시글 생성 시간
  `post_updated_time` datetime(6) DEFAULT NULL, -- 게시글 수정 시간
  PRIMARY KEY (`user_id`),                      -- Primary Key
  CONSTRAINT `fk_user_team` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2024-12-01 15:10:39
