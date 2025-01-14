package ${basePackage}.generator;

import com.scc.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * className:  FileGenerator
 * Package:  com.scc.generator
 * Description:
 *
 * @Date: 2024/11/2 20:53
 * @Author: shicc
 */
public class FileGenerator {

    public void doGenerator(Object model) throws TemplateException, IOException {
        // 静态资源生成
        String projectPath = System.getProperty("user.dir");
        String inputPath = projectPath + File.separator + "scc-generator-demo-projects/acm-template";
        StaticFileGenerator.copyFileByHutool(inputPath, projectPath);

        String srcPath = projectPath + File.separator + "scc-generator-maker" + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String targetPath = projectPath + File.separator + "acm-template/src/com/scc/acm/MainTemplate.java";
        DataModel DataModel = new DataModel();
        DataModel.setLoop(false);
        DataModel.setAuthor("hello world!");
        DataModel.setOutputText("sum =");
        DynamicFileGenerator.doGenerate(srcPath, targetPath, DataModel);
    }
}
