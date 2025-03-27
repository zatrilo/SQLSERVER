/*
 * Crééé le 17 nov. 2021
 *
 * TODO Pour changer le modele de ce fichier genere allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
package gsb.service;


import java.util.ArrayList;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;

/**
 * @author Isabelle
 * 17 nov. 2021
 * TODO Pour changer le modeele de ce commentaire de type genere, allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
public class MedicamentService {
	
	public static Medicament rechercherMedicament(String unCodeMedicament){
		Medicament unMedicament = null;
		try{
		if (unCodeMedicament==null) {
            throw new Exception("Donnee obligatoire : code");
        }
		unMedicament = MedicamentDao.rechercher(unCodeMedicament);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return unMedicament;
	}
	public static ArrayList<String> getListeMedicaments()
    {
        ArrayList<String> laListe = new ArrayList<String>();
        try
        {
            ArrayList<Medicament> medicaments = MedicamentDao.retournerCollectionDesMedicaments();
            for (Medicament medicament : medicaments)
            {
                laListe.add(medicament.getCode());
            }
        }
        catch(Exception e){
            System.out.println( e.getMessage());
        }
        return laListe;
    }
}

