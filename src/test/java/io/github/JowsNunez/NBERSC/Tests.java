package io.github.JowsNunez.NBERSC;

import io.github.jowsnunez.util.Extractor;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author el_fr
 */
public class Tests {

    @ParameterizedTest
    @MethodSource("classNameProvider")
    @DisplayName("Extraer nombre de clase de línea")
    public void testClassName(String line, String expectedClassName) {
        String className = new Extractor().extractObjClassName(line);
        Assertions.assertEquals(expectedClassName, className);
    }

    static Stream<Arguments> classNameProvider() {
        return Stream.of(
                Arguments.of("public class Example {", "Example"),
                Arguments.of("public class Example extends ParentExample {", "Example"),
                Arguments.of("public class Example extends ParentExample implements IExample, IExample {", "Example"),
                Arguments.of("public class example extends ParentExample implements IExample, IExample {", null)
        );
    }

    @ParameterizedTest
    @MethodSource("authorNameProvider")
    @DisplayName("Extraer nombre de author de línea")
    public void testAuthorName(String line, String expectedClassName) {
        String className = new Extractor().extractObjhAuthorName(line);
        Assertions.assertEquals(expectedClassName, className);
    }

    static Stream<Arguments> authorNameProvider() {
        return Stream.of(
                Arguments.of("* @author Jows Nunez", "Jows Nunez"),
                Arguments.of(" *@authorJows Nunez", "Jows Nunez"),
                Arguments.of("*@author Jows Nunez", "Jows Nunez"),
                Arguments.of("* @author ", ""),
                Arguments.of(" author jose", null)
        );
    }

    @ParameterizedTest
    @MethodSource("idAttrNameProvider")
    @DisplayName("Extraer nombre y Tipo  del atributo id de línea")
    public void testIdAttrName(String line, String[] expectedClassName) {
        String[] idTypeName = new Extractor().extractObjAttName(line);
        Assertions.assertArrayEquals(expectedClassName, idTypeName);
    }

    static Stream<Arguments> idAttrNameProvider() {
        return Stream.of(
                Arguments.of("private Long idExample;", new String[]{"Long", "idExample"}),
                Arguments.of("public Long iDExample;", new String[]{"Long", "iDExample"}),
                Arguments.of("private @Id Long idExample;", new String[]{"Long", "idExample"}),
                Arguments.of("private @Id Long ;", null),
                Arguments.of("private  Integer myId;", null)
        );
    }

    @ParameterizedTest
    @MethodSource("packageNameProvider")
    @DisplayName("Extraer nombre y Tipo  del atributo id de línea")
    public void testPackageName(String line, String[] expectedClassName) {
        String[] packageName = new Extractor().extractObjPackageName(line);

        Assertions.assertArrayEquals(expectedClassName, packageName);
    }

    static Stream<Arguments> packageNameProvider() {
        return Stream.of(
                Arguments.of("package com.example.entity;", new String[]{"com.example", "package com.example.entity;"}),
                Arguments.of("package com.example.Entity;", null),
                Arguments.of("Package com.example.entity;", null)
        );
    }

}
