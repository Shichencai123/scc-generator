package com.scc.maker.meta;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;

import com.scc.maker.meta.enums.FileGenerateTypeEnum;
import com.scc.maker.meta.enums.FileTypeEnum;
import com.scc.maker.meta.enums.ModelTypeEnum;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Date: 2025/1/24 15:15
 * @Author: shicc
 * @Description: 元信息校验
 */
public class MetaValidator {

    public static void doValidAndFill(Meta meta) {
        // 基础信息校验和默认值
        doValidateAndFillBaseConfig(meta);

        // fileConfig 校验和默认值
        doValidateAndFillFileConfig(meta);

        // modelConfig 校验和默认值
        doValidateAndFillModelConfig(meta);

    }

    private static void doValidateAndFillBaseConfig(Meta meta) {
        String name = StrUtil.isBlank(meta.getName()) ? "my-generator" : meta.getName();
        String description = StrUtil.isEmpty(meta.getDescription()) ? "我的模板代码生成器" : meta.getDescription();
        String author = StrUtil.isEmpty(meta.getAuthor()) ? "scc" : meta.getAuthor();
        String basePackage = StrUtil.isBlank(meta.getBasePackage()) ? "com.scc" : meta.getBasePackage();
        String version = StrUtil.isEmpty(meta.getVersion()) ? "1.0" : meta.getVersion();
        String createTime = StrUtil.isEmpty(meta.getCreateTime()) ? DateUtil.now() : meta.getCreateTime();
        meta.setName(name);
        meta.setDescription(description);
        meta.setAuthor(author);
        meta.setBasePackage(basePackage);
        meta.setVersion(version);
        meta.setCreateTime(createTime);
    }

    private static void doValidateAndFillModelConfig(Meta meta) {
        Meta.ModelConfig modelConfig = meta.getModelConfig();
        if (modelConfig == null) {
            return;
        }
        List<Meta.ModelConfig.ModelInfo> modelInfoList = modelConfig.getModels();
        if (CollectionUtil.isEmpty(modelInfoList)) {
            return;
        }
        for (Meta.ModelConfig.ModelInfo modelInfo : modelInfoList) {
            // 输出路径默认值
            String groupKey = modelInfo.getGroupKey();
            if (StrUtil.isNotEmpty(groupKey)) {
                String allArgsStr = modelInfo.getModels().stream().map(subModelInfo -> String.format("\"--%s\"", subModelInfo.getFieldName()))
                        .collect(Collectors.joining(", "));
                modelInfo.setAllArgsStr(allArgsStr);
                continue;
            }
            String fieldName = modelInfo.getFieldName();
            if (StrUtil.isBlank(fieldName)) {
                throw new MetaException("未填写 fieldName");
            }

            String modelInfoType = modelInfo.getType();
            if (StrUtil.isEmpty(modelInfoType)) {
                modelInfo.setType(ModelTypeEnum.STRING.getValue());
            }
        }
    }

    private static void doValidateAndFillFileConfig(Meta meta) {
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (fileConfig == null) {
            return;
        }
        // sourceRootPath：必填
        String sourceRootPath = fileConfig.getSourceRootPath();
        if (StrUtil.isBlank(sourceRootPath)) {
            throw new MetaException("未填写 sourceRootPath");
        }
        // inputRootPath：.source + sourceRootPath 的最后一个层级路径
        String inputRootPath = fileConfig.getInputRootPath();
        String defaultInputRootPath = ".source" + File.separator + FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
        if (StrUtil.isEmpty(inputRootPath)) {
            fileConfig.setInputRootPath(defaultInputRootPath);
        }
        // outputRootPath：默认为当前路径下的 generated
        String outputRootPath = fileConfig.getOutputRootPath();
        String defaultOutputRootPath = "generated";
        if (StrUtil.isEmpty(outputRootPath)) {
            fileConfig.setOutputRootPath(defaultOutputRootPath);
        }
        String fileConfigType = fileConfig.getType();
        String defaultType = FileTypeEnum.DIR.getValue();
        if (StrUtil.isEmpty(fileConfigType)) {
            fileConfig.setType(defaultType);
        }

        // fileInfo 默认值
        List<Meta.FileConfig.FileInfo> fileInfoList = fileConfig.getFiles();
        if (CollectionUtil.isEmpty(fileInfoList)) {
            return;
        }
        for (Meta.FileConfig.FileInfo fileInfo : fileInfoList) {
            if (FileTypeEnum.GROUP.getValue().equals(fileInfo.getType())) {
                continue;
            }
            // inputPath: 必填
            String inputPath = fileInfo.getInputPath();
            if (StrUtil.isBlank(inputPath)) {
                throw new MetaException("未填写 inputPath");
            }

            // outputPath: 默认等于 inputPath
            String outputPath = StrUtil.isEmpty(fileInfo.getOutputPath()) ? inputPath : fileInfo.getOutputPath();
            // type：默认 inputPath 有文件后缀（如 .java）为 file，否则为 dir
            String type = fileInfo.getType();
            if (StrUtil.isBlank(type)) {
                // 无文件后缀
                if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))) {
                    fileInfo.setType(FileTypeEnum.DIR.getValue());
                } else {
                    fileInfo.setType(FileTypeEnum.FILE.getValue());
                }
            }
            // generateType：如果文件结尾不为 Ftl，generateType 默认为 static，否则为 dynamic
            String generateType = fileInfo.getGenerateType();
            if (StrUtil.isBlank(generateType)) {
                // 为动态模板
                if (inputPath.endsWith(".ftl")) {
                    fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
                } else {
                    fileInfo.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
                }
            }
        }
    }

}

