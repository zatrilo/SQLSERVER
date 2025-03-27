package gsb.vue;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JIFChangeMotDePasse extends JInternalFrame {

    private JComboBox<String> matriculeComboBox;
    private JTextField motDePasseActuelField;
    private JPasswordField nouveauMdpField;
    private JPasswordField confirmerMdpField;
    private JButton validerButton;
    private JLabel resultatLabel;
    private JLabel dureeVieLabel;  // Nouveau JLabel pour afficher la durée de vie du mot de passe

    public JIFChangeMotDePasse() {
        super("Changer le mot de passe", true, true, true, true);

        // Configuration du formulaire
        setLayout(new GridLayout(6, 2));  // Augmenter la grille pour ajouter la durée de vie

        // Ajouter une liste déroulante pour choisir le matricule
        add(new JLabel("Matricule :"));
        matriculeComboBox = new JComboBox<>(getListeMatricules());
        matriculeComboBox.addActionListener(e -> {
            String matricule = (String) matriculeComboBox.getSelectedItem();
            String motDePasseActuel = getMotDePasseActuel(matricule);
            motDePasseActuelField.setText(motDePasseActuel);

            // Afficher la durée de vie du mot de passe
            int dureeVie = getDureeVieMotDePasse(matricule);
            dureeVieLabel.setText("Durée de vie : " + (dureeVie != -1 ? dureeVie + " jours" : "Inconnu"));
        });
        add(matriculeComboBox);

        // Afficher le mot de passe actuel
        add(new JLabel("Mot de passe actuel :"));
        motDePasseActuelField = new JTextField(20);
        motDePasseActuelField.setEditable(false); // Ne pas éditer le mot de passe actuel
        add(motDePasseActuelField);

        // Saisie du nouveau mot de passe
        add(new JLabel("Nouveau Mot de Passe :"));
        nouveauMdpField = new JPasswordField(20);
        add(nouveauMdpField);

        // Confirmer le nouveau mot de passe
        add(new JLabel("Confirmer Mot de Passe :"));
        confirmerMdpField = new JPasswordField(20);
        add(confirmerMdpField);

        // Afficher la durée de vie du mot de passe
        add(new JLabel("Durée de vie du mot de passe :"));
        dureeVieLabel = new JLabel("Non disponible");
        add(dureeVieLabel);

        // Bouton de validation
        validerButton = new JButton("Valider");
        add(validerButton);

        // Label de résultat
        resultatLabel = new JLabel();
        add(resultatLabel);

        validerButton.addActionListener(e -> {
            String matricule = (String) matriculeComboBox.getSelectedItem();
            String motDePasseActuel = motDePasseActuelField.getText();
            String nouveauMdp = new String(nouveauMdpField.getPassword());
            String confirmerMdp = new String(confirmerMdpField.getPassword());

            // Vérification que le nouveau mot de passe et sa confirmation sont identiques
            if (!nouveauMdp.equals(confirmerMdp)) {
                resultatLabel.setText("Les mots de passe ne correspondent pas");
            } else {
                String message = changerMotDePasse(matricule, motDePasseActuel, nouveauMdp);
                resultatLabel.setText(message);
            }
        });

        setSize(400, 250);
        setVisible(true);
    }
    
    // Méthode pour obtenir la liste des matricules des visiteurs depuis SQL Server
    private String[] getListeMatricules() {
        List<String> matricules = new ArrayList<>();
        String sql = "{CALL GetListeMatricules}";  // Appel de la procédure stockée

        try (Connection conn = getConnection(); 
             CallableStatement stmt = conn.prepareCall(sql); 
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                matricules.add(rs.getString("matricule"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convertir la liste en tableau et le retourner
        return matricules.toArray(new String[0]);
    }

    // Méthode pour obtenir le mot de passe actuel d'un visiteur via une procédure stockée
    private String getMotDePasseActuel(String matricule) {
        String motDePasse = "";
        String sql = "{CALL GetMotDePasseActuel(?)}";  // Appel de la procédure stockée

        try (Connection conn = getConnection(); 
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, matricule);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                motDePasse = rs.getString("mdp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return motDePasse;
    }

    // Méthode pour obtenir la durée de vie du mot de passe
    private int getDureeVieMotDePasse(String matricule) {
        int duree = -1;  // Valeur par défaut si la durée de vie n'est pas trouvée
        String sql = "{CALL duree_vie_mdp(?, ?)}";  // Appel de la procédure stockée

        try (Connection conn = getConnection(); 
             CallableStatement stmt = conn.prepareCall(sql)) {

            // Définir les paramètres de la procédure
            stmt.setString(1, matricule);  // Passer le matricule
            stmt.registerOutParameter(2, Types.INTEGER);  // Enregistrer le paramètre de sortie

            // Exécuter la procédure
            stmt.execute();

            // Récupérer la durée de vie du mot de passe
            duree = stmt.getInt(2);  // Récupérer la durée (en jours)

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return duree;
    }

    // Méthode pour obtenir la connexion à la base de données
    private Connection getConnection() throws SQLException {
        String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=GSBV2;encrypt=false";
        String user = "ADMIN_DB";
        String password = "password";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, password);
    }

    // Méthode pour changer le mot de passe via la procédure stockée
    public String changerMotDePasse(String matricule, String motDePasseActuel, String nouveauMdp) {
        String sql = "{CALL changer_mdp(?, ?, ?)}";  // Appel de la procédure stockée
        String resultat = "";

        try (Connection conn = getConnection(); 
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, matricule);
            stmt.setString(2, nouveauMdp);
            stmt.registerOutParameter(3, Types.VARCHAR);

            stmt.execute();

            resultat = stmt.getString(3);  // Récupérer le message de sortie
        } catch (SQLException e) {
            e.printStackTrace();
            resultat = "Erreur lors du changement de mot de passe";
        }

        return resultat;
    }
}
