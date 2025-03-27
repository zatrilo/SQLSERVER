package gsb.vue;

	import gsb.modele.Medicament;

	import java.awt.Container;
	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;
	import javax.swing.JTextField;

	/**
	 * @author Isabelle
	 * 17 nov. 2021
	 * TODO Pour changer le modeele de ce commentaire de type genere, allez :
	 * Fenetre - Preferences - Java - Style de code - Modeles de code
	 */
	public class JIFMedicament extends JInternalFrame  {
		/**
		 * Commentaire pour <code>serialVersionUID</code>
		 */
		private static final long serialVersionUID = 1L;
		protected JPanel p;  
		protected JPanel pTexte;
		protected JPanel pBoutons;
		// ajout Math
		// private JButton sortir;
		// Ajout Math
		private JButton sortie;

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
		
	    public JIFMedicament(MenuPrincipal fenetreContainer) {
	    	p = new JPanel();  // panneau principal de la fenetre
	        pBoutons = new JPanel();    // panneau supportant les boutons
	        pTexte = new JPanel(new GridLayout(9,2));
	        
	        // Ajout Math à valider
	        sortie = new JButton("Sortie");
	        pBoutons.add(sortie);
	    	
	    	 JLcode = new JLabel("Code");
	         JLnomCommercial = new JLabel("Nom Commercial");
	         JLcomposition = new JLabel("composition");
	         JLeffets = new JLabel("effets");
	         JLcontreIndications = new JLabel("Contre indications");
	         JLcodeFamille = new JLabel("code famille");
	         JLlibelleFamille = new JLabel("libelle de la famille");

	         
	         JTcode = new JTextField(20);
	         JTcode.setMaximumSize(JTcode.getPreferredSize());
	         JTnomCommercial = new JTextField();
	         JTcomposition = new JTextField();
	         JTeffets = new JTextField();    
	         JTcontreIndications = new JTextField();
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
	         pTexte.add(JLcodeFamille);
	         pTexte.add(JTcodeFamille);
	         pTexte.add(JLlibelleFamille);
	         pTexte.add(JTlibelleFamille);

	         setTitle("Fiche médicament");
	        // mise en forme de la fenetre

	         p.add(pTexte);
	         p.add(pBoutons);
	         Container contentPane = getContentPane();
	         contentPane.add(p);
	         
	         
	      // ajout Math
		  //sortir = new JButton("Sortir");
		  //pBoutons.add(sortir);
		  //sortir.addActionListener(this);

	         // Ajout Math à valider
	         sortie.addActionListener((ActionListener) new ActionListener() {
	             @Override
	             public void actionPerformed(ActionEvent e) {
	                 dispose(); // Ferme la fenêtre lorsqu'on clique sur "FERMER"
	             }	        	 
	         });
		}
	    
	    public void remplirText(Medicament unMedicament) 	
	    {  // methode qui permet de remplir les zones de texte a partir des valeurs passees en parametres
	        JTcode.setText(unMedicament.getCode());        
	        JTnomCommercial.setText(unMedicament.getNomCommercial());
	        JTcomposition.setText(unMedicament.getComposition());
	        JTeffets.setText(unMedicament.getEffets());    
	        JTcontreIndications.setText(unMedicament.getContreIndications());
	        JTcodeFamille.setText(unMedicament.getCodeFamille());
	        JTlibelleFamille.setText(unMedicament.getLibelleFamille());
	     }
	     
	      public void viderText() 	
	    {  // methode qui permet de vider les zones de texte 
	        JTcode.setText("");        
	        JTnomCommercial.setText("");
	        JTcomposition.setText("");
	        JTeffets.setText("");    
	        JTcontreIndications.setText("");
	        JTcodeFamille.setText("");
	        JTlibelleFamille.setText("");
	     }


	    
	}
