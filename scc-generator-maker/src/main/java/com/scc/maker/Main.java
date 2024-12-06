package com.scc.maker;

import com.scc.maker.generator.MainGenerator;
import com.scc.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * className:  Main
 * Package:  com.scc.cli
 * Description:
 *
 * @Date: 2024/11/4 23:19
 * @Author: shicc
 */
public class Main {
    public static void main(String[] args) throws TemplateException, IOException {
        MainGenerator.doGenerate(MetaManager.getMetaObject());
    }
}
