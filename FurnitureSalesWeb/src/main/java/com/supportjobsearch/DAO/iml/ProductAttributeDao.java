package com.supportjobsearch.DAO.iml;

import com.supportjobsearch.Bean.ProductAttribute;
import com.supportjobsearch.DAO.interf.IProductAttributeDao;
import com.supportjobsearch.database.JDBIConnect;

import java.util.ArrayList;
import java.util.List;

public class ProductAttributeDao extends ImplementBase implements IProductAttributeDao {
    //    JDBIConnect conn;
    public ProductAttributeDao() {
        super();
    }

    @Override
    public List<ProductAttribute> getAll() {
        db  = JDBIConnect.getInstance();
        return db.jdbi.withHandle(handle -> handle.createQuery("select * from product_atribute")
                .mapToBean(ProductAttribute.class).list());
    }

    @Override
    public ProductAttribute getById(int id) {
        return handle.createQuery("select * from product_atribute where id = :id")
                .bind("id", id)
                .mapToBean(ProductAttribute.class).findOne().orElse(null);
    }

    @Override
    public ProductAttribute addAttribute(ProductAttribute attribute) {
        log.info("Adding attribute: " + attribute);

        int id = handle.createUpdate("INSERT INTO product_atribute(material, size) VALUES (?, ?)")
                .bind(0, attribute.getMaterial())
                .bind(1, attribute.getSize())
                .executeAndReturnGeneratedKeys("id")
                .mapTo(Integer.class)
                .one();

        attribute.setId(id);
        return attribute;
    }

    @Override
    public boolean updateMaterial(int id, String newMaterial) {
        log.info("Updating material: " + newMaterial);
        boolean s = handle.createUpdate("Update product_atribute set material = :material where id = :id")
                .bind("id", id)
                .bind("material", newMaterial)
                .execute() > 0;

        return s;
    }

    @Override
    public boolean updateSize(int id, String newSize) {
        log.info("Updating size: " + newSize);
        boolean s = handle.createUpdate("Update product_atribute set size = :material where id = :id")
                .bind("id", id)
                .bind("material", newSize)
                .execute() > 0;

        return s;
    }

    @Override
    public boolean deleteAttributeById(int id) {
        return false;
    }
}

