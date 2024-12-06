package com.scc.maker.generator;

import cn.hutool.core.io.FileUtil;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * @Date: 2024/12/6 14:53
 * @Author: shicc
 * @Description: 脚本文件生成
 */
public class ScriptGenerator {

    public static void doGenerate(String outputPath, String jarPath) throws IOException {
        // 直接写入脚本文件
        // linux
        StringBuilder sb = new StringBuilder();
        sb.append("#!/bin/bash").append("\n");
        sb.append(String.format("java -jar %s \"$@\"", jarPath)).append("\n");
        FileUtil.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8), outputPath);
        // 添加可执行权限
        try {
            Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrwxrwx");
            Files.setPosixFilePermissions(Paths.get(outputPath), permissions);
        } catch (Exception e) {

        }

        // windows
        sb = new StringBuilder();
        sb.append("@echo off").append("\n");
        sb.append(String.format("java -jar %s %%*", jarPath)).append("\n");
        FileUtil.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8), outputPath + ".bat");
    }

    public static void main(String[] args) throws IOException {
        String baseDir = System.getProperty("user.dir");
        String outputPath = baseDir + File.separator + "generator";
        doGenerate(outputPath, baseDir + File.separator + "scc-generator-demo-projects/acm-template-pro" + File.separator + "target" + File.separator + "acm-template-pro-1.0-SNAPSHOT.jar");
    }
}
