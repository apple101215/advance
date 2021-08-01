CREATE TABLE `org_user` (
  `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `nike_name` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `user_name` varchar(50) NOT NULL DEFAULT '' COMMENT '姓名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `identity_number` varchar(100) NOT NULL DEFAULT '' COMMENT '身份证号',
  `mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱地址',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '人员状态：0=启用；1=禁用',
  `is_deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否已删除：0=未删除；1=已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE `order` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `receivable_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '应收',
  `discount_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '折扣',
  `receipt_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '实收',
  `receipt_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '订单收款状态 0=未收 1=已收部分 2=已收清',
  `order_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '订单状态：0=有效 1=取消',
  `is_deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否已删除：0=未删除；1=已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单';

CREATE TABLE `order_detail` (
  `order_detail_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单明细ID',
  `order_id` bigint(20) unsigned NOT NULL COMMENT '订单ID',
  `commodity_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  `receivable_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '应收',
  `discount_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '折扣',
  `receipt_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '实收',
  `receipt_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '收款状态 0=未收 1=已收部分 2=已收清',
  `order_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '状态：0=有效 1=取消',
  `is_deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否已删除：0=未删除；1=已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`order_detail_id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_commodity_id` (`commodity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单明细';

CREATE TABLE `commodity` (
  `commodity_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `supplier_id` bigint(20) unsigned NOT NULL COMMENT '供应商ID',
  `commodity_no` varchar(100) NOT NULL DEFAULT '' COMMENT '商品编码',
  `commodity_name` varchar(100) NOT NULL DEFAULT '' COMMENT '商品名称',
  `commodity_type` int(3) unsigned NOT NULL COMMENT '商品类型 ',
  `commodity_weight` bigint(20) unsigned NOT NULL COMMENT '商品重量',
  `commodity_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品价格',
  `commodity_cost` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品成本',
  `commodity_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '商品状态：0=有效 1=下架',
  `is_deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否已删除：0=未删除；1=已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`commodity_id`),
  KEY `idx_supplier_id` (`supplier_id`),
  KEY `idx_commodity_no` (`commodity_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

CREATE TABLE `supplier` (
  `supplier_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '供应商ID',
  `supplier_reg_no` varchar(100) NOT NULL DEFAULT '' COMMENT '供应商注册号',
  `supplier_name` varchar(100) NOT NULL DEFAULT '' COMMENT '供应商名称',
  `supplier_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '供应商状态：0=有效 1=下线',
  `supplier_mobile` varchar(20) NOT NULL DEFAULT '' COMMENT '供应商联系手机号',
  `supplier_address` varchar(100) NOT NULL DEFAULT '' COMMENT '供应商地址',
  `supplier_email` varchar(100) NOT NULL DEFAULT '' COMMENT '供应商邮箱地址',
  `is_deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否已删除：0=未删除；1=已删除',
  `create_time` NOT NULL datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` NOT NULL datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`supplier_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='供应商表';


CREATE TABLE `shopping_cart` (
  `shopping_cart_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '购物车ID',
  `user_id` bigint(20) unsigned NOT NULL COMMENT '用户ID',
  `receivable_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '应收',
  `discount_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '折扣',
  `is_deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否已删除：0=未删除；1=已删除',
  `create_time` NOT NULL datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` NOT NULL datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`shopping_cart_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车';


CREATE TABLE `shopping_cart_detail` (
  `shopping_cart_detail_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '购物车明细ID',
  `shopping_cart_id` bigint(20) unsigned NOT NULL COMMENT '购物车ID',
  `commodity_id` bigint(20) unsigned NOT NULL COMMENT '商品ID',
  `receivable_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '应收',
  `discount_amount` bigint(20) NOT NULL DEFAULT '0' COMMENT '折扣',
  `receipt_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '收款状态 0=未收 1=已收部分 2=已收清',
  `order_status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '订单状态：0=有效 1=取消',
  `is_deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否已删除：0=未删除；1=已删除',
  `create_time` NOT NULL datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` NOT NULL datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`shopping_cart_detail_id`),
  KEY `idx_shopping_cart_id` (`shopping_cart_id`),
  KEY `idx_commodity_id` (`commodity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='购物车明细';