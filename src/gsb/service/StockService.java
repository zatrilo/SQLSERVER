package gsb.service;

import java.util.ArrayList;

import gsb.modele.Stock;
import gsb.modele.dao.ConnexionMySql;
import gsb.modele.dao.StockDao;

/**
 * @author Caroline Jaffré
 */
public class StockService 
{

    public static int ajoutStock(int quantite, String matricule, String med_depotlegal)
    {
        int codeRequete = 0;
        int reqMaj = ConnexionMySql.execReqMaj("INSERT INTO STOCK (`STOCK`, `MATRICULE`, `MED_DEPOTLEGAL`) VALUES ('" + quantite + "', '" + matricule + "', '" + med_depotlegal + "') ");

		ConnexionMySql.fermerConnexionBd();
        if (reqMaj == 1)
        {
            codeRequete = 1;
            Stock stock = StockDao.rechercher(matricule, med_depotlegal);
            if (stock != null)
            {
                codeRequete = 2;
            }
        }
        ConnexionMySql.fermerConnexionBd();
        return codeRequete;
    }
    public static Stock rechercherStock(String matricule, String med_depotlegal)
    {
        Stock stock = StockDao.rechercher(matricule, med_depotlegal);
		return stock;
    }
    public static int updateStock(Stock unStock)
    {
        int codeRequete = 0;
        int reqMaj = ConnexionMySql.execReqMaj("UPDATE STOCK SET QTESTOCK = " + unStock.getQteStock() + " WHERE MATRICULE ='" + unStock.getUnVisiteur().getMatricule() +"' AND MED_DEPOTLEGAL = '" + unStock.getUnMedicament().getCode() + "'");
        ConnexionMySql.fermerConnexionBd();
        if (reqMaj == 1)
        {
            codeRequete = 1;
            Stock stock = StockDao.rechercher(unStock.getUnVisiteur().getMatricule(), unStock.getUnMedicament().getCode());
            if (stock != null)
            {
                codeRequete = 2;
            }
        }
        ConnexionMySql.fermerConnexionBd();
        return codeRequete;
    }
    public static ArrayList<Stock> listeStockVisiteur(String unMatricule)
    {
        ArrayList<Stock> laListe = StockDao.rechercher(unMatricule);

        return laListe;
    }
 }