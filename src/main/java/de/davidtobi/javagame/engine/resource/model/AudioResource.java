package de.davidtobi.javagame.engine.resource.model;

import de.davidtobi.javagame.engine.log.EngineLogger;
import de.davidtobi.javagame.engine.log.EngineLoggerLevel;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class AudioResource implements Resource {

    private Clip clip;
    private FloatControl volumeControl;

    @Override
    public void load(String path) {
        try (InputStream is = getClass().getResourceAsStream(path);
             InputStream bufferedIn = new java.io.BufferedInputStream(is);
             AudioInputStream audioStream = AudioSystem.getAudioInputStream(is)) {

            clip = AudioSystem.getClip();
            clip.open(audioStream);

            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            }

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exception) {
            EngineLogger.log(EngineLoggerLevel.ERROR,"Beim Laden der Aduiodatei mit dem Pfad " +
                    path + " ist ein Fehler aufgetreten", exception );
        }
    }

    public void play() {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void close() {
        if (clip != null) {
            clip.close();
        }
    }

    public void setVolume(float volume) {
        if (volumeControl != null) {
            // Apply an exponential curve to the volume
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();

            // Apply exponential scaling (you can adjust the exponent for finer control)
            float scale = (float) Math.log10(1 + 9 * volume); // Scale 0.0-1.0 to a perceptual range

            float dB = min + (scale * (max - min));
            volumeControl.setValue(dB);
        }
    }

    public float getVolume() {
        if (volumeControl != null) {
            // Convert decibels to a 0.0 - 1.0 range
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            return (volumeControl.getValue() - min) / (max - min);
        }
        return 1.0f;  // Default volume if no control is available
    }
}
