package com.scc.maker.generator;

import com.scc.maker.generator.main.GeneratorTemplate;

/**
 * className:  MainGenerator
 * Package:  com.scc.maker.generator
 * Description: 核心生成器
 *
 * @Date: 2024/11/27 23:02
 * @Author: shicc
 */
public class MainGenerator extends GeneratorTemplate {
    @Override
    protected void generateMiniSource(String outputRootPath, String sourceCopyDestPath, String shellOutputFilePath, String jarPath) {
        System.out.println("不要给我输出精简版的文件");
    }
}
