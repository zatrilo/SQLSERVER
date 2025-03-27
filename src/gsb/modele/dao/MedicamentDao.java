/*
 * Crééé le 17 nov. 2021
 *
 * TODO Pour changer le modele de ce fichier genere allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
package gsb.modele.dao;

//import gsb.modele.Localite;
import gsb.modele.Medecin;
import gsb.modele.Medicament;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 * @author Isabelle
 * 17 nov. 2021
 * TODO Pour changer le modeele de ce commentaire de type genere, allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
public class MedicamentDao {
	
	//Consultation medicament
	
	public static Medicament rechercher(String codeMedicament){
		Medicament unMedicament=null;
		//Localite uneLocalite= null;
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from MEDICAMENT where MED_DEPOTLEGAL ='"+codeMedicament+"'");
		try {
			if (reqSelection.next()) {
				//uneLocalite = LocaliteDao.rechercher(reqSelection.getString(5));
				unMedicament = new Medicament(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4), reqSelection.getString(5),null, reqSelection.getString(7), reqSelection.getString(8) );	
			};
		}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requete - select * from MEDICAMENT where MED_DEPOTLEGAL ='"+codeMedicament+"'");
			e.printStackTrace();
		}
		ConnexionMySql.fermerConnexionBd();
		return unMedicament;
	}

	public static ArrayList<Medicament> retournerCollectionDesMedicaments(){
		ArrayList<Medicament> collectionDesMedicaments = new ArrayList<Medicament>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select MED_DEPOTLEGAL from MEDICAMENT");
		try{
			while (reqSelection.next()) {
				String codeMedicament = reqSelection.getString(1);
				collectionDesMedicaments.add(MedicamentDao.rechercher(codeMedicament));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerCollectionDesMedicaments()");
		}
		return collectionDesMedicaments;
	}

	public static HashMap<String,Medicament> retournerDictionnaireDesmedicaments(){
		HashMap<String, Medicament > diccoDesMedicaments = new HashMap<String, Medicament>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select MED_DEPOTLEGAL from MEDICAMENT");
		try{
			while (reqSelection.next()) {
				String codeMedicament = reqSelection.getString(1);
				diccoDesMedicaments.put(codeMedicament, MedicamentDao.rechercher(codeMedicament));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerDiccoDesMedicaments()");
		}
		return diccoDesMedicaments;
		
	}
	
	//Ajout d'un medicament
	public static int AjoutUnMedicament(JPanel p, Medicament medicament) {
		
		
		String Mcode = medicament.getCode(); 
		String MnomCommercial = medicament.getNomCommercial();
		String Mcomposition = medicament.getComposition();
		String Meffets = medicament.getEffets();
		String McontreIndications = medicament.getContreIndications();
		String MechantilloncontreIndications = null;
		String McodeFamille = medicament.getCodeFamille();
		String MlibelleFamille = medicament.getLibelleFamille();
		int resultat = 0;
		
		// ResultSet reqSelection = ConnexionMySql.execReqSelection("INSERT INTO MEDICAMENT (code, nomCommercial, composition, effets, contreIndications, codeFamille, libelleFamille VALUES (?, ?, ?, ?, ?, ?, ?)");
		//int reqSelection = 
		String requeteInsertion = "INSERT INTO MEDICAMENT(`MED_DEPOTLEGAL`,`MED_NOMCOMMERCIAL`,`MED_COMPOSITION`,`MED_EFFETS`,`MED_CONTREINDIC`,`FAM_CODE`,`FAM_LIBELLE`)"
				+ " VALUES('"+Mcode+"','"+MnomCommercial+"','"+Mcomposition+"','"+Meffets+"','"+McontreIndications+"','"+McodeFamille+"','"+MlibelleFamille+"')";
				 
		//ajout d'un affichage indiquant l'acceptation ou le refus de la création d'un médicament
		try
		{ 
			resultat = ConnexionMySql.execReqMaj(requeteInsertion);
			if (resultat > 0) {
				JOptionPane.showMessageDialog(p, "Médicament ajouté avec succès.", "Réussite", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(p, "Problème pour ajouter le médicament.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		} 
		catch(Exception e)
		{ 
			System.out.println("echec insertion Medicament"); 
		}
		
		return resultat;
		
	}
}


