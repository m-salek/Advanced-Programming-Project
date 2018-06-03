package com.salek;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	
	public static AudioClip Background;
	public static AudioClip Gun1;
	
	public static void loadSound(){
//		Background = Applet.newAudioClip(Sound.class.getResource("/background.wav"));
		Gun1 = Applet.newAudioClip(Sound.class.getResource("/gun1.aiff"));
	}
	
}
