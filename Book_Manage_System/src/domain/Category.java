package domain;

/**
 * @Time : 2020/8/5 9:15
 * @Author : KKK
 * @File : Category.java
 * @Software: IntelliJ IDEA
 **/
public class Category {
    private String classificationID;
    private String classificationName;

    public Category() {
    }

    public Category(String classificationID, String classificationName) {
        this.classificationID = classificationID;
        this.classificationName = classificationName;
    }

    public String getClassificationID() {
        return classificationID;
    }

    public void setClassificationID(String classificationID) {
        this.classificationID = classificationID;
    }

    public String getClassificationName() {
        return classificationName;
    }

    public void setClassificationName(String classificationName) {
        this.classificationName = classificationName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "classificationID='" + classificationID + '\'' +
                ", classificationName='" + classificationName + '\'' +
                '}';
    }
}
