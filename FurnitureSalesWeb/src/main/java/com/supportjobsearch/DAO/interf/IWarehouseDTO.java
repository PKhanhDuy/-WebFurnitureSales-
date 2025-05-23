package com.supportjobsearch.DAO.interf;

import com.supportjobsearch.dto.WarehouseDto;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(WarehouseDto.class)
public interface IWarehouseDTO {
    @SqlQuery("""
            SELECT
                w.id AS id,
                w.wareHouseName AS name,
                a.FullAddress AS address,
                u.fullName AS manager,
                u.phoneNum AS phone,
                w.stock AS stock,
                COALESCE(SUM(oi.amount * p.price), 0) AS revenue
            FROM
                warehouse w
            LEFT JOIN
                users u ON w.managerID = u.id
            LEFT JOIN
                address a ON w.addressID = a.id -- Assuming there's an address table for location
            LEFT JOIN
                having_product hp ON w.id = hp.warehouseID
            LEFT JOIN
                order_item oi ON hp.productID = oi.productID
            LEFT JOIN
                orders o ON oi.orderID = o.id AND o.shippingStatus = 'SHIPPED'
            LEFT JOIN
                products p ON oi.productID = p.id
            GROUP BY
                w.id, w.wareHouseName, a.FullAddress, u.fullName, u.phoneNum, w.stock;
            """)
    public List<WarehouseDto> getWarehouse();
}
