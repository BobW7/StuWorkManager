/*
 Navicat Premium Data Transfer


 Source Server Type    : MySQL
 Source Server Version : 50740
 Source Schema         : school

 Target Server Type    : MySQL
 Target Server Version : 50740
 File Encoding         : 65001

 Date: 23/04/2023 11:21:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course_time` int(11) NOT NULL,
  `create_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `update_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, '英文', 12, '2023-03-31', '2023-04-23');
INSERT INTO `course` VALUES (2, '数学', 12, '2023-04-23', '2023-04-23');
INSERT INTO `course` VALUES (3, '物理', 22, '2023-04-23', '2023-04-23');
INSERT INTO `course` VALUES (4, '体育', 2, '2023-04-23', '2023-04-23');

-- ----------------------------
-- Table structure for s_homework
-- ----------------------------
DROP TABLE IF EXISTS `s_homework`;
CREATE TABLE `s_homework`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_homework
-- ----------------------------
INSERT INTO `s_homework` VALUES (1, '数学课堂练习1', '数学课堂练习1数学课堂练习1数学课堂练习1', '2023-04-23 00:00:00', '2023-04-23 00:00:00');
INSERT INTO `s_homework` VALUES (2, '英文课堂练习1', '英文课堂练习1英文课堂练习1英文课堂练习1', '2023-04-23 00:00:00', '2023-04-23 00:00:00');
INSERT INTO `s_homework` VALUES (3, '大一体育考试', '大一体育考试', '2023-04-23 00:00:00', '2023-04-23 00:00:00');

-- ----------------------------
-- Table structure for s_student
-- ----------------------------
DROP TABLE IF EXISTS `s_student`;
CREATE TABLE `s_student`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_student
-- ----------------------------
INSERT INTO `s_student` VALUES (2, '小明', '2023-04-23 00:00:00', '2023-04-23 00:00:00');
INSERT INTO `s_student` VALUES (3, '张三', '2023-04-23 00:00:00', '2023-04-23 00:00:00');
INSERT INTO `s_student` VALUES (6, '李四', '2023-04-23 00:00:00', '2023-04-23 00:00:00');

-- ----------------------------
-- Table structure for s_student_homework
-- ----------------------------
DROP TABLE IF EXISTS `s_student_homework`;
CREATE TABLE `s_student_homework`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) NOT NULL,
  `homework_id` bigint(20) NOT NULL,
  `homework_title` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `homework_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of s_student_homework
-- ----------------------------
INSERT INTO `s_student_homework` VALUES (1, 100, 100, '作业', 'https://www.google.com', '2020-03-03 12:00:00', NULL);
INSERT INTO `s_student_homework` VALUES (2, 123, 1, '数学课堂练习1', '英文课堂练习1英文课堂练习1英文课堂练习1', '2023-04-23 00:00:00', '2023-04-23 00:00:00');
INSERT INTO `s_student_homework` VALUES (3, 123, 3, '大一体育考试', '大一体育考试', '2023-04-23 00:00:00', '2023-04-23 00:00:00');

SET FOREIGN_KEY_CHECKS = 1;
