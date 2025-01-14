package ${basePackage}.generator;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * className:  DynamicFileGenerator
 * Package:  com.scc.generator
 * Description:
 *
 * @Date: 2024/11/2 11:04
 * @Author: shicc
 */
public class DynamicGenerator {
    /**
     *
     * @param inputPath 模板文件输入路径
     * @param outputPath 输出路径
     * @param model 数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        // 1、创建配置
        // new 出Configuration 对象，参数为FreeMaker 版本号
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);

        //指定模板文件所在路径
        File templateDir = new File(inputPath).getParentFile();
        cfg.setDirectoryForTemplateLoading(templateDir);

        //设置模板文件使用的字符集
        cfg.setDefaultEncoding("UTF-8");

        //去除数字逗号分隔符
        cfg.setNumberFormat("0.######");

        //设置异常处理器
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 2、加载模板
        //创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = cfg.getTemplate(templateName);

        // 3、准备数据model

        // 4、处理数据
        //如果文件不存在，创建目录
        if (!FileUtil.exist(outputPath)) {
            FileUtil.touch(outputPath);
        }
        //指定输出路径
        Writer out = new FileWriter(outputPath);

        //开始模板输出
        template.process(model, out);

        //释放文件资源
        out.close();
    }
}
