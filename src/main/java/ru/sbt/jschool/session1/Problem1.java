package ru.sbt.jschool.session1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 */
public class Problem1 {
/*public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }*/

    /*public static void main(String[] args) {
        Properties props = System.getProperties();
        for (Object prop : props.keySet()) {
            System.out.format("%s=%s%n",
                    prop,
                    props.get(prop));
        }
    }*/


    /*public static void main(String[] args) {
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                    envName,
                    env.get(envName));
        }
    }*/

    public static void main(String[] args) {
        final String helloStr = "Hello World!";
        final String keyName = "JSCHOOl1_COUNT";
        final String keyFile = "JSCHOOL1_PROPERTIES_FILE";
        String paramValue = null;

        for (String arg : args) {
            String[] vs = arg.split("=");
            if (vs.length < 2) {
                continue;
            }
            if (vs[0].equals(keyName)) {
                paramValue = vs[1];
                break;
            }
        }

        if (paramValue == null) {
            paramValue = System.getProperty(keyName);
          }

        if (paramValue == null) {
            paramValue = System.getenv().get(keyName);
        }

        if (paramValue == null) {
            try (BufferedReader br = new BufferedReader(new FileReader(System.getenv().get(keyFile)))) {
                String line = null;
                while (true) {
                    line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    String[] vs = line.split("=");
                    if (vs.length < 2) {
                        continue;
                    }
                    if (vs[0].equals(keyName)) {
                        paramValue = vs[1];
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (paramValue == null) {
            System.out.println("No matches");
            return;
        }

        try {
            int n = Integer.parseInt(paramValue);
            for (int i = 0; i < n; i++) {
                System.out.println(helloStr);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

