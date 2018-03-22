package ru.sbt.jschool.session1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class ParamFinder {
    final private String[] args;
    final private String propFilePath;

    public ParamFinder(String[] args, String path) {
        this.args = args;
        this.propFilePath = path;
    }

    private String getParamValue(String keyName) {
        for (String arg : args) {
            String[] vs = arg.split("=");
            if (vs.length < 2) {
                continue;
            }
            if (vs[0].equals(keyName)) {
                return vs[1];
            }
        }

        Properties sysProps = System.getProperties();
        for (Object prop : sysProps.keySet()) {
            if (prop.equals(keyName)) {
                return sysProps.get(keyName).toString();
            }
        }

        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            if (envName.equals(keyName)) {
                return env.get(keyName);
            }
        }

        Properties props = new Properties();
        try { InputStream input = new FileInputStream(propFilePath);
            props.load(input);
            return props.getProperty(keyName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public String stringValue(String name) {
        return getParamValue(name);

    }

    public Integer integerValue(String name) {
        Integer value = null;
        try {
            value = Integer.parseInt(getParamValue(name));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return value;
    }

    public Double doubleValue(String name) {
        Double value = null;
        try {
            value = Double.parseDouble(getParamValue(name));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return value;
    }
}
