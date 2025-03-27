package gsb.modele.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import gsb.modele.Medicament;
import gsb.modele.Stock;
import gsb.modele.Visiteur;

/*
 * @author Caroline Jaffré
 */
public class StockDao 
{
    public static Stock rechercher(String matricule, String med_depotlegal)
    {
        Stock stock = null;
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from STOCK where MATRICULE='"+matricule+"' AND MED_DEPOTLEGAL='" + med_depotlegal + "'");
		try {
			if (reqSelection.next()) 
            {
                Visiteur unVisiteur = VisiteurDao.rechercher(matricule);
                Medicament unMedicament = MedicamentDao.rechercher(med_depotlegal);

				stock = new Stock(reqSelection.getInt(1), unVisiteur, unMedicament);	
			};
			}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requête - select * from STOCK where MATRICULE='"+matricule+"' AND MED_DEPOTLEGAL='" + med_depotlegal + "'");
			e.printStackTrace();
			}
		ConnexionMySql.fermerConnexionBd();
		return stock;
    }
	 public static ArrayList<Stock> rechercher(String matricule)
	 {
		ArrayList<Stock> listeStock = new ArrayList<Stock>();
        ResultSet reqSelection = ConnexionMySql.execReqSelection("select * from STOCK where MATRICULE='"+matricule+"'");
		try {
			while (reqSelection.next()) 
            {
                Visiteur unVisiteur = VisiteurDao.rechercher(matricule);
                Medicament unMedicament = MedicamentDao.rechercher(reqSelection.getString(2));

				Stock unStock = new Stock(reqSelection.getInt(4), unVisiteur, unMedicament);
				listeStock.add(unStock);
			};
			}
		catch(Exception e) {
			System.out.println("erreur reqSelection.next() pour la requête - select STOCK , MED_DEPOTLEGAL from STOCK where MATRICULE='"+matricule+"'");
			e.printStackTrace();
			}
		ConnexionMySql.fermerConnexionBd();
		return listeStock;
	 }
}