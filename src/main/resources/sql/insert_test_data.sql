INSERT INTO ishop.category (id, name, url) VALUES ('1', 'Phone', '/phone');
INSERT INTO ishop.category (id, name, url) VALUES ('2', 'Tablet', '/tablet');
INSERT INTO ishop.category (id, name, url) VALUES ('3', 'Laptop', '/laptop');
INSERT INTO ishop.category (id, name, url) VALUES ('4', 'Smartwatch', '/smartwatch');
INSERT INTO ishop.category (id, name, url) VALUES ('5', 'Accessories', '/accessories');
INSERT INTO ishop.category (id, name, url) VALUES ('6', 'Monitor', '/monitor');

INSERT INTO ishop.producer (id, name) VALUES ('1', 'Apple');
INSERT INTO ishop.producer (id, name) VALUES ('2', 'Asus');
INSERT INTO ishop.producer (id, name) VALUES ('3', 'Samsung');
INSERT INTO ishop.producer (id, name) VALUES ('4', 'Xiaomi');
INSERT INTO ishop.producer (id, name) VALUES ('5', 'Dell');
INSERT INTO ishop.producer (id, name) VALUES ('6', 'Lenovo');
INSERT INTO ishop.producer (id, name) VALUES ('7', 'Sony');

INSERT INTO ishop.product (id, name, description, image_link, price, category_id,producer_id)
VALUES ('1' , 'IPhone11 Pro Gold', 'Display: 6,5" Retina / Processor A13 Bionic / Camera 12,0 MPx + 12,0 MPx + 12,0 MPx / NFC / 3G / LTE / GPS / GLONASS / iOS 13', '/media/1.jpg', '1150', '1', '1');
INSERT INTO ishop.product (id, name, description, image_link, price, category_id,producer_id)
VALUES ('2' , 'IPhone11 Pro Grey', 'Display: 6,5" Retina / Processor A13 Bionic / Camera 12,0 MPx + 12,0 MPx + 12,0 MPx / NFC / 3G / LTE / GPS / GLONASS / iOS 13', '/media/2.jpg', '1100', '1', '1');
INSERT INTO ishop.product (id, name, description, image_link, price, category_id,producer_id)
VALUES ('3' , 'Watch 44 mm Black', 'Watch 44 mm Nike+ Space Gray with Anthracite/Black Nike Sport Band (MX3A2) Series 5 GPS + LTE', '/media/3.jpg', '450', '4', '1');
INSERT INTO ishop.product (id, name, description, image_link, price, category_id,producer_id)
VALUES ('4' , 'Galaxy Active 2 Gold', 'Watch 44 mm Gold with Anthracite/ Series 5 GPS + LTE', '/media/4.jpg', '350', '4', '3');
INSERT INTO ishop.product (id, name, description, image_link, price, category_id,producer_id)
VALUES ('5' , 'Galaxy Active 2 Silver', 'Watch 44 mm Silver with Anthracite/ Series 5 GPS + LTE', '/media/5.jpg', '350', '4', '3');
INSERT INTO ishop.product (id, name, description, image_link, price, category_id,producer_id)
VALUES ('6' , 'Galaxy Active 2 Black', 'Watch 44 mm Black with Anthracite/ Series 5 GPS + LTE', '/media/6.jpg', '350', '4', '3');
INSERT INTO ishop.product (id, name, description, image_link, price, category_id,producer_id)
VALUES ('8', 'Kindle', 'Display 9.7\" IPS (2048х1536) / Apple A10 Fusion / RAM 2 Gb/ 128 Gb / Wi-Fi / Bluetooth 4.2 / iOS 11 / 478g', '/media/8.jpg', '100.00', '5', '6');
INSERT INTO ishop.product (id, name, description, image_link, price, category_id,producer_id)
VALUES ('9', 'Macbook pro 13', 'Display 13.3\" (2560x1600) / Intel Core i5-8257U/ RAM 8 GB / SSD 256 GB / Intel Iris Plus Graphics 645 / 304.1x212.4x14.9 mm', '/media/9.jpg', '1150.00', '3', '1');
INSERT INTO ishop.product (id, name, description, image_link, price, category_id,producer_id)
VALUES ('10', 'Macbook pro 16', 'Display 16\" (2560x1600) / Intel Core i5-8257U/ RAM 8 GB / SSD 256 GB / Intel Iris Plus Graphics 645 / 304.1x212.4x14.9 mm', '/media/10.jpg', '2150.00', '3', '1');
INSERT INTO ishop.product (id, name, description, image_link, price, category_id,producer_id)
VALUES ('11', 'Galaxy S20 Plus', 'Display 6.2\", 3200x1440/ Samsung Exynos 990 / Camera: 12 Мп + 64 Мп + 12 Мп/ RAM 8 GB/ 128 GB / 3G/ LTE/ 3700 mA*h', '/media/11.jpg', '820.00', '1', '3');
INSERT INTO ishop.product (id, name, description, image_link, price, category_id,producer_id)
VALUES ('12', 'Galaxy S20 Ultra', 'Display 6.2\", 3200x1440/ Samsung Exynos 990 / Camera: 12 Мп + 64 Мп + 12 Мп/ RAM 8 GB/ 128 GB / 3G/ LTE/ 3700 mA*h', '/media/12.jpg', '980.00', '1', '3');

