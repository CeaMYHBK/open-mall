/*
SQLyog Enterprise v12.08 (32 bit)
MySQL - 5.7.33-log : Database - ceam_mall
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ceam_mall` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `ceam_mall`;

/*Table structure for table `ceam_commercial` */

DROP TABLE IF EXISTS `ceam_commercial`;

CREATE TABLE `ceam_commercial` (
  `id` bigint(20) NOT NULL,
  `title` varchar(64) NOT NULL COMMENT '广告标题',
  `link` varchar(255) NOT NULL COMMENT '所广告的商品页面或者活动页面链接地址',
  `url` varchar(255) NOT NULL COMMENT '广告宣传图片',
  `position` int(2) DEFAULT NULL COMMENT '广告位置：1则是首页',
  `description` varchar(255) DEFAULT NULL COMMENT '活动内容',
  `start_time` datetime DEFAULT NULL COMMENT '广告开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '广告结束时间',
  `deleted` tinyint(1) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `add_by` bigint(20) DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `upd_by` bigint(20) DEFAULT NULL,
  `status` int(2) DEFAULT NULL COMMENT '1启用，0禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='广告表';

/*Data for the table `ceam_commercial` */

insert  into `ceam_commercial`(`id`,`title`,`link`,`url`,`position`,`description`,`start_time`,`end_time`,`deleted`,`add_time`,`add_by`,`upd_time`,`upd_by`,`status`) values (1,'护肤品','1','../../static/images/taobao.png',1,NULL,'2023-01-28 10:08:51','2023-01-31 10:08:54',0,'2023-01-28 10:09:14',1,'2023-01-28 10:09:16',1,1),(2,'女装','2','../../static/images/taobao.png',2,NULL,'2023-01-28 10:10:09','2023-01-31 10:10:13',0,'2023-01-28 10:10:17',1,'2023-01-28 10:10:21',1,1);

/*Table structure for table `ceam_coupon` */

DROP TABLE IF EXISTS `ceam_coupon`;

CREATE TABLE `ceam_coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) CHARACTER SET utf8 NOT NULL COMMENT '优惠券名称',
  `description` varchar(127) CHARACTER SET utf8 DEFAULT '' COMMENT '优惠券介绍，通常是显示优惠券使用限制文字',
  `tag` varchar(63) CHARACTER SET utf8 DEFAULT '' COMMENT '优惠券标签，例如新人专用',
  `total` int(11) NOT NULL DEFAULT '0' COMMENT '优惠券数量，如果是0，则是无限量',
  `discount` decimal(10,2) DEFAULT '0.00' COMMENT '优惠金额，',
  `min` decimal(10,2) DEFAULT '0.00' COMMENT '最少消费金额才能使用优惠券。',
  `limit_num` smallint(6) DEFAULT '1' COMMENT '用户领券限制数量，如果是0，则是不限制；默认是1，限领一张.',
  `type` smallint(6) DEFAULT '0' COMMENT '优惠券赠送类型，如果是0则通用券，用户领取；如果是1，则是注册赠券；如果是2，则是优惠券码兑换；',
  `status` smallint(6) DEFAULT '0' COMMENT '优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。',
  `goods_type` smallint(6) DEFAULT '0' COMMENT '商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。',
  `goods_value` varchar(255) CHARACTER SET utf8 DEFAULT '[]' COMMENT '商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。',
  `code` varchar(63) CHARACTER SET utf8 DEFAULT NULL COMMENT '优惠券兑换码',
  `time_type` smallint(6) DEFAULT '0' COMMENT '有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期；',
  `days` smallint(6) DEFAULT '0' COMMENT '基于领取时间的有效天数days。',
  `start_time` date DEFAULT NULL COMMENT '使用券开始时间',
  `end_time` date DEFAULT NULL COMMENT '使用券截至时间',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `upd_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='优惠券信息及规则表';

/*Data for the table `ceam_coupon` */

insert  into `ceam_coupon`(`id`,`name`,`description`,`tag`,`total`,`discount`,`min`,`limit_num`,`type`,`status`,`goods_type`,`goods_value`,`code`,`time_type`,`days`,`start_time`,`end_time`,`add_time`,`upd_time`,`deleted`) values (1,'注册赠送优惠券','往往','1',0,'11.00','11.00',1,0,0,0,'[]','1',1,1,'2023-02-08','2023-02-28','2023-02-08 19:06:58','2023-02-08 19:07:00',0);

/*Table structure for table `ceam_customer_coupon` */

DROP TABLE IF EXISTS `ceam_customer_coupon`;

CREATE TABLE `ceam_customer_coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) DEFAULT NULL,
  `coupon_id` bigint(20) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  `used_time` datetime DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `order_sn` varchar(100) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `upd_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ceam_customer_coupon` */

insert  into `ceam_customer_coupon`(`id`,`customer_id`,`coupon_id`,`status`,`used_time`,`start_time`,`end_time`,`order_sn`,`add_time`,`upd_time`,`deleted`) values (1,1,1,1,'2023-02-08 19:07:20','2023-02-08 19:07:22','2023-02-08 19:07:24',NULL,'2023-02-08 19:07:30','2023-02-08 19:07:33',0);

/*Table structure for table `ceam_customer_info` */

DROP TABLE IF EXISTS `ceam_customer_info`;

CREATE TABLE `ceam_customer_info` (
  `id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `ceam_customer_info` */

/*Table structure for table `ceam_customer_level` */

DROP TABLE IF EXISTS `ceam_customer_level`;

CREATE TABLE `ceam_customer_level` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '会员名称',
  `money` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '购买金额',
  `valid_date` int(11) NOT NULL DEFAULT '0' COMMENT '有效时间',
  `is_forever` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否为永久会员',
  `is_pay` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否购买,1购买,0不购买',
  `is_show` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否显示 1显示,0隐藏',
  `grade` int(11) NOT NULL DEFAULT '0' COMMENT '会员等级',
  `discount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '享受折扣',
  `image` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '会员卡背景',
  `icon` varchar(255) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '会员图标',
  `remark` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '说明',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `upd_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='设置用户等级表';

/*Data for the table `ceam_customer_level` */

insert  into `ceam_customer_level`(`id`,`name`,`money`,`valid_date`,`is_forever`,`is_pay`,`is_show`,`grade`,`discount`,`image`,`icon`,`remark`,`add_time`,`upd_time`,`deleted`) values (7,'黄金会员','0.00',133,1,0,1,5,'55.00','http://pic.dayouqiantu.cn/5c9ccca8b27f1.jpg','http://pic.dayouqiantu.cn/5c9ccca8aa5b9.png',NULL,'2023-02-02 22:11:11','2023-02-02 22:11:11',0),(8,'白银会员','0.00',133333,1,0,1,4,'443.00','','http://pic.dayouqiantu.cn/5c9ccca8a27f0.png',NULL,'2023-02-02 22:15:16','2023-02-02 22:15:16',0);

/*Table structure for table `ceam_footprints` */

DROP TABLE IF EXISTS `ceam_footprints`;

CREATE TABLE `ceam_footprints` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `goods_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '浏览商品ID',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `upd_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='用户浏览足迹表';

/*Data for the table `ceam_footprints` */

insert  into `ceam_footprints`(`id`,`customer_id`,`goods_id`,`add_time`,`upd_time`,`deleted`) values (1,1,1,'2023-02-04 19:39:32','2023-02-04 19:39:34',0);

/*Table structure for table `ceam_goods` */

DROP TABLE IF EXISTS `ceam_goods`;

CREATE TABLE `ceam_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_sn` varchar(36) DEFAULT NULL COMMENT '商品编号',
  `name` varchar(128) DEFAULT NULL COMMENT '商品名称',
  `category_id` bigint(20) DEFAULT NULL COMMENT '分类ID',
  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌ID',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `price` varchar(100) DEFAULT NULL COMMENT '价格',
  `marking_price` decimal(12,2) DEFAULT NULL COMMENT '市场价',
  `member_price` decimal(12,2) DEFAULT NULL COMMENT '会员价',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序',
  `is_new` tinyint(1) DEFAULT NULL COMMENT '是否新品首发，如果设置则可以在新品首发页面展示',
  `is_hot` tinyint(1) DEFAULT NULL COMMENT '是否人气推荐，如果设置则可以在人气推荐页面展示',
  `pic_url` varchar(255) DEFAULT NULL,
  `goods_type` int(2) DEFAULT NULL COMMENT '商品类型',
  `sales` int(11) DEFAULT NULL COMMENT '销量',
  `status` int(2) DEFAULT NULL COMMENT '1上架，2下架',
  `deleted` tinyint(1) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='商品信息表';

/*Data for the table `ceam_goods` */

insert  into `ceam_goods`(`id`,`goods_sn`,`name`,`category_id`,`brand_id`,`stock`,`price`,`marking_price`,`member_price`,`sort_order`,`is_new`,`is_hot`,`pic_url`,`goods_type`,`sales`,`status`,`deleted`,`add_time`) values (1,'1313','欧莱雅',NULL,NULL,13330,'133',NULL,NULL,NULL,0,1,'https://cbu01.alicdn.com/img/ibank/2019/076/261/11884162670_1376630459.400x400.jpg',NULL,13320,NULL,0,'2023-02-03 11:10:53'),(2,'1234','小米',NULL,NULL,1332,'1122',NULL,NULL,NULL,0,1,'https://cbu01.alicdn.com/img/ibank/2019/076/261/11884162670_1376630459.400x400.jpg',NULL,1,NULL,0,'2023-02-09 16:25:36'),(3,'1344','OPPO',NULL,NULL,1234,'1334',NULL,NULL,NULL,0,1,'https://cbu01.alicdn.com/img/ibank/2019/076/261/11884162670_1376630459.400x400.jpg',NULL,1,NULL,0,'2023-02-09 16:26:42');

/*Table structure for table `ceam_goods_bargain` */

DROP TABLE IF EXISTS `ceam_goods_bargain`;

CREATE TABLE `ceam_goods_bargain` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '砍价产品ID',
  `goods_id` bigint(20) unsigned NOT NULL COMMENT '关联产品ID',
  `title` varchar(255) NOT NULL COMMENT '砍价活动名称',
  `pic_url` varchar(150) NOT NULL COMMENT '砍价活动图片',
  `unit_name` varchar(16) DEFAULT NULL COMMENT '单位名称',
  `stock` int(10) unsigned DEFAULT NULL COMMENT '库存',
  `sales` int(10) unsigned DEFAULT NULL COMMENT '销量',
  `images` varchar(2000) NOT NULL COMMENT '砍价产品轮播图',
  `start_time` datetime NOT NULL COMMENT '砍价开启时间',
  `end_time` datetime NOT NULL COMMENT '砍价结束时间',
  `store_name` varchar(255) DEFAULT NULL COMMENT '砍价产品名称',
  `price` decimal(8,2) unsigned DEFAULT NULL COMMENT '砍价金额',
  `min_price` decimal(8,2) unsigned DEFAULT NULL COMMENT '砍价商品最低价',
  `num` int(10) unsigned DEFAULT NULL COMMENT '每次购买的砍价产品数量',
  `bargain_max_price` decimal(8,2) unsigned DEFAULT NULL COMMENT '用户每次砍价的最大金额',
  `bargain_min_price` decimal(8,2) unsigned DEFAULT NULL COMMENT '用户每次砍价的最小金额',
  `bargain_num` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '用户每次砍价的次数',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '砍价状态 0(到砍价时间不自动开启)  1(到砍价时间自动开启时间)',
  `description` varchar(255) DEFAULT NULL COMMENT '砍价详情',
  `give_integral` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '反多少积分',
  `info` varchar(255) DEFAULT NULL COMMENT '砍价活动简介',
  `cost` decimal(8,2) unsigned DEFAULT NULL COMMENT '成本价',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否删除 0未删除 1删除',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `upd_time` datetime DEFAULT NULL,
  `rule` varchar(255) DEFAULT NULL COMMENT '砍价规则',
  `look` int(10) unsigned DEFAULT '0' COMMENT '砍价产品浏览量',
  `share` int(10) unsigned DEFAULT '0' COMMENT '砍价产品分享量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='砍价表';

/*Data for the table `ceam_goods_bargain` */

insert  into `ceam_goods_bargain`(`id`,`goods_id`,`title`,`pic_url`,`unit_name`,`stock`,`sales`,`images`,`start_time`,`end_time`,`store_name`,`price`,`min_price`,`num`,`bargain_max_price`,`bargain_min_price`,`bargain_num`,`status`,`description`,`give_integral`,`info`,`cost`,`sort`,`deleted`,`add_time`,`upd_time`,`rule`,`look`,`share`) values (14,1,'1','https://cbu01.alicdn.com/img/ibank/2019/076/261/11884162670_1376630459.400x400.jpg','1',1,1,'1','2023-02-03 20:55:43','2023-02-03 20:55:47','1','133.00','133.00',1,'1.00','1.00',1,1,'1','0.00','1','1.00',0,0,'2023-02-03 20:56:15','2023-02-03 20:56:17','1',0,0);

/*Table structure for table `ceam_goods_brand` */

DROP TABLE IF EXISTS `ceam_goods_brand`;

CREATE TABLE `ceam_goods_brand` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '品牌商名称',
  `brands_desc` varchar(255) NOT NULL DEFAULT '' COMMENT '品牌商简介',
  `pic_url` varchar(255) NOT NULL DEFAULT '' COMMENT '品牌商页的品牌商图片',
  `sort_order` int(3) DEFAULT '50',
  `floor_price` decimal(10,2) DEFAULT '0.00' COMMENT '品牌商的商品低价，仅用于页面展示',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `upde_time` datetime DEFAULT NULL COMMENT '更新时间',
  `share_url` varchar(255) DEFAULT NULL COMMENT '分享二维码图片',
  `add_by` bigint(20) DEFAULT NULL COMMENT '管理用户id',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  `commpany` varchar(128) DEFAULT NULL,
  `auto_update_good` tinyint(1) DEFAULT NULL COMMENT '自动监控更新商品',
  `shop_url` varchar(255) DEFAULT NULL COMMENT '店铺url地址',
  `default_cate_id` bigint(20) DEFAULT NULL COMMENT '默认的商品类别id',
  `default_pages` int(4) DEFAULT NULL COMMENT '默认商品页面数',
  `add_precent` int(4) DEFAULT NULL COMMENT '店铺商品溢价',
  `address` varchar(127) DEFAULT NULL COMMENT '提货地址',
  `longitude` decimal(10,7) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10,7) DEFAULT NULL COMMENT '纬度',
  `fetch_time_rules` varchar(255) DEFAULT NULL COMMENT '提货时间配置',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='品牌商表';

