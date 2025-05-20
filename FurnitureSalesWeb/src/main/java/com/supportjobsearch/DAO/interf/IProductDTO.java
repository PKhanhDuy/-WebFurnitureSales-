package com.supportjobsearch.DAO.interf;

import com.supportjobsearch.dto.ProductDto;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(ProductDto.class)
public interface IProductDTO {

    @SqlQuery("""
                SELECT
                            p.id AS id,
                            p.proName AS name,
                            p.price AS price,
                            p.thumb AS thumb,
                            SUM(w.amount) AS stock,
                            c.cateName AS category,
                            COALESCE(AVG(r.stars), 0) AS averageRating,
                            COUNT(r.stars) AS totalReviews
                        FROM
                            products p
                        LEFT JOIN
                            having_product w ON p.id = w.productID
                        LEFT JOIN
                            category c ON p.cateID = c.id
                        LEFT JOIN
                            ratings r ON p.id = r.productID
                        GROUP BY
                            p.id
            """)
    List<ProductDto> getAllProducts();
}
