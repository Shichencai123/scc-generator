package com.scc.maker.cli.pattern;

/**
 * className:  TurnOnCommand
 * Package:  com.scc.cli.pattern
 * Description:
 *
 * @Date: 2024/11/4 22:55
 * @Author: shicc
 */
public class TurnOnCommand implements Command {
    private Device device;

    public TurnOnCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOff();
    }
}
