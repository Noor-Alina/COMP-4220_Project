CREATE DATABASE bookmanagement;

use bookmanagement;

CREATE TABLE StudentInfo (
    student_id INTEGER(9) PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    email VARCHAR(30)
);

CREATE TABLE BookInfo (
	book_isbn INTEGER(10) PRIMARY KEY,
    book_name VARCHAR(200),
    author_fname VARCHAR(30),
    author_lname VARCHAR(30),
    book_price DECIMAL(10,2),
    book_stock INTEGER(10)
    );
    