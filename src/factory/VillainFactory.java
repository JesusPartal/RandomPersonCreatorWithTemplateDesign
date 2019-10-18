package factory;

import model.BadFlyPerson;
import model.BadStrongMan;
import model.SuperVillain;
import model.SuperVillainType;

/**
 * VillainFactory.java 
 * Purpose: A SuperVillain object factory
 * @author jesus.partal@mycit.ie
 * @author Jesus Partal
 * @version 1.0
 * @since 06-10-2019
 */
public class VillainFactory {
	
	public static SuperVillain buildVillain(SuperVillainType type) {
		SuperVillain superVillain = null;
		switch(type) {
			case BADSTRONGMAN:
				superVillain = new BadStrongMan();
				break;
			case BADFLYPERSON:
				superVillain = new BadFlyPerson();
				break;
			default:
				break;
		}
		return superVillain;
	}
}
