package service;

import domain.Category;

import java.util.List;

/**
 * @Time : 2020/8/5 10:17
 * @Author : KKK
 * @File : CategoryService.java
 * @Software: IntelliJ IDEA
 **/
public interface CategoryService {
    public void addCategory(String categoryId,String categoryName);
    public void deleteCategory(String categoryId);
}
