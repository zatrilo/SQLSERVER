package gsb.modele;

public class Medicament {
	
	
	private String code;
    private String nomCommercial;
    private String composition;
    private String effets;
    private String contreIndications;
    private Float echantillon;
    private String codeFamille;
    private String libelleFamille;
    
    
    
    public Medicament(String code, String nomCommercial, String composition, String effets,
    		String contreIndications,Float echantillon ,String codeFamille, String libelleFamille) {
		this.code = code;
		this.nomCommercial = nomCommercial;
		this.composition = composition;
		this.effets = effets;
		this.contreIndications = contreIndications;
		this.codeFamille = codeFamille;
		this.libelleFamille = libelleFamille;
		this.echantillon = echantillon;
				
    }

    
 // Getters and Setters
    
    
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNomCommercial() {
		return nomCommercial;
	}
	public void setNomCommercial(String nomCommercial) {
		this.nomCommercial = nomCommercial;
	}
	public String getComposition() {
		return composition;
	}
	public void setComposition(String composition) {
		this.composition = composition;
	}
	public String getEffets() {
		return effets;
	}
	public void setEffets(String effets) {
		this.effets = effets;
	}
	public String getContreIndications() {
		return contreIndications;
	}
	public void setContreIndications(String contreIndications) {
		this.contreIndications = contreIndications;
	}
	public String getCodeFamille() {
		return codeFamille;
	}
	public void setCodeFamille(String codeFamille) {
		this.codeFamille = codeFamille;
	}
	public String getLibelleFamille() {
		return libelleFamille;
	}
	public void setLibelleFamille(String libelleFamille) {
		this.libelleFamille = libelleFamille;
	}


	public Float getEchantillon() {
		return echantillon;
	}


	public void setEchantillon(Float echantillon) {
		this.echantillon = echantillon;
	}

}

