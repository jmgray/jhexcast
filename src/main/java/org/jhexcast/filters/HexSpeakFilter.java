package org.jhexcast.filters;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class HexSpeakFilter implements HexFilter {
    protected List<String> unspeakables = null;
    public static String HS_FILE = "hslist.txt";

    public HexSpeakFilter() {
        this.unspeakables = this.loadUnspeakables();
    }

    protected List<String> loadUnspeakables() {
        ArrayList<String> unList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(HS_FILE))) {
            Stream<String> stream = br.lines();
            stream.forEach(unList::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return unList;
    }

    @Override
    public boolean isValid(String candidate) {
        for(String unspeak: unspeakables) {
            if (candidate.contains(unspeak)) {
                return false;
            }
        }
        return true;
    }
}
