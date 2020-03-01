import java.io.Serializable;

public class Echequier implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Case [] tabCase;
	
	public Echequier(){
		this.tabCase = new Case[64];
	}
	
	public Echequier(Echequier unEchequier){
		this.tabCase= unEchequier.tabCase;
	}
	
	public int recupIndex(int rangee,int colonne){
		//System.out.println((8*rangee)+colonne);
		return (8*rangee)+colonne;
	}
	
	public String toString(){
		return "je suis un echequier"+this.tabCase;
	}
	
	
}
