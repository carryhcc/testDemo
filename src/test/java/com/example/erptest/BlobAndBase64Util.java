package com.example.erptest;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;


/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/10/28 10:50
 */
public class BlobAndBase64Util {
    public static String getBase64InBlob(Blob realBlob) throws Exception {
        String result = "";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 创建一个长度为100的byte数组
        byte[] buff = new byte[100];
        int rc = 0;
        // 获取BLOB数据对象的二进制流
        InputStream binaryStream = realBlob.getBinaryStream();
        while ((rc = binaryStream.read(buff, 0, 100)) > 0) {
            byteArrayOutputStream.write(buff, 0, rc);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        result = Base64.encodeBase64String(byteArray);
        return result;
    }
}