package utils;

import java.util.UUID;

/**
 * @Time : 2020/8/6 14:18
 * @Author : KKK
 * @File : UploadUtils.java
 * @Software: IntelliJ IDEA
 **/
public class UploadUtils {
    public static String getUuidFileName(String fileName){
        //解决文件重名问题
        //获得后缀名
        int idx=fileName.lastIndexOf(".");
        String exName=fileName.substring(idx);
        //生成随机字符串
        String uuidFileName= UUID.randomUUID().toString().replace("-","")+exName;
        return uuidFileName;
    }
}
