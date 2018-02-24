/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017-02-08 11:15:54                          */
/*==============================================================*/

DROP TABLE IF EXISTS SYS_PRIV;

DROP TABLE IF EXISTS SYS_ROLE;

DROP TABLE IF EXISTS SYS_ROLE_PRIV;

DROP TABLE IF EXISTS SYS_USER;

DROP TABLE IF EXISTS SYS_USER_ROLE;

DROP TABLE IF EXISTS SYS_USER_SESSION;


/*==============================================================*/
/* Table: SYS_PRIV                                              */
/*==============================================================*/
CREATE TABLE SYS_PRIV
(
   PRIV_ID              BIGINT(9) NOT NULL AUTO_INCREMENT,
   PARENT_PRIV_ID       BIGINT(9) NOT NULL DEFAULT 0 COMMENT '父节点ID',
   PRIV_CODE            VARCHAR(255) NOT NULL COMMENT '权限CODE',
   PRIV_NAME            VARCHAR(60) NOT NULL COMMENT '权限名称',
   TYPE                 INT(9) NOT NULL COMMENT '0-目录;1-菜单;2-数据',
   URL                  VARCHAR(255) COMMENT '菜单URL路径',
   PATH                 VARCHAR(255) COMMENT '菜单PATH路径',
   DESCRIPTION          VARCHAR(1000) NOT NULL COMMENT '权限描述',
   STATE                CHAR(1) NOT NULL DEFAULT 'A' COMMENT 'A-可用;X:不可用',
   CREATE_TIME          DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
   UPDATE_TIME          DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (PRIV_ID)
) AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

/*==============================================================*/
/* Table: SYS_ROLE                                              */
/*==============================================================*/
CREATE TABLE SYS_ROLE
(
   ROLE_ID              BIGINT(9) NOT NULL AUTO_INCREMENT,
   ROLE_CODE            VARCHAR(60) NOT NULL COMMENT '角色CODE',
   ROLE_NAME            VARCHAR(60) NOT NULL COMMENT '角色名称',
   STATE                CHAR(1) NOT NULL DEFAULT 'A' COMMENT 'A-可用;X:不可用',
   DESCRIPTION          VARCHAR(255) NOT NULL COMMENT '角色描述',
   CREATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP,
   UPDATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (ROLE_ID)
) AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

/*==============================================================*/
/* Table: SYS_ROLE_PRIV                                         */
/*==============================================================*/
CREATE TABLE SYS_ROLE_PRIV
(
   ROLE_ID              BIGINT(9) NOT NULL COMMENT '角色ID',
   PRIV_ID              BIGINT(9) NOT NULL COMMENT '权限ID',
   STATE                CHAR(1) NOT NULL DEFAULT 'A' COMMENT 'A-可用;X:不可用',
   CREATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP,
   UPDATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (ROLE_ID, PRIV_ID)
);

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
CREATE TABLE SYS_USER
(
   USER_ID              BIGINT(9) NOT NULL AUTO_INCREMENT,
   USER_NAME            VARCHAR(60) NOT NULL COMMENT '用户名',
   PASSWORD             VARCHAR(128) NOT NULL COMMENT '密码',
   SALT                 VARCHAR(255) NOT NULL COMMENT '密码盐值',
   NAME                 VARCHAR(128) COMMENT '姓名',
   MOBILE               VARCHAR(255) COMMENT '手机号',
   STATE                CHAR(1) NOT NULL COMMENT 'A-可用/有效;X-不可用/无效',
   STATE_TIME           DATETIME NOT NULL COMMENT '状态变动时间',
   IS_LOCKED            CHAR(1) NOT NULL DEFAULT 'N' COMMENT '是否锁定，Y-锁定;N-没有锁定;NULL-等价N没有锁定',
   PWD_EXP_TIME         DATETIME COMMENT '密码过期时间，NULL-永不过期',
   USER_EFF_TIME        DATETIME NOT NULL COMMENT '生效时间',
   USER_EXP_TIME        DATETIME COMMENT '失效时间，NULL-永不失效',
   FORCE_LOGIN          CHAR(1) DEFAULT 'N' COMMENT 'Y-允许强制登录，N-不允许',
   LOGIN_FAIL           INT(6) NOT NULL COMMENT '登录失败次数，空表示0',
   CREATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP,
   UPDATE_TIME          DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (USER_ID)
) AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

/*==============================================================*/
/* Table: SYS_USER_ROLE                                         */
/*==============================================================*/
CREATE TABLE SYS_USER_ROLE
(
   USER_ID              BIGINT(9) NOT NULL COMMENT '用户ID',
   ROLE_ID              BIGINT(9) NOT NULL COMMENT '角色ID',
   STATE                CHAR(1) NOT NULL DEFAULT 'A' COMMENT 'A-可用;X-不可用',
   CREATE_TIME          DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
   UPDATE_TIME          DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (ROLE_ID, USER_ID)
);

/*==============================================================*/
/* Table: SYS_USER_SESSION                                      */
/*==============================================================*/
CREATE TABLE SYS_USER_SESSION
(
   ID                   BIGINT(9) NOT NULL AUTO_INCREMENT,
   TOKEN                VARCHAR(255) NOT NULL COMMENT '用户TOKEN',
   USER_ID              BIGINT(9) NOT NULL COMMENT '用户ID',
   STATE                CHAR(1) NOT NULL DEFAULT 'A' COMMENT 'A-可用;X-不可用',
   CREATE_TIME          DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
   UPDATE_TIME          DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   LAST_UPDATE_TIME     DATETIME NOT NULL,
   UA                   VARCHAR(255) NOT NULL COMMENT 'UserAgent',
   PRIMARY KEY (ID)
)
AUTO_INCREMENT=1001 DEFAULT CHARSET=UTF8;
