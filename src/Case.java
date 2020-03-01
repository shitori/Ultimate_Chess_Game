import java.io.Serializable;

public class Case implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int rangee;

    private int colonne;

    public Piece piece;

    int getRangee() {
        
        return this.rangee;
    }

    void setRangee(int value) {
        
        this.rangee = value;
    }

    int getColonne() {
        
        return this.colonne;
    }

    void setColonne(int value) {
        
        this.colonne = value;
    }
    
    public Case(int r, int c){
    	this.rangee=r;
    	this.colonne=c;
    	this.piece= new Vide();
    }
    
    public Case(int r, int c, Piece p){
    	this.rangee=r;
    	this.colonne=c;
    	this.piece=p;
    }
    
    public Case(Piece p){
    	this.piece=p;
    }
    
    public String toString (){
    	return "rangée: "+this.rangee+" / colonne: "+this.colonne;
    }

}
