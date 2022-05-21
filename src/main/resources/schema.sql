create table tbl_Book
(
    Id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    Title      varchar(256) NOT NULL,
    Author     varchar(256) NOT NULL,
    Publisher  varchar(256),
    Page_Count smallint,
    Price      float(2)     NOT NULL,
    Stock      integer
);

---------------------------------------

create table tbl_Customer
(
    Id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    First_Name varchar(128) NOT NULL,
    Last_Name  varchar(128) NOT NULL,
    Birth_Date date         NOT NULL,
    Email      varchar(256) NOT NULL
);
CREATE UNIQUE INDEX UX_CUSTOMER_EMAIL ON tbl_Customer (Email);

---------------------------------------

create table tbl_Order
(
    Id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    Customer_Id  BIGINT   NOT NULL,
    Book_Id      BIGINT   NOT NULL,
    Payment_Mode char(15) NOT NULL,
    Order_Date   date     NOT NULL,
    CONSTRAINT FK_SECURITY_CUSTOMER_ID FOREIGN KEY (Customer_Id) REFERENCES tbl_Customer (id),
    CONSTRAINT FK_SECURITY_BOOK_ID FOREIGN KEY (Book_Id) REFERENCES tbl_Book (id)
);

---------------------------------------

CREATE TABLE tbl_Role
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_Name varchar(100)
);


CREATE TABLE tbl_User
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    username   varchar(255) NOT NULL,
    password   varchar(255) NOT NULL,
    first_Name varchar(255) NOT NULL,
    last_Name  varchar(255) NOT NULL
);


CREATE TABLE tbl_User_Role
(
    user_Id BIGINT NOT NULL,
    role_Id BIGINT NOT NULL,
    CONSTRAINT FK_SECURITY_USER_ID FOREIGN KEY (user_Id) REFERENCES tbl_User (id),
    CONSTRAINT FK_SECURITY_ROLE_ID FOREIGN KEY (role_Id) REFERENCES tbl_Role (id)
);

----------------------------------------