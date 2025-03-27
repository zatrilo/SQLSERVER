/*
 * Crééé le 17 nov. 2021
 *
 * TODO Pour changer le modele de ce fichier genere allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
package gsb.tests;

import gsb.modele.Medicament;
import gsb.service.MedicamentService;

/**
 * @author Isabelle
 * 17 nov. 2021
 * TODO Pour changer le modeele de ce commentaire de type genere, allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
public class MedicamentTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Medicament unMedicament = MedicamentService.rechercherMedicament("m002");
		System.out.println(unMedicament.getCode());
		System.out.println(unMedicament.getNomCommercial());
		System.out.println(unMedicament.getComposition());
		System.out.println(unMedicament.getEffets());
		System.out.println(unMedicament.getContreIndications());
		System.out.println(unMedicament.getCodeFamille());
		System.out.println(unMedicament.getLibelleFamille());
		

	}

}
