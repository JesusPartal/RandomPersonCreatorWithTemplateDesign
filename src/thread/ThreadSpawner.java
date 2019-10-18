package thread;

import java.util.Random;

import factory.VillainFactory;
import model.Person;
import model.SuperVillain;
import model.SuperVillainType;
import storage.FileStorageController;

/**
 * ThreadSpawner.java 
 * Purpose: thread that creates different types of heros
 * @author jesus.partal@mycit.ie
 * @author Jesus Partal
 * @version 1.3
 * @since 08-10-2019
 */
public class ThreadSpawner extends Thread {
	private int threadDuration, threadCadency;
	private static FileStorageController fileStorageController;
	
	public ThreadSpawner(int duration, int cadency) {
		fileStorageController = FileStorageController.getInstance();
		this.threadDuration = duration;
		this.threadCadency = cadency;
	}
	
	@Override
	public void run() {
		boolean isVillain;
		for (int time = 0; time <= threadDuration; time = time + threadCadency) {
			try {
				isVillain = getRandomBoolean();
				
				Person spawnedPerson = generatePerson(isVillain);
				
				System.out.println(spawnedPerson.getClass().getName() + " / " + spawnedPerson.getStats());
				System.out.println("--------------------------");
				
				Thread.sleep(threadCadency * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean getRandomBoolean() {
		Random random = new Random();
		return random.nextBoolean();
	
	}
	
	public static Person generatePerson(boolean isVillain) {	
		if (isVillain) {
			boolean isStrong = getRandomBoolean();
			
			SuperVillain superVillain = (isStrong) ? VillainFactory.buildVillain(SuperVillainType.BADSTRONGMAN) 
					: VillainFactory.buildVillain(SuperVillainType.BADFLYPERSON);
			
			superVillain.beingEvil();
			
			fileStorageController.pathCreator(superVillain);
			
			return superVillain;
		} else {
			return new Person();
		}
	}

}
