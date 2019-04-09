CREATE DATABASE IF NOT EXISTS `eavinlau` DEFAULT CHARACTER SET utf8;

CREATE TABLE `home` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `type` varchar(8) NOT NULL,
  `name` varchar(256) DEFAULT NULL,
  `title` varchar(128) DEFAULT NULL,
  `view` int(32) DEFAULT NULL,
  `ctime` varchar(32) DEFAULT NULL,
  `mtime` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert  into `home`(`id`,`type`,`name`,`title`,`view`,`ctime`,`mtime`) values (1,'d','刘浩阳.mp4','刘浩阳',0,'2019-04-09 18:01:40','2019-04-09 18:03:13');

CREATE TABLE `user` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) DEFAULT NULL,
  `type` varchar(8) DEFAULT NULL,
  `googleCode` varchar(32) DEFAULT NULL,
  `ctime` varchar(32) DEFAULT NULL,
  `mtime` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`,`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert  into `user`(`id`,`username`,`password`,`type`,`googleCode`,`ctime`,`mtime`) values (1,'eavinlau','e10adc3949ba59abbe56e057f20f883e','1','QYD2DDG5TSIUA32Z','2019-04-09 18:08:11','2019-04-09 18:08:11');
