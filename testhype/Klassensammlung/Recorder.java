package com.pipehype;

import java.io.File;

import java.io.IOException;

import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;

//Diese Klasse enth�lt einen den Recorder zur Aufnahme des Pfeiftons
public class Recorder {
	
	// eine neue Aufnahmedatei wird erstellt. Mit "Environment.getExternalStorageDirectory()" wird der Pfad der SD-Karte angegeben.
	public static final File file = new File(Environment.getExternalStorageDirectory(), "aufnahme.test");
	
	// ein neues MediaRecorder-Objekt wird erstellt.
	private MediaRecorder mr = new MediaRecorder();
	
	// �berpr�fung ob es sich um einen Emulator, oder ein echtes Android-Ger�t handelt.
	public static boolean isEmulator(){
		return Build.FINGERPRINT.startsWith("generic");
	}
	
	// Die Aufnahme starten.
	public void start(){
		try {
			//�berpr�fung ob Datei bereits existiert. Wenn ja wird sie gel�scht und anschlie�end neu erstellt.
			if(file.exists()) file.delete();
			file.createNewFile();
			//Einstellungen f�r die Audioaufnahme werden festgelegt.
			mr.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
		    mr.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
		    mr.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
		    mr.setAudioChannels(2);
		    mr.setAudioSamplingRate( isEmulator() ? 8000 : 44100 );
		    //Pfad in der die Aufnahme geschrieben werden soll wird festgelegt.
		    mr.setOutputFile(file.getAbsolutePath());
		   //Vorbereiten und Starten der Aufnahme.
		    mr.prepare();
		    mr.start();
		    System.out.println("start");
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Schlie�en und Zur�cksetzen des MediaRecorders.
	public void close(){
		stop();
		mr.release();
		System.out.println("close");
	}
	
	public void stop(){		
		mr.reset();
		 System.out.println("stop");
	}

}
