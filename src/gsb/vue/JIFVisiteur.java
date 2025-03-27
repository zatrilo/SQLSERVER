package gsb.vue;

import gsb.modele.Visiteur;
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
 * @author 
 * 17 nov. 2021
 */
public class JIFVisiteur extends JInternalFrame {
    private static final long serialVersionUID = 1L;

    protected JPanel p;
    protected JPanel pTexte;
    protected JPanel pBoutons;

    protected JLabel JLmatricule;
    protected JLabel JLnom;
    protected JLabel JLprenom;
    protected JLabel JLlogin;
    protected JLabel JLmdp;
    protected JLabel JLtelephone;
    protected JLabel JLadresse;
    protected JLabel JLcodepostal;
    protected JLabel JLdateEntree;
    protected JLabel JLprime;
    protected JLabel JLcodeUnite;
    protected JLabel JLnomUnite;

    protected JTextField JTmatricule;
    protected JTextField JTnom;
    protected JTextField JTprenom;
    protected JTextField JTlogin;
    protected JTextField JTmdp;
    protected JTextField JTtelephone;
    protected JTextField JTadresse;
    protected JTextField JTcodepostal;
    protected JTextField JTdateEntree;
    protected JTextField JTprime;
    protected JTextField JTcodeUnite;
    protected JTextField JTnomUnite;
    protected JButton btnFermer;
    public JIFVisiteur() {
        p = new JPanel();
        pBoutons = new JPanel();
        pTexte = new JPanel(new GridLayout(13, 2));

        JLmatricule = new JLabel("Matricule");
        JLnom = new JLabel("Nom");
        JLprenom = new JLabel("Prénom");
        JLlogin = new JLabel("Login");
        JLmdp = new JLabel("Mdp");
        JLtelephone = new JLabel("Téléphone");
        JLadresse = new JLabel("Adresse rue");
        JLcodepostal = new JLabel("Code postal");
        JLdateEntree = new JLabel("Date Entree");
        JLprime = new JLabel("Prime");
        JLcodeUnite = new JLabel("Code Unite");
        JLnomUnite = new JLabel("Nom Unite");

        JTmatricule = new JTextField();
        JTnom = new JTextField();
        JTprenom = new JTextField();
        JTlogin = new JTextField();
        JTmdp = new JTextField();
        JTtelephone = new JTextField();
        JTadresse = new JTextField();
        JTcodepostal = new JTextField();
        JTdateEntree = new JTextField();
        JTprime = new JTextField();
        JTcodeUnite = new JTextField();
        JTnomUnite = new JTextField();

        // Ajout des labels et champs texte dans le panneau pTexte
        pTexte.add(JLmatricule);
        pTexte.add(JTmatricule);
        pTexte.add(JLnom); 
        pTexte.add(JTnom);
        pTexte.add(JLprenom);
        pTexte.add(JTprenom);
        pTexte.add(JLlogin);
        pTexte.add(JTlogin);
        pTexte.add(JLmdp); 
        pTexte.add(JTmdp);
        pTexte.add(JLtelephone); 
        pTexte.add(JTtelephone);
        pTexte.add(JLadresse);
        pTexte.add(JTadresse);
        pTexte.add(JLcodepostal); 
        pTexte.add(JTcodepostal);
        pTexte.add(JLdateEntree);
        pTexte.add(JTdateEntree);
        pTexte.add(JLprime); 
        pTexte.add(JTprime);
        pTexte.add(JLcodeUnite);
        pTexte.add(JTcodeUnite);
        pTexte.add(JLnomUnite);
        pTexte.add(JTnomUnite);
        
     // Création du bouton "Fermer"
        btnFermer = new JButton("Fermer");
        
        // Ajout d'un action listener pour fermer la fenêtre
        btnFermer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Ferme la fenêtre
            }
        });
        
        // Ajout du bouton au panneau des boutons
        pBoutons.add(btnFermer);
        // Ajout des panneaux au panneau principal
        p.setLayout(new GridLayout(2, 1));
        p.add(pTexte);
        p.add(pBoutons);

        Container contentPane = getContentPane();
        contentPane.add(p);
    }

    public void remplirText(Visiteur unVisiteur) {
        JTmatricule.setText(unVisiteur.getMatricule());
        JTnom.setText(unVisiteur.getNom());
        JTprenom.setText(unVisiteur.getPrenom());
        JTlogin.setText(unVisiteur.getLogin());
        JTmdp.setText(unVisiteur.getMdp());
        JTtelephone.setText(unVisiteur.getTelephone());
        JTadresse.setText(unVisiteur.getAdresse());
        JTcodepostal.setText(unVisiteur.getCodepostal());
        JTdateEntree.setText(unVisiteur.getDateentree() );
        JTprime.setText(String.valueOf(unVisiteur.getPrime()));
        JTcodeUnite.setText(unVisiteur.getCodeunit());
        JTnomUnite.setText(unVisiteur.getNomunit());
    }

    public void viderText() {
        JTmatricule.setText("");
        JTnom.setText("");
        JTprenom.setText("");
        JTlogin.setText("");
        JTmdp.setText("");
        JTtelephone.setText("");
        JTadresse.setText("");
        JTcodepostal.setText("");
        JTdateEntree.setText("");
        JTprime.setText("");
        JTcodeUnite.setText("");
        JTnomUnite.setText("");
    }
}
