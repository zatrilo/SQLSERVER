package gsb.tests;

/**import gsb.modele.Visite;
import gsb.vue.JIFListeVisites;
import gsb.vue.MenuPrincipal;



public class JIFListeVisitesTest {

    public static void main(String[] args) {
        MenuPrincipal mockMenuPrincipal = new MenuPrincipal();
        JIFListeVisites jifListeVisites = new JIFListeVisites(mockMenuPrincipal);

        System.out.println("Test 1 : Initialisation de l'interface");
        testInitialisationInterface(jifListeVisites);

        System.out.println("Test 2 : Chargement des visites");
        testChargerVisites(jifListeVisites);

        System.out.println("Test 3 : Filtrage des visites");
        testFiltrerVisites(jifListeVisites);
    }

    private static void testInitialisationInterface(JIFListeVisites jifListeVisites) {
        if (jifListeVisites.panelPrincipal != null
                && jifListeVisites.comboCodeVisiteur != null
                && jifListeVisites.comboDateVisite != null
                && jifListeVisites.table != null) {
            System.out.println("-> OK : Les composants sont bien initialisés.");
        } else {
            System.out.println("-> ERREUR : Certains composants ne sont pas initialisés.");
        }
    }

    private static void testChargerVisites(JIFListeVisites jifListeVisites) {
        Visite visite1 = new Visite("REF001", "V001", "M001", "2023-10-01", "Paris");
        Visite visite2 = new Visite("REF002", "V002", "M002", "2023-10-02", "Lyon");

        jifListeVisites.listeVisites.put(visite1.getReference(), visite1);
        jifListeVisites.listeVisites.put(visite2.getReference(), visite2);

        int nbVisites = jifListeVisites.chargerVisites();

        if (nbVisites == 2) {
            System.out.println("-> OK : Les visites sont correctement chargées.");
        } else {
            System.out.println("-> ERREUR : Le chargement des visites a échoué.");
        }
    }

    private static void testFiltrerVisites(JIFListeVisites jifListeVisites) {
        Visite visite1 = new Visite("REF001", "V001", "M001", "2023-10-01", "Paris");
        Visite visite2 = new Visite("REF002", "V002", "M002", "2023-10-02", "Lyon");
        Visite visite3 = new Visite("REF003", "V001", "M003", "2023-10-01", "Nice");

        jifListeVisites.listeVisites.put(visite1.getReference(), visite1);
        jifListeVisites.listeVisites.put(visite2.getReference(), visite2);
        jifListeVisites.listeVisites.put(visite3.getReference(), visite3);

        // Simule la sélection d'un visiteur et d'une date
        jifListeVisites.comboCodeVisiteur.setSelectedItem("V001");
        jifListeVisites.comboDateVisite.setSelectedItem("2023-10-01");

        jifListeVisites.filtrerVisites();

        int nbLignes = jifListeVisites.tableModel.getRowCount();
        if (nbLignes == 2) {
            System.out.println("-> OK : Le filtrage a retourné les bonnes visites.");
        } else {
            System.out.println("-> ERREUR : Le filtrage n'est pas correct.");
        }
    }
}
**/