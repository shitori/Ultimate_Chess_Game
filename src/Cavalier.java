import java.io.Serializable;

public class Cavalier extends Piece implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cavalier(int p, Couleur c, String n) {
		super(p, c, n);
	}

	public String toString(){
		if (this.couleur==Couleur.blanc)
			return "C"+this.paire+"b";
		else
			return "C"+this.paire+"n";
	}
	
	@Override
	Echequier move(Echequier unEchequier, Case caseDepart, Case caseArriver) {
		

		Piece pieceMove=unEchequier.tabCase[unEchequier.recupIndex(8-caseDepart.getRangee(), caseDepart.getColonne()-1)].piece;
		
		unEchequier.tabCase[unEchequier.recupIndex(8-caseDepart.getRangee(), caseDepart.getColonne()-1)].piece= new Vide();
		
		unEchequier.tabCase[unEchequier.recupIndex(8-caseArriver.getRangee(), caseArriver.getColonne()-1)].piece=pieceMove;
	
		
		return unEchequier;
	}
}
