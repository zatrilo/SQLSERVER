/*
 * Crééé le 17 nov. 2021
 *
 * TODO Pour changer le modele de ce fichier genere allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
package gsb.vue;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;
import gsb.service.MedicamentService;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;

/**
 * @author Isabelle
 * 17 nov. 2021
 * TODO Pour changer le modeele de ce commentaire de type genere, allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
public class JIFMedicamentAjout extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	//private ArrayList<Medecin> lesMedecins;
	private HashMap<String,Medicament> diccoMedicament;


	protected JPanel p;
	protected JScrollPane scrollPane;
	protected JPanel pSaisie;
	protected JTextField JTcodeMedicament;
	protected JButton JBafficherFiche;
	protected MenuPrincipal fenetreContainer;
	protected JTable table;
	protected JPanel pBoutons;
	protected JPanel pTexte;
	private JButton sortie;
	private JButton ajout;
	
	protected JLabel JLcode;
	protected JLabel JLnomCommercial;
	protected JLabel JLcomposition;
	protected JLabel JLeffets;
	protected JLabel JLcontreIndications;
	protected JLabel JLcodeFamille;
	protected JLabel JLlibelleFamille;
	protected JLabel JLechantillon;
	
	protected JTextField JTcode;
	protected JTextField JTnomCommercial;
	protected JTextField JTcomposition;
	protected JTextField JTeffets;
	protected JTextField JTcontreIndications;
    protected JTextField JTcodeFamille;
    protected JTextField JTlibelleFamille;
    protected JTextField JTechantillon;

	public JIFMedicamentAjout(MenuPrincipal uneFenetreContainer) {

    	p = new JPanel();  // panneau principal de la fenetre
        pBoutons = new JPanel();    // panneau supportant les boutons
        pTexte = new JPanel(new GridLayout(9,2));
        
        // Ajout Math à valider
        sortie = new JButton("Sortie");
        pBoutons.add(sortie);
        ajout = new JButton("Ajout d'un médicament");
        pBoutons.add(ajout);
    	
    	 JLcode = new JLabel("Code");
         JLnomCommercial = new JLabel("Nom Commercial");
         JLcomposition = new JLabel("composition");
         JLeffets = new JLabel("effets");
         JLcontreIndications = new JLabel("Contre indications");
         JLechantillon = new JLabel("Prix échantillon");
         JLcodeFamille = new JLabel("code famille");
         JLlibelleFamille = new JLabel("libelle de la famille");

         JTcode = new JTextField(20);
         JTcode.setMaximumSize(JTcode.getPreferredSize());
         JTnomCommercial = new JTextField();
         JTcomposition = new JTextField();
         JTeffets = new JTextField();    
         JTcontreIndications = new JTextField();
         
         JTechantillon = new JTextField();
         JTechantillon.setEditable(false);
         
         JTcodeFamille= new JTextField();
         JTlibelleFamille = new JTextField();
         
         pTexte.add(JLcode);
         pTexte.add(JTcode);
         pTexte.add(JLnomCommercial);
         pTexte.add(JTnomCommercial);
         pTexte.add(JLcomposition);
         pTexte.add(JTcomposition);
         pTexte.add(JLeffets);
         pTexte.add(JTeffets);
         pTexte.add(JLcontreIndications);
         pTexte.add(JTcontreIndications);
         pTexte.add(JLechantillon);
         pTexte.add(JTechantillon);
         pTexte.add(JLcodeFamille);
         pTexte.add(JTcodeFamille);
         pTexte.add(JLlibelleFamille);
         pTexte.add(JTlibelleFamille);

         setTitle("Ajout d'un médicament");
        // mise en forme de la fenetre

         p.add(pTexte);
         p.add(pBoutons);
         Container contentPane = getContentPane();
         contentPane.add(p);
         
         sortie.addActionListener((ActionListener) new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 dispose(); // Ferme la fenêtre lorsqu'on clique sur "FERMER"
             }	        	 
         });
         
         ajout.addActionListener((ActionListener) new ActionListener() {
        	
			@Override
             public void actionPerformed(ActionEvent e) {
            	 String codeMedicament = JTcode.getText();
            	 
            	 if (JTcode.getText().isEmpty() || JTnomCommercial.getText().isEmpty() ||
            			 JTcomposition.getText().isEmpty() || JTeffets.getText().isEmpty() ||
            			 JTcontreIndications.getText().isEmpty() || JTcodeFamille.getText().isEmpty() ||
            			 JTlibelleFamille.getText().isEmpty()
            			 
            	 ) {
					JOptionPane.showMessageDialog(p, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
            	 }
            	 else {
            		 Medicament medicament = new Medicament(JTcode.getText(),
            				 								JTnomCommercial.getText(),
            				 								JTcomposition.getText(),
            				 								JTeffets.getText(),
            				 		            			JTcontreIndications.getText(),
            				 		            			Float.parseFloat("0.0"),
            				 		            			JTcodeFamille.getText(),
            				 		            			JTlibelleFamille.getText()
            				 		            			);
            		 System.out.println("code médicament : " + JTcode.getText()); 
            		 MedicamentDao.AjoutUnMedicament(p, medicament);
            	 }
            		
                 // dispose(); // Ferme la fenêtre lorsqu'on clique sur "FERMER"
             }	        	 
         });

	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
   		if (source instanceof JButton){
   			if (diccoMedicament.containsKey(JTcodeMedicament.getText())){
   	   			Medicament unMedicament = diccoMedicament.get(JTcodeMedicament.getText());
   	   			fenetreContainer.ouvrirFenetre(new JIFMedicamentFiche(fenetreContainer, unMedicament ));
   	   			
   			}
   		}
   		if(source == table){
   			JTcodeMedicament.setText((String)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn()));
   			
   		}
	}
}

