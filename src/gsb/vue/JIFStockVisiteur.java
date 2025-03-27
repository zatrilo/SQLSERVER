package gsb.vue;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import gsb.modele.Stock;
import gsb.modele.dao.StockDao;
import gsb.service.StockService;
import gsb.service.VisiteurService;

/*
 * Vue Consulter le Stock d'un visiteur
 * @author Caroline Jaffré
 */
public class JIFStockVisiteur extends JInternalFrame  implements ActionListener 
{
    // Panels
    protected JPanel p;  
    protected JPanel pCode;
    // Labels
    protected JLabel JLcodeVisiteur;
    // Champs
	protected JComboBox<String> JCVisiteur;
    protected JTable table;
    protected DefaultTableModel tableModel;
    protected ScrollPane scrollPane;

    public JIFStockVisiteur()
    {
        // J'ai choisi le BorderLayout pour coller à la maquette
        p = new JPanel(new BorderLayout());  // panneau principal de la fenêtre
        pCode = new JPanel();
        JLcodeVisiteur = new JLabel("Code Visiteur");

        // Options visiteur
        JCVisiteur = new JComboBox<String>();
        
        ArrayList<String> matricules = VisiteurService.getListeVisiteurs();
        for (String matricule : matricules)
        {
            JCVisiteur.addItem(matricule);
        }
        //  création de la table et de son modèle
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Code");
        tableModel.addColumn("Nom");
        tableModel.addColumn("Stock");
        tableModel.addRow(new Object[]{"Code", "Nom", "Stock"});

        // Ajouts des données du premier visiteur :
        ArrayList<Stock> premierVisiteur = StockDao.rechercher(matricules.get(0));
        System.out.println(matricules.get(0));
        System.out.println(premierVisiteur.get(0).getQteStock());
        for (int i = 0; i < premierVisiteur.size(); i++)
        {
            tableModel.addRow(new Object[]{premierVisiteur.get(i).getUnMedicament().getCode(), premierVisiteur.get(i).getUnMedicament().getNomCommercial(), premierVisiteur.get(i).getQteStock()});
        }
        
        
        table = new JTable(tableModel);

        // Scrollpane (zone défilante)
        scrollPane = new ScrollPane();
        scrollPane.add(table);

        // mies en place des éléments
        pCode.add(JLcodeVisiteur);
        pCode.add(JCVisiteur);
        p.add(pCode, BorderLayout.PAGE_START);
        p.add(scrollPane, BorderLayout.CENTER);
        Container contentPane = getContentPane();
        contentPane.add(p);
        JCVisiteur.addActionListener(this);

        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setTitle("Stock echantillon pour un visiteur");
        // ajout d'un écouteur d'évènement pour la fermeture de la fenêtre
        addInternalFrameListener(new InternalFrameAdapter(){
            public void  internalFrameClosing(InternalFrameEvent e) {
                          //le code que tu veux exécuter à la fermeture de la JInternalFrame
            }
        });
    }
    public void actionPerformed(ActionEvent evt) 
    { 
        String visiteurSelect = (String)JCVisiteur.getSelectedItem();
        ArrayList<Stock> listeStock = StockService.listeStockVisiteur(visiteurSelect);
        // On supprime tous les anciennes lignes
        tableModel.setRowCount(1);
        
        // Ensuite, on repeuple la liste avec les nouvelles infos
        for (int i = 0; i < listeStock.size(); i++)
        {
            Stock leStock = listeStock.get(i);
            tableModel.addRow(new Object[]{leStock.getUnMedicament().getCode(), leStock.getUnMedicament().getNomCommercial(), leStock.getQteStock()});
        }
    }
}