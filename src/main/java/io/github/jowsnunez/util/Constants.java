
package io.github.jowsnunez.util;

/**
 *
 * @author JowsNunez
 */
public class Constants {
    public static final String C_INT ="int";
    public static final String C_DOUBLE ="double";
    public static final String C_FLOAT ="float";
    public static final String C_BOOLEAN ="boolean";
    public static final String C_LONG ="long";
    public static final String C_PUBLIC = "public";
    public static final String C_PRIVATE = "private";
    public static final String C__PROTECTED = "protected";        
    public static final String C_MOD_PATTERN ="\\s*(public|private|protected)\\s+";
    public static final String C_ANOT_PATTERN = "(@Id\\s+)?";
    public static final String C_PRIMOBJ_PATTERN ="(int|double|float|boolean|long|[A-Z][a-z]*){1}+\\s+";
    public static final String C_NAME_ATTR="((id|iD|ID|Id)[A-Z_a-z]*);";
    public static final String C_ATTR_PATTERN = C_MOD_PATTERN + C_ANOT_PATTERN + C_PRIMOBJ_PATTERN + C_NAME_ATTR;
    public static final String C_AUTHOR_PATTERN = "(\\s*\\*\\s*@author\\s*((?U)[^\\n\\r]*\\s*+)?\\s*$)";
    public static final String C_PACKAGE_PATTERN = "(\\s*package\\s+([a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)*)(\\.entity;$))";
    
    public static final String C_CLASS_NAME_PATTERN ="([A-Z]+[A-Za-z0-9]*)";
    public static final String C_EXTENDS_PATTERN ="(\\s+extends\\s+([A-Z]+[A-Za-z0-9]*)+)?";
    public static final String C_IMPLEMENTS_PATTERN ="(\\s+implements\\s+((([A-Z]+[A-Za-z0-9]*)(\\s*,\\s*)?)+)?+)?";
    public static final String C_CLASS_PATTERN = "(\\s*public\\s+class\\s+"+C_CLASS_NAME_PATTERN+C_EXTENDS_PATTERN+C_IMPLEMENTS_PATTERN+"\\s*\\{$)";
}
