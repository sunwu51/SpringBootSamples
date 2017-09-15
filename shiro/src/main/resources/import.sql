INSERT INTO users (userid,username,password) VALUES (1,'admin','admin');
INSERT INTO users (userid,username,password) VALUES (2,'frank','123456');
INSERT INTO users (userid,username,password) VALUES (3,'guest','123123');

INSERT INTO user_roles (id,username,role_name) VALUES (1,'admin','admin');
INSERT INTO user_roles (id,username,role_name) VALUES (2,'admin','general');
INSERT INTO user_roles (id,username,role_name) VALUES (3,'admin','guest');
INSERT INTO user_roles (id,username,role_name) VALUES (4,'frank','general');
INSERT INTO user_roles (id,username,role_name) VALUES (5,'frank','guest');
INSERT INTO user_roles (id,username,role_name) VALUES (6,'guest','guest');

INSERT INTO roles_permissions (id,role_name,permission) VALUES (1,'admin','insert');
INSERT INTO roles_permissions (id,role_name,permission) VALUES (2,'admin','delete');
INSERT INTO roles_permissions (id,role_name,permission) VALUES (3,'admin','update');
INSERT INTO roles_permissions (id,role_name,permission) VALUES (4,'admin','select');
INSERT INTO roles_permissions (id,role_name,permission) VALUES (5,'general','select');