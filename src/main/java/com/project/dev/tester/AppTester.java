/*
 * @fileoverview    {AppTester}
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
package com.project.dev.tester;

import com.project.dev.midi.player.MidiPlayer;

/**
 * TODO: Description of {@code AppTester}.
 *
 * @author Dyson Parra
 * @since 11
 */
public class AppTester {

    /**
     * Ejecuta las pruebas de la aplicación.
     *
     * @param args argumentos de la linea de comandos.
     * @return {@code true} si se ejecutan las pruebas correctamente, {@code false} caso contrario.
     */
    public static boolean startTesting(String[] args) {
        boolean result = true;
        try {
            System.out.println("Path: " + System.getProperty("user.dir"));

            // Reproduce el do central.
            //MidiPlayer.playNote(60, 3000);

            // Reproduce el archivo MIDI en la ruta relativa indicada.
            //MidiPlayer.playMidiFile(MidiPlayer.TEST_MIDI_PROJ_PATH, 0);

            // Reproduce el archivo MIDI indicado por el buffer de datos del recurso.
            MidiPlayer.playMidiFile(AppTester.class.getResourceAsStream(MidiPlayer.TEST_MIDI_RES_PATH), 3000);
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

}
