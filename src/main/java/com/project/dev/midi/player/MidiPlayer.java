/*
 * @fileoverview    {MidiPlayer}
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
package com.project.dev.midi.player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;

/**
 * TODO: Description of {@code MidiPlayer}.
 *
 * @author Dyson Parra
 * @since 11
 */
public class MidiPlayer implements MidiPlayerConstant {

    public static Synthesizer synthesizer = null;                                                           // Crea el sintetizador para cargar el soundfont del programa.
    public static Sequencer sequencer;                                                                      // Reproductor de archivos MIDI.

    static {
        try {
            MidiPlayer.synthesizer = MidiSystem.getSynthesizer();                                           // Crea un sintetizador.
            MidiPlayer.synthesizer.open();                                                                  // Abre el sintetizador.

            MidiPlayer.synthesizer.unloadAllInstruments(MidiPlayer.synthesizer.getDefaultSoundbank());      // Borra isntrumentos por defecto del sintetizador.
            InputStream soundfont = MidiPlayer.class.getResourceAsStream(SOUNDFONT_RES_PATH);
            InputStream bufferedSoundfont = new BufferedInputStream(soundfont);

            MidiPlayer.synthesizer.loadAllInstruments(MidiSystem.getSoundbank(bufferedSoundfont));          // Carga un soundfont en el sintetizador.
            //showInstruments();

            MidiPlayer.sequencer = MidiSystem.getSequencer();                                               // Crea secuencia de reproducción.
        } catch (MidiUnavailableException | InvalidMidiDataException | IOException ex) {
            Logger.getLogger(MidiPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * FIXME: Description of {@code showInstruments}. Muestra los instrumentos del sintetizador.
     */
    public static void showInstruments() {
        Instrument[] loadedInstruments = MidiPlayer.synthesizer.getLoadedInstruments();                 // Obtiene todos los intrumentos cargados en el sintetizador.
        for (int i = 0; i < loadedInstruments.length; i++)
            System.out.println(i + " " + loadedInstruments[i]);

        System.out.println("");
        Instrument[] avalibleInstruments = synthesizer.getAvailableInstruments();                       // Obtiene todos los intrumentos disponibles para agregar al sintetizador.
        for (int i = 0; i < avalibleInstruments.length; i++)
            System.out.println(i + " " + avalibleInstruments[i]);
    }

    /**
     * FIXME: Description of {@code playNote}. Reproduce el sonido de una nota ([0, 127] 60 = Do
     * central).
     *
     * @param note es la nota a reproducir.
     * @param time es la cantidad de tiempo que se reproducirá la nota.
     *
     * @throws java.lang.InterruptedException si no es posible esperar el tiempo indicado antes de
     *                                        detener la reproducción.
     */
    public static void playNote(int note, int time) throws InterruptedException {

        MidiChannel[] mChannels = synthesizer.getChannels();                            // Obtiene los canales del sintetizador.
        mChannels[0].programChange(mChannels[0].getProgram(), 1);                       // Asigna id del instrumento a los canales dle sintetizador.
        mChannels[0].noteOn(note, 100);                                                 // Reproduce la nota indicada en el canal indicado con velocidad 100.
        //mChannels[0].noteOn(70, 100);                                                 // Reproduce la nota indicada en el canal indicado con velocidad 100.
        Thread.sleep(time);                                                             // Espera el tiempo indicado en milisegundos.
        mChannels[0].noteOff(60);                                                       // Para de reproducir la nota.
    }

    /**
     * FIXME: Description of {@code playMidiFile}. Reproduce un archivo MIDI.
     *
     * @param inputStream es el archivo MIDI cargado en memoria.
     *
     * @throws IOException              si no es posible procesar {@code inputStream} como archivo
     *                                  MIDI.
     * @throws InvalidMidiDataException si se encontró información no procesable como archivo MIDI
     *                                  al procesar {@code inputStream}.
     * @throws MidiUnavailableException si el archivo MIDI indicado por {@code inputStream} no es
     *                                  válido.
     */
    public static void playMidiFile(InputStream inputStream) throws IOException, InvalidMidiDataException, MidiUnavailableException {
        sequencer.open();                                                               // Inicia la secuencia de reproducción.
        sequencer.getTransmitter().setReceiver(synthesizer.getReceiver());              // Indica que la secuencia de reproducción será el sintetizador.
        sequencer.setSequence(inputStream);                                             // Indica que se reproducirá el archivo MIDI carago en memoria indicado por {@code inputStream}.
        sequencer.start();                                                              // Inicia la reproducción del archivo MIDI.
    }

    /**
     * FIXME: Description of {@code playMidiFile}. Reproduce un archivo MIDI.
     *
     * @param path es la ruta del archivo MIDI.
     *
     * @throws IOException              si no es posible crear un {@code InputStream} y procesarlo
     *                                  como archivo MIDI usando la ruta indicada en {@code path}.
     * @throws InvalidMidiDataException si se encontró información no procesable como archivo MIDI
     *                                  al procesar el {@code InputStream} creado apartir de la ruta
     *                                  indicada por {@code path}.
     * @throws MidiUnavailableException si el archivo MIDI en la ruta indicada por {@code path} no
     *                                  es válido.
     */
    public static void playMidiFile(String path) throws IOException, InvalidMidiDataException, MidiUnavailableException {
        File file = new File(path);                                                     // Crea un archivo con la ruta del archivo MIDI.
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));   // Crea un lector de buffer de audio.
        playMidiFile(inputStream);                                                      // Reproduce el lector de bufffer de audio.
    }

    /**
     * FIXME: Description of {@code playMidiFile}. Reproduce un archivo MIDI.
     *
     * @param inputStream es el archivo MIDI cargado en memoria.
     * @param time        es la cantidad de tiempo que se reproducirá el archivo MIDI en
     *                    milisegundos, si {0} o menor se reproduce el archivo completo.
     *
     * @throws IOException                    si no es posible procesar {@code inputStream} como un
     *                                        archivo MIDI.
     * @throws InvalidMidiDataException       si se encontró información no procesable como archivo
     *                                        MIDI al procesar {@code inputStream}.
     * @throws MidiUnavailableException       si el archivo MIDI indicado por {@code inputStream} no
     *                                        es válido.
     * @throws java.lang.InterruptedException si no es posible esperar {@code time} antes de detener
     *                                        la reproducción del archivo MIDI.
     */
    public static void playMidiFile(InputStream inputStream, int time) throws IOException, InvalidMidiDataException, MidiUnavailableException, InterruptedException {
        playMidiFile(inputStream);                                                      // Reproduce el archivo MIDI indicado por {@code inputStream}.

        if (time > 0) {                                                                 // Si se indicó un tiempo mayor que cero.
            Thread.sleep(time);                                                         // Espera el tiempo indicado por {@code time}.
            sequencer.close();                                                          // Detiene la reproducción.
        }
    }

    /**
     * FIXME: Description of {@code playMidiFile}. Reproduce un archivo MIDI.
     *
     * @param path es la ruta del archivo MIDI.
     * @param time es la cantidad de tiempo que se reproducirá el archivo MIDI en milisegundos, si
     *             {0} o menor se reproduce el archivo completo.
     *
     * @throws IOException                    si no es posible crear un {@code InputStream} y
     *                                        procesarlo como archivo MIDI usando la ruta indicada
     *                                        en {@code path}.
     * @throws InvalidMidiDataException       si se encontró información no procesable como archivo
     *                                        MIDI al procesar el {@code InputStream} creado apartir
     *                                        de la ruta indicada por {@code path}.
     * @throws MidiUnavailableException       si el archivo MIDI en la ruta indicada por
     *                                        {@code path} no es válido.
     * @throws java.lang.InterruptedException si no es posible esperar {@code time} antes de detener
     *                                        la reproducción del archivo MIDI.
     */
    public static void playMidiFile(String path, int time) throws IOException, InvalidMidiDataException, MidiUnavailableException, InterruptedException {
        playMidiFile(path);                                                             // Reproduce el archivo MIDI en la ruta indicada por {@code path}.

        if (time > 0) {                                                                 // Si se indicó un tiempo mayor que cero.
            Thread.sleep(time);                                                         // Espera el tiempo indicado por {@code time}.
            sequencer.close();                                                          // Detiene la reproducción.
        }
    }

}
