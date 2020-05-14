/*
DROP TABLE IF EXISTS product_category_roll_up;
DROP TABLE IF EXISTS product_category_classification;
DROP TABLE IF EXISTS product_identification_types_products;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_categories;
DROP TABLE IF EXISTS product_category_types;
DROP TABLE IF EXISTS product_identification_types;
DROP TABLE IF EXISTS product_sub_types
*/

/*
Defines the type of table product_categories
*/
CREATE TABLE product_category_types (
	id SERIAL PRIMARY KEY,
	category_type_name TEXT
);

/*
Available product categories
*/
CREATE TABLE product_categories (
	id SERIAL PRIMARY KEY,
	category_name TEXT,
	
	/*
	- foreign key
	- e.g. by Material, Purpose: (office, child care), Industry
	*/
	category_type INTEGER,
	
	CONSTRAINT fk_product_categories_ref_product_category_types FOREIGN KEY (category_type)
	REFERENCES product_category_types
);

/*
This is to define a category can be constituded of other product categories.
E.g. Korea, Vietnam, Thailand products can be considered as Asian Products.
*/
CREATE TABLE product_category_roll_up (
	parent_category_id INTEGER,
	child_category_id INTEGER,
	
	CONSTRAINT pk_product_category_roll_up PRIMARY KEY (parent_category_id, child_category_id),
	CONSTRAINT fk_parent_category_id_ref_product_category FOREIGN KEY (parent_category_id)
	REFERENCES product_categories,
	CONSTRAINT fk_child_category_id_ref_product_category FOREIGN KEY (child_category_id)
	REFERENCES product_categories
);

/*
This is different from product categories
E.g. products can be goods or services
*/
CREATE TABLE product_sub_types (
	id SERIAL PRIMARY KEY,
	name TEXT
);


CREATE TABLE products (
	id SERIAL PRIMARY KEY,
	product_name TEXT,
	product_description TEXT,
	product_sub_type INTEGER,
	
	CONSTRAINT fk_products_ref_product_sub_type FOREIGN KEY (product_sub_type)
	REFERENCES product_sub_types
);

/*
Each product may have different product ID
E.g. each product has its Universal ID standard in America, and/or in Europe
*/
CREATE TABLE product_identification_types (
	id INTEGER PRIMARY KEY,
	/*e.g. ISBN, SKU, UPC-A, UPC-E*/
	name TEXT,
	description TEXT
);

/*
Many-to-many table between products and product_identification_types
*/
CREATE TABLE product_identification_types_products (
	product_id INTEGER,
	product_identification_type_id INTEGER,
	product_id_number TEXT,
	
	CONSTRAINT pk_product_identification_types_products PRIMARY KEY (product_id, product_identification_type_id),
	CONSTRAINT fk_ref_product_identification_types FOREIGN KEY (product_identification_type_id)
	REFERENCES product_identification_types,
	CONSTRAINT product_identification_types_products_ref_products FOREIGN KEY (product_id)
	REFERENCES products
);

/*
Many to many relation ship between product and category
*/
CREATE TABLE product_category_classification (
	/*
	- Foreign key
	*/
	product_id INTEGER,
	/*
	- Foreign key
	*/
	category_id INTEGER,
	
	CONSTRAINT fk_product_category_classification_ref_product FOREIGN KEY (product_id)
	REFERENCES products,
	
	CONSTRAINT fk_product_category_classification_ref_product_category FOREIGN KEY (category_id)
	REFERENCES product_categories
);