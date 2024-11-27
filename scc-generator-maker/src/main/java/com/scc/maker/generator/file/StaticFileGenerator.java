package com.scc.maker.generator.file;

import cn.hutool.core.io.FileUtil;

import java.io.File;

/**
 * className:  StaticFileGenerator
 * Package:  com.scc.generator
 * Description:
 *
 * @Date: 2024/11/1 22:49
 * @Author: shicc
 */
public class StaticFileGenerator {
    /**
     *
     * 拷贝文件，将输入目录完整拷贝到输出目录
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public static void copyFileByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }
}