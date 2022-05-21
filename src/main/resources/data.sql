INSERT INTO tbl_Role (id, role_Name) VALUES (1, 'USER');

INSERT INTO tbl_User (id, username, password, first_Name, last_Name)
VALUES (1,  'user', 'User$12345', 'User', 'User');

INSERT INTO tbl_User_Role(user_Id, role_Id) VALUES (1, 1);

------------------------------------------------------------------------------------------------------------------------