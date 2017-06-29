package ru.stga.pft.sandbox;

/**
 * Created by admin on 29.06.2017.
 */
public class Equality {
    public static void main (String [] args) {
        String s1 = "firefox";
        //String s2 = s1;
        //String s2 = new String (s1);

        String s2 = "firefox";

        System.out.println (s1 == s2);
        System.out.println (s1.equals(s2));
    }
}
