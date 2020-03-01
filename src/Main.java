import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		Partie partieInitMain= new Partie();
		Joueur j1 = new Joueur();
		Joueur j2 = new Joueur();
		Echequier echequier = new Echequier();
		Affichage a = new Affichage();
		Arbitre arbitre = new Arbitre();
		DialogueHommeMachine dhm= new DialogueHommeMachine();
		String infoChargement=dhm.demanderChargement();
		
		if(infoChargement.equals("oui")){
			partieInitMain.charger();
			
		}
		else if(infoChargement.equals("non")){
			dhm.demanderNom(j1, j2);
			Couleur infoCouleur= dhm.demanderCouleur();
			while (infoCouleur==null){
				System.out.println("Erreur de saisie; tapez noir ou blanc");
				infoCouleur= dhm.demanderCouleur();
			}
			if (infoCouleur.equals(Couleur.noir)){
				j1.setCouleur(Couleur.noir);
				j2.setCouleur(Couleur.blanc);
			}
			else{
				j1.setCouleur(Couleur.blanc);
				j2.setCouleur(Couleur.noir);
			}
			partieInitMain= new Partie(echequier,j1,j2,arbitre);
			partieInitMain.initialiser();
		}
		else{
			a.quitterJeu();
		}
		dhm= new DialogueHommeMachine(partieInitMain);
		
		Piece roiJ1= dhm.getP().getArbitre().existancePiece(dhm.getP().getEchequier(),"roi", dhm.getP().getJoueur1());
		Piece roiJ2= dhm.getP().getArbitre().existancePiece(dhm.getP().getEchequier(),"roi", dhm.getP().getJoueur2());
		while ( arbitre.verifierEtMat(dhm.getP().getJoueur2(),dhm.getP().getJoueur1(),roiJ1,dhm.getP().getEchequier())== false 
				&& arbitre.verifierEtMat(dhm.getP().getJoueur1(),dhm.getP().getJoueur2(),roiJ2,dhm.getP().getEchequier())== false){ 
			
				if(dhm.getP().getTour()%2==0){
					dhm.protocoleJoueur1();
				}
				else{
					dhm.protocoleJoueur2();
				}
				dhm.getP().setTour();
				dhm.getP().sauvegarder();
			}
	}
}
