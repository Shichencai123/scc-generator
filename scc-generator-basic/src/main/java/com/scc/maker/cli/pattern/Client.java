package com.scc.maker.cli.pattern;

/**
 * className:  Client
 * Package:  com.scc.cli.pattern
 * Description:
 *
 * @Date: 2024/11/4 22:57
 * @Author: shicc
 */
public class Client {
    public static void main(String[] args) {
        Device device = new Device("TV");
        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand(new TurnOnCommand(device));
        remoteControl.pressButton();
    }
}
