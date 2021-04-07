package service;

import domain.User;

import java.util.List;

/**
 * @Time : 2020/8/5 10:16
 * @Author : KKK
 * @File : UserService.java
 * @Software: IntelliJ IDEA
 **/
public interface UserService {
    public void regist(List<User> userList,User user);

    User login(List<User> userList, User user);
}
