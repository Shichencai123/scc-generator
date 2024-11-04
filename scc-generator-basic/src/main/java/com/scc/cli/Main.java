package com.scc.cli;

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
        args = new String[]{"config"};
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}
