package main;

import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;

public class Sound {
    private Clip clip;
    private URL[] soundURL = new URL[Config.AUDIO_AMOUNT];

    public Sound() {
    }

    public final void setFile(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);

            /* Convertion of the audio to be playable on most devices */
            AudioFormat decodedFormat = new AudioFormat(
            AudioFormat.Encoding.PCM_SIGNED,
            Config.SAMPLE_RATE,
            Config.SAMPLE_SIZE,
            Config.CHANNELS,
            Config.FRAME_SIZE,  // 2 канала x 16 бит = 4 байта
            Config.FRAME_RATE,
            false
            );

            AudioInputStream decodedStream = AudioSystem.getAudioInputStream(decodedFormat, ais);

            clip = AudioSystem.getClip();
            clip.open(decodedStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void play() {
        clip.start();
    }

    public final void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public final void stop() {
        clip.stop();
    }

}
