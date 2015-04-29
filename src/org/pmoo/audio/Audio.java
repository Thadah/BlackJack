package org.pmoo.audio;

import java.net.URL;

import javafx.scene.media.*;

public class Audio {
	public Media media;

	public Audio(String pSong){
		new javafx.embed.swing.JFXPanel();
		URL bip = getClass().getResource(pSong);
		this.media = new Media(bip.toString());
	}
	
	public void PlayAudio(){
		MediaPlayer player = new MediaPlayer(this.media);
		player.play();
	}

}
