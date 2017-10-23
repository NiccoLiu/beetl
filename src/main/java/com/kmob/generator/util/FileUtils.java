package com.kmob.generator.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    /**
     * 读取文件，返回byte[] 如果不存在，返回null
     * 
     * @param path
     * @return
     * @throws IOException
     */
    public static byte[] read(String path) throws IOException {
        int base_size = 1024;
        File file = new File(path);
        // 不存在创建
        if (!file.exists()) {
            return null;
        }

        FileInputStream fis = new FileInputStream(file);
        int len = 0;
        byte[] dataByte = new byte[base_size];

        ByteArrayOutputStream out = new ByteArrayOutputStream(base_size);
        while ((len = fis.read(dataByte)) != -1) {
            out.write(dataByte, 0, len);
        }
        byte[] content = out.toByteArray();

        fis.close();
        out.close();

        // 没有读取到数据
        if (content.length == 0) {
            return null;
        }

        return content;
    }

    /**
     * 写文件，如果存在，删除
     * 
     * @param path
     * @param data
     * @throws IOException
     */
    public static void write(String path, byte[] data) throws IOException {
        File file = new File(path);
        // 不存在，创建
        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.flush();
        fos.close();
    }

    /**
     * 查找当前文件下所有properties文件
     * 
     * @param baseDirName
     *            查找的文件夹路径
     */
    public static List<String> findFiles(String baseDirName) {
        List<String> files = new ArrayList<String>();
        // 判断目录是否存在
        File baseDir = new File(baseDirName);
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            System.err.println("search error：" + baseDirName + "is not a dir！");
        } else {
            String[] filelist = baseDir.list();
            for (String fileName : filelist) {
                files.add(fileName);
            }
        }
        return files;
    }

    /**
     * 查找当前文件下所有properties文件
     * 
     * @param baseDirName
     *            查找的文件夹路径
     */
    public static List<String> findFileNames(String baseDirName, FileFilter fileFilter) {
        List<String> files = new ArrayList<String>();
        // 判断目录是否存在
        File baseDir = new File(baseDirName);
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            System.err.println("search error：" + baseDirName + "is not a dir！");
        } else {
            File[] filelist = baseDir.listFiles(fileFilter);
            for (File file : filelist) {
                if (file.isFile()) {
                    files.add(file.getName());
                }
            }
        }
        return files;
    }
    
    /**
     * 递归查询目录下所有文件
     * 
     * 
     * @param file
     * @param resultFileName
     * @return
     */
    public static List<String> recursionFileNames(File file,List<String> resultFileNames){
        File[] files = file.listFiles();
        if(files==null){
            return resultFileNames;
        }
        for (File f : files) {
            if(f.isDirectory()){
                recursionFileNames(f,resultFileNames);
            } else {
                resultFileNames.add(f.getPath());
            }
        }
        return resultFileNames;
    }
}