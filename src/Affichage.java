import java.io.Serializable;

public class Affichage implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Affichage (){
		
	}
	
    public String jeu(Echequier unEchequier) {
    	String s="\n";
    	for (int a=0; a<10; a++){
    		s=s+"********";
    	}
    	
    	s=s+"*\n";
    	
    	for (int b=0; b<10; b++){
    		
    		for (int c=0; c<10; c++){
    			s=s+"*       ";
    		}
    		s=s+"*\n";
    		
    		for (int d=0; d<10; d++){
    			
    			if (b==0 && d==0)
    				s=s+"*       ";
    			else if (b==0 && d==9)
    				s=s+"*       ";
    			else if (b==9 && d==0)
    				s=s+"*       ";
    			else if (b==9 && d==9)
    				s=s+"*       ";
    			
    			else if(b==0){
    				s=s+"*   "+afficherColonne(d)+"   ";
    			}
    			else if(b==9){
    				s=s+"*   "+afficherColonne(d)+"   ";    			
    			}
    		
    			else if (d==0){
    				s=s+"*   "+(9-b)+"   ";  
    			}
    			
    			else if (d==9){
    				s=s+"*   "+(9-b)+"   ";  
    			}
    			
    			else{
    				s=s+"*  "+unEchequier.tabCase[unEchequier.recupIndex(b-1, d-1)].piece.toString()+"  ";
    				
    			}
    			
    		}
    		s=s+"*\n";

    		for (int e=0; e<10; e++){
    			s=s+"*       ";
    		}
    		s=s+"*\n";
    		
    		for (int f=0; f<10; f++){
        		s=s+"********";
        	}
        	s=s+"*\n";
    	}
		return s;
    }
    
    private String afficherColonne(int Colonne) {
    	if(Colonne==1)
    		return "A";
    	if(Colonne==2)
    		return "B";
    	if(Colonne==3)
    		return "C";
    	if(Colonne==4)
    		return "D";
    	if(Colonne==5)
    		return "E";
    	if(Colonne==6)
    		return "F";
    	if(Colonne==7)
    		return "G";
    	if(Colonne==8)
    		return "H";    	
    	return null;	
	}
	
	public void checkPartie(Partie p){
		System.out.println(p.getJoueur1()+"/"+p.getJoueur2());
		check(p.getEchequier());
	}
	
    public void check(Echequier tab ){
    	for(int i=0;i<64;i++){
    		System.out.println("Case numero "+i+"/rangée: "+tab.tabCase[i].getRangee()+"/colonne: "+tab.tabCase[i].getColonne()+"/"+ tab.tabCase[i].piece.toString()+"/"+tab.tabCase[i].piece.nom+"/"+tab.tabCase[i].piece.couleur+"/"+tab.tabCase[i].piece.paire);
    	}
    }
    
    public void historiqueCoup(String move){
    	System.out.println(move);
    }

	public void quitterJeu() {
		System.out.println("Réponse non reconue.");
		System.exit(0);
		
	}

	public void perdant(Joueur joueurActuel) {
		System.out.println(joueurActuel.getNom()+" a abandonné, la partie est terminé.");
		System.exit(0);
		
	}

	public void winner(Joueur gagnant)throws NullPointerException {
		if(gagnant==null){
			throw new NullPointerException("c quoi ce bordel");
		}
		
		System.out.println("Félicitation "+gagnant.getNom()+", vous avez gagné.");
		System.exit(0);
		
		
	}

}
