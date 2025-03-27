package gsb.vue;

import gsb.service.VisiteurService;
import gsb.modele.dao.VisiteurDao; // Import pour utiliser la méthode getCodesPostaux
import gsb.utils.ValidationUtils;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JIFVisiteurAjouter extends JInternalFrame {

    private MenuPrincipal menuPrincipal;
    private JTextField txtMatricule, txtNom, txtPrenom, txtLogin, txtTelephone, txtAdresse,
            txtDateEntree, txtPrime, txtNomUnite;
    private JComboBox<String> comboCodePostal, comboCodeUnite;
    private JPasswordField txtMdp;
    private JButton btnAjouter;

    // Liste dynamique des codes postaux récupérés depuis la base de données
    private String[] codesPostaux;

    // Map pour relier les codes d'unités à leurs noms
    private Map<String, String> uniteNoms;

    public JIFVisiteurAjouter(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;

        setTitle("Ajouter un Visiteur");
        setSize(600, 500);
        setResizable(false);

        // Initialiser la map des codes unités et noms
        initUniteNoms();
        
        // Initialiser les codes postaux depuis la base de données
        initCodesPostaux();

        // Initialisation des champs de saisie
        txtMatricule = new JTextField();
        txtNom = new JTextField();
        txtPrenom = new JTextField();
        txtLogin = new JTextField();
        txtMdp = new JPasswordField();
        txtTelephone = new JTextField();
        txtAdresse = new JTextField();
        txtDateEntree = new JTextField();
        txtPrime = new JTextField();

        txtNomUnite = new JTextField();
        txtNomUnite.setEditable(false);

        // JComboBox pour les codes postaux (dynamique)
        comboCodePostal = new JComboBox<>(codesPostaux);
        
        // JComboBox pour les unités
        comboCodeUnite = new JComboBox<>(uniteNoms.keySet().toArray(new String[0]));
        comboCodeUnite.addActionListener(e -> {
            String selectedCodeUnite = (String) comboCodeUnite.getSelectedItem();
            txtNomUnite.setText(uniteNoms.get(selectedCodeUnite));
        });

        btnAjouter = new JButton("Ajouter Visiteur");

        // Utilisation de GridLayout avec 2 colonnes
        setLayout(new GridLayout(13, 2, 5, 5));

        // Ajout des labels et des champs dans l'ordre souhaité
        add(new JLabel("Matricule:"));
        add(txtMatricule);

        add(new JLabel("Nom:"));
        add(txtNom);

        add(new JLabel("Prénom:"));
        add(txtPrenom);

        add(new JLabel("Login:"));
        add(txtLogin);

        add(new JLabel("Mot de Passe:"));
        add(txtMdp);

        add(new JLabel("Téléphone:"));
        add(txtTelephone);

        add(new JLabel("Adresse:"));
        add(txtAdresse);

        add(new JLabel("Code Postal:"));
        add(comboCodePostal); // Ajout du JComboBox au lieu de JTextField

        add(new JLabel("Date d'Entrée:"));
        add(txtDateEntree);

        add(new JLabel("Prime:"));
        add(txtPrime);

        add(new JLabel("Code Unité:"));
        add(comboCodeUnite);

        add(new JLabel("Nom Unité:"));
        add(txtNomUnite);

        // Ligne vide pour laisser un espace avant le bouton
        add(new JLabel());
        add(btnAjouter);

        // Gestionnaire d'événements pour le bouton
        btnAjouter.addActionListener(e -> {
            String matricule = txtMatricule.getText().toUpperCase();
            String nom = txtNom.getText();
            String prenom = txtPrenom.getText();
            String login = txtLogin.getText();
            String mdp = new String(txtMdp.getPassword());
            String telephone = txtTelephone.getText();
            String adresse = txtAdresse.getText();
            String codePostal = (String) comboCodePostal.getSelectedItem();
            String dateEntree = txtDateEntree.getText();

            // Validation du matricule
            if (matricule.length() > 4) {
                JOptionPane.showMessageDialog(this, "Le matricule doit contenir au maximum 4 caractères.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (matricule.length() == 0) {
                JOptionPane.showMessageDialog(this, "Le matricule est obligatoire.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validation du mot de passe
            if (mdp.length() < 8) {
                JOptionPane.showMessageDialog(this, "Le mot de passe doit contenir au moins 8 caractères.", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Validation du téléphone
            if (!ValidationUtils.isTelephoneValide(telephone)) {
                JOptionPane.showMessageDialog(this, "Numéro de téléphone invalide.");
                return;
            }

            // Validation de la date d'entrée
            String dateFormatted;
            if (!ValidationUtils.isDateValide(dateEntree)) {
                JOptionPane.showMessageDialog(this, "Date invalide. Format attendu : jj/MM/aaaa.");
                return;
            }

            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = inputFormat.parse(dateEntree);
                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormatted = outputFormat.format(date);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Erreur lors de la conversion de la date.");
                return;
            }

            // Validation de la prime
            Float prime = null;
            if (!txtPrime.getText().isEmpty()) {
                try {
                    prime = Float.parseFloat(txtPrime.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Prime doit être un nombre valide.");
                    return;
                }
            }

            String codeUnite = (String) comboCodeUnite.getSelectedItem();
            String nomUnite = txtNomUnite.getText();

            VisiteurService service = new VisiteurService();
            int resultat = service.creerVisiteur(matricule, nom, prenom, login, mdp, telephone, adresse, codePostal, dateFormatted, prime, codeUnite, nomUnite);

            if (resultat == 1) {
                JOptionPane.showMessageDialog(this, "Visiteur ajouté avec succès.");
                viderChamps();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout du visiteur.");
            }
        });
    }

    private void viderChamps() {
        txtMatricule.setText("");
        txtNom.setText("");
        txtPrenom.setText("");
        txtLogin.setText("");
        txtMdp.setText("");
        txtTelephone.setText("");
        txtAdresse.setText("");
        comboCodePostal.setSelectedIndex(0);
        txtDateEntree.setText("");
        txtPrime.setText("");
        comboCodeUnite.setSelectedIndex(0);
        txtNomUnite.setText("");
    }

    private void initUniteNoms() {
        uniteNoms = new HashMap<>();
        uniteNoms.put("SW", "SWISS");
        uniteNoms.put("BO", "BOURDIN");
    }

    private void initCodesPostaux() {
        ArrayList<String> listeCodesPostaux = VisiteurDao.getCodesPostaux();
        codesPostaux = listeCodesPostaux.toArray(new String[0]);
    }
}
