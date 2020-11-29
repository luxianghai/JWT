CREATE DATABASE JWT;
CREATE TABLE `user`
(
	id INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(30),
	`password` VARCHAR(18)
);


INSERT INTO `user`(`name`,`password`) VALUES('xiaochen','123456');
SELECT `id`,`name`,`password` FROM `user`;