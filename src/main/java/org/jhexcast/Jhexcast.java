package org.jhexcast;

import org.jhexcast.utils.Hexutils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class Jhexcast {

    public static final Map<Integer, Integer> PRIMES = new HashMap<>(
            Map.of(2, 7,
                    3, 127,
                    4, 2039,
                    5, 28031,
                    6, 280037,
                    7, 6000047,
                    8, 94217771,
                    10, 1647483757)
    );

    protected static final String INIT_ACTION = "init";
    protected static final String NO_INIT_ACTION = "noinit";
    protected static final String SEQUENCE_ACTION = "sequence";
    protected static final String NEXT_ACTION = "next";
    protected static final String UNKNOWN_ACTION = "unknown";
    protected static final String MAXTRIES_ACTION = "maxtries";

    protected String getAction(String[] args) {
        if(args.length > 0) {
            if(args[0].toLowerCase().equals(INIT_ACTION)) {
                return INIT_ACTION;
            }
            else if(args[0].toLowerCase().equals(SEQUENCE_ACTION)) {
                return SEQUENCE_ACTION;
            } else {
                return NEXT_ACTION;
            }
        }
        return NEXT_ACTION;
    }

    public void process(String[] arguments) {
        String action = this.getAction(arguments);
        Integer vecsize = null;
        Integer vecdim = null;

        if(arguments.length > 1) {
            vecsize = Integer.parseInt(arguments[1]);
        }
        if(arguments.length > 2) {
            vecdim = Integer.parseInt(arguments[2]);
        }
        switch (action) {
            case INIT_ACTION: this.performInitialize(vecdim, vecsize); return;
            case SEQUENCE_ACTION: this.performSequence(); return;
            case NEXT_ACTION: this.performNext(); return;
            case UNKNOWN_ACTION: handleOutput(UNKNOWN_ACTION, action); return;
            default:
                handleOutput(UNKNOWN_ACTION, action);
        }
    }

    protected void performSequence() {
        if(!Hexutils.fileExists(HexConfig.CONFIG_FILE)) {
            handleOutput(NO_INIT_ACTION, null);
        }
        HexConfig config = HexConfig.getInstance(null);
        Hexinator hexinator = new Hexinator(config);
        Map<String, Integer> sequence = hexinator.getSequence();
        this.handleOutput(SEQUENCE_ACTION, sequence);
    }

    protected void performNext() {
        try {
            Hexinator hexinator = new Hexinator(HexConfig.getInstance(null));
            String hexs = hexinator.getNextValidHexString();
            this.handleOutput(NEXT_ACTION, hexs);
        } catch (HexinatorMaxTriesException x) {
            this.handleOutput(MAXTRIES_ACTION, null);
        }
    }

    protected void performInitialize(Integer vectorDimension, Integer vectorSize) {
        Properties initVals = new Properties();
        Integer vecdim = null;
        Integer vecsize = null;

        if(vectorDimension == null){
            vecdim = Integer.parseInt((String) HexConfig.DEFAULT_VALS.get("dimension"));
        } else {
            vecdim = vectorDimension;
        }

        if(vectorSize == null){
            vecsize = Integer.parseInt((String) HexConfig.DEFAULT_VALS.get("size"));
        } else {
            vecsize = vectorSize;
        }

        int linearSize = (int) Math.pow(vecsize, vecdim);

        String leap = null;
        int primeIndex = String.valueOf(linearSize).length();
        if(PRIMES.containsKey(primeIndex)) {
          leap = String.valueOf(PRIMES.get(primeIndex));
        } else {
          leap = "7";
        }

        initVals.setProperty("size", String.valueOf(vecsize));
        initVals.setProperty("dimension", String.valueOf(vecdim));
        initVals.setProperty("index", String.valueOf((new Random().nextInt(linearSize))));
        initVals.setProperty("leap", leap);
        initVals.setProperty("validate", String.valueOf(vecdim == 8)); // validate if Hex

        HexConfig.getInstance(initVals);
    }

    protected void handleOutput(String action, Object payload) {
        String msg = "";

        switch (action) {
            case NO_INIT_ACTION: {
                msg += "You must initialize the system before use:\n";
                msg += "java PATH jhexcast init";
                System.out.println(msg);
                break;
            }
            case SEQUENCE_ACTION: {
                Hexutils.dumpMap((Map<String, Integer>)payload);
                break;
            }
            case UNKNOWN_ACTION: {
                msg += String.format("'%s' is an unknown command.", payload);
                System.out.println(msg);
                break;
            }
            case NEXT_ACTION: {
                System.out.println(payload);
                break;
            }
            case MAXTRIES_ACTION: {
                msg += "Jhexcast has tried {} times and can't find a valid Hexadecimal string.";
                msg += "This could mean the universe has been breached. Best seek shelter.";
                msg = String.format(msg, HexValidator.MAX_TRIES);
                System.out.println(msg);
                break;
            }
        }
    }
}

