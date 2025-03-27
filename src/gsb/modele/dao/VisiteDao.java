/*
 * Crééé le 17 nov. 2021
 *
 * TODO Pour changer le modele de ce fichier genere allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
package gsb.modele.dao;

//import gsb.modele.Localite;
import gsb.modele.Visite;
import gsb.modele.Localite;
import gsb.modele.Medicament;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 * @author Isabelle
 * 17 nov. 2021
 * TODO Pour changer le modeele de ce commentaire de type genere, allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
public class VisiteDao {
	
	//Consultation medicament
	
	public static List<Visite> listeVisiteVille(){
	    List<Visite> listedesVisites = new ArrayList<>();
	    ResultSet reqSelection = ConnexionMySql.execReqSelection(
	        "SELECT VISITE.REFERENCE, VISITE.DATEVISITE, VISITE.COMMENTAIRE, VISITE.MATRICULE, VISITE.CODEMED, VISITE.MED_OFFERT_1, " +
	        "VISITE.QUANTITE_MED_1, VISITE.MED_OFFERT_2, VISITE.QUANTITE_MED_2, LOCALITE.VILLE " +
	        "FROM VISITE " +
	        "JOIN MEDECIN ON VISITE.CODEMED = MEDECIN.CODEMED " +  // Ajout d'un espace à la fin de cette ligne
	        "JOIN LOCALITE ON MEDECIN.CODEPOSTAL = LOCALITE.CODEPOSTAL"
	    );

	    try {
	        while (reqSelection.next()) {
	            Visite visite = new Visite(
	                reqSelection.getString("REFERENCE"),
	                reqSelection.getString("DATEVISITE"),
	                reqSelection.getString("COMMENTAIRE"),
	                reqSelection.getString("MATRICULE"),
	                reqSelection.getString("CODEMED"),
	                reqSelection.getString("MED_OFFERT_1"),
	                reqSelection.getInt("QUANTITE_MED_1"),
	                reqSelection.getString("MED_OFFERT_2"),
	                reqSelection.getInt("QUANTITE_MED_2"),
	                reqSelection.getString("VILLE")
	            );
	            listedesVisites.add(visite);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Erreur dans listeVisiteVille()");
	    }
	    return listedesVisites;
	}
	
	// recherche du contenu d'une visite
	public static Visite recherchevisite(String referencevisite){
		Visite visite = null;

		ResultSet reqSelection = ConnexionMySql.execReqSelection(
	        "SELECT REFERENCE, DATEVISITE, COMMENTAIRE, MATRICULE, CODEMED, MED_OFFERT_1, " +
	        "QUANTITE_MED_1, MED_OFFERT_2, QUANTITE_MED_2 FROM VISITE where REFERENCE ='"+referencevisite+"'"
	    );

	    try {
	        if (reqSelection.next()) {
	            visite = new Visite(
	                reqSelection.getString("REFERENCE"),
	                reqSelection.getString("DATEVISITE"),
	                reqSelection.getString("COMMENTAIRE"),
	                reqSelection.getString("MATRICULE"),
	                reqSelection.getString("CODEMED"),
	                reqSelection.getString("MED_OFFERT_1"),
	                reqSelection.getInt("QUANTITE_MED_1"),
	                reqSelection.getString("MED_OFFERT_2"),
	                reqSelection.getInt("QUANTITE_MED_2")
	                
	            );
	            
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Erreur dans recherchevisite()");
	    }
	    return visite;
	}

	public static String getLastNumVisite(){
		
		String getLastNumVisite = null;
	    ResultSet reqSelection = ConnexionMySql.execReqSelection(
	        "SELECT MAX(REFERENCE) AS dernierNumVisite FROM VISITE"
	    );

	    try {
	        if (reqSelection.next()) {
	        	getLastNumVisite = reqSelection.getString("dernierNumVisite");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Erreur dans lastNumVisite()");
	        getLastNumVisite = null;
	    }
	    
	    return getLastNumVisite;
	}

	public static int AjoutUneVisite(JPanel p, Visite visite) {

		String Vreference = visite.getReference(); 
		String Vdatevisite = visite.getDateVisite();
		String Vmatricule = visite.getMatriculeVisiteur();
		String Vcodemedecin = visite.getCodeMedecin();
		int resultat = 0;
		
		//int reqSelection = 
		String requeteInsertion = "INSERT INTO VISITE(`REFERENCE`,`DATEVISITE`,`MATRICULE`,`CODEMED`)"
				+ " VALUES('"+Vreference+"','"+Vdatevisite+"','"+Vmatricule+"','"+Vcodemedecin+"')";
				 

		try
		{ 
			resultat = ConnexionMySql.execReqMaj(requeteInsertion);
			if (resultat > 0) {
				JOptionPane.showMessageDialog(p, "Visite  ajouté avec succès.", "Réussite", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(p, "Problème pour ajouter la visite.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		} 
		catch(Exception e)
		{ 
			System.out.println("echec insertion Visite"); 
		}
		
		return resultat;
		
	}
	
	
	public static int MAJUneVisite(JPanel p, Visite visite, int IndicUpdateVisite) {

	 String Vreference = visite.getReference();
     System.out.println("reference visite : " + Vreference); 
     int resultat = 0;
     String requeteInsertion = "";
     
	 String Vcommentaire = visite.getCommentaire();
	 String VmedOffert1 = visite.getMedOffert1();
	 int VquantiteMed1 = visite.getQuantiteMed1();
	 String VmedOffert2 = visite.getMedOffert2();
	 int VquantiteMed2 = visite.getQuantiteMed2();
	

		if (IndicUpdateVisite == 0) {
			requeteInsertion = "UPDATE VISITE SET COMMENTAIRE='"+Vcommentaire+"'"
					+ " where REFERENCE ='"+Vreference+"'";
		}
		else if (IndicUpdateVisite == 1) {
			requeteInsertion = "UPDATE VISITE SET COMMENTAIRE='"+Vcommentaire+"'"
			+ ",MED_OFFERT_1='"+VmedOffert1+"',QUANTITE_MED_1='"+VquantiteMed1+"'"
			+ " where REFERENCE ='"+Vreference+"'";
			
		}
		else {
				requeteInsertion = "UPDATE VISITE SET COMMENTAIRE='"+Vcommentaire+"'"
				+ ",MED_OFFERT_1='"+VmedOffert1+"',QUANTITE_MED_1='"+VquantiteMed1+"',MED_OFFERT_2='"+VmedOffert2+"',QUANTITE_MED_2='"+VquantiteMed2+"'"
				+ " where REFERENCE ='"+Vreference+"'";
		}
	 
		try
		{ 
			resultat = ConnexionMySql.execReqMaj(requeteInsertion);
			if (resultat > 0) {
				JOptionPane.showMessageDialog(p, "Visite mise à jour avec succès.", "Réussite", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(p, "Problème pour mettre à jour la visite.", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		} 
		catch(Exception e)
		{ 
			System.out.println("echec mise à jour Visite"); 
		}
		
		return resultat;
	
	}


}



