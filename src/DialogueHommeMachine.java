import java.util.Scanner;

public class DialogueHommeMachine  {
	
	private Scanner sc;
	private Scanner scj1;
	private Scanner scj2;
	private Scanner scColonne;
	private Scanner scRangee;
	private Scanner scPaire;
	private Scanner scPiece;
	
	private Partie p;
	private Affichage a;
	
	public DialogueHommeMachine(){
		
	}

	public DialogueHommeMachine(Partie p) {
		this.setP(new Partie (p));
		this.setA(new Affichage());
	}

	public String demanderChargement() {
		sc= new Scanner(System.in);
		System.out.println("Voulez vous chargez la partie précedente?(Tapez uniquement oui ou non)");
		String str = sc.nextLine();
		return str;
	}
	
    public void demanderNom(Joueur joueur1, Joueur joueur2) {
    	
    	System.out.println("Veuillez saisir le nom du joueur 1 :");
		scj1 = new Scanner(System.in);
		joueur1.setNom(scj1.nextLine());
		
		System.out.println("Veuillez saisir le nom du joueur 2 :");
		scj2 = new Scanner(System.in);
		joueur2.setNom(scj2.nextLine());
		
		System.out.println("Le joueur 1 s'appelle "+joueur1.getNom()+" et le joueur 2 s'appelle "+joueur2.getNom()+".");
    }
    
    public Couleur demanderCouleur() {
    	System.out.println("Veuillez saisir la couleur des pièces du joueur 1:");
    	sc = new Scanner(System.in);
    	String str = sc.nextLine();	
    	
    	if(str.equals("noir") || str.equals("blanc")){
    		if (str.equals("noir")){
    			System.out.println("Le joueur 1 possède les pièces noires et le joueur 2 les pièces blanches");
    			return Couleur.noir;}
    		else{
    			System.out.println("Le joueur 1 possède les pièces blanches et le joueur 2 les pièces noires");
    			return Couleur.blanc;}
    	}
    	
    	else
    		return null;
    }

	public void protocoleJoueur1() {
		System.out.println("Tour "+getP().getTour()+"\nC'est au tour de "+getP().getJoueur1().getNom());
		System.out.println(getA().jeu(getP().getEchequier()));
		getA().historiqueCoup(getP().getHistorique());
		demandeInfoJoueur(getP().getJoueur1());
			
	}

	

	public void protocoleJoueur2() {
		System.out.println("Tour "+getP().getTour()+"\nC'est au tour de "+getP().getJoueur2().getNom());
		System.out.println(getA().jeu(getP().getEchequier()));
		getA().historiqueCoup(getP().getHistorique());
		demandeInfoJoueur(getP().getJoueur2());
		
	}

	
	private void demandeInfoJoueur(Joueur joueurActuel) {
	
    	String strPiece= demanderPiece(joueurActuel);
    	Piece pieceV1 = p.getArbitre().existancePiece(p.getEchequier(),strPiece,joueurActuel);
    	int strPaire= demanderPaire(joueurActuel,pieceV1);
    	Piece pieceV2 = p.getArbitre().existancePaire(p.getEchequier(),pieceV1,strPaire);
    	int strRangee= demanderRangee();
    	String strColonne= demanderColonne();
    	Case caseArriver = new Case(strRangee,convertionLettre(strColonne));
    	
    	int rDepart= p.rangeeDepart(pieceV2);
		int cDepart= p.colonneDepart(pieceV2);
		Case caseDepart = new Case(rDepart,cDepart);
    	while(p.getArbitre().verifCouleurPiece(p.getEchequier(),caseArriver,joueurActuel)==true){
    		 strRangee= demanderRangee();
        	 strColonne= demanderColonne();
        	 caseArriver = new Case(strRangee,convertionLettre(strColonne));
    	}
    	
    	while(p.getArbitre().validerCoup(p.getEchequier(), caseDepart, caseArriver, pieceV2)==false){
    		System.out.println("L'arbitre "+ p.getArbitre().getNomArbitre()+" ne valide pas ce coup selon les règles du jeu des echecs.");
    		 strPiece= demanderPiece(joueurActuel);
    		 pieceV1 = p.getArbitre().existancePiece(p.getEchequier(),strPiece,joueurActuel);
        	 strPaire= demanderPaire(joueurActuel,pieceV1);
        	 pieceV2 = p.getArbitre().existancePaire(p.getEchequier(),pieceV1,strPaire);
        	 strRangee= demanderRangee();
        	 strColonne= demanderColonne();
        	 caseArriver = new Case(strRangee,convertionLettre(strColonne));
        	 rDepart= p.rangeeDepart(pieceV2);
    		 cDepart= p.colonneDepart(pieceV2);
    		 caseDepart = new Case(rDepart,cDepart);
        	while(p.getArbitre().verifCouleurPiece(p.getEchequier(),caseArriver,joueurActuel)==true){
       		 strRangee= demanderRangee();
           	 strColonne= demanderColonne();
           	 caseArriver = new Case(strRangee,convertionLettre(strColonne));
        	}
    	}
    	
    	p.jouer(joueurActuel,pieceV2,caseDepart,caseArriver);
	}

	

