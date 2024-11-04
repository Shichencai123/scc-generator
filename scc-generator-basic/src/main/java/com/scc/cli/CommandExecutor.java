package com.scc.cli;

import com.scc.cli.command.ConfigCommand;
import com.scc.cli.command.GenerateCommand;
import com.scc.cli.command.ListCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * className:  CommandExecutor
 * Package:  com.scc.cli
 * Description:
 *
 * @Date: 2024/11/4 23:06
 * @Author: shicc
 */
@Command(name = "executor", mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable {

    private CommandLine commandLine;
    {
        commandLine = new CommandLine(this)
                .addSubcommand(new GenerateCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand());
    }

    @Override
    public void run() {
        System.out.println("请输入具体的指令，或输入--help查询帮助文档~");
    }

    public void doExecute(String[] args) {
        commandLine.execute(args);
    }
}
