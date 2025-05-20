package com.supportjobsearch.DAO.iml;

import com.supportjobsearch.Category;
import org.jdbi.v3.core.Jdbi;

import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends ImplementBase implements ICategoryDao {
    List<Category> categories = new ArrayList<>();
    JDBIConnect conn = JDBIConnect.getInstance();

    public CategoryDao() {
        super();
    }

    public Jdbi getJdbi(){
        return db.jdbi;
    }

    @Override
    public List<Category> getAllCategory() {
        return conn.jdbi.withHandle(handle ->
                handle.createQuery("select * from category")
                    .mapToBean(Category.class).list());
    }

    @Override
    public Category getCategoryById(int id) {
        return conn.jdbi.withHandle(handle ->
                handle.createQuery("select * from category where id = :id")
                    .bind("id", id)
                    .mapToBean(Category.class).findOne().orElse(null));
    }

    @Override
    public boolean addCategory(int id, String cateName) {
        return conn.jdbi.withHandle(handle ->
                handle.createUpdate("insert into category(cateName) values (:cateName)")
                    .bind("cateName", cateName)
                    .execute() > 0);
    }

    @Override
    public boolean updateName(int id, String newName) {
        return conn.jdbi.withHandle(handle ->
                handle.createUpdate("update category set cateName = :newName where id = :id")
                    .bind("newName", newName)
                    .bind("id", id)
                    .execute() > 0);
    }

    @Override
    public boolean deleteCategory(int id) {
        return conn.jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM category WHERE id = :id")
                        .bind("id", id)
                        .execute() > 0
        );
    }

    @Override
    public Category getCategoryByName(String name) {
        log.info("Get Category by name: " + name);
        return handle.createQuery("SELECT * FROM category WHERE cateName = ?")
                .bind(0, name)
                .mapToBean(Category.class)
                .findOne().orElse(null);
    }


    public static void main(String[] args) {
        CategoryDao dao = new CategoryDao();
        var a = dao.getCategoryById(1);
        System.out.println(a);
    }
}
