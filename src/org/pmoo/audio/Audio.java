package org.pmoo.audio;

import java.net.URL;

import javafx.scene.media.*;
import javafx.util.Duration;

public class Audio {
	
	//Atributuak
	public String nireAudio;
	public Media media;

	//Eraikitzailea
	public Audio(String pSong){
		new javafx.embed.swing.JFXPanel();
		nireAudio = pSong;
	}
	
	//Beste metodoak
	public void playAudio(){
		URL url = getClass().getResource(nireAudio);
		this.media = new Media(url.toString());
		MediaPlayer player = new MediaPlayer(this.media);
		player.play();
		Duration duration = player.getTotalDuration();
		player.setStopTime(duration);
	}
}
