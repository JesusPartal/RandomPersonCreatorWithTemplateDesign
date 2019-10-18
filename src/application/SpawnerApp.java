package application;

import thread.ThreadSpawner;

/**
 * SpawnerApp.java 
 * Purpose: an app that creates random person and villains
 * @author jesus.partal@mycit.ie
 * @author Jesus Partal
 * @version 1.0
 * @since 11-10-2019
 */
public class SpawnerApp {

	private final static int DURATION = 20;
	private final static int CADENCY = 1;
	
	public static void main(String[] args) {
		
		Thread threadSpawner = new ThreadSpawner(DURATION, CADENCY);
		threadSpawner.run();
	}
}
