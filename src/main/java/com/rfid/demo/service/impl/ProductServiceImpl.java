package com.rfid.demo.service.impl;

import com.rfid.demo.dao.ProductDao;
import com.rfid.demo.dao.SonProductDao;
import com.rfid.demo.entity.Product;
import com.rfid.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private SonProductDao sonProductDao;

    @Override
    public int[] add(Product product) {
        int[] i=new int[2];
        i[0]=productDao.add(product);
        i[1]=sonProductDao.addSonProducts(product);

        return i;
    }

    @Override
    public int update(Product product) {
        return productDao.update(product);
    }

    @Override
    public int delete(String orderNum) {
        return productDao.delete(orderNum);
    }

    @Override
    public Product findProductByOrder(String orderNum) {
        return productDao.findProductByOrder(orderNum);
    }

    @Override
    public List<Product> findAllProduct() {

        return productDao.findAllProduct();
    }
}
