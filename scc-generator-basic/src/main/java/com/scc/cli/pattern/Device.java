package com.scc.cli.pattern;

/**
 * className:  Device
 * Package:  com.scc.cli.pattern
 * Description:
 *
 * @Date: 2024/11/4 22:53
 * @Author: shicc
 */
public class Device {
    private String name;

    public Device(String name) {
        this.name = name;
    }

    public void turnOn() {
        System.out.println(name + "设备打开");
    }

    public void turnOff() {
        System.out.println(name + "设备关闭");
    }
}
