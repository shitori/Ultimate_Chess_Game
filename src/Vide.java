import java.io.Serializable;

public class Vide extends Piece implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Vide() {
		super(1, Couleur.invisible, "vide");
	}

	public String toString(){
		return "   ";
	}



	@Override
	Echequier move(Echequier unEchequier, Case caseDepart, Case caseArriver ) {
		System.out.println("Déplacement imposossible!");
		return null;
	}

}