	public String demanderPiece(Joueur joueurActuel) {
		scPiece = new Scanner(System.in);
		System.out.println("Quelle type de pièce souhaiter vous déplacer ?(Si vous voulez abandonner c'est maintenant en écrivant abandon)");
		String strPiece = scPiece.nextLine();
		if(strPiece.equals("abandon")){
			getA().perdant(joueurActuel);
		}
		while (p.getArbitre().existancePiece(p.getEchequier(),strPiece,joueurActuel)==null){
			System.out.println("Erreur: vous ne posseder plus ce type de piece sur l'echequier.");
			
			System.out.println("Quelle type pièce souhaiter vous déplacer ?");
			strPiece = scPiece.nextLine();
		}
		return strPiece;
		
	}

	public int demanderPaire(Joueur joueurActuel, Piece pieceV1) {
		scPaire = new Scanner(System.in);
		System.out.println("Quelle doublon de cette piece souhaiter vous déplacer ?(si il n'y qu'un exemplaire de cette pièce (exemple:le roi) ecrivez quand même: 1)");
		int strPaire = scPaire.nextInt();
		
		while (p.getArbitre().existancePaire(p.getEchequier(),pieceV1,strPaire)==null){
			System.out.println("Erreur: vous ne posseder plus ce doublon de cette piece sur l'echequier.");
			
    		System.out.println("Quelle doublon de cette piece souhaiter vous déplacer ?(si il n'y qu'un exemplaire de cette pièce (exemple:le roi) ecrivez quand même: 1)");
			strPaire = scPaire.nextInt();
		}
		return strPaire;
	}
	
	
	public String demanderColonne() {
		scColonne = new Scanner(System.in);
		System.out.println("Dans quelle colonne souhaiter vous la déplacer ?(écrire une lettre en MAJUSCULE entre A à H)");
		String strColonne = scColonne.nextLine();
		
		while (p.getArbitre().indiceValide(p.convertionLettre(strColonne))==false){
			System.out.println("Erreur d'indice.");
			
			System.out.println("Dans quelle colonne souhaiter vous la déplacer ?(écrire une lettre en MAJUSCULE entre A à H)");
    		strColonne = scColonne.nextLine();
		}
		return strColonne;
	}

	public int demanderRangee() {
		scRangee = new Scanner(System.in);
		System.out.println("Dans quelle rangée souhaiter vous la déplacer ?(écrire un chiffre entre 1 à 8)");
		int strRangee = scRangee.nextInt();
		
		while (p.getArbitre().indiceValide(strRangee)==false){
			System.out.println("Erreur d'indice.");
			
   		
   		System.out.println("Dans quelle rangée souhaiter vous la déplacer ?(écrire un chiffre entre 1 à 8)");
   		strRangee = scRangee.nextInt();
		}
		return strRangee;
	}
	
	
	
	
	
	
	
	
	   public int convertionLettre(String Lettre){
	    	if(Lettre.equals("A"))
	    		return 1;
	    	if(Lettre.equals("B"))
	    		return 2;
	    	if(Lettre.equals("C"))
	    		return 3;
	    	if(Lettre.equals("D"))
	    		return 4;
	    	if(Lettre.equals("E"))
	    		return 5;
	    	if(Lettre.equals("F"))
	    		return 6;
	    	if(Lettre.equals("G"))
	    		return 7;
	    	if(Lettre.equals("H"))
	    		return 8;
	    	return 0;
	    	
	    }
	    
	    public String convertionChiffre(int Chiffre){
	    	if(Chiffre==1)
	    		return "A";
	    	if(Chiffre==2)
	    		return "B";
	    	if(Chiffre==3)
	    		return "C";
	    	if(Chiffre==4)
	    		return "D";
	    	if(Chiffre==5)
	    		return "E";
	    	if(Chiffre==6)
	    		return "F";
	    	if(Chiffre==7)
	    		return "G";
	    	if(Chiffre==8)
	    		return "H";    	
	    	return null;
	    	
	    }

	
	public Partie getP() {
		return p;
	}

	public void setP(Partie p) {
		this.p = p;
	}

	public Affichage getA() {
		return a;
	}

	public void setA(Affichage a) {
		this.a = a;
	}
	

	
}
