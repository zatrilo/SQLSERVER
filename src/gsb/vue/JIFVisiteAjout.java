package gsb.vue;

import gsb.modele.Medicament;
import gsb.modele.Visite;
import gsb.modele.dao.MedecinDao;
import gsb.modele.dao.MedicamentDao;
import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.VisiteurDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class JIFVisiteAjout extends JInternalFrame implements ActionListener {
	/**
	 * Commentaire pour <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	private static final String String = null;
	protected JPanel p;
	protected JPanel parentPanel;
	protected JPanel Panelpartie1;
	protected JPanel Panelpartie2;
	protected JPanel Panelpartie3;
	protected JPanel Panelpartie4;
	protected JPanel pBoutons;
	//Bouton Fermer consutation médicaments
	private JButton sortie;
	private JButton ajout;
	
	protected JLabel JLreference;
	protected JLabel JLdateVisite;
	protected JLabel JLcommentaire;
	protected JLabel JLmatriculeVisiteur;
	protected JLabel JLcodeMedecin;
    //protected JLabel JLmedOffert1;
    //protected JLabel JLquantiteMed1;
    //protected JLabel JLmedOffert2;
    //protected JLabel JLquantiteMed2;
    
    
	protected JTextField JTreference;
	protected JTextField JTdateVisite;
	protected JTextField JTcommentaire;
	protected JTextField JTmatriculeVisiteur;
	protected JTextField JTcodeMedecin;
    //protected JTextField JTmedOffert1;
    //protected JTextField JTquantiteMed1;
    //protected JTextField JTmedOffert2;
    //protected JTextField JTquantiteMed2;
    
    // Constructeur de la classe JIFMedicament
    public JIFVisiteAjout(MenuPrincipal fenetreContainer) {
    	p = new JPanel();  // panneau principal de la fenetre
        pBoutons = new JPanel();    // panneau supportant les boutons
        
        //parentPanel = new JPanel(new BorderLayout());
        parentPanel = new JPanel();
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.Y_AXIS));
        
        Panelpartie1 = new JPanel(new GridLayout(1,2));
        Panelpartie2 = new JPanel(new GridLayout(3,2));
        Panelpartie3 = new JPanel(new GridLayout(1,2));
        Panelpartie4 = new JPanel(new GridLayout(4,2));
       
        //Bouton Fermer consutation médicaments
        sortie = new JButton("FERMER");
        pBoutons.add(sortie);
        ajout = new JButton("Ajout d'une visite");
        pBoutons.add(ajout);        
        
        // recherche du dernier numéro de visite
        String LastNumVisite = RechercheLastNumVisite();
        // ajout +1 pour obtenir un nouveau numéro de visite 
        String NewNumVisite = genererProchainNumVisite(LastNumVisite);
        
    	 JLreference = new JLabel("Référence");
         JLdateVisite = new JLabel("Date visite");
         JLmatriculeVisiteur = new JLabel("Matricule visiteur");
         JLcodeMedecin = new JLabel("Code Médecin");
         JLcommentaire = new JLabel("Commentaire");
         //JLmedOffert1 = new JLabel("Dépot légal Médicament 1");
         //JLquantiteMed1 = new JLabel("Quantité offerte Médicament 1");
         //JLmedOffert2 = new JLabel("Dépot légal Médicament 2");
         //JLquantiteMed2 = new JLabel("Quantité offerte Médicament 2");
         
         JTreference = new JTextField(20);
         
         // JTdateVisite.setMaximumSize(JTreference.getPreferredSize());
         JTdateVisite = new JTextField();
         
         JTmatriculeVisiteur = new JTextField();
         
         JTcodeMedecin = new JTextField();
         
         JTcommentaire = new JTextField();
         JTcommentaire.setEditable(false);
         
         //JTmedOffert1 = new JTextField();
         //JTmedOffert1.setEditable(false);
         
         //JTquantiteMed1 = new JTextField();
         //JTquantiteMed1.setEditable(false);
         
         //JTmedOffert2 = new JTextField();
         //JTmedOffert2.setEditable(false);
         
         //JTquantiteMed2 = new JTextField();
         //JTquantiteMed2.setEditable(false);
         
         Panelpartie1.add(JLreference);
         JTreference.setText(NewNumVisite);
         
         Panelpartie1.add(JTreference);
         
         Panelpartie2.add(JLdateVisite);
         Panelpartie2.add(JTdateVisite);
         Panelpartie2.add(JLmatriculeVisiteur);
         Panelpartie2.add(JTmatriculeVisiteur);
         Panelpartie2.add(JLcodeMedecin);
         Panelpartie2.add(JTcodeMedecin);
         
         
         Panelpartie3.add(JLcommentaire);
         Panelpartie3.add(JTcommentaire);
         
         //Panelpartie4.add(JLmedOffert1);
         //Panelpartie4.add(JTmedOffert1);
         //Panelpartie4.add(JLquantiteMed1);
         //Panelpartie4.add(JTquantiteMed1);
         //Panelpartie4.add(JLquantiteMed2);
         //Panelpartie4.add(JTmedOffert2);
         //Panelpartie4.add(JLmedOffert2);
         //Panelpartie4.add(JTquantiteMed2);
         

         parentPanel.add(Panelpartie1);
         parentPanel.add(Box.createVerticalStrut(10));
         parentPanel.add(Panelpartie2);
         parentPanel.add(Box.createVerticalStrut(10));
         parentPanel.add(Panelpartie3);
         parentPanel.add(Box.createVerticalStrut(10));
         //parentPanel.add(Panelpartie4);
         //parentPanel.add(Box.createVerticalStrut(10));
         //parentPanel.add(tableauPanel);
         
         
         
         setTitle("Ajout d'une visite");
         
        // mise en forme de la fenetre

         p.add(parentPanel);
         p.add(pBoutons);
         Container contentPane = getContentPane();
         contentPane.add(p);
         
       //Bouton Fermer consutation médicaments
         sortie.addActionListener((ActionListener) new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 dispose(); // Ferme la fenêtre lorsqu'on clique sur "FERMER"
             }
         });
         

         ajout.addActionListener((ActionListener) new ActionListener() {
         	
			@Override
             public void actionPerformed(ActionEvent e) {
            	 String REFERENCEvisite = JTreference.getText();
            	 
                 Panelpartie1.add(JTreference);
                 
                 Panelpartie2.add(JLdateVisite);
                 Panelpartie2.add(JTdateVisite);
                 Panelpartie2.add(JLmatriculeVisiteur);
                 Panelpartie2.add(JTmatriculeVisiteur);
                 Panelpartie2.add(JLcodeMedecin);
                 Panelpartie2.add(JTcodeMedecin);
                 //JTnomCommercial = new JTextField();
                 //JTcomposition = new JTextField();
                 //JTeffets = new JTextField();    
                 //JTcontreIndications = new JTextField();
                 //JTcodeFamille= new JTextField();
                 //JTlibelleFamille = new JTextField();
            	 
            	 
            	// if (codeMedicament.isEmpty()) {
            	 if (JTreference.getText().isEmpty() || JTdateVisite.getText().isEmpty() ||
            			 JTmatriculeVisiteur.getText().isEmpty() || JTcodeMedecin.getText().isEmpty()
            			 
            	 ) {
					JOptionPane.showMessageDialog(p, "Tous les champs doivent être remplis.", "Erreur", JOptionPane.ERROR_MESSAGE);
            	 }
            	 else {
            		String DateSaisie = JTdateVisite.getText();
            		
                    if (!estDateValide(DateSaisie)) {
                    	JOptionPane.showMessageDialog(p, "La date n'est pas valide ! \nformat demandé AAAA-MM-JJ", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                    	// JOptionPane.showMessageDialog(p, "La date est valide !", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    	
                    	boolean fTrouve = false;
                    	
                    	// vérification que le matricule visiteur saisi existe
                        String matriculevisiteur = JTmatriculeVisiteur.getText();
                        fTrouve = RechercheMatriculeVisiteur(matriculevisiteur);
                        
                        if (!fTrouve) {
                        	JOptionPane.showMessageDialog(p, "Le matricule visiteur saisi n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                        	// vérification que le matricule visiteur saisi existe
                            String codemedecin = JTcodeMedecin.getText();
                            fTrouve = RechercheCodeMedecin(codemedecin);
                            if (!fTrouve) {
                            	JOptionPane.showMessageDialog(p, "Le code médecin saisi n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                            else {
                        		Visite visite = new Visite(JTreference.getText(),
    				 										JTdateVisite.getText(),
				 											JTmatriculeVisiteur.getText(),
			 												JTcodeMedecin.getText());
    		
                        		// System.out.println("Nouveau numéro de visite : " + JTreference.getText()); 
                    			VisiteDao.AjoutUneVisite(p, visite);
                            }

                        	
                        }
                        
                    }
                    

                    
                    

            	 }
            		
                 // dispose(); // Ferme la fenêtre lorsqu'on clique sur "FERMER"
             }	        	 
         });


	}

	public String RechercheLastNumVisite() {
		// TODO Auto-generated method stub
        // Charge les visites depuis la base de données
		String LastNumVisite;
        return LastNumVisite = VisiteDao.getLastNumVisite();
	}
	
	public boolean RechercheMatriculeVisiteur(String codevisiteur) {
		boolean fTrouve = false;
		
		fTrouve = VisiteurDao.RechercheMatriculeVisiteur(codevisiteur);
		return fTrouve;
	}

	public boolean RechercheCodeMedecin(String codemedecin) {
		boolean fTrouve = false;
		
		fTrouve = MedecinDao.RechercheCodeMedecin(codemedecin);
		return fTrouve;
	}

	
	
	private static boolean estDateValide(String date) {
        // Vérification du format avec une regex
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
        	// ^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$
            return false;
        }

        // Validation de la date en utilisant LocalDate
        try {
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy/MM/dd][yyyy-MM-dd]");
            LocalDate.parse(date, formatter); // Exception si la date est invalide
         // 
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
	
    // Méthode pour générer le prochain numéro de visite
    public String genererProchainNumVisite(String LastNumVisite) {
        int numero = Integer.parseInt(LastNumVisite.substring(1)); // Retirer "V"
        numero++; // Incrémenter
        return String.format("v%04d", numero); // Générer nouveau numéro
        
    }

	public void remplirText(Visite uneVisite) 	
    {  // methode qui permet de remplir les zones de texte a partir des valeurs passees en parametres
        JTreference.setText(uneVisite.getReference());        
        JTdateVisite.setText(uneVisite.getDateVisite());
        JTmatriculeVisiteur.setText(uneVisite.getMatriculeVisiteur());
        JTcodeMedecin.setText(uneVisite.getCodeMedecin());
        JTcommentaire.setText(uneVisite.getCommentaire());
        //JTmedOffert1.setText(uneVisite.getMedOffert1());
        //JTquantiteMed1.setText(String.valueOf(uneVisite.getQuantiteMed1()));
        //JTmedOffert2.setText(uneVisite.getMedOffert2());
        //JTquantiteMed2.setText(String.valueOf(uneVisite.getQuantiteMed2()));

        
        
    }
     
      public void viderText() 	
    {  // methode qui permet de vider les zones de texte 
        JTreference.setText("");        
        JTdateVisite.setText("");
        JTcommentaire.setText("");
        JTmatriculeVisiteur.setText("");    
        JTcodeMedecin.setText("");
        //JTmedOffert1.setText("");
        //JTquantiteMed1.setText("");
        //JTmedOffert2.setText("");
        //JTquantiteMed2.setText("");
     }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


    
}

