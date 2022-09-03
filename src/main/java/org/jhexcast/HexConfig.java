package org.jhexcast;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HexConfig {
//    """
//    Configuration class for the Hexinator
//    Persists various bits to a file t keep track of the state
//    """
    public static String CYCLE_SECTION = "CYCLE";
    public static String CONFIG_FILE = "hfa.properties";

    public static Map<String, ?> DEFAULT_VALS = new HashMap<>(
            Map.of( "size", "4",
                    "dimension", "2",
                    "leap", "7",
                    "index", "3",
                    "startIndex", "3",
                    "nth", "0",
                    "validate", "false")
    );

    private static HexConfig instance=null;

    private Properties properties=null;

    public static HexConfig getInstance(Properties initial) {
        if (instance == null) {
            instance = new HexConfig(initial);
        }
        return instance;
    }

    private HexConfig(Properties initial) {
        this.initialize(initial);
    }

    private void initialize(Properties initial) {
        if(initial != null) {
            this.properties = (Properties) initial.clone();
        } else {
            this.properties = new Properties();
        }
        this.properties.putAll(DEFAULT_VALS);
        this.saveProperties();
    }

    private void saveProperties() {
        try {
            final OutputStream outputstream = new FileOutputStream(CONFIG_FILE);
            this.properties.store(outputstream, "jhexcast properties");
            outputstream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadProperties() {
        try {
            this.properties = new Properties();
            final InputStream inputstream = new FileInputStream(CONFIG_FILE);
            this.properties.load(inputstream);
            inputstream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setLeap(int leap) {
        this.properties.setProperty("leap", Integer.toString(leap));
    }

    public int getLeap() {
        return Integer.parseInt(this.properties.getProperty("leap"));
    }

    public void setSize(int size) {
        this.properties.setProperty("size", Integer.toString(size));
    }

    public int getSize() {
        return Integer.parseInt(this.properties.getProperty("size"));
    }
    
    public void setDimension(int dimension) {
        this.properties.setProperty("dimension", Integer.toString(dimension));
    }

    public int getDimension() {
        return Integer.parseInt(this.properties.getProperty("dimension"));
    }

    public void setIndex(int index) {
        this.properties.setProperty("index", Integer.toString(index));
    }

    public int getIndex() {
        return Integer.parseInt(this.properties.getProperty("index"));
    }

    public boolean getValidate() {
        return Hexutils.boolFromString(this.properties.getProperty("validate"));
    }

    public int getStartIndex() {
        return Integer.parseInt(this.properties.getProperty("startIndex"));
    }

    public void setStartIndex(int startIndex) {
        this.properties.setProperty("startIndex", Integer.toString(startIndex));
    }
}
