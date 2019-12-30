# rfid_demo_new

rfid inventory management . Spring boot web+jdbc

### database default

#### databse demo

##### table products

```sql
CREATE TABLE products (
  orderNum varchar(50) NOT NULL DEFAULT '',
  name varchar(20) DEFAULT NULL,
  inventory int(11) DEFAULT NULL,
  expiration int(11) DEFAULT NULL,
  productionDate datetime DEFAULT NULL,
  specification varchar(100) DEFAULT NULL,
  QRUrl varchar(100) DEFAULT NULL,
  PRIMARY KEY (orderNum)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

##### table son_product

```sql
CREATE TABLE `son_product` (
  `id` varchar(60) NOT NULL DEFAULT '',
  `orderNum` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `orderNum` (`orderNum`),
  CONSTRAINT `son_product_ibfk_1` FOREIGN KEY (`orderNum`) REFERENCES `products` (`orderNum`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```



##### table epc

```sql
CREATE TABLE `epc` (
  `epc` varchar(100) NOT NULL DEFAULT '',
  `id` varchar(60) DEFAULT '',
  PRIMARY KEY (`epc`),
  KEY `fk_1` (`id`),
  CONSTRAINT `fk_1` FOREIGN KEY (`id`) REFERENCES `son_product` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

