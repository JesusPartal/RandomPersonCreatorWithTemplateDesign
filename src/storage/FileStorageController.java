package storage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import model.SuperVillain;

/**
 * FileStorageController.java 
 * Purpose: Store and read serializable objects
 * @author jesus.partal@mycit.ie
 * @author Jesus Partal
 * @version 1.4
 * @since 06-10-2019
 */
public class FileStorageController implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String SPAWNING_DIRECTORY = "../BattleZoneFolder/";
	private final String SPAWNED_FILENAME = "batllezone_";
	private final String SPAWNED_EXTENSION = ".ser";
	private int fileVersion;
	public static FileStorageController instance;
	
	private FileStorageController() {
	}
	
	public synchronized static FileStorageController getInstance() {
		/*
		 * returns the instance of FileStorageController
		 */
		if(instance == null) {
			instance = new FileStorageController();
		}
		return instance;
	}

	public void pathCreator(SuperVillain villain) {
		/*
		 * creates the path where the hero is going to be saved
		 */
		fileVersion = checkFileVersion();
		String savePath = SPAWNING_DIRECTORY 
				+ SPAWNED_FILENAME
				+ fileVersion
				+ SPAWNED_EXTENSION;
		File savedFile = new File(savePath);
		
		if(!savedFile.getParentFile().exists())
			savedFile.getParentFile().mkdir();
		
		try {
			System.out.println("saving to: "  + savePath);
			storeObject(villain, savePath);
		} catch (Exception errorWritingToFile){
			System.out.println("Error while writing file...");
		}
	}
	
	public synchronized static void storeObject(Object object, String filename) {
		/*
		 * stores an object as a serializable file
		 */
		try {
			FileOutputStream fileOutput = new FileOutputStream(filename);
			ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
			
			objectOutput.writeObject(object);
			objectOutput.close();
			fileOutput.close();
		} catch (IOException iOException) {
			storeObject(iOException, "IOException.ser");
		}
	}
	
	public synchronized int checkFileVersion() {
		/*
		 * checks the current file version
		 */
		int increase = 1;
		
		File file = new File(SPAWNING_DIRECTORY 
							+ SPAWNED_FILENAME
							+ increase
							+ SPAWNED_EXTENSION);
		
		while(file.exists()) {
			increase ++;
			file = new File(SPAWNING_DIRECTORY 
					+ SPAWNED_FILENAME
					+ increase
					+ SPAWNED_EXTENSION);
		}
		return increase;
	}
	
}
