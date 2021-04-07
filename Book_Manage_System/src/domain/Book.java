package domain;

import java.util.List;

/**
 * @Time : 2020/8/5 10:11
 * @Author : KKK
 * @File : Book.java
 * @Software: IntelliJ IDEA
 **/
public class Book{
    private String bookID;
    private String bookName;
    private String categoryName;
    private Float price;
    private String cover;
    private String remarks;

    public Book() {
    }

    public Book(String bookID, String bookName, String categoryName,
                Float price, String cover, String remarks) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.categoryName = categoryName;
        this.price = price;
        this.cover = cover;
        this.remarks = remarks;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID='" + bookID + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookClassification='" + categoryName + '\'' +
                ", price=" + price +
                ", cover='" + cover + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
