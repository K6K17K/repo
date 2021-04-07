package service.Impl;

import domain.User;
import service.UserService;

import java.util.List;

/**
 * @Time : 2020/8/5 12:54
 * @Author : KKK
 * @File : UserServiceImpl.java
 * @Software: IntelliJ IDEA
 **/
public class UserServiceImpl implements UserService {
    @Override
    public void regist(List<User> userList, User user) {
        userList.add(user);
    }

    @Override
    public User login(List<User> userList, User user) {
        for (User existUser : userList) {
        if (existUser.getUsername().equals(user.getUsername())&&existUser.getPassword().equals(user.getPassword())){
            return existUser;
        }
        }
        return null;
    }
}
