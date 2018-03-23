package ru.sbt.jschool.session1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class ParamFinder {
    private String[] args;
    private Properties props;


    public ParamFinder(String[] args, String path) {
        this.args = args;
        this.props = new Properties();
        try (InputStream input = new FileInputStream(path)) {
            props.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String getParamValue(String keyName) {
        String value = null;

        for (String arg : args) {
            String[] vs = arg.split("=");
            if (vs.length < 2) {
                continue;
            }
            if (vs[0].equals(keyName)) {
                return vs[1];
            }
        }
        value = System.getProperty(keyName);
        if (value != null) {
            return value;
        }

        value = System.getenv().get(keyName);
        if (value != null) {
            return value;
        }

        if (!props.isEmpty()) {
            return props.getProperty(keyName);
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
