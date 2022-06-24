-- ensure that the table with this name is removed before creating a new one.
DROP TABLE IF EXISTS orderxmenu;
DROP TABLE IF EXISTS orders;

-- Create order table
CREATE TABLE orders (
   id_order int UNIQUE NOT NULL AUTO_INCREMENT,
   chat_id VARCHAR(100),
   date_time VARCHAR(100),
   status boolean default(true),
   summ decimal,
   PRIMARY KEY (id_order),
   FOREIGN KEY (chat_id) REFERENCES tg_user (chat_id)
);

-- Create orderxmenu table
CREATE TABLE orderxmenu (
    id int NOT NULL AUTO_INCREMENT,
    id_order int NOT NULL,
    id_menu int NOT NULL,
    FOREIGN KEY (id_order) REFERENCES orders (id_order),
    FOREIGN KEY (id_menu) REFERENCES menu (id_menu),
    PRIMARY KEY (id)
);