package gsb.modele;

import java.util.Date;

public class Visiteur {
	protected String matricule ;
	protected String nom;
	protected String prenom;
	protected String login;
	protected String mdp;
	protected String telephone;
	protected String adresse;
	protected String codepostal;
	protected String dateentree ;
	protected Float prime ;
	protected String codeunit;
	protected String nomunit;

	public Visiteur(String matricule, String nom, String prenom,
			String login, String mdp,String telephone, String adresse,
			String codepostal,String dateentree,Float prime , String codeunit,String nomunit) {
		this.matricule = matricule;
		this.nom =nom;
		this.prenom = prenom;
		this.login = login;
		this.mdp = mdp;
		this.telephone = telephone;
		this.adresse = adresse;
		this.codepostal = codepostal;
		this.dateentree = dateentree;
		this.prime = prime;
		this.codeunit = codeunit;
		this.nomunit = nomunit;
	}





	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodepostal() {
		return codepostal;
	}

	public void setCodepostal(String codepostal) {
		this.codepostal = codepostal;
	}

	public String getDateentree() {
		return dateentree;
	}

	public void setDateentree(String dateentree) {
		this.dateentree = dateentree;
	}
	
	public Float getPrime() {
		return prime;
	}

	public void setPrime(Float prime) {
		this.prime = prime;
	}

	public String getCodeunit() {
		return codeunit;
	}

	public void setCodeunit(String codeunit) {
		this.codeunit = codeunit;
	}

	public String getNomunit() {
		return nomunit;
	}

	public void setNomunit(String nomunit) {
		this.nomunit = nomunit;
	}

	
	
	

	
	
	

}
