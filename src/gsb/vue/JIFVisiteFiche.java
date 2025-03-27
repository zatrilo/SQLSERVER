/*
 * Crééé le 17 nov. 2021
 *
 * TODO Pour changer le modele de ce fichier genere allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
package gsb.vue;



import gsb.modele.Medicament;
import gsb.modele.Visite;

/**
 * @author Isabelle
 * 17 nov. 2021
 * TODO Pour changer le modeele de ce commentaire de type genere, allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
public class JIFVisiteFiche extends JIFVisite {
	public JIFVisiteFiche(MenuPrincipal uneFenetreContainer, Visite uneVisite) {
		super(uneFenetreContainer, uneVisite);
		this.remplirText(uneVisite);

	}

	
	

}