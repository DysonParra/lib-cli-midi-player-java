/*
 * @fileoverview {Application} se encarga de realizar tareas especificas.
 *
 * @version             1.0
 *
 * @author              Dyson Arley Parra Tilano <dysontilano@gmail.com>
 * Copyright (C) Dyson Parra
 *
 * @History v1.0 --- La implementacion de {Application} fue realizada el 31/07/2022.
 * @Dev - La primera version de {Application} fue escrita por Dyson A. Parra T.
 */
package com.project.dev;

import com.project.dev.midi.player.MidiPlayer;

/**
 * TODO: Definición de {@code Application}.
 *
 * @author Dyson Parra
 * @since 1.8
 */
public class Application {

    /**
     * Entrada principal del sistema.
     *
     * @param args argumentos de la linea de comandos.
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("\n...START...");
        // Reproduce el do central durante 3000 ms.
        //MidiPlayer.playNote(60, 3000);
        System.out.println(System.getProperty("user.dir"));
        // Reproduce el archivo MIDI en la ruta relativa indicada.
        //MidiPlayer.playMidiFile(MidiPlayer.TEST_MIDI_PROJ_PATH, 000);
        // Reproduce el archivo MIDI indicado por el buffer de datos del recurso.
        MidiPlayer.playMidiFile(
                Application.class.getResourceAsStream(MidiPlayer.TEST_MIDI_RES_PATH), 000);
        System.out.println("...END...");
    }

}
