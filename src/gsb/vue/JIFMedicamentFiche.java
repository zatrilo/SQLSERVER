/*
 * Crééé le 17 nov. 2021
 *
 * TODO Pour changer le modele de ce fichier genere allez :
 * Fenetre - Preferences - Java - Style de code - Modeles de code
 */
package gsb.vue;



import gsb.modele.Medicament;

public class JIFMedicamentFiche extends JIFMedicament {
	public JIFMedicamentFiche(MenuPrincipal uneFenetreContainer, Medicament unMedicament) {
		super(uneFenetreContainer);
		this.remplirText(unMedicament);

	}
	

}

