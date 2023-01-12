package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL[30];
	
	public Sound() {
		// INSTANCIATES AUDIO FILES
		soundURL[0] = getClass().getResource("/sound/atkEffect.wav");
		soundURL[1] = getClass().getResource("/sound/gameMusic.wav");
		soundURL[2] = getClass().getResource("/sound/dmgEffect.wav");
		soundURL[3] = getClass().getResource("/sound/game_over.wav");

	}
	public void setFile(int i) {
		
		try {
			
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(audioStream);
			
		}catch(Exception e) {
			
		}
	}
	public void play() {
		
		clip.start();
	}
	public void loop() {
		
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	public void stop() {
		
		clip.stop();
	}
}
