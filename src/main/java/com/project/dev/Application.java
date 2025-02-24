/*
 * @fileoverview    {Application}
 *
 * @version         2.0
 *
 * @author          Dyson Arley Parra Tilano <dysontilano@gmail.com>
 *
 * @copyright       Dyson Parra
 * @see             github.com/DysonParra
 *
 * History
 * @version 1.0     Implementation done.
 * @version 2.0     Documentation added.
 */
package com.project.dev;

import com.project.dev.tester.AppTester;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO: Description of {@code Application}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
public class Application {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Entrada principal del sistema.
     *
     * @param args argumentos de la linea de comandos.
     */
    public static void main(String[] args) {
        System.out.printf("\nStart date: %s\n\n", DATE_FORMAT.format(new Date()));
        System.out.printf("\nResult: %s\n", AppTester.startTesting(args));
        System.out.printf("\nEnd date:   %s\n", DATE_FORMAT.format(new Date()));
    }

}
