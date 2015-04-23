package org.pmoo.audio;

import java.io.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Audio {

	public static void main(String[] args) throws Exception{
		new javafx.embed.swing.JFXPanel();
		String bip = new File("/home/thadah/Descargas/scatman.mp3").toURI().toString();
		MediaPlayer player = new MediaPlayer(new Media(bip));
		player.play();

	}

}
