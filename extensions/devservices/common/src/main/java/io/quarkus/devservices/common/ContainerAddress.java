package io.quarkus.devservices.common;

public class ContainerAddress {
    private final String ipAddress;
    private final int port;

    public ContainerAddress(String ipAddress, Integer port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

    public String getUrl() {
        return String.format("%s:%d", ipAddress, port);
    }
}
