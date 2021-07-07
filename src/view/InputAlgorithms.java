package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum InputAlgorithms {
    BUY("^(?i)\\s*buy\\s+(\\w+)\\s*$"),
    BUILD("^(?i)\\s*build\\s+(\\w+)\\s*$"),
    UPGRAID("^(?i)\\s*UPGRAID\\s+(\\w+)\\s*$"),
    PICKUP("^(?i)\\s*pickup\\s+(\\d+\\.\\d+|\\d+)\\s+(\\d+\\.\\d+|\\d+)\\s*$"),
    WELL("^(?i)\\s*well\\s*$"),
    PLANT("^(?i)\\s*plant\\s+(\\d+\\.\\d+|\\d+)\\s+(\\d+\\.\\d+|\\d+)\\s*$"),
    WORK("^(?i)\\s*work\\s+(\\w+)\\s*$"),
    CAGE("^(?i)\\s*cage\\s+(\\d+\\.\\d+|\\d+)\\s+(\\d+\\.\\d+|\\d+)\\s*$"),
    TURN("^(?i)\\s*turn\\s+(\\d+)\\s*$"),
    TRUCKLOAD("^(?i)\\s*truck\\s+load\\s+(\\w+)\\s*$"),
    TRUCKUNLOAD("^(?i)\\s*truck\\s+unload\\s+(\\w+)\\s*$"),
    TRUCKGO("^(?i)\\s*truck\\s+go\\s*$"),
    INQUIRY("^(?i)\\s*inquiry\\s*$");
    final Pattern inputPattern;
    InputAlgorithms(String s) {
        this.inputPattern=Pattern.compile(s);
    }
    public Matcher inputMatcher(String input){

        return this.inputPattern.matcher(input);
    }

}
