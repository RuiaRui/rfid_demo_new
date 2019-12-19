package com.rfid.demo;

import com.rfid.demo.entity.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {
    @Test
    public void test(){
        Product product=new Product();
        product.setInventory(1);
        assertEquals(1,product.getInventory());
    }

}
