/**
 * @description: 自定义类加载器
 * @author: fancying
 * @create: 2018-12-02 22:55
 **/
package chapter4;

import java.io.FileNotFoundException;

public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException{
        try {
            byte[] result = getClassFromCustomPath(name);
            if (result == null){
                throw new FileNotFoundException();
            } else {
                return defineClass(name, result, 0, result.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new ClassNotFoundException(name);
    }

    private byte[] getClassFromCustomPath(String name) {
        return name.getBytes();
    }

    public static void main(String[] args) throws Exception{
        CustomClassLoader customClassLoader = new CustomClassLoader();
        try {
            Class<?> clazz = Class.forName("One", true ,customClassLoader);
            Object obj = clazz.newInstance();
            System.out.println(obj.getClass().getClassLoader());
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}