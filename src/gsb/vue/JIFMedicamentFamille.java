package gsb.vue;

import gsb.modele.Medicament;
import gsb.modele.dao.MedicamentDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ArrayList; // Ajoutez cet import en haut du fichier

public class JIFMedicamentFamille extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private HashMap<String, Medicament> diccoMedicament;
    protected JPanel p;
    protected JScrollPane scrollPane;
    protected JPanel pSaisie;
    //protected JTextField JTcodeMedicament;
    //protected JButton JBafficherFiche;
    protected MenuPrincipal fenetreContainer;
    protected JTable table;
    protected JComboBox<String> comboBoxFamilles; // Liste déroulante pour les familles
    protected JPanel pBoutons;
    private JButton JBfermer; //bouton fermer ajouté
    
    public JIFMedicamentFamille(MenuPrincipal uneFenetreContainer) {
        fenetreContainer = uneFenetreContainer;
        diccoMedicament = MedicamentDao.retournerDictionnaireDesmedicaments();
        int nbLignes = diccoMedicament.size();
        
        p = new JPanel(); // Panneau principal de la fenêtre
        
        // Initialisation de la JComboBox pour les familles de médicaments
        comboBoxFamilles = new JComboBox<>();
        comboBoxFamilles.addItem("Sélectionnez une famille");
        
        // Ajoutez les familles de médicaments dans le comboBoxFamilles sans doublons
        HashSet<String> familles = new HashSet<>();
        for (Medicament medicament : diccoMedicament.values()) {
            familles.add(medicament.getLibelleFamille()); // On suppose que getFamille() existe
        }
        for (String famille : familles) {
            comboBoxFamilles.addItem(famille);
        }
        
        // Ajoutez un écouteur pour filtrer selon la famille sélectionnée
        comboBoxFamilles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String familleSelectionnee = (String) comboBoxFamilles.getSelectedItem();
                if (!familleSelectionnee.equals("Sélectionnez une famille")) {
                	afficherMedicamentsParFamille(familleSelectionnee);
                }
            }


        });
        
        setTitle("Médicaments par famille");
        
        // Ajoutez la JComboBox au panneau de saisie
        pSaisie = new JPanel();
        pSaisie.add(new JLabel("Famille :"));
        pSaisie.add(comboBoxFamilles);
        
        /**
      //Bouton Fermer consutation médicaments
        fermer.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Ferme la fenêtre lorsqu'on clique sur "FERMER"
            }
        });
        **/
        // Initialisation des données de la JTable
        String[][] data = new String[nbLignes][5];
        int i = 0;
        for (Map.Entry<String, Medicament> uneEntree : diccoMedicament.entrySet()) {
            Medicament medicament = uneEntree.getValue();
            data[i][0] = medicament.getCode();
            data[i][1] = medicament.getNomCommercial();
            data[i][2] = medicament.getComposition();
            data[i][3] = medicament.getEffets();
            data[i][4] = medicament.getContreIndications();
            i++;
        }
        String[] columnNames = {"Depot légal", "Nom commercial", "Composition", "Effets", "Contre indications"};
        table = new JTable(data, columnNames);
        
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));
        p.add(scrollPane);
        
        //JTcodeMedicament = new JTextField(20);
        //JTcodeMedicament.setMaximumSize(JTcodeMedicament.getPreferredSize());
        //JBafficherFiche = new JButton("Afficher Fiche ");
        //JBafficherFiche.addActionListener(this); // Source d'événement
        //pSaisie.add(JTcodeMedicament);
        //pSaisie.add (JBafficherFiche);
        p.add(pSaisie);
        
        //Création bouton fermer
        JBfermer = new JButton("Fermer");
        JBfermer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Ferme la fenêtre courante
            }
        });
        pSaisie.add(JBfermer); // Ajout du bouton Fermer au panneau de saisie
        
        
        // Mise en forme de la fenêtre
        Container contentPane = getContentPane();
        contentPane.add(p);
    }

    // Méthode pour afficher les médicaments en fonction de la famille sélectionnée
    private void afficherMedicamentsParFamille(String familleSelectionnee) {
        ArrayList<String[]> dataListe = new ArrayList<>();
        
        for (Medicament medicament : diccoMedicament.values()) {
            if (medicament.getLibelleFamille().equals(familleSelectionnee)) {
                String[] row = {
                    medicament.getCode(),
                    medicament.getNomCommercial(),
                    medicament.getComposition(),
                    medicament.getEffets(),
                    medicament.getContreIndications()
                };
                dataListe.add(row);
            }
        }

        // Conversion de l'ArrayList en tableau 2D pour la JTable
        String[][] data = new String[dataListe.size()][5];
        dataListe.toArray(data);

        // Mise à jour du modèle de la JTable avec les médicaments filtrés
        table.setModel(new javax.swing.table.DefaultTableModel(
            data,
            new String[]{"Depot légal", "Nom commercial", "Composition", "Effets", "Contre indications"}
        ));
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
    }
}
