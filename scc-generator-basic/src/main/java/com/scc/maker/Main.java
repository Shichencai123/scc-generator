package com.scc.maker;

import com.scc.maker.cli.CommandExecutor;

/**
 * className:  Main
 * Package:  com.scc.cli
 * Description:
 *
 * @Date: 2024/11/4 23:19
 * @Author: shicc
 */
public class Main {
    public static void main(String[] args) {
//        args = new String[]{"generate", "-l", "-a", "-o"};
//        args = new String[]{"config"};
        //args = new String[]{"list"};
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}
