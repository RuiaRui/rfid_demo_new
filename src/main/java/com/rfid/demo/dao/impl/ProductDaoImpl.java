package com.rfid.demo.dao.impl;

import com.rfid.demo.dao.ProductDao;
import com.rfid.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int add(Product product) {
        return jdbcTemplate.update("INSERT INTO products (orderNum,name,inventory, " +
                        "expiration,productionDate, specification, QRUrl) " +
                        "VALUES (?,?,?,?,?,?,?); ",
        product.getOrderNum(),product.getName(),
                product.getInventory(),product.getExpiration(),
                product.getProductionDate(),product.getSpecification(),product.getQrUrl());



    }

    @Override
    public int update(Product product) {
        return jdbcTemplate.update("UPDATE products " +
                        "SET name=? ,inventory=? ,expiration=?," +
                        " productionDate=?, specification=?, QRUrl=? WHERE orderNum=?",
                product.getName(), product.getInventory(),product.getExpiration(),
                product.getProductionDate(),product.getSpecification(),
                product.getQrUrl(),
                product.getOrderNum());
    }

    @Override
    public int delete(String orderNum) {
        return jdbcTemplate.update("DELETE from products where orderNum=?",orderNum);

    }

    @Override
    public Product findProductByOrder(String orderNum) {
        // BeanPropertyRowMapper 使获取的 List 结果列表的数据库字段和实体类自动对应
        List<Product> list = jdbcTemplate.query("select * from products where orderNum = ?", new BeanPropertyRowMapper(Product.class),orderNum);
        if(list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<Product> findAllProduct() {
        // 使用Spring的JdbcTemplate查询数据库，获取List结果列表，数据库表字段和实体类自动对应，可以使用BeanPropertyRowMapper
        List<Product> list = jdbcTemplate.query("select * from products;",  new BeanPropertyRowMapper(Product.class));
        if(list.size() > 0){
            return list;
        }else{
            return null;
        }
    }

}
