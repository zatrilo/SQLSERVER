package gsb.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AssociationResponsableVisiteur extends JFrame {

    private JComboBox<String> responsableDropdown;
    private JComboBox<String> visiteurDropdown;
    private JLabel verificationLabel;
    private JButton affecterButton;

    public AssociationResponsableVisiteur() {
        setTitle("Association Responsable - Visiteur");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Composants de l'interface
        add(new JLabel("Matricule Responsable :"));
        responsableDropdown = new JComboBox<>();
        add(responsableDropdown);

        add(new JLabel("Matricule Visiteur :"));
        visiteurDropdown = new JComboBox<>();
        add(visiteurDropdown);

        verificationLabel = new JLabel("Vérification automatique : ???", SwingConstants.CENTER);
        verificationLabel.setForeground(Color.RED);
        add(verificationLabel);

        affecterButton = new JButton("Affecter Responsable");
        JButton annulerButton = new JButton("Annuler");
        add(annulerButton);
        add(affecterButton);

        // Charger les visiteurs et responsables
        chargerPersonnes();

        // Écouteur pour la vérification automatique d'unité
        visiteurDropdown.addActionListener(e -> verifierUnite());
        responsableDropdown.addActionListener(e -> verifierUnite());

        // Action du bouton "Affecter Responsable"
        affecterButton.addActionListener(e -> affecterResponsable());

        // Annuler et fermer la fenêtre
        annulerButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    // Charger les visiteurs sans responsable et les responsables
    private void chargerPersonnes() {
        String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=GSB;encrypt=false"; // url : chaine de connexion
        String user = "ADMIN_DB";
        String password = "password";
        // try permet d'essayer de lancer la connexion
        try {Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
            Connection cnx = DriverManager.getConnection(url, user, password); 
             Statement stmt = cnx.createStatement(); {

            // Charger les visiteurs (sans responsable)
            ResultSet rs = stmt.executeQuery("SELECT MATRICULE, NOM, CODEUNIT FROM visiteur WHERE MATRICULERESP IS NULL");
            while (rs.next()) {
                visiteurDropdown.addItem(rs.getString("MATRICULE") + " - " + rs.getString("NOM") + " [" + rs.getString("CODEUNIT") + "]");
            }

            // Charger les responsables (visiteurs déjà responsables)
            rs = stmt.executeQuery("SELECT MATRICULE, NOM, CODEUNIT FROM visiteur WHERE MATRICULERESP IS NOT NULL");
            while (rs.next()) {
                responsableDropdown.addItem(rs.getString("MATRICULE") + " - " + rs.getString("NOM") + " [" + rs.getString("CODEUNIT") + "]");
            }

        } }catch(Exception e) {
            System.out.println("Echec lors de la connexion à SQL Server : " + e.getMessage());}
    }

    // Vérification si le visiteur et le responsable sont de la même unité
    private void verifierUnite() {
        if (responsableDropdown.getSelectedItem() != null && visiteurDropdown.getSelectedItem() != null) {
            String visiteurInfo = visiteurDropdown.getSelectedItem().toString();
            String responsableInfo = responsableDropdown.getSelectedItem().toString();

            String codeUnitVisiteur = visiteurInfo.substring(visiteurInfo.indexOf("[") + 1, visiteurInfo.indexOf("]"));
            String codeUnitResponsable = responsableInfo.substring(responsableInfo.indexOf("[") + 1, responsableInfo.indexOf("]"));

            if (codeUnitVisiteur.equals(codeUnitResponsable)) {
                verificationLabel.setText("Vérification automatique : ✅ Unité OK");
                verificationLabel.setForeground(Color.GREEN);
                affecterButton.setEnabled(true);
            } else {
                verificationLabel.setText("Vérification automatique : ❌ Unités différentes !");
                verificationLabel.setForeground(Color.RED);
                affecterButton.setEnabled(false);
            }
        }
    }

    // Associer le visiteur au responsable
    private void affecterResponsable() {
        if (responsableDropdown.getSelectedItem() != null && visiteurDropdown.getSelectedItem() != null) {
            String matriculeResponsable = responsableDropdown.getSelectedItem().toString().split(" - ")[0];
            String matriculeVisiteur = visiteurDropdown.getSelectedItem().toString().split(" - ")[0];

            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=GSB;encrypt=false"; // url : chaine de connexion
            String user = "ADMIN_DB";
            String password = "password";
            // try permet d'essayer de lancer la connexion
            try {Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
                Connection cnx = DriverManager.getConnection(url, user, password); 
                 PreparedStatement pstmt = cnx.prepareStatement("UPDATE visiteur SET MATRICULERESP = ? WHERE MATRICULE = ?"); {

                pstmt.setString(1, matriculeResponsable);
                pstmt.setString(2, matriculeVisiteur);
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Responsable assigné avec succès !");
                dispose();

            } }catch(Exception e) {
                System.out.println("Echec lors de la connexion à SQL Server : " + e.getMessage());}
        }
    }

    public static void main(String[] args) {
        new AssociationResponsableVisiteur();
    }
}
