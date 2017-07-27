
--drop table itsmorder;
--drop table itsmorderhistory;
--drop table sysconf;
--drop table apilog;

 
CREATE TABLE itsmorder
 (
   uuid varchar2(36),
   orId varchar2(20) NOT NULL,
   orCategory varchar2(20),
   title varchar2(100),
   workContent varchar(500),
   applicant varchar2(20),
   applyTime timestamp,
   performer varchar2(20),
   performTime timestamp,
   ISOwner varchar2(20),
   businessSystem varchar2(100),
   performMode varchar2(100),
   annotation varchar2(500),
   dataState varchar2(10),
   status varchar2(5),
   creator varchar2(20),
   createTime timestamp,
   modifier varchar(20),
   modifyTime timestamp,
   att1 varchar2(1000),
   att2 varchar2(1000),
   att3 varchar2(1000),
   PRIMARY KEY (uuid)
 );

CREATE TABLE itsmorderhistory
 (
   uuid varchar2(36),
   orId varchar2(20),
   orCategory varchar2(20),
   title varchar2(100),
   workContent varchar(500),
   applicant varchar2(20),
   applyTime timestamp,
   performer varchar2(20),
   performTime timestamp,
   ISOwner varchar2(20),
   businessSystem varchar2(100),
   performMode varchar2(100),
   annotation varchar2(500),
   dataState varchar2(10),
   status varchar2(5),
   creator varchar2(20),
   createTime timestamp,
   modifier varchar(20),
   modifyTime timestamp,
   att1 varchar2(1000),
   att2 varchar2(1000),
   att3 varchar2(1000),
   backupTime timestamp,
   PRIMARY KEY (uuid)
 );


CREATE TABLE sysconf 
(    
  uuid varchar2(36),
  CFGCategory varchar2(20),
  CFGGroup varchar2(20),
  CFGKey varchar2(20),
  CFGKeyType varchar2(20),
  CFGValue varchar2(50),
  CFGValueType varchar2(20),
  CFGValueInfo varchar2(200),
  status varchar2(5),
  creator varchar2(20),
  createTime timestamp,
  modifier varchar2(20),
  modifyTime timestamp,
  att1 varchar2(1000),
  att2 varchar2(1000),
  att3 varchar2(1000),
 PRIMARY KEY (uuid),
CONSTRAINT sysconf_Unique UNIQUE (CFGKey, CFGGroup, CFGCategory)
);

insert into sysconf(uuid,CFGCategory,CFGGroup,CFGKey,CFGKeyType,CFGValue,CFGValueType,CFGValueInfo,creator,createTime) values(sys_guid(),'thread','backup','batchcounts','String','1000','int','备份数据库每批量数','initer',sysdate);
insert into sysconf(uuid,CFGCategory,CFGGroup,CFGKey,CFGKeyType,CFGValue,CFGValueType,CFGValueInfo,creator,createTime) values(sys_guid(),'thread','clean','batchcounts','String','1000','int','备份数据库每批量数','initer',sysdate);
insert into sysconf(uuid,CFGCategory,CFGGroup,CFGKey,CFGKeyType,CFGValue,CFGValueType,CFGValueInfo,creator,createTime) values(sys_guid(),'thread','backup','looptime','String','3','int','每三个月备份一次','initer',sysdate);
insert into sysconf(uuid,CFGCategory,CFGGroup,CFGKey,CFGKeyType,CFGValue,CFGValueType,CFGValueInfo,creator,createTime) values(sys_guid(),'thread','clean','looptime','String','6','int','每六个月删除一次','initer',sysdate);
insert into sysconf(uuid,CFGCategory,CFGGroup,CFGKey,CFGKeyType,CFGValue,CFGValueType,CFGValueInfo,creator,createTime) values(sys_guid(),'log','log4j','appender','String','LOG_ROOT','String','日志','initer',sysdate);
insert into sysconf(uuid,CFGCategory,CFGGroup,CFGKey,CFGKeyType,CFGValue,CFGValueType,CFGValueInfo,creator,createTime) values(sys_guid(),'log','apilog','logLevel','String','debug','String','日志级别','initer',sysdate);
insert into sysconf(uuid,CFGCategory,CFGGroup,CFGKey,CFGKeyType,CFGValue,CFGValueType,CFGValueInfo,creator,createTime) values(sys_guid(),'ui','layout','title','String','IWS','String','标题','initer',sysdate);
insert into sysconf(uuid,CFGCategory,CFGGroup,CFGKey,CFGKeyType,CFGValue,CFGValueType,CFGValueInfo,creator,createTime) values(sys_guid(),'interface','insertitsm','enable','String','true','Boolean','是否启用ITSM插入接口','initer',sysdate);
insert into sysconf(uuid,CFGCategory,CFGGroup,CFGKey,CFGKeyType,CFGValue,CFGValueType,CFGValueInfo,creator,createTime) values(sys_guid(),'interface','queryitsm','enable','String','true','Boolean','是否启用ITSM查询接口','initer',sysdate);
insert into sysconf(uuid,CFGCategory,CFGGroup,CFGKey,CFGKeyType,CFGValue,CFGValueType,CFGValueInfo,creator,createTime) values(sys_guid(),'interface','edititsm','enable','String','true','Boolean','是否启用ITSM更新接口','initer',sysdate);
insert into sysconf(uuid,CFGCategory,CFGGroup,CFGKey,CFGKeyType,CFGValue,CFGValueType,CFGValueInfo,creator,createTime) values(sys_guid(),'interface','inititsm','enable','String','true','Boolean','是否启用ITSM初始化接口','initer',sysdate);

CREATE TABLE apilog
 (
   uuid varchar2(36),
   logcode varchar2(10),
   loglevel varchar2(10),
   logtitle varchar2(50),
   logcontent clob,
   extlogcode varchar2(10),
   extlogcontent clob,
   logcategory varchar2(20),
   logmodule varchar2(20),
   actip varchar2(20),
   actmac varchar2(20),
   status varchar2(5),
   creator varchar2(20),
   createTime timestamp,
   modifier varchar(20),
   modifyTime timestamp,
   att1 varchar2(1000),
   att2 varchar2(1000),
   att3 varchar2(1000),
   PRIMARY KEY (uuid)
 );




