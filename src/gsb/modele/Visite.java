package gsb.modele;

public class Visite {
		private String reference;
	    private String dateVisite;
	    private String commentaire;
	    private String matriculeVisiteur;
	    private String codeMedecin;
	    private String medOffert1;
	    private int quantiteMed1;
	    private String medOffert2;
	    private int quantiteMed2;
	    private String ville;

	    // Constructeur
	    public Visite(String reference, String dateVisite, String commentaire, String matriculeVisiteur, String codeMedecin, String medOffert1, int quantiteMed1 ,String medOffert2, int quantiteMed2, String ville) {
	    	this.reference = reference;
	        this.dateVisite = dateVisite;
	        this.commentaire = commentaire;
	        this.matriculeVisiteur = matriculeVisiteur;
	        this.codeMedecin = codeMedecin;
	        this.medOffert1 = medOffert1;
	        this.quantiteMed1 = quantiteMed1;
	        this.medOffert2 = medOffert2;
	        this.quantiteMed2 = quantiteMed2;
	        this.ville = ville;
	    }
	    
	    public Visite(String reference, String dateVisite, String commentaire, String matriculeVisiteur, String codeMedecin, String medOffert1, int quantiteMed1 ,String medOffert2, int quantiteMed2) {
	    	this.reference = reference;
	        this.dateVisite = dateVisite;
	        this.commentaire = commentaire;
	        this.matriculeVisiteur = matriculeVisiteur;
	        this.codeMedecin = codeMedecin;
	        this.medOffert1 = medOffert1;
	        this.quantiteMed1 = quantiteMed1;
	        this.medOffert2 = medOffert2;
	        this.quantiteMed2 = quantiteMed2;
	    }	    
	    
	    public Visite(String reference, String dateVisite, String matriculeVisiteur, String codeMedecin) {
	    	this.reference = reference;
	        this.dateVisite = dateVisite;
	        this.matriculeVisiteur = matriculeVisiteur;
	        this.codeMedecin = codeMedecin;
	    }
	    
	    public Visite(String reference, String commentaire, String medOffert1, int quantiteMed1 ,String medOffert2, int quantiteMed2) {
	    	this.reference = reference;
	        this.commentaire = commentaire;
	        this.medOffert1 = medOffert1;
	        this.quantiteMed1 = quantiteMed1;
	        this.medOffert2 = medOffert2;
	        this.quantiteMed2 = quantiteMed2;
	    }

	    // Getters et setters
	    
		public String getReference() {
			return reference;
		}


		public void setReference(String reference) {
			this.reference = reference;
		}


		public String getDateVisite() {
			return dateVisite;
		}


		public void setDateVisite(String dateVisite) {
			this.dateVisite = dateVisite;
		}


		public String getCommentaire() {
			return commentaire;
		}


		public void setCommentaire(String commentaire) {
			this.commentaire = commentaire;
		}


		public String getMatriculeVisiteur() {
			return matriculeVisiteur;
		}


		public void setMatriculeVisiteur(String matriculeVisiteur) {
			this.matriculeVisiteur = matriculeVisiteur;
		}


		public String getCodeMedecin() {
			return codeMedecin;
		}


		public void setCodeMedecin(String codeMedecin) {
			this.codeMedecin = codeMedecin;
		}


		public String getMedOffert1() {
			return medOffert1;
		}


		public void setMedOffert1(String medOffert1) {
			this.medOffert1 = medOffert1;
		}


		public int getQuantiteMed1() {
			return quantiteMed1;
		}


		public void setQuantiteMed1(int quantiteMed1) {
			this.quantiteMed1 = quantiteMed1;
		}


		public String getMedOffert2() {
			return medOffert2;
		}


		public void setMedOffert2(String medOffert2) {
			this.medOffert2 = medOffert2;
		}


		public int getQuantiteMed2() {
			return quantiteMed2;
		}

		public void setQuantiteMed2(int quantiteMed2) {
			this.quantiteMed2 = quantiteMed2;
		}
		
		public String getVille() {
		    return ville;
		}

		public void setVille(String ville) {
		    this.ville = ville;
		}

		

	}



