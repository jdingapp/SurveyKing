create table if not exists t_user(
  id varchar(64),
  full_name varchar,
  avatar varchar,
  gender varchar,
  username varchar,
  phone varchar,
  password varchar,
  salt varchar,
  status int default 1,
  create_by varchar,
  create_at timestamp,
  update_by varchar,
  update_at timestamp,
  deleted int default 0,
  PRIMARY KEY(id),
  UNIQUE KEY t_user_username_UK(username)
);

create table if not exists t_project(
  id varchar(64) NOT NULL,
  short_id varchar(32),
  name varchar(64) comment '项目名称',
  survey CLOB comment '问卷',
  setting varchar comment '问卷设置',
  status int default 0 comment '0未发布 1已发布',
  create_by varchar,
  create_at timestamp,
  update_by varchar,
  belong_group varchar,
  update_at timestamp,
  deleted int default 0,
  PRIMARY KEY(id),
  UNIQUE KEY t_project_short_id_UK(short_id)
);

create table if not exists t_answer(
  id varchar(64),
  short_id varchar(64),
  answer CLOB comment '问卷答案',
  attachment varchar comment '问卷元数据',
  meta_info varchar comment '问卷元数据',
  temp_save int comment '0暂存 1已完成',
  create_by varchar,
  create_at timestamp,
  update_by varchar,
  update_at timestamp,
  deleted int default 0,
  PRIMARY KEY(id)
);

create table if not exists t_file(
    id varchar(64),
    original_name varchar(256),
    file_name  varchar(256),
    file_path varchar(512),
    thumb_file_path varchar(512),
    storage_type int,
    create_by varchar,
    create_at timestamp,
    update_by varchar,
    update_at timestamp,
    shared int default 0,
    deleted int default 0,
    PRIMARY KEY(id)
);

create table if not exists t_template(
    id varchar(64),
    name varchar(64) comment '模板标题',
    question_type varchar(64) comment '问题类型',
    template CLOB comment '模板',
    category varchar(256)  comment '模板分类',
    tag varchar(512)  comment '标签',
    priority int comment '排序优先级',
    preview_url varchar(512) comment '预览地址',
    create_at timestamp,
    create_by varchar,
    update_at timestamp,
    update_by varchar,
    shared int default 0,
    deleted int default 0,
    PRIMARY KEY(id)
);