package com.scc.cli.pattern;

/**
 * className:  TurnOffCommand
 * Package:  com.scc.cli.pattern
 * Description:
 *
 * @Date: 2024/11/4 22:52
 * @Author: shicc
 */
public class TurnOffCommand implements Command {
    private Device device;

    public TurnOffCommand(Device device) {
        this.device = device;
    }

    @Override
    public void execute() {
        device.turnOff();
    }
}
