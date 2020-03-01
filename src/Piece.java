import java.io.Serializable;

abstract public class Piece implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Couleur couleur;
    protected int paire;
    protected String nom;
   

    Couleur getCouleur() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.couleur;
    }

    void setCouleur(Couleur value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.couleur = value;
    }
    
    abstract Echequier move(Echequier unEchequier, Case caseDepart, Case caseArriver );
    
    
    public Piece(int p,Couleur c,String n){
    	this.couleur=c;
    	this.paire=p;
    	this.nom=n;
    }
    
  
}