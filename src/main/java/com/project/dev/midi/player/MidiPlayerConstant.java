/*
 * @overview        {MidiPlayerConstant}
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

/**
 * TODO: Description of {@code MidiPlayerConstant}.
 *
 * @author Dyson Parra
 * @since Java 17 (LTS), Gradle 7.3
 */
public interface MidiPlayerConstant {

    public static final String RES_ROOT_PATH = "/assets/com/project/dev/midi/player";
    public static final String PROJ_PATH = "src/main/resources";
    public static final String PROJ_RES_PATH = PROJ_PATH + RES_ROOT_PATH;

    public static final String SOUNDFONT_FILE = "Soundfont.SF2";
    public static final String SOUNDFONT_RES_PATH = RES_ROOT_PATH + "/" + SOUNDFONT_FILE;
    public static final String SOUNDFONT_PROJ_PATH = PROJ_RES_PATH + "/" + SOUNDFONT_FILE;

    public static final String TEST_MIDI_FILE = "Kimi.mid";
    public static final String TEST_MIDI_RES_PATH = RES_ROOT_PATH + "/" + TEST_MIDI_FILE;
    public static final String TEST_MIDI_PROJ_PATH = PROJ_RES_PATH + "/" + TEST_MIDI_FILE;
}
