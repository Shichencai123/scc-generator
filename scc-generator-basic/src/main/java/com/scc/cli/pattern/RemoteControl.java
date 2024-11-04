package com.scc.cli.pattern;

/**
 * className:  RemoteControl
 * Package:  com.scc.cli.pattern
 * Description:
 *
 * @Date: 2024/11/4 22:55
 * @Author: shicc
 */
public class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}
