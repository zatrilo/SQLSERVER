/*
 * Crééé le 17 nov. 2021
 *
 * TODO Pour changer le modele de ce fichier genere allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
package gsb.tests;

import gsb.modele.dao.ConnexionMySql;

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
public class ConnexionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Raccord de methode auto-genere
		ResultSet resultat = ConnexionMySql.execReqSelection("select * from LOCALITE");
		try {
			while (resultat.next()) {
				System.out.println(resultat.getString(1) + "  "
						+ resultat.getString(2));
			}
			;
			System.out.println("fin");
		} catch (Exception e) {
			System.out.println("Exception");
			e.printStackTrace();
		}
	}

}


