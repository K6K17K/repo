package service.Impl;

import domain.Category;
import service.CategoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Time : 2020/8/5 17:17
 * @Author : KKK
 * @File : CategoryServiceImpl.java
 * @Software: IntelliJ IDEA
 **/
public class CategoryServiceImpl implements CategoryService {
    private static final List<Category> categoryDb =new ArrayList<>();

    public static List<Category> getCategoryDb() {
        return categoryDb;
    }

    @Override
    public void addCategory(String categoryId, String categoryName) {
        Category category = new Category(categoryId,categoryName);
        categoryDb.add(category);
    }

    @Override
    public void deleteCategory(String categoryId) {
        for (Category category : categoryDb) {
            if (category.getClassificationID().equals(categoryId)) {
                categoryDb.remove(category);
                break;
            }
        }
    }
}
