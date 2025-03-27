package gsb.modele.dao;

import gsb.modele.Visiteur;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * @author Isabelle
 * 17 nov. 2021
 * TODO Pour changer le modeele de ce commentaire de type genere, allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
public class VisiteurDao {
	
	 
public static boolean RechercheMatriculeVisiteur(String codevisiteur){
		
		boolean fTrouve = false;
		
	    ResultSet reqSelection = ConnexionMySql.execReqSelection(
	        "SELECT MATRICULE FROM VISITEUR where MATRICULE= '"+codevisiteur+"'"
	    );

	    try {
	        if (reqSelection.next()) {
	        	fTrouve = true;
	        	// matricule trouvé
	        	//getLastNumVisite = reqSelection.getString("dernierNumVisite");
	        }
	    } catch (SQLException e) {
	    	// matricule non trouve
	    	fTrouve = false;
	        //e.printStackTrace();
	        //System.out.println("Erreur dans lastNumVisite()");
	        //getLastNumVisite = null;
	    }
	    return fTrouve;
	}
	public static Visiteur rechercher(String codeVisiteur){


		Visiteur  unVisiteur=null;
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from VISITEUR where MATRICULE ='"+codeVisiteur+"'");
		try {
			if (reqSelection.next()) {
				unVisiteur = new Visiteur(reqSelection.getString(1), reqSelection.getString(2), reqSelection.getString(3), reqSelection.getString(4), reqSelection.getString(5),  reqSelection.getString(6), reqSelection.getString(7), reqSelection.getString(8), reqSelection.getString(9),reqSelection.getFloat(10),reqSelection.getString(11),reqSelection.getString(12));
				
			}
			}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requete - select * from VISITEUR where MATRICULE ='"+codeVisiteur+"'");
			e.printStackTrace();
			}
		ConnexionMySql.fermerConnexionBd();
		return unVisiteur;
	}
	public static ArrayList<String> getCodesPostaux() {
	    ArrayList<String> codesPostaux = new ArrayList<>();
	    ResultSet reqSelection = ConnexionMySql.execReqSelection("SELECT CODEPOSTAL FROM LOCALITE ORDER BY CODEPOSTAL ASC");
	    
	    try {
	        while (reqSelection.next()) {
	            codesPostaux.add(reqSelection.getString(1));
	        }
	    } catch (SQLException e) {
	        System.out.println("Erreur lors de la récupération des codes postaux");
	        e.printStackTrace();
	    } finally {
	        ConnexionMySql.fermerConnexionBd();
	    }
	    
	    return codesPostaux;
	}
	
	public static ArrayList<Visiteur> retournerCollectionDesVisiteurs(){
		ArrayList<Visiteur> collectionDesVisiteurs = new ArrayList<Visiteur>();
		ResultSet reqSelection = ConnexionMySql.execReqSelection("select MATRICULE from VISITEUR ORDER BY MATRICULE ASC");
		try{
		while (reqSelection.next()) {
			String codeVisiteur = reqSelection.getString(1);
		    collectionDesVisiteurs.add(VisiteurDao.rechercher(codeVisiteur));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur retournerCollectionDesVisiteurs()");
		}
		return collectionDesVisiteurs;
	}
		public static SortedMap<String,Visiteur> retournerDictionnaireDesVisiteurs(){
			SortedMap<String, Visiteur> diccoDesVisiteurs = new TreeMap<String, Visiteur>();
			ResultSet reqSelection = ConnexionMySql.execReqSelection("select MATRICULE from VISITEUR");
			try{
			while (reqSelection.next()) {
				
				String codeVisiteur = reqSelection.getString(1);
				
				diccoDesVisiteurs.put(codeVisiteur, VisiteurDao.rechercher(codeVisiteur));
				System.out.println(VisiteurDao.rechercher(codeVisiteur).getMatricule());
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("erreur retournerDiccoDesVisiteurs()");
			}
			return diccoDesVisiteurs;
		}
		
		public static int creer(Visiteur unVisiteur){
			int result = 0;
			String requeteInsertion;
			String matricule= unVisiteur.getMatricule();
			String nom = unVisiteur.getNom();
			String prenom = unVisiteur.getPrenom();
			String login =  unVisiteur.getLogin();
			String mdp = unVisiteur.getMdp();
			String telephone = unVisiteur.getTelephone();
			String adresse=  unVisiteur.getAdresse();
			String codepostal = unVisiteur.getCodepostal();
			String dateentree = unVisiteur.getDateentree();
			Float prime=  unVisiteur.getPrime();
			String codeunit = unVisiteur.getCodeunit();
			String nomunit = unVisiteur.getNomunit();
			requeteInsertion = "insert into VISITEUR values('"+matricule+"','"+nom+"','"+prenom+"','"+login+"','"+mdp+"','"+telephone+"','"+adresse+"','"+codepostal+"','"+dateentree+"','"+prime+"','"+codeunit+"','"+nomunit+"')";
			try{
				result = ConnexionMySql.execReqMaj(requeteInsertion);
			}
			catch(Exception e){
				System.out.println("echec insertion visiteur");
			}
			ConnexionMySql.fermerConnexionBd();
			return result;
		}

	}
