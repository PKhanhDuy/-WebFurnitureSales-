package com.supportjobsearch.DAO.interf;

import com.supportjobsearch.Bean.ProductAttribute;

import java.util.List;

public interface IProductAttributeDao {
    //Lấy tất cả các thuộc tính
    List<ProductAttribute> getAll();

    //Lấy thuộc tính theo id
    ProductAttribute getById(int id);

    //thêm một thuộc tính
    ProductAttribute addAttribute(ProductAttribute attribute);

    //cap nhat thuoc tính material theo id
    boolean updateMaterial(int id, String newMaterial);

    //Cap nhat thuoc tinh size theo id
    boolean updateSize(int id, String newSize);

    //xóa một thuộc tính có id = ?
    boolean deleteAttributeById(int id);

}
