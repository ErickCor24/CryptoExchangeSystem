package org.globant.view;

public class ColorView {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public void redColor(String var) {
        System.out.println(ANSI_RED + var + ANSI_RESET);
    }

    public void yellowColor(String var) {
        System.out.println(ANSI_YELLOW + var + ANSI_RESET);
    }

    public void greenColor(String var) {
        System.out.println(ANSI_GREEN + var + ANSI_RESET);
    }

    public void blueColor(String var) {
        System.out.println(ANSI_BLUE + var + ANSI_RESET);
    }

}
