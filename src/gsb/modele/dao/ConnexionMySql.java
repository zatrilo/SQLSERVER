package gsb.modele.dao;


/*
 * Crééé le 17 nov. 2021
 *
 * TODO Pour changer le modele de ce fichier genere allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Isabelle
 * 17 nov. 2021
 * TODO Pour changer le modeele de ce commentaire de type genere, allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
public class ConnexionMySql { // DAO = Data Access Object
	
	static Connection cnx;
	
	public ConnexionMySql(){
		cnx = null;
	}
	
	/**
	 * methode qui permet la connexion a la base de donn�es
	 * le fait que la methode soit static permet d'eviter d'instancier dans une classe un objet ConnexioMySql
	 * pour utiliser cette methode ecrire : ConnexionMySql.connecterBd()
	 */
	public static void connecterBd(){
		//connexion a la base de donnees a partir de jdbc
		String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=GSBV2;encrypt=false"; // url : chaine de connexion
        String user = "ADMIN_DB";
        String password = "password";
        // try permet d'essayer de lancer la connexion
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            cnx = DriverManager.getConnection(url, user, password); 
        } 
        // si la connexion echoue un message d'erreur est affiche
        catch(Exception e) {
            System.out.println("Echec lors de la connexion à SQL Server : " + e.getMessage());}

	}
	
	/**
	 * @param laRequete requ�te SQL de type SELECT
	 * @return un curseur qui contient les lignes obtenues lors de l'execution de la requete, null sinon
	 * pour utiliser cette methode ecrire : ConnexionMySql.execReqSelection(uneRequete) ou uneRequete est de type String
	 */
	public static ResultSet execReqSelection(String laRequete){ 
		connecterBd();
		ResultSet resultatReq = null;
		try {
				Statement requete = cnx.createStatement(); 
				resultatReq =requete.executeQuery(laRequete); 
		} 
		catch(Exception e) {  System.out.println("Erreur requete : "+laRequete);  }
		return resultatReq;	
	}
	
	/**
	 * @param laRequete requete SQL de type INSERT, UPDATE ou DELETE
	 * @return 1 si la MAJ s'est bien deroulee, 0 sinon
	 * pour utiliser cette methode ecrire : ConnexionMySql.execReqMaj(uneRequete) ou uneRequete est de type String
	 */
	public static int execReqMaj(String laRequete){
		connecterBd();
		int nbMaj =0;
		try {
		Statement s = cnx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        nbMaj = s.executeUpdate(laRequete);
        s.close();}
		catch (Exception er) {
			er.printStackTrace(); 
			System.out.println("echec requete : "+laRequete); }
		return nbMaj;       
	}
	
	/**
	 * attention : tant que la connexion n'est pas fermee, 
	 * les MAJ ne sont pas effectives, on reste en mode deconnecte
	 * pour utiliser cette methode ecrire : ConnexionMySql.fermerConnexionBd()
	 */
	public static void fermerConnexionBd(){
		try{cnx.close();}
		catch(Exception e) {  System.out.println("Erreur sur fermeture connexion");  } 
	}

}
