package com.rfid.demo.service.impl;

import com.rfid.demo.dao.SonProductDao;
import com.rfid.demo.entity.Product;
import com.rfid.demo.entity.SonProduct;
import com.rfid.demo.service.SonProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SonProductServiceImpl implements SonProductService {
    @Autowired
    private SonProductDao sonProductDao;

    @Override
    public int addSonProducts(Product product) {
        return sonProductDao.addSonProducts(product);
    }

    @Override
    public List<SonProduct> findSonProductByEPC() {
        List<Map<String, Object>> mapList=sonProductDao.findSonProductByEPC();

        List<SonProduct> products=new ArrayList<>();
        for (Map<String,Object> map:mapList) {
            SonProduct sonProduct=new SonProduct();
            sonProduct.setEpc(map.get("epc").toString());
            if(map.get("id")!=null){

                Product product=new Product();
                product.setOrderNum((String)map.get("orderNum"));
                product.setName((String)map.get("name"));
                product.setInventory((Integer)map.get("inventory"));
                product.setQRUrl((String)map.get("QRUrl"));
                product.setExpiration((Integer)map.get("expiration"));
                product.setSpecification((String)map.get("specification"));
                product.setProductionDate((Date)map.get("productionDate"));

                sonProduct.setParent(product);
                sonProduct.setId((String) map.get("id"));
            }
            products.add(sonProduct);

        }
        return products;
    }

    @Override
    public List<String> getAllSonProductId() {
        return sonProductDao.getAllSonProductId();
    }

    @Override
    public int addEPC(String EPC) {
        return sonProductDao.addEPC(EPC);
    }

    @Override
    public int alterEPC(String oldEPC, String newEPC) {

        return sonProductDao.alterEPC(oldEPC,newEPC);
    }

    @Override
    public int writeEPC(String EPC, String orderNum){
        return sonProductDao.writeEPC(EPC,orderNum);
    }
}
