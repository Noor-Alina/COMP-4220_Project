CREATE DATABASE bookmanagement;

use bookmanagement;

DROP Table employeeManagement;
DROP Table placedspecificorder;
DROP TABLE soldbooks;
DROP Table loanedbooks;
DROP Table orderinventory;
DROP TABLE reservedBooks;
DROP TABLE studentInfo;
DROP TABLE employeeInfo;
DROP TABLE bookInfo;
DROP Table LibraryBookCheck;
DROP TABLE LibraryRequest;


CREATE TABLE StudentInfo (
    student_id INTEGER(9) PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    email VARCHAR(30)
);

CREATE TABLE BookInfo (
	book_isbn INTEGER(10) PRIMARY KEY,
    book_name VARCHAR(50),
    author_fname VARCHAR(30),
    author_lname VARCHAR(30),
    book_publisher VARCHAR(50),
    book_price DECIMAL(10,2),
    sell_stock INTEGER(10),
    loan_stock INTEGER(10)
    );

CREATE TABLE EmployeeInfo (
	emp_id INTEGER(5) PRIMARY KEY,
    emp_fname VARCHAR(30),
    emp_lname VARCHAR(30)
);

CREATE TABLE loanedBooks(
	reservation_id INTEGER(5) PRIMARY KEY,
    book_isbn INTEGER(10),
    student_id INTEGER(9),
    loaned_date DATE,
    due_date DATE,
    FOREIGN KEY (student_id) REFERENCES StudentInfo(student_id),
    FOREIGN KEY (book_isbn) REFERENCES BookInfo(book_isbn)
);

CREATE TABLE PlacedSpecificOrder(
	order_id INTEGER AUTO_INCREMENT,
    student_id INTEGER(9), 
    book_isbn INTEGER(10),
    emp_id INTEGER(5),
    order_date DATE,
    PRIMARY KEY (order_id),
    FOREIGN KEY (student_id) REFERENCES StudentInfo(student_id),
    FOREIGN KEY (book_isbn) REFERENCES BookInfo(book_isbn),
    FOREIGN KEY (emp_id) REFERENCES EmployeeInfo(emp_id)
);

ALTER TABLE PlacedSpecificOrder AUTO_INCREMENT = 30001;

CREATE TABLE OrderInventory(
	id INTEGER(5) PRIMARY KEY AUTO_INCREMENT,
    book_isbn INTEGER(10),
    item_quantity INTEGER(10),
    arrival_date DATE,
    FOREIGN KEY (book_isbn) REFERENCES BookInfo(book_isbn)
);


CREATE TABLE ReservedBooks(
	reservation_id INTEGER AUTO_INCREMENT,
	student_id INTEGER(9), 
    book_isbn INTEGER(10),
    emp_id INTEGER(5),
    reservedInStock BOOLEAN,
    reserved_date DATE,
    PRIMARY KEY (reservation_id),
    FOREIGN KEY (student_id) REFERENCES StudentInfo(student_id),
    FOREIGN KEY (book_isbn) REFERENCES BookInfo(book_isbn),
    FOREIGN KEY (emp_id) REFERENCES EmployeeInfo(emp_id)
);

ALTER TABLE ReservedBooks AUTO_INCREMENT = 50001;

CREATE TABLE SoldBooks(
	purchase_id INTEGER AUTO_INCREMENT,
    student_id INTEGER(9), 
    book_isbn INTEGER(10),
    emp_id INTEGER(5),
    payment_id VARCHAR(4),
    purchase_date DATE,
    PRIMARY KEY (purchase_id),
    FOREIGN KEY (student_id) REFERENCES StudentInfo(student_id),
    FOREIGN KEY (book_isbn) REFERENCES BookInfo(book_isbn),
    FOREIGN KEY (emp_id) REFERENCES EmployeeInfo(emp_id)
);

ALTER TABLE SoldBooks AUTO_INCREMENT = 10001;

CREATE TABLE EmployeeManagement (
	id INTEGER(5) PRIMARY KEY AUTO_INCREMENT,
    emp_id INTEGER(5),
    work_date DATE,
    starting_time VARCHAR(30),
    ending_time VARCHAR(30),
    FOREIGN KEY (emp_id) REFERENCES EmployeeInfo(emp_id)
);

CREATE TABLE LibraryBookCheck(
	id INTEGER(5) PRIMARY KEY AUTO_INCREMENT,
    book_isbn INTEGER(10),
    lastloaned_date DATE, 
    FOREIGN KEY (book_isbn) REFERENCES BookInfo(book_isbn)
);


CREATE TABLE LibraryRequest(
	id INTEGER(5) PRIMARY KEY AUTO_INCREMENT,
    book_isbn INTEGER(10),
    emp_id INTEGER(5),
    #implement function so that request status defaults to "pending" when a request is added
    request_status VARCHAR(10) CHECK (request_status = 'Pending' OR request_status = 'Approved' OR request_status = 'Declined'),
    FOREIGN KEY (emp_id) REFERENCES EmployeeInfo(emp_id),
    FOREIGN KEY (book_isbn) REFERENCES BookInfo(book_isbn)
);


