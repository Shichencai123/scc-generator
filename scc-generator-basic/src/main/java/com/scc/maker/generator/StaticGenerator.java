package com.scc.maker.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;

/**
 * className:  StaticGenerator
 * Package:  com.scc.generator
 * Description:
 *
 * @Date: 2024/11/1 22:49
 * @Author: shicc
 */
public class StaticGenerator {
    public static void main(String[] args) {
        String propertyPath = System.getProperty("user.dir");
        System.out.println(propertyPath);
        String inputPath = propertyPath + File.separator + "scc-generator-demo-projects" + File.separator + "acm-template";
        String outputPath = propertyPath;
        //copyFileByHutool(inputPath, outputPath);
        copyFilesByRecursive(new File(inputPath), new File(outputPath));
    }

    /**
     *
     * 拷贝文件，将输入目录完整拷贝到输出目录
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public static void copyFileByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }

    /**
     * 递归拷贝目录
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */

    public static void copyFilesByRecursive(File src, File desc) {
        //@TODO
        /*final String[] files = src.list();
        if(ArrayUtil.isNotEmpty(files)){
            for (String file : files) {
                File srcFile = new File(src, file);
                File destFile = new File(desc, file);
                // 递归复制
                if (srcFile.isDirectory()) {
                    copyFilesByRecursive(srcFile, destFile);
                } else {
                    FileUtil.copy(srcFile, destFile, false);
                }
            }
        }*/
    }
}