/*Data for the table `ceam_goods_brand` */

/*Table structure for table `ceam_goods_category` */

DROP TABLE IF EXISTS `ceam_goods_category`;

CREATE TABLE `ceam_goods_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品分类表ID',
  `pid` bigint(20) NOT NULL COMMENT '父id',
  `cate_name` varchar(100) NOT NULL COMMENT '分类名称',
  `sort` mediumint(9) DEFAULT NULL COMMENT '排序',
  `pic` varchar(128) DEFAULT '' COMMENT '图标',
  `is_show` tinyint(1) DEFAULT '1' COMMENT '是否推荐',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `upd_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) unsigned DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `pid` (`pid`) USING BTREE,
  KEY `is_base` (`is_show`) USING BTREE,
  KEY `sort` (`sort`) USING BTREE,
  KEY `add_time` (`add_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='商品分类表';

/*Data for the table `ceam_goods_category` */

insert  into `ceam_goods_category`(`id`,`pid`,`cate_name`,`sort`,`pic`,`is_show`,`add_time`,`upd_time`,`deleted`) values (11,0,'每日签到',1,'/static/images/sing.png',1,'2023-01-29 23:52:19','2023-01-29 23:52:22',0),(12,0,'手机数码',2,'/static/images/sj.png',1,'2023-02-08 20:46:05','2023-02-08 20:46:07',0),(13,0,'砍价活动',3,'/static/images/kj.png',1,'2023-02-08 20:46:30','2023-02-08 20:46:33',0),(14,0,'秒杀活动',4,'/static/images/seckill.png',1,'2023-02-08 20:57:45','2023-02-08 20:57:47',0),(15,0,'护肤美妆',5,'/static/images/mz.png',1,'2023-02-08 21:00:14','2023-02-08 21:00:17',0),(16,0,'抽奖活动',6,'/static/images/cj.png',1,'2023-02-08 21:07:10','2023-02-08 21:07:12',0),(17,0,'积分商品',7,'/static/images/jf.png',1,'2023-02-08 21:40:14','2023-02-08 21:40:16',0),(18,0,'领券中心',8,'/static/images/yhj.png',1,'2023-02-08 21:41:33','2023-02-08 21:41:36',0);

/*Table structure for table `ceam_goods_collect` */

DROP TABLE IF EXISTS `ceam_goods_collect`;

CREATE TABLE `ceam_goods_collect` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `value_id` int(11) NOT NULL DEFAULT '0' COMMENT '如果type=0，则是商品ID；如果type=1，则是专题ID',
  `type` int(2) NOT NULL DEFAULT '0' COMMENT '收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `upd_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`customer_id`) USING BTREE,
  KEY `goods_id` (`value_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='收藏表';

/*Data for the table `ceam_goods_collect` */

insert  into `ceam_goods_collect`(`id`,`customer_id`,`value_id`,`type`,`add_time`,`upd_time`,`deleted`) values (82,1,1,0,'2023-02-04 19:38:09','2023-02-04 19:38:13',0);

/*Table structure for table `ceam_goods_seckill` */

DROP TABLE IF EXISTS `ceam_goods_seckill`;

CREATE TABLE `ceam_goods_seckill` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品秒杀产品表id',
  `goods_id` bigint(20) unsigned NOT NULL COMMENT '商品id',
  `pic_url` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '推荐图',
  `images` varchar(2000) CHARACTER SET utf8 NOT NULL COMMENT '轮播图',
  `title` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '活动标题',
  `info` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '简介',
  `price` decimal(10,2) unsigned DEFAULT NULL COMMENT '价格',
  `cost` decimal(8,2) unsigned DEFAULT '0.00' COMMENT '成本',
  `origin_price` decimal(10,2) unsigned DEFAULT NULL COMMENT '原价',
  `give_integral` decimal(10,2) unsigned DEFAULT NULL COMMENT '返多少积分',
  `sort` int(10) unsigned NOT NULL COMMENT '排序',
  `stock` int(10) unsigned NOT NULL COMMENT '库存',
  `sales` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '销量',
  `unit_name` varchar(16) CHARACTER SET utf8 NOT NULL COMMENT '单位名',
  `description` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '内容',
  `start_time` date NOT NULL COMMENT '开始时间',
  `end_time` date NOT NULL COMMENT '结束时间',
  `add_time` datetime NOT NULL COMMENT '添加时间',
  `upd_time` datetime DEFAULT NULL,
  `status` int(3) unsigned NOT NULL COMMENT '产品状态',
  `deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '删除 0未删除1已删除',
  `max_num` int(10) unsigned NOT NULL COMMENT '最多秒杀几个',
  `is_show` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '显示',
  `time_id` int(10) unsigned DEFAULT '0' COMMENT '时间段id',
  `spec_type` tinyint(1) DEFAULT NULL COMMENT '规格 0单 1多',
  `temp_id` int(11) DEFAULT NULL COMMENT '运费模板id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `product_id` (`goods_id`) USING BTREE,
  KEY `start_time` (`start_time`,`end_time`) USING BTREE,
  KEY `is_del` (`deleted`) USING BTREE,
  KEY `is_show` (`status`) USING BTREE,
  KEY `add_time` (`add_time`) USING BTREE,
  KEY `sort` (`sort`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='商品秒杀产品表';

/*Data for the table `ceam_goods_seckill` */

insert  into `ceam_goods_seckill`(`id`,`goods_id`,`pic_url`,`images`,`title`,`info`,`price`,`cost`,`origin_price`,`give_integral`,`sort`,`stock`,`sales`,`unit_name`,`description`,`start_time`,`end_time`,`add_time`,`upd_time`,`status`,`deleted`,`max_num`,`is_show`,`time_id`,`spec_type`,`temp_id`) values (9,1,'https://cbu01.alicdn.com/img/ibank/2019/076/261/11884162670_1376630459.400x400.jpg','1','1','1','1.00','0.00','1.00','1.00',1,1,1,'1',NULL,'2023-02-03','2023-02-03','2023-02-03 20:30:50','2023-02-03 20:30:48',1,0,1,1,0,1,1);

/*Table structure for table `ceam_role_menu` */

DROP TABLE IF EXISTS `ceam_role_menu`;

CREATE TABLE `ceam_role_menu` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '角色映射对应菜单的标识id',
  `role_id` bigint(20) unsigned NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) unsigned NOT NULL COMMENT '菜单ID-只对应根菜单',
  PRIMARY KEY (`id`),
  KEY `security_role_menu_security_menu_info_id_fk` (`menu_id`),
  KEY `security_role_menu_security_user_role_id_fk` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=763 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ceam_role_menu` */

insert  into `ceam_role_menu`(`id`,`role_id`,`menu_id`) values (701,1,159),(702,1,2),(703,1,137),(704,1,136),(705,1,35),(706,1,46),(707,1,158),(708,1,47),(709,1,37),(710,1,135),(711,1,172),(712,1,56),(713,1,126),(714,1,139),(715,1,128),(716,1,55),(717,1,173),(718,1,144),(719,1,133),(720,1,118),(721,1,77),(722,1,129),(723,1,140),(724,1,18),(725,1,14),(726,1,3),(727,1,66),(728,1,40),(729,1,188),(730,1,131),(731,1,232),(732,1,86),(733,1,142),(734,1,7),(735,1,19),(736,1,143),(737,1,119),(738,1,132),(739,1,120),(740,1,231),(741,1,141),(742,1,54),(743,1,174),(744,1,130),(745,1,63),(746,1,39),(747,1,41),(748,1,189),(749,1,134),(750,1,5),(751,1,156),(752,1,190),(753,1,157),(754,1,230),(755,1,45),(756,1,83),(757,1,138),(758,1,9),(759,1,1),(760,1,175),(761,1,53),(762,1,127);

/*Table structure for table `ceam_sys_dept` */

DROP TABLE IF EXISTS `ceam_sys_dept`;

CREATE TABLE `ceam_sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '名称',
  `pid` bigint(20) NOT NULL COMMENT '上级部门',
  `enabled` tinyint(1) NOT NULL COMMENT '状态',
  `add_time` datetime DEFAULT NULL COMMENT '创建日期',
  `upd_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='部门';

/*Data for the table `ceam_sys_dept` */

insert  into `ceam_sys_dept`(`id`,`name`,`pid`,`enabled`,`add_time`,`upd_time`,`deleted`) values (1,'CeaM Mall技术',0,1,'2023-01-29 20:16:51','2023-01-29 20:16:54',0),(14,'架构组',1,1,'2023-01-29 20:19:04','2023-01-29 20:19:07',0),(15,'研发部',1,1,'2023-01-29 20:19:35','2023-01-29 20:19:37',0),(16,'产品组',1,1,'2023-01-29 20:19:57','2023-01-29 20:20:00',0),(17,'架构小组1',14,1,'2023-01-29 20:20:29','2023-01-29 20:20:32',0),(18,'研发小组1',15,1,'2023-01-29 20:20:55','2023-01-29 20:20:58',0),(19,'PM',1,1,'2023-01-29 20:23:25','2023-01-29 20:23:27',0),(20,'需求分析师',1,1,'2023-01-29 20:24:10','2023-01-29 20:24:13',0),(21,'测试组',1,1,'2023-01-29 20:24:37','2023-01-29 20:24:39',0);

/*Table structure for table `ceam_sys_dict` */

DROP TABLE IF EXISTS `ceam_sys_dict`;

CREATE TABLE `ceam_sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '字典名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '描述',
  `add_time` datetime DEFAULT NULL COMMENT '创建日期',
  `upd_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='数据字典';

/*Data for the table `ceam_sys_dict` */

insert  into `ceam_sys_dict`(`id`,`name`,`remark`,`add_time`,`upd_time`,`deleted`) values (1,'user_state','用户状态','2023-01-29 11:27:02','2023-01-29 11:27:04',0);

/*Table structure for table `ceam_sys_dict_detail` */

DROP TABLE IF EXISTS `ceam_sys_dict_detail`;

CREATE TABLE `ceam_sys_dict_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `label` varchar(255) NOT NULL COMMENT '字典标签',
  `value` varchar(255) NOT NULL COMMENT '字典值',
  `sort` varchar(255) DEFAULT NULL COMMENT '排序',
  `dict_id` bigint(20) DEFAULT NULL COMMENT '字典id',
  `add_time` datetime DEFAULT NULL COMMENT '创建日期',
  `upd_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='数据字典详情';

/*Data for the table `ceam_sys_dict_detail` */

insert  into `ceam_sys_dict_detail`(`id`,`label`,`value`,`sort`,`dict_id`,`add_time`,`upd_time`,`deleted`) values (1,'启用','1','1',1,'2023-01-29 21:13:21','2023-01-29 21:13:23',0);

/*Table structure for table `ceam_sys_job` */

DROP TABLE IF EXISTS `ceam_sys_job`;

CREATE TABLE `ceam_sys_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) NOT NULL COMMENT '岗位名称',
  `enabled` tinyint(1) NOT NULL COMMENT '岗位状态',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `add_time` datetime DEFAULT NULL COMMENT '创建日期',
  `upd_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='岗位';

/*Data for the table `ceam_sys_job` */

insert  into `ceam_sys_job`(`id`,`name`,`enabled`,`dept_id`,`add_time`,`upd_time`,`deleted`) values (13,'架构工程师',1,14,'2023-01-29 20:39:42','2023-01-29 20:39:44',0);

/*Table structure for table `ceam_sys_menu` */

DROP TABLE IF EXISTS `ceam_sys_menu`;

CREATE TABLE `ceam_sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `i_frame` bit(1) DEFAULT NULL COMMENT '是否外链',
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '菜单名称',
  `component` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '组件',
  `pid` bigint(20) NOT NULL COMMENT '上级菜单ID',
  `sort` bigint(20) NOT NULL COMMENT '排序',
  `icon` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '图标',
  `path` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '链接地址',
  `cache` bit(1) DEFAULT b'0' COMMENT '缓存',
  `hidden` bit(1) DEFAULT b'0' COMMENT '是否隐藏',
  `component_name` varchar(20) CHARACTER SET utf8 DEFAULT '-' COMMENT '组件名称',
  `add_time` datetime DEFAULT NULL COMMENT '创建日期',
  `permission` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '权限',
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `upd_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FKqcf9gem97gqa5qjm4d3elcqt5` (`pid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;

/*Data for the table `ceam_sys_menu` */

insert  into `ceam_sys_menu`(`id`,`i_frame`,`name`,`component`,`pid`,`sort`,`icon`,`path`,`cache`,`hidden`,`component_name`,`add_time`,`permission`,`type`,`upd_time`,`deleted`) values (1,'\0','系统管理',NULL,0,99,'system','system','\0','\0',NULL,'2018-12-18 15:11:29',NULL,1,'2021-04-04 16:02:03',0),(2,'\0','用户管理','system/user/index',1,2,'peoples','user','\0','\0','User','2018-12-18 15:14:44','user:list',1,NULL,0),(3,'\0','角色管理','system/role/index',1,3,'role','role','\0','\0','Role','2018-12-18 15:16:07','roles:list',1,NULL,0),(5,'\0','菜单管理','system/menu/index',1,5,'menu','menu','\0','\0','Menu','2018-12-18 15:17:28','menu:list',1,NULL,0),(6,'\0','系统监控',NULL,0,100,'monitor','monitor','\0','\0',NULL,'2018-12-18 15:17:48',NULL,1,NULL,0),(7,'\0','操作日志','monitor/log/index',6,11,'log','logs','','\0','Log','2018-12-18 15:18:26','log:list',1,NULL,0),(9,'\0','SQL监控','monitor/sql/index',6,14,'sqlMonitor','druid','\0','\0','Sql','2018-12-18 15:19:34',NULL,1,NULL,0),(14,'\0','邮件工具','tools/email/index',36,24,'email','email','\0','\0','Email','2018-12-27 10:13:09',NULL,1,NULL,0),(18,'\0','存储管理','tools/storage/index',36,23,'qiniu','storage','\0','\0','Storage','2018-12-31 11:12:15','storage:list',1,'2020-08-06 22:57:28',0),(19,'\0','支付宝工具','tools/aliPay/index',36,27,'alipay','aliPay','\0','\0','AliPay','2018-12-31 14:52:38',NULL,1,NULL,0),(28,'\0','定时任务','system/timing/index',36,21,'timing','timing','\0','\0','Timing','2019-01-07 20:34:40','timing:list',1,NULL,0),(30,'\0','代码生成','generator/index',36,22,'dev','generator','\0','\0','GeneratorIndex','2019-01-11 15:45:55',NULL,1,NULL,0),(32,'\0','异常日志','monitor/log/errorLog',6,12,'error','errorLog','\0','\0','ErrorLog','2019-01-13 13:49:03','logError:list',1,NULL,0),(35,'\0','部门管理','system/dept/index',1,6,'dept','dept','\0','\0','Dept','2019-03-25 09:46:00','dept:list',1,NULL,0),(36,'\0','系统工具','',0,101,'sys-tools','sys-tools','\0','\0',NULL,'2019-03-29 10:57:35',NULL,1,NULL,0),(37,'\0','岗位管理','system/job/index',1,7,'Steve-Jobs','job','\0','\0','Job','2019-03-29 13:51:18','user:list',1,NULL,0),(39,'\0','字典管理','system/dict/index',1,8,'dictionary','dict','\0','\0','Dict','2019-04-10 11:49:04','dict:list',1,NULL,0),(40,'\0','商品管理','',0,1,'shop','shop','','\0','','2019-10-03 17:40:19',NULL,1,NULL,0),(41,'\0','商品分类','shop/cate/index',40,11,'icon','cate','\0','\0','Cate','2019-10-03 17:42:35','CeaMSTORECATEGORY_ALL,CeaMSTORECATEGORY_SELECT',1,NULL,0),(45,'\0','商品信息','shop/goods/tab',40,12,'develop','goods','','\0','Goods','2019-10-04 15:34:35','CeaMSTOREPRODUCT_SELECT,CeaMSTORECATEGORY_ALL,CeaMSTORECATEGORY_SELECT,CeaMSYSTEMGROUPDATA_SELECT',1,'2020-08-06 23:01:36',0),(46,'\0','会员管理','',0,2,'peoples','member','\0','\0','','2019-10-06 16:18:05',NULL,1,NULL,0),(47,'\0','会员信息','shop/user/index',46,21,'peoples','member','\0','\0','Member','2019-10-06 16:20:17','CeaMUSER_SELECT',1,NULL,0),(48,'\0','微信管理','',0,14,'weixin','wechat','\0','\0','','2019-10-06 18:28:54',NULL,1,'2020-06-26 15:18:32',0),(49,'\0','微信菜单','wechat/menu/index',48,31,'menu','wemenu','\0','\0','WeMenu','2019-10-06 18:31:06','CeaMWechatMenu_ALL,CeaMWechatMenu_SELECT',1,NULL,0),(50,'\0','图文管理','wechat/article/index',48,32,'article','wearticle','\0','\0','WeArticle','2019-10-07 17:33:45','CeaMARTICLE_ALL,CeaMARTICLE_SELECT',1,NULL,0),(51,'\0','自动回复','wechat/reply/index',48,33,'reply','wereply','\0','\0','Wereply','2019-10-10 09:58:31','CeaMWECHATREPLY_ALL',1,NULL,0),(52,'\0','公众号配置','wechat/config/index',48,34,'configure','weconfig','\0','\0','WeConfig','2019-10-10 15:52:24','CeaMSYSTEMCONFIG_ALL',1,NULL,0),(53,'\0','订单管理','',0,4,'lock','order','\0','\0','','2019-10-14 14:35:18',NULL,1,NULL,0),(54,'\0','订单','shop/order/index',53,41,'order','order','','\0','Order','2019-10-14 14:36:28','CeaMSTOREORDER_SELECT,CeaMEXPRESS_SELECT',1,'2020-10-27 18:36:36',0),(55,'\0','商城配置','',0,15,'configure','set','\0','\0','','2019-10-18 15:21:26',NULL,1,'2020-06-26 15:18:20',0),(56,'\0','首页幻灯片','shop/set/index',55,51,'banner','homeBanner','\0','\0','HomeBanner','2019-10-18 15:24:30','CeaMSYSTEMGROUPDATA_ALL,CeaMSYSTEMGROUPDATA_SELECT',1,NULL,0),(57,'\0','首页导航按钮','shop/set/menu',55,52,'button','homeMenus','\0','\0','HomeMenus','2019-10-18 17:23:35','CeaMSYSTEMGROUPDATA_ALL,CeaMSYSTEMGROUPDATA_SELECT',1,NULL,0),(59,'\0','首页滚动新闻','shop/set/roll',55,54,'news','roll','\0','\0','Roll','2019-10-21 16:41:30','CeaMSYSTEMGROUPDATA_ALL,CeaMSYSTEMGROUPDATA_SELECT',1,NULL,0),(60,'\0','热门搜索','shop/set/hot',55,55,'search','hot','\0','\0','Hot','2019-10-26 18:21:54','CeaMSYSTEMGROUPDATA_ALL,CeaMSYSTEMGROUPDATA_SELECT',1,NULL,0),(61,'\0','个人中心菜单','shop/set/usermenu',55,56,'menu','userMenu','\0','\0','UserMenu','2019-10-26 18:42:18','CeaMSYSTEMGROUPDATA_ALL,CeaMSYSTEMGROUPDATA_SELECT',1,NULL,0),(62,'\0','评论管理','shop/reply/index',53,42,'comment','reply','\0','\0','Reply','2019-11-03 14:39:09','CeaMSTOREPRODUCTREPLY_SELECT',1,NULL,0),(63,'\0','营销管理','',0,6,'yingxiao','activity','\0','\0','','2019-11-09 14:17:42',NULL,1,NULL,0),(64,'\0','优惠券制作','activity/coupon/index',229,61,'coupon','coupon','\0','\0','Coupon','2019-11-09 14:18:58','CeaMSTORECOUPON_SELECT',1,'2020-06-26 15:16:40',0),(65,'\0','已发布优惠券','activity/couponissue/index',229,62,'coupon2','couponissue','\0','\0','Couponissue','2019-11-09 14:20:35','CeaMSTORECOUPONISSUE_SELECT',1,'2020-06-26 15:16:48',0),(66,'\0','优惠券领取记录','activity/storecouponuser/index',229,63,'log','couponuser','\0','\0','Couponuser','2019-11-09 14:21:35','CeaMSTORECOUPONUSER_SELECT,CeaMSTORECOUPONUSER_SELECT',1,'2020-08-06 23:11:10',0),(68,'\0','积分配置','wechat/config/point',227,59,'configure','pointConfig','\0','\0','PointConfig','2019-11-10 18:45:55','CeaMSYSTEMCONFIG_SELECT,CeaMSYSTEMCONFIG_SELECT',1,'2020-08-06 23:09:31',0),(69,'\0','分销管理','',0,7,'fenxiao','promoter','\0','\0','','2019-11-11 10:42:16',NULL,1,NULL,0),(70,'\0','分销配置','wechat/config/promoter',69,71,'configure','promoterconfig','\0','\0','Promoterconfig','2019-11-11 10:48:37','CeaMSYSTEMCONFIG_SELECT,CeaMSYSTEMCONFIG_SELECT',1,'2020-08-06 23:12:31',0),(71,'\0','分销员','shop/user/aindex',69,72,'user','agent','\0','\0','Agent','2019-11-13 18:32:00','CeaMUSER_SELECT',1,NULL,1),(72,'\0','提现管理','activity/extract/index',176,81,'tixian','extract','\0','\0','Extract','2019-11-14 10:49:39','CeaMUSEREXTRACT_SELECT,CeaMUSEREXTRACT_SELECT',1,'2020-08-06 23:07:28',0),(73,'\0','拼团产品','activity/combination/index',63,64,'peoples','combination','\0','\0','Combination','2019-11-18 14:23:04','CeaMSTORECOMBINATION_SELECT',1,NULL,0),(74,'\0','拼团列表','activity/combinlist/index',63,65,'list','pink','\0','\0','Pink','2019-11-21 19:35:58','CeaMSTOREPINK_SELECT',1,NULL,0),(75,'\0','微信支付配置','wechat/config/pay',48,35,'wxpay','wxpayconfig','\0','\0','Wxpayconfig','2019-11-28 17:06:22','CeaMSYSTEMCONFIG_ALL',1,NULL,0),(76,'\0','小程序配置','wechat/config/wxapp',48,36,'configure','wxapp','\0','\0','Wxapp','2019-11-29 15:13:46','CeaMSYSTEMCONFIG_ALL',1,NULL,0),(77,'\0','会员等级','shop/userlevel/index',46,22,'dengji','userlevel','\0','\0','Userlevel','2019-12-04 16:35:41','CeaMSYSTEMUSERLEVEL_SELECT',1,NULL,0),(78,'\0','等级任务','shop/usertask/index',46,23,'task manege','usertask','\0','\0','Usertask','2019-12-04 17:26:19','CeaMSYSTEMUSERTASK_SELECT',1,NULL,0),(79,'\0','签到天数配置','shop/set/sign',227,61,'sign2','signday','\0','\0','Signday','2019-12-05 14:12:16','CeaMSYSTEMGROUPDATA_ALL,CeaMSYSTEMGROUPDATA_SELECT',1,'2020-06-26 15:09:25',0),(80,'\0','用户账单','shop/user/bill',46,24,'list','bill','\0','\0','Bill','2019-12-11 17:28:38','CeaMUSERBILL_ALL,CeaMUSERBILL_SELECT',1,NULL,0),(81,'\0','物流快递','shop/express/index',53,43,'express','express','\0','\0','Express','2019-12-12 16:36:00','CeaMEXPRESS_SELECT',1,NULL,0),(82,'\0','微信模板消息','wechat/template/index',48,35,'anq','template','\0','\0','Template','2019-12-13 14:42:50','CeaMWechatTemplate:list',1,NULL,0),(83,'\0','秒杀产品','activity/seckill/index',63,66,'seckill','seckill','\0','\0','Seckill','2019-12-16 13:06:29','CeaMSTORESECKILL_SELECT',1,NULL,0),(84,'\0','秒杀配置','shop/set/seckill',63,67,'configure','seckillconfig','\0','\0','Seckillconfig','2019-12-16 16:07:42','CeaMSYSTEMGROUPDATA_SELECT',1,NULL,0),(86,'\0','砍价产品','activity/bargain/index',63,956,'Sign','bargain','\0','\0','Bargain','2019-12-22 12:25:55','CeaMSTOREBARGAIN_SELECT',1,NULL,0),(87,'\0','生成配置','generator/config',36,33,'dev','generator/config/:tableName','','','GeneratorConfig','2019-11-17 20:08:56','',1,NULL,0),(88,'\0','生成预览','generator/preview',36,999,'java','generator/preview/:tableName','','','Preview','2019-11-26 14:54:36',NULL,1,NULL,0),(116,'\0','生成配置','generator/config',36,33,'dev','generator/config/:tableName','','','GeneratorConfig','2019-11-17 20:08:56','',1,NULL,0),(117,'\0','图表库','components/Echarts',10,50,'chart','echarts','','\0','Echarts','2019-11-21 09:04:32','',1,NULL,0),(118,'\0','商品新增','shop/goods/form',45,1,'anq','goodsAdd','\0','','GoodsAdd','2019-12-24 13:00:47','CeaMSTOREPRODUCT_EDIT',2,'2020-10-27 18:42:37',0),(119,'\0','商品修改','shop/goods/form',45,3,'anq','goodsEdit/:id','\0','','GoodsEdit','2019-12-24 13:02:23','CeaMSTOREPRODUCT_CREATE',2,'2020-07-10 16:45:33',0),(120,'\0','商品删除',NULL,45,4,'anq',NULL,'\0','',NULL,'2019-12-24 13:03:51','CeaMSTOREPRODUCT_DELETE',2,'2020-07-10 16:22:51',0),(121,'\0','在线用户','monitor/online/index',6,10,'Steve-Jobs','online','\0','\0','OnlineUser','2020-01-06 22:46:43',NULL,1,NULL,0),(122,'\0','浏览记录','monitor/log/mlog',40,13,'log','viewlog','\0','\0','Viewlog','2020-01-07 13:17:21',NULL,1,NULL,1),(123,'\0','后台接口文档','tools/swagger/index',36,31,'swagger','swagger2','\0','\0','Swagger','2020-01-07 18:05:52',NULL,1,NULL,0),(124,'\0','在线会员','monitor/online/indext',46,25,'Steve-Jobs','onlinet','\0','\0','OnlineMember','2020-01-13 10:53:07','auth_online',1,'2020-08-06 22:50:49',0),(125,'\0','邮费配置','wechat/config/postage',55,58,'configure','postageConfig','\0','\0','PostageConfig','2020-02-13 15:38:24','CeaMSYSTEMCONFIG_SELECT',1,NULL,1),(126,'\0','编辑',NULL,54,1,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:05:28','CeaMSTOREORDER_EDIT',2,NULL,0),(127,'\0','用户新增',NULL,2,2,NULL,'add','\0','',NULL,'2020-02-14 21:12:21','user:add',2,NULL,0),(128,'\0','用户编辑',NULL,2,3,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:12:47','user:edit',2,NULL,0),(129,'\0','用户删除',NULL,2,4,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:13:08','user:del',2,NULL,0),(130,'\0','角色创建',NULL,3,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:13:49','roles:add',2,NULL,0),(131,'\0','角色修改',NULL,3,3,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:14:11','roles:edit',2,NULL,0),(132,'\0','角色删除',NULL,3,999,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:14:38','roles:del',2,NULL,0),(133,'\0','菜单新增',NULL,5,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:15:05','menu:add',2,NULL,0),(134,'\0','菜单编辑',NULL,5,3,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:18:44','menu:edit',2,NULL,0),(135,'\0','菜单删除',NULL,5,4,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:19:05','menu:del',2,NULL,0),(136,'\0','部门新增',NULL,35,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:21:07','dept:add',2,NULL,0),(137,'\0','部门编辑',NULL,35,3,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:21:33','dept:edit',2,NULL,0),(138,'\0','部门删除',NULL,35,4,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:21:53','dept:del',2,NULL,0),(139,'\0','岗位新增',NULL,37,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:29:04','job:add',2,NULL,0),(140,'\0','岗位编辑',NULL,37,3,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:48:38','job:edit',2,NULL,0),(141,'\0','岗位删除',NULL,37,4,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:49:00','job:del',2,NULL,0),(142,'\0','字典新增',NULL,39,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:49:26','dict:add',2,NULL,0),(143,'\0','字典编辑',NULL,39,3,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:49:39','dict:edit',2,NULL,0),(144,'\0','字典删除',NULL,39,4,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:49:56','dict:del',2,NULL,0),(147,'\0','上传文件',NULL,18,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:53:49','storage:add',2,NULL,0),(148,'\0','文件编辑',NULL,18,3,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:54:06','storage:edit',2,NULL,0),(149,'\0','文件删除',NULL,18,4,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:54:27','storage:del',2,NULL,0),(150,'\0','任务新增',NULL,28,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:55:58','timing:add',2,NULL,0),(151,'\0','任务编辑',NULL,28,3,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:56:54','timing:edit',2,NULL,0),(152,'\0','任务删除',NULL,28,4,NULL,NULL,'\0','\0',NULL,'2020-02-14 21:57:10','timing:del',2,NULL,0),(153,'\0','新增分类',NULL,41,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:00:41','CeaMSTORECATEGORY_CREATE',2,NULL,0),(154,'\0','分类编辑',NULL,41,3,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:01:15','CeaMSTORECATEGORY_EDIT',2,NULL,0),(155,'\0','分类删除',NULL,41,4,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:01:37','CeaMSTORECATEGORY_DELETE',2,NULL,0),(156,'\0','修改会员',NULL,47,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:03:40','CeaMUSER_EDIT',2,NULL,0),(157,'\0','等级新增',NULL,77,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:06:55','CeaMSYSTEMUSERLEVEL_CREATE',2,NULL,0),(158,'\0','等级编辑',NULL,77,3,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:08:03','CeaMSYSTEMUSERLEVEL_EDIT',2,NULL,0),(159,'\0','等级删除',NULL,77,4,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:08:41','CeaMSYSTEMUSERLEVEL_DELETE',2,NULL,0),(160,'\0','编辑任务',NULL,78,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:10:08','CeaMSYSTEMUSERTASK_EDIT',2,NULL,0),(161,'\0','评论删除',NULL,62,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:14:22','CeaMSTOREPRODUCTREPLY_DELETE',2,NULL,0),(162,'\0','新增物流',NULL,81,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:15:33','CeaMEXPRESS_CREATE',2,NULL,0),(163,'\0','编辑物流',NULL,81,3,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:15:53','CeaMEXPRESS_EDIT',2,NULL,0),(164,'\0','删除物流',NULL,81,4,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:16:11','CeaMEXPRESS_DELETE',2,NULL,0),(165,'\0','新增优惠券',NULL,64,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:18:32','CeaMSTORECOUPON_CREATE',2,NULL,0),(166,'\0','编辑优惠券',NULL,64,3,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:18:50','CeaMSTORECOUPON_EDIT',2,NULL,0),(167,'\0','删除优惠券',NULL,64,4,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:19:10','CeaMSTORECOUPON_DELETE',2,NULL,0),(168,'\0','编辑已发布',NULL,65,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:20:23','CeaMSTORECOUPONISSUE_EDIT',2,NULL,0),(169,'\0','删除已发布',NULL,65,3,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:20:42','CeaMSTORECOUPONISSUE_DELETE',2,NULL,0),(170,'\0','编辑拼团',NULL,73,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:24:15','CeaMSTORECOMBINATION_EDIT',2,NULL,0),(171,'\0','删除拼团',NULL,73,3,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:24:37','CeaMSTORECOMBINATION_DELETE',2,NULL,0),(172,'\0','编辑秒杀',NULL,83,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:25:23','CeaMSTORESECKILL_EDIT',2,NULL,0),(173,'\0','删除秒杀',NULL,83,3,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:25:41','CeaMSTORESECKILL_DELETE',2,NULL,0),(174,'\0','编辑砍价',NULL,86,2,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:26:20','CeaMSTOREBARGAIN_EDIT',2,NULL,0),(175,'\0','删除砍价',NULL,86,999,NULL,NULL,'\0','\0',NULL,'2020-02-14 22:26:40','CeaMSTOREBARGAIN_DELETE',2,NULL,0),(176,'\0','财务管理',NULL,0,8,'price','price','\0','\0',NULL,'2020-03-02 22:30:23',NULL,1,NULL,0),(177,'\0','充值管理','shop/recharge/index',176,82,'rec','recharge','\0','\0','Recharge','2020-03-02 23:05:26','CeaMUserRecharge:list',1,NULL,0),(178,'\0','门店管理',NULL,0,9,'store','store','\0','\0',NULL,'2020-03-03 17:27:53',NULL,1,NULL,0),(179,'\0','门店列表','shop/store/index',178,92,'edit','storeinfo','\0','\0','Storeinfo','2020-03-03 17:29:09','CeaMSystemStore:list',1,NULL,0),(180,'\0','门店配置','shop/store/set',178,91,'configure','storeset','\0','\0','Storeset','2020-03-04 13:09:54','CeaMSYSTEMCONFIG_SELECT,CeaMSYSTEMCONFIG_SELECT',1,'2020-08-06 23:05:23',0),(181,'\0','核销订单','shop/order/indext',178,95,'order','ordert','','\0','Ordert','2020-03-05 17:04:12','CeaMSTOREORDER_SELECT',1,'2020-10-27 17:59:24',0),(182,'\0','充值金额配置','shop/set/recharge',176,83,'money','rechargeset','\0','\0','Rechargeset','2020-03-21 14:24:05','CeaMSYSTEMGROUPDATA_ALL,CeaMSYSTEMGROUPDATA_SELECT',1,'2020-06-26 15:11:06',0),(183,'\0','店员列表','shop/storestaff/index',178,94,'peoples','staff','\0','\0','Staff','2020-03-22 14:11:36','CeaMSystemStoreStaff:list',1,NULL,0),(184,'\0','新增菜单',NULL,49,0,'add',NULL,'\0','\0',NULL,'2020-06-14 20:10:02','CeaMWechatMenu_CREATE',2,NULL,0),(185,'\0','模板新增',NULL,82,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 20:14:17','CeaMWechatTemplate:add',2,NULL,0),(186,'\0','模板修改',NULL,82,2,NULL,NULL,'\0','\0',NULL,'2020-06-14 20:14:46','CeaMWechatTemplate:edit',2,NULL,0),(187,'\0','模板删除',NULL,82,3,NULL,NULL,'\0','\0',NULL,'2020-06-14 20:15:10','CeaMWechatTemplate:del',2,NULL,0),(188,'\0','新增幻灯片',NULL,56,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 20:33:48','CeaMSYSTEMGROUPDATA_CREATE',2,NULL,0),(189,'\0','修改幻灯片',NULL,56,2,NULL,NULL,'\0','\0',NULL,'2020-06-14 20:35:11','CeaMSYSTEMGROUPDATA_EDIT',2,NULL,0),(190,'\0','删除幻灯片',NULL,56,3,NULL,NULL,'\0','\0',NULL,'2020-06-14 20:40:30','CeaMSYSTEMGROUPDATA_DELETE',2,NULL,0),(191,'\0','新增导航按钮',NULL,57,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 20:42:43','CeaMSYSTEMGROUPDATA_CREATE',2,NULL,0),(192,'\0','修改导航按钮',NULL,57,2,NULL,NULL,'\0','\0',NULL,'2020-06-14 20:43:53','CeaMSYSTEMGROUPDATA_EDIT',2,NULL,0),(193,'\0','删除导航按钮',NULL,57,3,NULL,NULL,'\0','\0',NULL,'2020-06-14 20:44:43','CeaMSYSTEMGROUPDATA_DELETE',2,NULL,0),(194,'\0','新增滚动新闻',NULL,59,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 20:48:32','CeaMSYSTEMGROUPDATA_CREATE',2,NULL,0),(195,'\0','修改滚动新闻',NULL,59,2,NULL,NULL,'\0','\0',NULL,'2020-06-14 20:48:52','CeaMSYSTEMGROUPDATA_EDIT',2,NULL,0),(196,'\0','删除滚动新闻',NULL,59,3,NULL,NULL,'\0','\0',NULL,'2020-06-14 20:49:32','CeaMSYSTEMGROUPDATA_DELETE',2,NULL,0),(197,'\0','新增热门搜索',NULL,60,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 21:14:25','CeaMSYSTEMGROUPDATA_CREATE',2,NULL,0),(198,'\0','修改热门搜索',NULL,60,2,NULL,NULL,'\0','\0',NULL,'2020-06-14 21:14:55','CeaMSYSTEMGROUPDATA_EDIT',2,NULL,0),(199,'\0','删除热门搜索',NULL,60,3,NULL,NULL,'\0','\0',NULL,'2020-06-14 21:15:25','CeaMSYSTEMGROUPDATA_DELETE',2,NULL,0),(200,'\0','新增个人中心菜单',NULL,61,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 21:17:47','CeaMSYSTEMGROUPDATA_CREATE',2,NULL,0),(201,'\0','修改个人中心菜单',NULL,61,2,NULL,NULL,'\0','\0',NULL,'2020-06-14 21:18:37','CeaMSYSTEMGROUPDATA_EDIT',2,NULL,0),(202,'\0','删除个人中心菜单',NULL,61,3,NULL,NULL,'\0','\0',NULL,'2020-06-14 21:19:47','CeaMSYSTEMGROUPDATA_DELETE',2,NULL,0),(203,'\0','新增积分配置',NULL,68,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 21:20:47','CeaMSYSTEMCONFIG_CREATE',2,NULL,0),(204,'\0','新增签到天数',NULL,79,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 21:26:32','CeaMSYSTEMGROUPDATA_CREATE',2,NULL,0),(205,'\0','修改签到天数',NULL,79,2,NULL,NULL,'\0','\0',NULL,'2020-06-14 22:26:32','CeaMSYSTEMGROUPDATA_EDIT',2,NULL,0),(206,'\0','删除签到天数',NULL,79,3,NULL,NULL,'\0','\0',NULL,'2020-06-14 22:26:52','CeaMSYSTEMGROUPDATA_DELETE',2,NULL,0),(207,'\0','新增邮费配置',NULL,125,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 21:29:20','CeaMSYSTEMCONFIG_CREATE',2,NULL,1),(208,'\0','新增充值金额',NULL,182,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 21:30:59','CeaMSYSTEMGROUPDATA_CREATE',2,NULL,0),(209,'\0','修改充值金额',NULL,182,2,NULL,NULL,'\0','\0',NULL,'2020-06-14 22:30:30','CeaMSYSTEMGROUPDATA_EDIT',2,NULL,0),(210,'\0','删除充值金额',NULL,182,3,NULL,NULL,'\0','\0',NULL,'2020-06-14 22:30:59','CeaMSYSTEMGROUPDATA_DELETE',2,NULL,0),(211,'\0','新增秒杀配置',NULL,84,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 21:43:36','CeaMSYSTEMGROUPDATA_CREATE',2,NULL,0),(212,'\0','修改秒杀配置',NULL,84,2,NULL,NULL,'\0','\0',NULL,'2020-06-14 21:43:56','CeaMSYSTEMGROUPDATA_EDIT',2,NULL,0),(213,'\0','删除秒杀配置',NULL,84,3,NULL,NULL,'\0','\0',NULL,'2020-06-14 22:23:36','CeaMSYSTEMGROUPDATA_DELETE',2,NULL,0),(214,'\0','新增分销配置',NULL,70,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 21:46:46','CeaMSYSTEMCONFIG_CREATE',2,NULL,0),(215,'\0','提现审核',NULL,72,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 21:56:11','CeaMUSEREXTRACT_EDIT',2,NULL,0),(216,'\0','删除充值',NULL,177,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 21:59:11','CeaMUserRecharge:del',2,NULL,0),(217,'\0','导出充值',NULL,177,2,NULL,NULL,'\0','\0',NULL,'2020-06-14 21:59:54','CeaMUserRecharge:list',2,NULL,0),(218,'\0','新增门店',NULL,179,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 22:01:57','CeaMSystemStore:add',2,NULL,0),(219,'\0','修改门店',NULL,179,2,NULL,NULL,'\0','\0',NULL,'2020-06-14 22:02:30','CeaMSystemStore:edit',2,NULL,0),(220,'\0','删除门店',NULL,179,3,NULL,NULL,'\0','\0',NULL,'2020-06-14 22:02:57','CeaMSystemStore:del',2,NULL,0),(221,'\0','新增门店配置',NULL,180,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 22:04:25','CeaMSYSTEMCONFIG_CREATE',2,NULL,0),(222,'\0','编辑核销订单',NULL,181,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 22:07:26','CeaMSTOREORDER_EDIT',2,NULL,0),(223,'\0','新增店员',NULL,183,1,NULL,NULL,'\0','\0',NULL,'2020-06-14 22:11:13','CeaMSystemStoreStaff:add',2,NULL,0),(224,'\0','修改店员',NULL,183,2,NULL,NULL,'\0','\0',NULL,'2020-06-14 22:11:37','CeaMSystemStoreStaff:edit',2,NULL,0),(225,'\0','删除店员',NULL,183,3,NULL,NULL,'\0','\0',NULL,'2020-06-14 22:11:59','CeaMSystemStoreStaff:del',2,NULL,0),(229,'\0','电子券管理',NULL,0,5,'coupon','syscoupon','\0','\0','','2020-06-26 15:15:47',NULL,1,'2020-06-26 15:22:16',0),(230,'\0','会员足迹','shop/footprints/index',46,999,'Sign','footprints','\0','\0','Footprints','2023-02-04 16:39:53','list',1,NULL,0),(231,'\0','会员收藏','shop/collect/index',46,999,'tab','collect','\0','\0','Collect','2023-02-04 16:47:56','list',1,NULL,0),(232,'\0','意见反馈','shop/feedback/index',46,999,'article','feedback','\0','\0','Feedback','2023-02-04 17:21:26','list',1,NULL,0);

/*Table structure for table `ceam_sys_role` */

DROP TABLE IF EXISTS `ceam_sys_role`;

CREATE TABLE `ceam_sys_role` (
  `id` bigint(21) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) NOT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `enabled` tinyint(1) NOT NULL COMMENT '是否启用',
  `data_scope` varchar(255) DEFAULT NULL COMMENT '数据权限',
  `permission` varchar(255) DEFAULT NULL COMMENT '功能权限',
  `level` int(2) DEFAULT NULL COMMENT '角色级别',
  `deleted` tinyint(1) DEFAULT NULL COMMENT '逻辑删除',
  `add_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `name_UNIQUE` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='角色表';

/*Data for the table `ceam_sys_role` */

insert  into `ceam_sys_role`(`id`,`name`,`description`,`enabled`,`data_scope`,`permission`,`level`,`deleted`,`add_by`,`update_time`,`add_time`) values (1,'管理员','管理员',1,'全部','admin',1,0,1,'2023-01-28 23:01:57','2023-01-28 23:01:59'),(6,'1','1',1,'1','1',2,0,1,'2023-01-28 23:33:49','2023-01-28 23:33:51'),(7,'3',NULL,3,NULL,NULL,NULL,0,NULL,NULL,NULL),(11,'4',NULL,3,NULL,NULL,NULL,0,NULL,NULL,NULL),(12,'5',NULL,3,NULL,NULL,NULL,0,NULL,NULL,NULL),(13,'6',NULL,3,NULL,NULL,NULL,0,NULL,NULL,NULL),(14,'7',NULL,3,NULL,NULL,NULL,0,NULL,NULL,NULL),(15,'8',NULL,3,NULL,NULL,NULL,0,NULL,NULL,NULL),(16,'9',NULL,3,NULL,NULL,NULL,0,NULL,NULL,NULL),(17,'0',NULL,3,NULL,NULL,NULL,0,NULL,NULL,NULL),(18,'10',NULL,3,NULL,NULL,NULL,0,NULL,NULL,NULL);

/*Table structure for table `ceam_sys_user` */

DROP TABLE IF EXISTS `ceam_sys_user`;

CREATE TABLE `ceam_sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '唯一标识',
  `username` varchar(63) NOT NULL COMMENT '用户名称',
  `password` varchar(63) NOT NULL COMMENT '用户密码',
  `sex` int(2) DEFAULT NULL COMMENT '性别：0 未知， 1男， 1 女',
  `birthday` datetime DEFAULT NULL COMMENT '生日',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近一次登录时间',
  `last_login_ip` varchar(63) DEFAULT NULL COMMENT '最近一次登录IP地址',
  `user_level` int(2) DEFAULT NULL COMMENT '用户层级 0 普通用户，1 VIP用户，2 区域代理用户',
  `nickname` varchar(63) DEFAULT NULL COMMENT '用户昵称或网络名称',
  `phone_num` varchar(20) DEFAULT NULL COMMENT '用户手机号码',
  `avatar` varchar(255) DEFAULT NULL COMMENT '用户头像图片',
  `weixin_openid` varchar(63) DEFAULT NULL COMMENT '微信登录openid',
  `share_user_id` bigint(20) DEFAULT NULL,
  `status` int(2) NOT NULL COMMENT '0 可用, 1 禁用, 2 注销',
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL COMMENT '逻辑删除',
  `dept_id` bigint(20) DEFAULT NULL,
  `attention` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Data for the table `ceam_sys_user` */

insert  into `ceam_sys_user`(`id`,`username`,`password`,`sex`,`birthday`,`last_login_time`,`last_login_ip`,`user_level`,`nickname`,`phone_num`,`avatar`,`weixin_openid`,`share_user_id`,`status`,`add_time`,`update_time`,`deleted`,`dept_id`,`attention`) values (1,'admin','$2a$10$fP.426qKaTmix50Oln8L.uav55gELhAd0Eg66Av4oG86u8km7D/Ky',1,'2023-01-28 15:02:56','2023-01-28 15:02:59','1',1,'格林','18199898879','1','1',1,0,'2023-01-28 15:03:17','2023-01-28 15:03:20',0,NULL,1),(2,'ceam','$2a$10$fP.426qKaTmix50Oln8L.uav55gELhAd0Eg66Av4oG86u8km7D/Ky',0,'2023-01-28 15:04:19','2023-01-28 15:04:21','1',1,'CeaM','18310989769','1','1',1,0,'2023-01-28 16:04:35','2023-01-28 15:04:38',0,NULL,0);

/*Table structure for table `ceam_user_role` */

DROP TABLE IF EXISTS `ceam_user_role`;

CREATE TABLE `ceam_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户 映射 角色的关系id',
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ceam_user_role` */

insert  into `ceam_user_role`(`id`,`user_id`,`role_id`) values (4,1,1),(5,2,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
