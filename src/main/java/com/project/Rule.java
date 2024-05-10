package com.project;

class Rule {
    private String name;
    private String port;
    private String protocol;
    private String app;
    private String user;
    private String group;
    private String ip;
    private String action;
    private String interfaze;
    private String direction;

    public Rule(){}

    public Rule(String name, String port, String protocol, String app, String user, String group, String ip, String action, String interfaze, String direction) {
        this.name = name;
        this.port = port;
        this.protocol = protocol;
        this.app = app;
        this.user = user;
        this.group = group;
        this.ip = ip;
        this.action = action;
        this.interfaze = interfaze;
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getInterfaze() {
        return interfaze;
    }

    public void setInterfaze(String interfaze) {
        this.interfaze = interfaze;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
