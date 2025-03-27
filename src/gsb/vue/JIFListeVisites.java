package gsb.vue;

import gsb.modele.Medicament;
import gsb.modele.Visite;
import gsb.modele.dao.VisiteDao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class JIFListeVisites extends JInternalFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private HashMap<String, Visite> listeVisites = new HashMap<>();
    private VisiteDao visitesDao;
    public JPanel panelPrincipal;
    protected JScrollPane scrollPane;
    protected JPanel panelSaisie;
    public JComboBox<String> comboCodeVisiteur;
    public JComboBox<String> comboDateVisite;
    protected JTextField JTreference;
    protected JButton JBdetailVisite;
    public JTable table;
    protected MenuPrincipal fenetreContainer;
    private DefaultTableModel tableModel;

    public JIFListeVisites(MenuPrincipal uneFenetreContainer) {
        this.fenetreContainer = uneFenetreContainer;

        // Initialisation de l'interface et des composants
        panelPrincipal = new JPanel(new BorderLayout());
        scrollPane = new JScrollPane();

        // Ajoutez les autres composants ou appelez une méthode d'initialisation
        initialiserInterface();
        
        // Chargez les visites
        int nbLignes = chargerVisites();

        // *******************************************************************************
        // Ajoutez les dates des visites dans la comboDateVisite sans doublons
        // listeVisites.clear();
        HashSet<String> visites = new HashSet<>();
        for (Visite visite : listeVisites.values()) {
            visites.add(visite.getDateVisite()); // On suppose que getFamille() existe
        }
        for (String visite : visites) {
        	comboDateVisite.addItem(visite);
        }
        
        // Ajoutez un écouteur pour filtrer selon la famille sélectionnée
        comboDateVisite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dateSelectionnee = (String) comboDateVisite.getSelectedItem();
                if (!dateSelectionnee.equals("sélectionnez une date visite")) {
                	afficherVisitesParDate(dateSelectionnee);
                }
            }
        });

     // *******************************************************************************
        // Ajoutez les code visiteurs des visites dans la comboCodeVisiteur sans doublons
        HashSet<String> visites2 = new HashSet<>();
        for (Visite visite : listeVisites.values()) {
            visites2.add(visite.getMatriculeVisiteur()); // On suppose que getFamille() existe
        }
        for (String visite : visites2) {
        	comboCodeVisiteur.addItem(visite);
        }
        
        // Ajoutez un écouteur pour filtrer selon la famille sélectionnée
        comboDateVisite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String MatriculeSelectionnee = (String) comboCodeVisiteur.getSelectedItem();
                if (!MatriculeSelectionnee.equals("sélectionnez code visiteur")) {
                	afficherVisitesParCodeVisiteur(MatriculeSelectionnee);
                }
            }
        });
        

    }

    private void initialiserInterface() {
        panelPrincipal = new JPanel(new BorderLayout());
        panelSaisie = new JPanel();

        panelSaisie.add(new JLabel("Code visiteur"));
        comboCodeVisiteur = new JComboBox<>(getUniqueVisiteurs());
        panelSaisie.add(comboCodeVisiteur);

        panelSaisie.add(new JLabel("Date visite"));
        comboDateVisite = new JComboBox<>(getUniqueDates());
        panelSaisie.add(comboDateVisite);

        JButton JBfiltrer = new JButton("Filtrer");
        JBfiltrer.addActionListener(e -> filtrerVisites());
        panelSaisie.add(JBfiltrer);

        panelPrincipal.add(panelSaisie, BorderLayout.NORTH);

        // Initialisation de la table
        tableModel = new DefaultTableModel(new String[] {"Référence", "Code Médecin", "Lieu"}, 0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        JPanel panelBas = new JPanel();
        panelBas.add(new JLabel("Référence"));
        JTreference = new JTextField(10);
        panelBas.add(JTreference);

        JBdetailVisite = new JButton("Visite détaillée");
        JBdetailVisite.addActionListener(this);
        panelBas.add(JBdetailVisite);

        panelPrincipal.add(panelBas, BorderLayout.SOUTH);

        Container contentPane = getContentPane();
        contentPane.add(panelPrincipal);

        setTitle("Liste des visites");
        setSize(500, 400);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
    }

    private int chargerVisites() {
        // Charge les visites depuis la base de données
        List<Visite> visites = VisiteDao.listeVisiteVille();
        listeVisites.clear();

        for (Visite visite : visites) {
            listeVisites.put(visite.getReference(), visite);
            tableModel.addRow(new Object[] {visite.getReference(), visite.getCodeMedecin(), visite.getVille()});
        }
        
        int nbLignes = listeVisites.size();
        
        return nbLignes;
    }

    private String[] getUniqueVisiteurs() {
        List<String> visiteurs = new ArrayList<>();
        visiteurs.add("sélectionnez code visiteur");
        for (Visite visite : listeVisites.values()) {
            if (!visiteurs.contains(visite.getCodeMedecin())) {
            	System.out.println(visite.getCodeMedecin());
                visiteurs.add(visite.getCodeMedecin());
            }
        }
        return visiteurs.toArray(new String[0]);
    }

    private String[] getUniqueDates() {
        List<String> dates = new ArrayList<>();
        dates.add("sélectionnez date visite");
        for (Visite visite : listeVisites.values()) {
            if (!dates.contains(visite.getDateVisite())) {
            	System.out.println(visite.getDateVisite());
                dates.add(visite.getDateVisite());
            }
        }
        return dates.toArray(new String[0]);
    }
    
    private void afficherVisitesParDate(String dateSelectionnee) {
        ArrayList<String[]> dataListe = new ArrayList<>();
        
        for (Visite visite : listeVisites.values()) {
            if (visite.getDateVisite().equals(dateSelectionnee)) {
                String[] row = {
                		
                    visite.getReference(),
                    visite.getCodeMedecin(),
                    visite.getVille()
                    
                };
                dataListe.add(row);
            }
        }
    }
 
    private void afficherVisitesParCodeVisiteur(String MatriculeSelectionnee) {
        ArrayList<String[]> dataListe = new ArrayList<>();
        
        System.out.println("Matricule : " + MatriculeSelectionnee);
        
        for (Visite visite : listeVisites.values()) {
        	
            if (visite.getMatriculeVisiteur().equals(MatriculeSelectionnee)) {
            	System.out.println("GetMatriculeVisiteur : " + visite.getMatriculeVisiteur());
            	System.out.println("Matricule Sélectionnée : " + MatriculeSelectionnee);
            	
                String[] row = {
                		
                    visite.getReference(),
                    visite.getCodeMedecin(),
                    visite.getVille()
                    
                };
                dataListe.add(row);
            }
        }
    }
        
        
        
        

    private void filtrerVisites() {
        String codeVisiteur = (String) comboCodeVisiteur.getSelectedItem();
        String dateVisite = (String) comboDateVisite.getSelectedItem();

        tableModel.setRowCount(0);  // Vide la table avant d'ajouter de nouvelles lignes

        for (Visite visite : listeVisites.values()) {
            // boolean visiteurMatch = codeVisiteur.equals("sélectionnez code visiteur") || visite.getCodeMedecin().equals(codeVisiteur);
            boolean visiteurMatch = codeVisiteur.equals("sélectionnez code visiteur") || visite.getMatriculeVisiteur().equals(codeVisiteur);
            boolean dateMatch = dateVisite.equals("sélectionnez date visite") || visite.getDateVisite().equals(dateVisite);

            if (visiteurMatch && dateMatch) {
                tableModel.addRow(new Object[]{visite.getReference(), visite.getCodeMedecin(), visite.getVille()});
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == JBdetailVisite) {
            String reference = JTreference.getText();
            
            if (listeVisites.containsKey(JTreference.getText())) {
                Visite uneVisite = listeVisites.get(reference);
                fenetreContainer.ouvrirFenetre(new JIFVisiteFiche(fenetreContainer, uneVisite ));
            } else {
                //JOptionPane.showMessageDialog(this, "Référence de visite non trouvée.");
                JOptionPane.showMessageDialog(panelPrincipal, "La référence de la visite doit être renseignée ou celle-ci n'a pas été trouvée.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }}
        }


    }



