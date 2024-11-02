import com.scc.model.TemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * className:  FreeMakerTest
 * Package:  PACKAGE_NAME
 * Description:
 *
 * @Date: 2024/11/2 9:41
 * @Author: shicc
 */
public class FreeMakerTest2 {
    @Test
    public void test() throws IOException, TemplateException {
        // 1、创建配置
        // new 出Configuration 对象，参数为FreeMaker 版本号
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);

        //指定模板文件所在路径
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        //设置模板文件使用的字符集
        cfg.setDefaultEncoding("UTF-8");

        //去除数字逗号分隔符
        cfg.setNumberFormat("0.######");

        //设置异常处理器
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        // 2、加载模板
        //创建模板对象，加载指定模板
        Template template = cfg.getTemplate("MainTemplate.java.ftl");

        // 3、准备数据
        //创建数据模型
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setAuthor("scc");
        templateConfig.setLoop(true);
        templateConfig.setOutputText("Sum:");

        // 4、处理数据
        //指定输出路径
        Writer out = new FileWriter("src/main/java/com/scc/generator/MainTemplate.java");

        //开始模板输出
        template.process(templateConfig, out);

        //释放文件资源
        out.close();

    }
}
