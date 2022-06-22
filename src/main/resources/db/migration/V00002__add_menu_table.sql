-- ensure that the table with this name is removed before creating a new one.
DROP TABLE IF EXISTS menu;

-- Create tg_user table
CREATE TABLE menu (
   id_menu int UNIQUE NOT NULL AUTO_INCREMENT,
   name VARCHAR(100),
   price decimal,
   PRIMARY KEY (id_menu)
);