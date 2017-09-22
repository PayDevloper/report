-----------------------------------------------------
-- Export file for user DEV_LIVE_PROD3             --
-- Created by Administrator on 2017/1/11, 18:18:05 --
-----------------------------------------------------

spool 20170111db.log

prompt
prompt Creating table PAY_BOND
prompt =======================
prompt
create table PAY_BOND
(
  SELLER         VARCHAR2(200),
  MARKETING      VARCHAR2(200),
  MARKETINGID    VARCHAR2(200),
  WARNINGSUM     VARCHAR2(200),
  CREATEPERSON   VARCHAR2(200),
  CREATETIME     VARCHAR2(200),
  APPROVE        VARCHAR2(200),
  PAYMENT        VARCHAR2(200),
  REMARK         VARCHAR2(500),
  REMARK1        VARCHAR2(500),
  REMARK2        VARCHAR2(500),
  REMARK3        VARCHAR2(500),
  REMARK4        VARCHAR2(500),
  REMARK5        VARCHAR2(500),
  ID             VARCHAR2(200),
  CODEID         VARCHAR2(200),
  SUBITEMCODE    VARCHAR2(200),
  SUBITEMNAME    VARCHAR2(200),
  TOTALITEMNAME  VARCHAR2(200),
  FINANCEACC     VARCHAR2(200),
  MINPAYAMOUNT   VARCHAR2(200),
  HAVEDEPOSIT    VARCHAR2(200) default 0,
  WHETHERALERTED VARCHAR2(200),
  AUDITPERSON    VARCHAR2(50),
  AUDITTIME      VARCHAR2(50)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );
comment on column PAY_BOND.AUDITPERSON
  is '审核人';
comment on column PAY_BOND.AUDITTIME
  is '审核日期';

