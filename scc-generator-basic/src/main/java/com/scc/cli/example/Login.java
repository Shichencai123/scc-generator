package com.scc.cli.example;

import picocli.CommandLine;

import java.util.concurrent.Callable;

/**
 * className:  Login
 * Package:  com.scc.cli.example
 * Description:
 *
 * @Date: 2024/11/4 22:11
 * @Author: shicc
 */
public class Login implements Callable<Integer> {

    @CommandLine.Option(names = {"-u", "--user"}, description = "User name")
    String user;

    /**
     * arity 交互和非交互式输入可选
     */
    @CommandLine.Option(names = {"-p", "--password"}, arity = "0..1", description = "Passphrase", interactive = true, prompt = "请输入密码：")
    String password;

    @CommandLine.Option(names = {"-cp", "--checkPassword"}, arity = "0..1", description = "check password", interactive = true, prompt = "请确认密码：")
    String checkPassword;

    @Override
    public Integer call() throws Exception {
        System.out.println("password = " + password);
        System.out.println("checkPassword = " + checkPassword);
        return 0;
    }

    public static void main(String[] args) {
        //TODO 反射微调用户输入参数（必填）
        new CommandLine(new Login()).execute("-u", "root", "-p", "-cp");
    }
}
