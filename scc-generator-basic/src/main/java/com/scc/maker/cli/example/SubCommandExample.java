package com.scc.maker.cli.example;

import picocli.CommandLine;

/**
 * className:  SubCommandExample
 * Package:  com.scc.cli.example
 * Description:
 *
 * @Date: 2024/11/4 22:39
 * @Author: shicc
 */
@CommandLine.Command(name = "main", mixinStandardHelpOptions = true)
public class SubCommandExample implements Runnable{

    @Override
    public void run() {
        System.out.println("执行主命令");
    }

    @CommandLine.Command(name = "add", mixinStandardHelpOptions = true)
    public static class AddCommand implements Runnable {

        @Override
        public void run() {
            System.out.println("执行add命令");
        }
    }

    @CommandLine.Command(name = "delete", mixinStandardHelpOptions = true)
    public static class DeleteCommand implements Runnable {

        @Override
        public void run() {
            System.out.println("执行delete命令");
        }
    }

    @CommandLine.Command(name = "query", mixinStandardHelpOptions = true)
    public static class QueryCommand implements Runnable {

        @Override
        public void run() {
            System.out.println("执行query命令");
        }
    }

    public static void main(String[] args) {
        //执行主命令
        String[] arg = new String[]{};
        //执行add命令
        //String[] arg = new String[]{"add"};
        //执行delete命令
        //String[] arg = new String[]{"delete"};
        //执行query命令
        //String[] arg = new String[]{"query"};
        //执行update命令, 无该命令
        //String[] arg = new String[]{"update"};
        int exitCode = new CommandLine(new SubCommandExample())
                .addSubcommand(new AddCommand())
                .addSubcommand(new DeleteCommand())
                .addSubcommand(new QueryCommand())
                .execute(arg);
        System.exit(exitCode);
    }
}
