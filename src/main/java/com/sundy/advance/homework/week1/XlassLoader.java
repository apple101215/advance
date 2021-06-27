package com.sundy.advance.homework.week1;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: sundy
 * @Date: 2021/6/26
 */
public class XlassLoader extends ClassLoader{

    public static void main(String[] args) {
        try {
            String className = "Hello";
            String methodName = "hello";
            XlassLoader classLoader = new XlassLoader();
            //用findClass也可以，Class<?> clazz = classLoader.findClass(className);
            Class<?> clazz = classLoader.loadClass(className);
            //这样也行：Object instance = clazz.newInstance();
            Object instance = clazz.getDeclaredConstructor().newInstance();
            Method method = clazz.getMethod(methodName);
            method.invoke(instance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //如果支持包名，则需要进行路径转换
        final String suffix = ".xlass";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(name + suffix);
        try {
            int length = inputStream.available();
            byte[] bytes = new byte[length];
            inputStream.read(bytes);
            byte[] decodeBytes = decode(bytes);
            return defineClass(name, decodeBytes, 0, decodeBytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ClassNotFoundException(name, e);
        }finally {
            close(inputStream);
        }
    }

    private byte[] decode(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte)(255 - bytes[i]);
        }
        return bytes;
    }

    // 关闭
    private static void close(Closeable res) {
        if (null != res) {
            try {
                res.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
