/*
 * Crééé le 17 nov. 2021
 *
 * TODO Pour changer le modele de ce fichier genere allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
package gsb.service;

import java.util.Date;

import gsb.modele.Visiteur;
import gsb.modele.dao.VisiteurDao;
import gsb.utils.ValidationUtils;
import java.util.ArrayList;
/**
 * @author Isabelle
 * 17 nov. 2021
 * TODO Pour changer le modeele de ce commentaire de type genere, allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
public class VisiteurService {
	
	public static Visiteur rechercherVisiteur(String unCodeVisiteur){
		Visiteur unVisiteur = null;
		try{
		if (unCodeVisiteur==null) {
            throw new Exception("Donnee obligatoire : code");
        }
		unVisiteur = VisiteurDao.rechercher(unCodeVisiteur);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}
		return unVisiteur;
	}
	public int creerVisiteur(String matricule, String nom,  String prenom, String login, String mdp,
			String telephone,String adresse, String codepostal,String dateentree,Float prime ,String codeunit,String nomunit){
		Visiteur unVisiteur = null;
		int resultat = 0;
		try{ // controle des paramètres d'entrée
			 // Si une donnée est manquante on lève une exception
	        if (matricule==null || nom == null || prenom == null || login == null || mdp == null ||telephone == null || adresse == null || codepostal == null || dateentree == null ||prime == null || codeunit == null || nomunit == null) {
	            throw new Exception("Données obligatoires : matricule, nom, prenom, login, mdp, adresse, codepostal,  dateentree,codeunit,nomunit");
	        }
	        if (VisiteurDao.rechercher(matricule) != null) {
	            // Si la base de donnée contient déjà un client ayant ce code on envoie une exception
	            throw new Exception("Un contact avec le code " + matricule  + " existe déjà");
	        }
	        unVisiteur = new Visiteur(matricule, nom, prenom, login, mdp, telephone,adresse, codepostal, dateentree,prime,codeunit,nomunit);
	        resultat = VisiteurDao.creer(unVisiteur);
		}
		catch(Exception e){
			System.out.println( e.getMessage());
		}		
		return resultat;	
	}
	  public static ArrayList<String> getListeVisiteurs()
	    {
	        ArrayList<String> laListe = new ArrayList<String>();
			try
	        {
			    ArrayList<Visiteur> visiteurs = VisiteurDao.retournerCollectionDesVisiteurs();
	            for (Visiteur visiteur : visiteurs)
	            {
	                laListe.add(visiteur.getMatricule());
	            }
			}
			catch(Exception e){
				System.out.println( e.getMessage());
			}
			return laListe;
		}   
	    
	    public static Visiteur rechercher(String unMatricule) {
	        return VisiteurDao.rechercher(unMatricule);
	    }

	    public static ArrayList<String> listeMatricules()
		{
			ArrayList<String> lesMatricules = new ArrayList<String>();

			ArrayList<Visiteur> lesVisiteurs = VisiteurDao.retournerCollectionDesVisiteurs();
			for (int i = 0; i < lesVisiteurs.size(); i++)
			{
				lesMatricules.add(lesVisiteurs.get(i).getMatricule());
			}
			return lesMatricules;
		}
	
	
}
