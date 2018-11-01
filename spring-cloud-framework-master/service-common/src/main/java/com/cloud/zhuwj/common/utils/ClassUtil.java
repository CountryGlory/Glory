package com.cloud.zhuwj.common.utils;


import com.cloud.zhuwj.common.constants.GlobalContanst;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.JarURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author zhuwj
 * @Description: 类操作util
 * @date 2018/3/27 13:46
 */
public class ClassUtil {
    private final static String POINT = ".";
    private final static String CLASS = ".class";

    /**
     * 获取某个包下所有的class信息
     *
     * @param packageName
     *            包名
     * @return
     */
    public static List<Class<?>> getClasses(String packageName)
            throws Exception {
        String packageFileName = packageName.replace(POINT, File.separator);
        URL fullPath = getDefaultClassLoader().getResource(packageFileName);
        String protocol = fullPath.getProtocol();
        if (protocol.equals(GlobalContanst.PROTOCOL_FILE)) {
            try {
                File dir = new File(fullPath.toURI());
                return findClassesByFile(dir, packageName);
            } catch (URISyntaxException e) {
                throw new Exception(e);
            }
        } else if (protocol.equals(GlobalContanst.PROTOCOL_JAR)) {
            try {
                return findClassesByJar(
                        ((JarURLConnection) fullPath.openConnection())
                                .getJarFile(),
                        packageName);
            } catch (IOException e) {
                throw new Exception(e);
            }
        }
        return null;
    }



    /**
     * 扫描目录下所有的class对象
     *
     * @param dir
     *            文件目录
     * @param packageName
     *            包的全限类名
     * @return
     */
    private static List<Class<?>> findClassesByFile(File dir, String packageName) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return file.isDirectory() || file.getName().endsWith(CLASS);
            }
        });
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    classes.addAll(findClassesByFile(file, packageName + POINT
                            + file.getName()));
                } else {
                    try {
                        classes.add(Class.forName(packageName + POINT
                                + file.getName().replace(CLASS, "")));
                    } catch (ClassNotFoundException e) {
                        ;
                    }
                }
            }
        }
        return classes;
    }

    /**
     * 扫描jar包下所有的class对象
     *
     * @param jar
     *            jar包对象
     * @param packageName
     *            包的全限类名
     * @return
     */
    private static List<Class<?>> findClassesByJar(JarFile jar,
                                                   String packageName) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        Enumeration<JarEntry> jarEntries = jar.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            if (jarEntry.isDirectory()) {
                continue;
            }
            String className = jarEntry.getName()
                    .replace(File.separator, POINT);
            if (!className.startsWith(packageName)
                    || !className.endsWith(CLASS)) {
                continue;
            }
            try {
                classes.add(Class.forName(className.replace(CLASS, "")));
            } catch (ClassNotFoundException e) {
                ;
            }
        }
        return classes;
    }

    public static Object deepClone(Object obj) throws Exception {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (IOException e) {
            throw new Exception(e);
        } catch (ClassNotFoundException e) {
            throw new Exception(e);
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (oos != null) {
                    oos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                ;// ignore
            }
        }
    }

    /**
     * 获得泛型类型
     *
     * @param clazz
     * @return
     */
    public static Class<?> getGenericType(Class<?> clazz) {
        if(clazz == Object.class){
            return null;
        }
        Type type = clazz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType ptype = ((ParameterizedType) type);
            Type[] args = ptype.getActualTypeArguments();
            return (Class<?>) args[0];
        }
        return getGenericType(clazz.getSuperclass());
    }

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtil.class.getClassLoader();
            if (cl == null) {
                // getClassLoader() returning null indicates the bootstrap
                // ClassLoader
                try {
                    cl = ClassLoader.getSystemClassLoader();
                } catch (Throwable ex) {
                    // Cannot access system ClassLoader - oh well, maybe the
                    // caller can live with null...
                }
            }
        }
        return cl;
    }


    public static void main(String[] args) throws Exception {
        //获取当前模块包下面的资源
        System.err.println(getClasses("com.gdf.carbon.core"));
    }
}
