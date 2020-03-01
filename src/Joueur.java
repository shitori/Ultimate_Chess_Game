import java.io.Serializable;

public class Joueur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Couleur couleur;
    
    private String nom;


   public Couleur getCouleur() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.couleur;
    }

    public void setCouleur(Couleur value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.couleur = value;
    }

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Joueur(String n, Couleur c){
		this.nom=n;
		this.couleur=c;
	}
	
	public Joueur(){
		this.nom="default";
		this.couleur=Couleur.invisible;
	}
	
	public Joueur(Joueur j){
		this.nom=j.nom;
		this.couleur=j.couleur;
	}
	
	public String toString (){
		return "Bonjour je m'appelle "+this.nom+" et je suis le joueur "+this.couleur+"."; 
	}

}
