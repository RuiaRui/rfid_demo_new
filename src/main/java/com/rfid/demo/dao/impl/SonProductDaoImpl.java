package com.rfid.demo.dao.impl;

import com.rfid.demo.dao.SonProductDao;
import com.rfid.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SonProductDaoImpl implements SonProductDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addSonProducts(Product product) {
        StringBuilder sql= new StringBuilder("INSERT INTO son_product(id,orderNum) Values");
        for(int i=1;i<=product.getInventory();i++){
            if(i!=1){
                sql.append(",");
            }
            sql.append("('"+product.getOrderNum()+"_"+i+"','"+product.getOrderNum()+"')");
        }

        return jdbcTemplate.update(sql.toString());
    }

    @Override
    public List<Map<String, Object>> findSonProductByEPC() {

        return jdbcTemplate.queryForList("select * from (epc left join son_product using(id)) " +
                "left join products using (orderNum)");

    }

    @Override
    public List<String> getAllSonProductId() {
        return jdbcTemplate.queryForList("select id from son_product",String.class);
    }

    @Override
    public List<Map<String, Object>> getAllEpc() {
        return jdbcTemplate.queryForList("select * from epc");
    }

    @Override
    public int addEPC(String EPC) {
        return jdbcTemplate.update("insert into epc (epc,id) VALUES (?,?)",EPC, null);

    }

    @Override
    public int alterEPC(String oldEPC, String newEPC) {
        return jdbcTemplate.update("UPDATE epc " +
                        "SET epc=? WHERE epc=?",
                oldEPC,newEPC);
    }

    @Override
    public int writeEPC(String EPC, String id) {
        return jdbcTemplate.update("UPDATE epc " +
                        "SET id=? WHERE epc=?",
                id,EPC);
    }

}
