create database sms;
use sms;
-- tạo bảng Khách hàng
create table customer (
    customer_id int auto_increment primary key,
    customer_name varchar(255) not null
);

-- tạo bảng nhân viên
create table employee (
    employee_id int auto_increment primary key,
    employee_name varchar(255) not null,
    salary decimal(10, 2) not null,
    supervisor_id int
);

-- tạo bảng sản phẩm
create table product (
    product_id int auto_increment primary key,
    product_name varchar(255) not null,
    list_price decimal(10, 2) not null
);

-- tạo bảng đơn hàng
create table orders (
    order_id int auto_increment primary key,
    order_date datetime not null,
    customer_id int not null,
    employee_id int not null,
    total decimal(10, 2),
    foreign key (customer_id) references customer(customer_id),
    foreign key (employee_id) references employee(employee_id)
);

-- tạo bảng chi tiết về đơn hàng
create table lineitem (
    order_id int not null,
    product_id int not null,
    quantity int not null,
    price decimal(10, 2),
    primary key (order_id, product_id),
    foreign key (order_id) references orders(order_id),
    foreign key (product_id) references product(product_id)
);


-- câu 1
select customer_id, customer_name
from customer
order by customer_id;

-- Câu 2
select
    order_id,
    order_date,
    customer_id,
    employee_id,
    total
from orders
where customer_id = 1;

-- Câu 3
-- ví dụ muốn xem chi tiết của đơn hàng có order_id = 1
select *
from LineItem
where order_id = 1;

-- Câu 4
DELIMITER //

CREATE FUNCTION func_tinhtongdonhang(in_order_id INT)
RETURNS DECIMAL(10, 2)
DETERMINISTIC
BEGIN
    DECLARE s_total DECIMAL(10, 2);

    SELECT SUM(quantity * price) 
    INTO s_total
    FROM LineItem
    WHERE order_id = in_order_id;

    RETURN s_total;
END //

DELIMITER ;

-- câu 5

DELIMITER //

CREATE PROCEDURE sp_Addnguoidungmoi(IN in_customer_name VARCHAR(255))
BEGIN
    INSERT INTO Customer(customer_name)VALUES (in_customer_name);
END //

DELIMITER ;

-- Cau 6

delimiter //

create procedure sp_deletekhachhang(in in_customer_id int)
begin

    delete from lineitem where order_id in (select order_id from orders where customer_id = in_customer_id);

    delete from orders where customer_id = in_customer_id;

    delete from customer where customer_id = in_customer_id;
end //

delimiter ;

-- câu 7

DELIMITER //

CREATE PROCEDURE sp_Updatekhachhang(IN in_customer_id INT,IN in_new_name VARCHAR(255))

BEGIN

    UPDATE Customer SET customer_name = in_new_name WHERE customer_id = in_customer_id;

END //
DELIMITER ;

-- câu 8

DELIMITER //

CREATE PROCEDURE sp_Insertdonhangmoi(IN in_customer_id INT,IN in_employee_id INT)
BEGIN
    
    INSERT INTO Orders(order_date, customer_id, employee_id, total) VALUES (NOW(), in_customer_id, in_employee_id, NULL);


END//

DELIMITER ;

-- câu 9

DELIMITER //

CREATE PROCEDURE sp_Addchitietdonhang(IN in_order_id INT,IN in_product_id INT,IN in_quantity INT,IN in_price_at_purchase DECIMAL(10, 2))
BEGIN
    INSERT INTO LineItem(order_id, product_id, quantity, price)VALUES (in_order_id, in_product_id, in_quantity, in_price_at_purchase);
END//

DELIMITER ;

-- câu 10

DELIMITER //

CREATE PROCEDURE sp_UpdateOrderTotal(IN in_order_id INT)
BEGIN
    DECLARE v_calculated_total DECIMAL(10, 2);
    
    SET v_calculated_total = func_tinhtongdonhang(in_order_id);
		
    UPDATE Orders SET total = v_calculated_total WHERE order_id = in_order_id;
END//

DELIMITER ;



