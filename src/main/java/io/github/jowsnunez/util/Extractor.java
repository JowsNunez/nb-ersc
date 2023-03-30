/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.jowsnunez.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author el_fr
 */
public class Extractor {

    public Extractor() {

    }

    public String[] extractObjAttName(String line) {
        String[] extract = new String[2];
        Pattern pattern = Pattern.compile(Constants.C_ATTR_PATTERN);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            extract[1] = matcher.group(4);
            extract[0] = matcher.group(3);
            return extract;
        }
        return null;
    }
    
     public String extractObjhAuthorName(String line) {
        String extract;
        Pattern pattern = Pattern.compile(Constants.C_AUTHOR_PATTERN);
        Matcher matcher = pattern.matcher(line);
         System.out.println(pattern);
        if (matcher.matches()) {
            System.out.println(matcher);
            extract = matcher.group(2);
            return extract;
        }
        return null;
    }
     
     public String[] extractObjPackageName(String line) {
        String[] extract = new String[2];
        Pattern pattern = Pattern.compile(Constants.C_PACKAGE_PATTERN);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            System.out.println(matcher);
            extract[1] = matcher.group(1);
            extract[0] = matcher.group(2);
            return extract;
        }
        return null;
    }
     public String extractObjClassName(String line) {
        String extract ;
        Pattern pattern = Pattern.compile(Constants.C_CLASS_PATTERN);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            extract = matcher.group(2);
            
            return extract;
        }
        return null;
    }
     
     

}
