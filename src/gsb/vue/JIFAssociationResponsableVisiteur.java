package gsb.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class JIFAssociationResponsableVisiteur extends JInternalFrame {

    private JComboBox<String> responsableDropdown;
    private JComboBox<String> visiteurDropdown;
    private JButton affecterButton;

    public JIFAssociationResponsableVisiteur() {
        super("Associer un Visiteur à un Responsable", true, true, true, true);
        setSize(500, 200);
        setLayout(new GridLayout(4, 2, 10, 10));

        // Composants de l'interface
        add(new JLabel("Matricule Responsable :"));
        responsableDropdown = new JComboBox<>();
        add(responsableDropdown);

        add(new JLabel("Matricule Visiteur :"));
        visiteurDropdown = new JComboBox<>();
        add(visiteurDropdown);

        affecterButton = new JButton("Affecter Responsable");
        JButton annulerButton = new JButton("Annuler");
        add(annulerButton);
        add(affecterButton);

        // Charger les visiteurs et responsables
        chargerPersonnes();

        // Écouteurs d'événements
        affecterButton.addActionListener(e -> affecterResponsable());
        annulerButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    // Charger les visiteurs et responsables via la procédure stockée
    private void chargerPersonnes() {
        try (Connection conn = getConnection();
             CallableStatement stmt = conn.prepareCall("{call GetAllVisiteurs}")) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String matricule = rs.getString("MATRICULE");
                String nom = rs.getString("NOM");
                String info = matricule + " - " + nom;

                responsableDropdown.addItem(info);
                visiteurDropdown.addItem(info);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur de chargement : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Associer un responsable via la procédure stockée
    private void affecterResponsable() {
        if (responsableDropdown.getSelectedItem() != null && visiteurDropdown.getSelectedItem() != null) {
            String matriculeResponsable = responsableDropdown.getSelectedItem().toString().split(" - ")[0];
            String matriculeVisiteur = visiteurDropdown.getSelectedItem().toString().split(" - ")[0];

            if (matriculeVisiteur.equals(matriculeResponsable)) {
                JOptionPane.showMessageDialog(this, "Un visiteur ne peut pas être son propre responsable !");
                return;
            }

            try (Connection conn = getConnection();
                 CallableStatement stmt = conn.prepareCall("{call AssignerResponsable(?, ?)}")) {

                stmt.setString(1, matriculeResponsable);
                stmt.setString(2, matriculeVisiteur);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Responsable assigné avec succès !");
                dispose();

            } catch (SQLException e) {
                if (e.getMessage().contains("Le visiteur et le responsable doivent appartenir à la même unité")) {
                    JOptionPane.showMessageDialog(this, "❌ Erreur : Le visiteur et son responsable doivent appartenir à la même unité.", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur d'affectation : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Connexion à SQL Server
    private Connection getConnection() throws SQLException {
    	String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=GSB;encrypt=false"; // url : chaine de connexion
        String user = "ADMIN_DB";
        String password = "password";
        try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return DriverManager.getConnection(url, user, password);
    }
}