prompt
prompt Creating table PAY_BONDHISTORY
prompt ==============================
prompt
create table PAY_BONDHISTORY
(
  SELLER         VARCHAR2(200),
  MARKETING      VARCHAR2(200),
  MARKETINGID    VARCHAR2(200),
  WARNINGSUM     VARCHAR2(200),
  CREATEPERSON   VARCHAR2(200),
  CREATETIME     VARCHAR2(200),
  APPROVE        VARCHAR2(200),
  PAYMENT        VARCHAR2(200),
  REMARK         VARCHAR2(500),
  REMARK1        VARCHAR2(500),
  REMARK2        VARCHAR2(500),
  REMARK3        VARCHAR2(500),
  REMARK4        VARCHAR2(500),
  REMARK5        VARCHAR2(500),
  ID             VARCHAR2(200),
  CODEID         VARCHAR2(200),
  SUBITEMCODE    VARCHAR2(200),
  SUBITEMNAME    VARCHAR2(200),
  TOTALITEMNAME  VARCHAR2(200),
  FINANCEACC     VARCHAR2(200),
  MINPAYAMOUNT   VARCHAR2(200),
  HAVEDEPOSIT    VARCHAR2(200),
  WHETHERALERTED VARCHAR2(200),
  AUDITPERSON    VARCHAR2(50),
  AUDITTIME      VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column PAY_BONDHISTORY.AUDITPERSON
  is '审核人';
comment on column PAY_BONDHISTORY.AUDITTIME
  is '审核日期';

prompt
prompt Creating table PAY_COSTPAY
prompt ==========================
prompt
create table PAY_COSTPAY
(
  SELLER        VARCHAR2(200),
  MARKETING     VARCHAR2(200),
  MARKETINGID   VARCHAR2(200),
  SHOULDPAY     VARCHAR2(200),
  ACTUALPAY     VARCHAR2(200),
  PAYMENTMETHOD VARCHAR2(200),
  CREATEPERSON  VARCHAR2(200),
  CREATETIME    VARCHAR2(200),
  REMARK        VARCHAR2(200),
  REMARK1       VARCHAR2(200),
  REMARK2       VARCHAR2(200),
  REMARK3       VARCHAR2(200),
  REMARK4       VARCHAR2(200),
  REMARK5       VARCHAR2(200),
  ID            VARCHAR2(200),
  CODEID        VARCHAR2(200),
  PAYMENTCODE   VARCHAR2(200),
  SUBITEMNAME   VARCHAR2(200),
  TOTALITEMNAME VARCHAR2(200),
  FINANCEACC    VARCHAR2(200),
  PAYORDERID    VARCHAR2(200),
  PAYTIME       VARCHAR2(200)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PAY_DEPOSITBALANCE
prompt =================================
prompt
create table PAY_DEPOSITBALANCE
(
  SELLER             VARCHAR2(50),
  MARKETING          VARCHAR2(50),
  MARKETINGID        VARCHAR2(50),
  ORDERNUMBER        VARCHAR2(50),
  PRODUCTBRAND       VARCHAR2(50),
  EXCHANGEMODEL      VARCHAR2(50),
  MEASUREDIMENSION   VARCHAR2(50),
  PRODUCTCATEGORY    VARCHAR2(50),
  PRODUCTAPPLICATION VARCHAR2(50),
  BUYERID            VARCHAR2(50),
  DEDUCTINGMONEY     VARCHAR2(50),
  CREATEPERSON       VARCHAR2(50),
  CREATETIME         VARCHAR2(50),
  REMARK             VARCHAR2(200),
  REMARK1            VARCHAR2(200),
  REMARK2            VARCHAR2(200),
  REMARK3            VARCHAR2(200),
  REMARK4            VARCHAR2(200),
  REMARK5            VARCHAR2(200),
  ID                 VARCHAR2(50) not null,
  CODEID             VARCHAR2(50)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );

prompt
prompt Creating table PAY_EDITACCOUNTINGRULES
prompt ======================================
prompt
create table PAY_EDITACCOUNTINGRULES
(
  SELLER                 VARCHAR2(50),
  MARKETING              VARCHAR2(50),
  MARKETINGID            VARCHAR2(50),
  TRADINGPATTERNS        VARCHAR2(50),
  CHARGINGDIMENSIONALITY VARCHAR2(50),
  DIMENSIONPARAMETER     VARCHAR2(50),
  CHARGINGPARTY          VARCHAR2(50),
  PRODUCTCATEGORY        VARCHAR2(50),
  PRODUCTAPPLICATION     VARCHAR2(50),
  PRODUCTBRAND           VARCHAR2(50),
  CHARGINGMODE           VARCHAR2(50),
  PRIORITY               VARCHAR2(50),
  EFFECTIVETIME          VARCHAR2(50),
  INVALIDTIME            VARCHAR2(50),
  CREATEPERSON           VARCHAR2(50),
  CREATETIME             VARCHAR2(50),
  APPROVE                VARCHAR2(50),
  REMARK                 VARCHAR2(200),
  REMARK1                VARCHAR2(200),
  REMARK2                VARCHAR2(200),
  REMARK3                VARCHAR2(200),
  REMARK4                VARCHAR2(200),
  REMARK5                VARCHAR2(200),
  ID                     VARCHAR2(50) not null,
  CODEID                 VARCHAR2(50),
  ORDERSOURCE            VARCHAR2(50),
  LADDERPRICE            VARCHAR2(50),
  STARTTHRESHOLD         VARCHAR2(50),
  ENDTHRESHOLD           VARCHAR2(50),
  STARTTHRESHOLDUNIT     VARCHAR2(50),
  ENDTHRESHOLDUNIT       VARCHAR2(50),
  STEPMODE               VARCHAR2(50),
  STEPMODENUM            VARCHAR2(50),
  AUDITPERSON            VARCHAR2(50),
  AUDITTIME              VARCHAR2(50)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column PAY_EDITACCOUNTINGRULES.TRADINGPATTERNS
  is '交易模式';
comment on column PAY_EDITACCOUNTINGRULES.CHARGINGDIMENSIONALITY
  is '计量维度单位';
comment on column PAY_EDITACCOUNTINGRULES.DIMENSIONPARAMETER
  is '计量维度';
comment on column PAY_EDITACCOUNTINGRULES.CHARGINGPARTY
  is '收费对象';
comment on column PAY_EDITACCOUNTINGRULES.PRODUCTCATEGORY
  is '产品分类';
comment on column PAY_EDITACCOUNTINGRULES.PRODUCTAPPLICATION
  is '产品应用';
comment on column PAY_EDITACCOUNTINGRULES.PRODUCTBRAND
  is '产品牌号';
comment on column PAY_EDITACCOUNTINGRULES.CHARGINGMODE
  is '计费模式';
comment on column PAY_EDITACCOUNTINGRULES.PRIORITY
  is '优先级';
comment on column PAY_EDITACCOUNTINGRULES.EFFECTIVETIME
  is '有效期开始';
comment on column PAY_EDITACCOUNTINGRULES.INVALIDTIME
  is '有效期结束';
comment on column PAY_EDITACCOUNTINGRULES.ORDERSOURCE
  is '订单来源';
comment on column PAY_EDITACCOUNTINGRULES.LADDERPRICE
  is '阶梯价格';
comment on column PAY_EDITACCOUNTINGRULES.STARTTHRESHOLD
  is '开始阙值设置';
comment on column PAY_EDITACCOUNTINGRULES.ENDTHRESHOLD
  is '结束阙值设置';
comment on column PAY_EDITACCOUNTINGRULES.STARTTHRESHOLDUNIT
  is '开始阙值设置单位';
comment on column PAY_EDITACCOUNTINGRULES.ENDTHRESHOLDUNIT
  is '结束阙值设置单位';
comment on column PAY_EDITACCOUNTINGRULES.STEPMODE
  is '费用维度';
comment on column PAY_EDITACCOUNTINGRULES.STEPMODENUM
  is '费用单价';
comment on column PAY_EDITACCOUNTINGRULES.AUDITPERSON
  is '审核人';
comment on column PAY_EDITACCOUNTINGRULES.AUDITTIME
  is '审核日期';

prompt
prompt Creating table PAY_EDITACCOUNT_HISTORY
prompt ======================================
prompt
create table PAY_EDITACCOUNT_HISTORY
(
  SELLER                 VARCHAR2(50),
  MARKETING              VARCHAR2(50),
  MARKETINGID            VARCHAR2(50),
  TRADINGPATTERNS        VARCHAR2(50),
  CHARGINGDIMENSIONALITY VARCHAR2(50),
  DIMENSIONPARAMETER     VARCHAR2(50),
  CHARGINGPARTY          VARCHAR2(50),
  PRODUCTCATEGORY        VARCHAR2(50),
  PRODUCTAPPLICATION     VARCHAR2(50),
  PRODUCTBRAND           VARCHAR2(50),
  CHARGINGMODE           VARCHAR2(50),
  PRIORITY               VARCHAR2(50),
  EFFECTIVETIME          VARCHAR2(50),
  INVALIDTIME            VARCHAR2(50),
  CREATEPERSON           VARCHAR2(50),
  CREATETIME             VARCHAR2(50),
  APPROVE                VARCHAR2(50),
  REMARK                 VARCHAR2(200),
  REMARK1                VARCHAR2(200),
  REMARK2                VARCHAR2(200),
  REMARK3                VARCHAR2(200),
  REMARK4                VARCHAR2(200),
  REMARK5                VARCHAR2(200),
  ID                     VARCHAR2(50),
  CODEID                 VARCHAR2(50),
  ORDERSOURCE            VARCHAR2(50),
  LADDERPRICE            VARCHAR2(50),
  STARTTHRESHOLD         VARCHAR2(50),
  ENDTHRESHOLD           VARCHAR2(50),
  STARTTHRESHOLDUNIT     VARCHAR2(50),
  ENDTHRESHOLDUNIT       VARCHAR2(50),
  STEPMODE               VARCHAR2(50),
  STEPMODENUM            VARCHAR2(50),
  AUDITPERSON            VARCHAR2(50),
  AUDITTIME              VARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column PAY_EDITACCOUNT_HISTORY.TRADINGPATTERNS
  is '交易模式';
comment on column PAY_EDITACCOUNT_HISTORY.CHARGINGDIMENSIONALITY
  is '计量维度单位';
comment on column PAY_EDITACCOUNT_HISTORY.DIMENSIONPARAMETER
  is '计量维度';
comment on column PAY_EDITACCOUNT_HISTORY.CHARGINGPARTY
  is '收费对象';
comment on column PAY_EDITACCOUNT_HISTORY.PRODUCTCATEGORY
  is '产品分类';
comment on column PAY_EDITACCOUNT_HISTORY.PRODUCTAPPLICATION
  is '产品应用';
comment on column PAY_EDITACCOUNT_HISTORY.PRODUCTBRAND
  is '产品牌号';
comment on column PAY_EDITACCOUNT_HISTORY.CHARGINGMODE
  is '计费模式';
comment on column PAY_EDITACCOUNT_HISTORY.PRIORITY
  is '优先级';
comment on column PAY_EDITACCOUNT_HISTORY.EFFECTIVETIME
  is '有效期开始';
comment on column PAY_EDITACCOUNT_HISTORY.INVALIDTIME
  is '有效期结束';
comment on column PAY_EDITACCOUNT_HISTORY.ORDERSOURCE
  is '订单来源';
comment on column PAY_EDITACCOUNT_HISTORY.LADDERPRICE
  is '阶梯价格';
comment on column PAY_EDITACCOUNT_HISTORY.STARTTHRESHOLD
  is '开始阙值设置';
comment on column PAY_EDITACCOUNT_HISTORY.ENDTHRESHOLD
  is '结束阙值设置';
comment on column PAY_EDITACCOUNT_HISTORY.STARTTHRESHOLDUNIT
  is '开始阙值设置单位';
comment on column PAY_EDITACCOUNT_HISTORY.ENDTHRESHOLDUNIT
  is '结束阙值设置单位';
comment on column PAY_EDITACCOUNT_HISTORY.STEPMODE
  is '费用维度';
comment on column PAY_EDITACCOUNT_HISTORY.STEPMODENUM
  is '费用单价';
comment on column PAY_EDITACCOUNT_HISTORY.AUDITPERSON
  is '审核人';
comment on column PAY_EDITACCOUNT_HISTORY.AUDITTIME
  is '审核日期';

prompt
prompt Creating table PAY_NEWACCOUNTINGRULES
prompt =====================================
prompt
create table PAY_NEWACCOUNTINGRULES
(
  SELLER               VARCHAR2(50),
  MARKETING            VARCHAR2(50),
  MARKETINGID          VARCHAR2(20),
  EXCHANGEMODEL        VARCHAR2(20),
  MEASUREDIMENSION     VARCHAR2(20),
  PRODUCTCATEGORY      VARCHAR2(20),
  PRODUCTAPPLICATION   VARCHAR2(20),
  PRODUCTBRAND         VARCHAR2(20),
  COLLECTIONOBJECT     VARCHAR2(20),
  CREATEPERSON         VARCHAR2(20),
  CREATETIME           VARCHAR2(20),
  APPROVE              VARCHAR2(20),
  BILLINGCONFIGURATION VARCHAR2(20),
  REMARK               VARCHAR2(200),
  REMARK1              VARCHAR2(200),
  REMARK2              VARCHAR2(200),
  REMARK3              VARCHAR2(200),
  REMARK4              VARCHAR2(200),
  REMARK5              VARCHAR2(200),
  ORDERSOURCEOBJECT    VARCHAR2(20),
  ID                   VARCHAR2(50) not null,
  CODEID               VARCHAR2(50),
  AUDITPERSON          VARCHAR2(50),
  AUDITTIME            VARCHAR2(50)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column PAY_NEWACCOUNTINGRULES.ORDERSOURCEOBJECT
  is '订单来源计费';
comment on column PAY_NEWACCOUNTINGRULES.AUDITPERSON
  is '审核人';
comment on column PAY_NEWACCOUNTINGRULES.AUDITTIME
  is '审核日期';

prompt
prompt Creating table PAY_RIGHTGOLD
prompt ============================
prompt
create table PAY_RIGHTGOLD
(
  SELLER        VARCHAR2(50),
  MARKETING     VARCHAR2(50),
  MARKETINGID   VARCHAR2(50),
  SUBITEMCODE   VARCHAR2(50),
  SUBITEMNAME   VARCHAR2(50),
  TOTALITEMNAME VARCHAR2(20),
  FINANCEACC    VARCHAR2(20),
  CREATEPERSON  VARCHAR2(20),
  CREATETIME    VARCHAR2(20),
  APPROVE       VARCHAR2(20),
  PAYMENT       VARCHAR2(20),
  REMARK        VARCHAR2(200),
  REMARK1       VARCHAR2(200),
  REMARK2       VARCHAR2(200),
  REMARK3       VARCHAR2(200),
  REMARK4       VARCHAR2(300),
  REMARK5       VARCHAR2(300),
  ID            VARCHAR2(50) not null,
  PAYAMOUNT     VARCHAR2(50),
  STARTDATE     VARCHAR2(50),
  ENDDATE       VARCHAR2(50),
  AUDITPERSON   VARCHAR2(50),
  AUDITTIME     VARCHAR2(50)
)
tablespace SYSTEM
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column PAY_RIGHTGOLD.SELLER
  is '卖家';
comment on column PAY_RIGHTGOLD.MARKETING
  is '销售组织';
comment on column PAY_RIGHTGOLD.MARKETINGID
  is '销售组织ID';
comment on column PAY_RIGHTGOLD.SUBITEMCODE
  is '子项配置编号';
comment on column PAY_RIGHTGOLD.SUBITEMNAME
  is '子项名称';
comment on column PAY_RIGHTGOLD.TOTALITEMNAME
  is '总项名称';
comment on column PAY_RIGHTGOLD.FINANCEACC
  is '财务科目';
comment on column PAY_RIGHTGOLD.APPROVE
  is '是否审核';
comment on column PAY_RIGHTGOLD.PAYMENT
  is '是否缴费';
comment on column PAY_RIGHTGOLD.PAYAMOUNT
  is '缴费金额';
comment on column PAY_RIGHTGOLD.STARTDATE
  is '生效开始日期';
comment on column PAY_RIGHTGOLD.ENDDATE
  is '生效截止日期';
comment on column PAY_RIGHTGOLD.AUDITPERSON
  is '审核人';
comment on column PAY_RIGHTGOLD.AUDITTIME
  is '审核日期';

prompt
prompt Creating table PAY_SUBITEMS
prompt ===========================
prompt
create table PAY_SUBITEMS
(
  ID           VARCHAR2(36) not null,
  SUBNAME      VARCHAR2(100) not null,
  TOTALNAME    VARCHAR2(100) not null,
  TOTALID      VARCHAR2(100) not null,
  FINANCEACC   VARCHAR2(100) not null,
  AUDIT_STATUS VARCHAR2(100),
  OPERUSER     VARCHAR2(100) not null,
  OPERDATE     VARCHAR2(100) not null,
  REMARK       VARCHAR2(500),
  REMARK1      VARCHAR2(100),
  REMARK2      VARCHAR2(100),
  REMARK3      VARCHAR2(100),
  REMARK4      VARCHAR2(100),
  REMARK5      VARCHAR2(100),
  CODEID       VARCHAR2(100)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column PAY_SUBITEMS.SUBNAME
  is '子项名称';
comment on column PAY_SUBITEMS.TOTALNAME
  is '总项名称';
comment on column PAY_SUBITEMS.TOTALID
  is '总项编号';
comment on column PAY_SUBITEMS.FINANCEACC
  is '财务科目';
comment on column PAY_SUBITEMS.AUDIT_STATUS
  is '是否审核';
comment on column PAY_SUBITEMS.OPERUSER
  is '创建人';
comment on column PAY_SUBITEMS.OPERDATE
  is '创建日期';
comment on column PAY_SUBITEMS.REMARK
  is '备注';
comment on column PAY_SUBITEMS.REMARK1
  is '备用1';
comment on column PAY_SUBITEMS.REMARK2
  is '备用2';
comment on column PAY_SUBITEMS.REMARK3
  is '备用3';
comment on column PAY_SUBITEMS.REMARK4
  is '备用4';
comment on column PAY_SUBITEMS.REMARK5
  is '备用5';
comment on column PAY_SUBITEMS.CODEID
  is '数据标识用于历史记录关联';

prompt
prompt Creating table PAY_TOTALITEMS
prompt =============================
prompt
create table PAY_TOTALITEMS
(
  ID        VARCHAR2(36) not null,
  TOTALNAME VARCHAR2(100) not null,
  TOTALTYPE VARCHAR2(100) not null,
  OPERUSER  VARCHAR2(100) not null,
  OPERDATE  VARCHAR2(100) not null,
  REMARK    VARCHAR2(500),
  REMARK1   VARCHAR2(100),
  REMARK2   VARCHAR2(100),
  REMARK3   VARCHAR2(100),
  REMARK4   VARCHAR2(100),
  REMARK5   VARCHAR2(100),
  CODEID    VARCHAR2(100)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
comment on column PAY_TOTALITEMS.TOTALNAME
  is '总项名称';
comment on column PAY_TOTALITEMS.TOTALTYPE
  is '总项类型';
comment on column PAY_TOTALITEMS.OPERUSER
  is '创建人';
comment on column PAY_TOTALITEMS.OPERDATE
  is '创建日期';
comment on column PAY_TOTALITEMS.REMARK
  is '备注';
comment on column PAY_TOTALITEMS.REMARK1
  is '备用1';
comment on column PAY_TOTALITEMS.REMARK2
  is '备用2';
comment on column PAY_TOTALITEMS.REMARK3
  is '备用3';
comment on column PAY_TOTALITEMS.REMARK4
  is '备用4';
comment on column PAY_TOTALITEMS.REMARK5
  is '备用5';
comment on column PAY_TOTALITEMS.CODEID
  is '数据标识用于历史记录关联';

prompt
prompt Creating view PAY_ORDER_VIEWS
prompt =============================
prompt
create or replace view pay_order_views as
select ORDER_ID as ordernumber,remark1,marketingid,EXCHANGEMODEL,FINISH_TIME as remark2 from 
(select order_id,sum(quantity) as remark1,saler_id as marketingid,chname as exchangemodel,to_char(finish_time,'yyyy-mm-dd hh24:mi:ss') as finish_time from (
select do.*,spad.finish_time from 
(select dorder.order_id,ite.quantity,desorss.chname,other.saler_id from 
(select * from DEV_LIVE_PROD3.DCSPP_ORDER where STATE='QUOTED') dorder 
left join DEV_LIVE_PROD3.SUMAO_ORDER_OTHER other on dorder.order_id=other.order_id 
left join (select order_id,chname from DEV_LIVE_PROD3.dcspp_order desor left join  pay_dictionary pay on 
pay.serialid=desor.type where pay.type='jyms') desorss on  dorder.order_id=desorss.order_id 
left join (select order_id,quantity from DCSPP_ORDER_ITEM itemss left join DCSPP_ITEM  item on 
itemss.commerce_items=item.commerce_item_id) ite on dorder.order_id=ite.order_id order by dorder.order_id) do 
left join dcspp_order_pg dop on do.order_id=dop.order_id  
left join (select * from sumao_pay_group where use_for=2) spg on dop.payment_groups=spg.payment_group_id 
left join (select * from sumao_pay_amount_detail where type=5) spad on spg.payment_group_id=spad.payment_group_id) o 
 where o.finish_time is not null group by order_id,saler_id,chname,finish_time) od 
 where od.order_id not in (select distinct t.ordernumber from pay_depositbalance t)
/


spool off
