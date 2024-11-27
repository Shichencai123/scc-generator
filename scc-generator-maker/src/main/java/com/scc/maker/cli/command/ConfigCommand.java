package com.scc.maker.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.scc.maker.model.DataModel;
import picocli.CommandLine;

import java.lang.reflect.Field;

/**
 * className:  ConfigCommand
 * Package:  com.scc.cli.command
 * Description:
 *
 * @Date: 2024/11/4 23:06
 * @Author: shicc
 */
@CommandLine.Command(name = "config", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {

    @Override
    public void run() {
        Field[] fields = ReflectUtil.getFields(DataModel.class);
        for (Field field : fields) {
            String name = field.getName();
            Class<?> type = field.getType();
            System.out.println("name=" + name + ",type=" + type);
        }
    }
}
