package com.supportjobsearch.DAO.interf;

import com.supportjobsearch.dto.CategoryDto;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(CategoryDto.class)
public interface ICategoryDTO {

    @SqlQuery("""
            SELECT
                c.id AS categoryid,
                c.cateName AS category_name,
                p.price AS starting_price,
                IFNULL(SUM(hp.amount), 0) AS product_stock,  -- This assumes the stock is stored in `having_product`
                p.thumb
            FROM
                category c
            JOIN
                products p ON c.id = p.cateID
            LEFT JOIN
                having_product hp ON p.id = hp.productID
            GROUP BY
                c.id;

            """)
    List<CategoryDto> getCategories();
}
