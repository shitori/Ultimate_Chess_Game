import java.io.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Partie implements Serializable {
    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Echequier unEchequier ;

    private Arbitre arbitre;

    private Joueur joueur1;
    private Joueur joueur2;
    
    private String historique;
    private int tour;

    private Affichage a;
    
    public Partie(){
    	this.setEchequier(new Echequier());
    	this.setArbitre(new Arbitre());
    	this.setJoueur1(new Joueur());
    	this.setJoueur2(new Joueur());
    	this.setHistorique("");
    	this.tour=0;
    	

    }
    
    

	public Partie(Echequier e,Joueur j1,Joueur j2,Arbitre a){
    	this.setArbitre(a);
    	this.setJoueur1(j1);
    	this.setJoueur2(j2);
    	this.setEchequier(e);
    	this.setHistorique("Liste de coup effectuer :");
    	this.tour=1;
    	this.a= new Affichage();
    	
    }
	
	public Partie(Partie p){
    	this.setArbitre(p.arbitre);
    	this.setJoueur1(p.joueur1);
    	this.setJoueur2(p.joueur2);
    	this.setEchequier(p.unEchequier);
    	this.setHistorique(p.historique);
    	this.tour=p.tour;
    	this.a= new Affichage();
    	
    }
	
    public void initialiser() {
    	int c=1;
    	int r=1;
    	for(int i=0;i<64;i++){
    		if(c>8){
    			c=1;
    			r++;
    		}
    		this.unEchequier.tabCase[i] = new Case(9-r,c) ;
    		c++;
    	}
		
    	for(int Pb = 8;Pb<16;Pb++){
    		
    		this.unEchequier.tabCase[Pb].piece = new Pion(Pb-7,Couleur.noir,"pion");
    	}
    	
    	for(int Pn = 48;Pn<56;Pn++){
    		
    		this.unEchequier.tabCase[Pn].piece = new Pion(Pn-47,Couleur.blanc,"pion");
    	}
    	
    	
    	this.unEchequier.tabCase[0].piece=new Tour(1,Couleur.noir,"tour");
    	this.unEchequier.tabCase[7].piece=new Tour(2,Couleur.noir,"tour");
    	this.unEchequier.tabCase[56].piece=new Tour(1,Couleur.blanc,"tour");
    	this.unEchequier.tabCase[63].piece=new Tour(2,Couleur.blanc,"tour");
    	
    	this.unEchequier.tabCase[1].piece=new Cavalier(1,Couleur.noir,"cavalier");
    	this.unEchequier.tabCase[6].piece=new Cavalier(2,Couleur.noir,"cavalier");
    	this.unEchequier.tabCase[57].piece=new Cavalier(1,Couleur.blanc,"cavalier");
    	this.unEchequier.tabCase[62].piece=new Cavalier(2,Couleur.blanc,"cavalier");
    	
    	this.unEchequier.tabCase[2].piece=new Fou(1,Couleur.noir,"fou");
    	this.unEchequier.tabCase[5].piece=new Fou(2,Couleur.noir,"fou");
    	this.unEchequier.tabCase[58].piece=new Fou(1,Couleur.blanc,"fou");
    	this.unEchequier.tabCase[61].piece=new Fou(2,Couleur.blanc,"fou");
    	
    	this.unEchequier.tabCase[4].piece=new Roi(1,Couleur.noir,"roi");
    	this.unEchequier.tabCase[60].piece=new Roi(1,Couleur.blanc,"roi");
    	
    	this.unEchequier.tabCase[3].piece=new Dame(1,Couleur.noir,"dame");
    	this.unEchequier.tabCase[59].piece=new Dame(1,Couleur.blanc,"dame");
    	
    }

    public void jouer(Joueur joueurActuel,Piece pieceV2,Case caseDepart,Case caseArriver) {
    	
    	Piece pieceMouvement= pieceV2;
    	
    	
    	if (this.arbitre.verifierDisparitionRoi(this.getEchequier(),caseArriver)== true){
    		
			this.arbitre.verifierEtMatFast(joueurActuel);
			
		
	}

    		this.setEchequier(pieceMouvement.move(this.getEchequier(),caseDepart,caseArriver));
    		
    		
    		
    		this.historique=this.historique+"\nTour "+this.getTour()+": "+joueurActuel.getNom()+" � d�placer "+pieceMouvement.nom+" "+pieceMouvement.paire+" de la case "+caseDepart.getRangee()+" "+convertionChiffre(caseDepart.getColonne())+" vers la case "+caseArriver.getRangee()+" "+convertionChiffre(caseArriver.getColonne());
    	
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
    
    
   public int colonneDepart(Piece piece){
	   for(int i=0;i<64;i++){
		   if(this.unEchequier.tabCase[i].piece.couleur.equals(piece.couleur) 
				   && this.unEchequier.tabCase[i].piece.nom.equals(piece.nom) 
				   		&& this.unEchequier.tabCase[i].piece.paire==piece.paire){
			   return this.unEchequier.tabCase[i].getColonne();
		   }
	   }
    	return -1;
    }
	   
    
    public int rangeeDepart(Piece piece){
    	for(int i=0;i<64;i++){
		   if(this.unEchequier.tabCase[i].piece.couleur.equals(piece.couleur) 
				   && this.unEchequier.tabCase[i].piece.nom.equals(piece.nom) 
				   		&& this.unEchequier.tabCase[i].piece.paire==piece.paire){
			   return this.unEchequier.tabCase[i].getRangee();
		   }
	   }
    	return -1;
    }
    
    
    public void sauvegarder() throws IOException {
    	 final String chemin1 = "./SauvegardeDernierePartieEchequier.ser";
         final File fichierEchequier =new File(chemin1); 
 
         final String chemin3 = "./SauvegardeDernierePartieListeCoup.txt";
         final File fichierCoup =new File(chemin3); 
         
         final String chemin4 = "./SauvegardeDernierePartieTour.txt";
         final File fichierTour =new File(chemin4); 
        
         ObjectOutputStream saveObj = null;
             FileWriter writer3 = null;
             FileWriter writer4 = null;
			try {
				saveObj= new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fichierEchequier)));
				saveObj.writeObject(new Partie(this.unEchequier,this.joueur1,this.joueur2, new Arbitre()));
				writer3 = new FileWriter(fichierCoup);
				writer4 = new FileWriter(fichierTour);
				writer3.write(this.historique);
				writer4.write(this.tour+"/"+"num tour");
			} catch (IOException e) {
				
				e.printStackTrace();
			}finally{
				System.out.println("Sauvegarde termin�.");
				
				 writer3.close();
				 writer4.close();
				 saveObj.close();
			}
 
    }
    
    public void charger(){
    	final String chemin1 = "./SauvegardeDernierePartieEchequier.ser";
        final File fichierEchequier =new File(chemin1); 
        
        final String chemin3 = "./SauvegardeDernierePartieListeCoup.txt";
        final File fichierCoup =new File(chemin3); 
        
        final String chemin4 = "./SauvegardeDernierePartieTour.txt";
        final File fichierTour =new File(chemin4); 
        
        ObjectInputStream loadObj= null;
        Partie chargement = null;
        try{
        	try {
				loadObj= new ObjectInputStream(new BufferedInputStream(new FileInputStream(fichierEchequier)));
			} catch (IOException e) {
				e.printStackTrace();
			}
        	 chargement= (Partie)loadObj.readObject();
        } catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}finally{
        	try {
				loadObj.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        this.unEchequier= new Echequier(chargement.unEchequier);
        this.joueur1= new Joueur(chargement.joueur1);
        this.joueur2= new Joueur(chargement.joueur2);

        
        

        try (BufferedReader br= new BufferedReader(new FileReader(fichierCoup))){
	
	
        	String ligne;
	
        	while((ligne= br.readLine()) != null){
        			this.historique= this.historique+"\n"+ligne;
        	}
        } catch (IOException e) {
	
		e.printStackTrace();
        }

        try (BufferedReader br= new BufferedReader(new FileReader(fichierTour))){
	
	
        	String ligne;
	
        	while((ligne= br.readLine()) != null){
        		String[] parties= ligne.split("/");
        		int t= Integer.parseInt(parties[0]);
        		this.tour= t;
        	}
        } catch (IOException e) {
	
        	e.printStackTrace();
        }
        
        
        
        
        
    }
    
    
    
    public Joueur getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}

	public String getHistorique() {
		return historique;
	}

	public void setHistorique(String historique) {
		this.historique = historique;
	}

	public int getTour() {
		return tour;
	}

	public void setTour() {
		this.tour = tour+1;
	}
	
	public Echequier getEchequier(){
		return this.unEchequier;
	}
	
	public void setEchequier(Echequier echequier) {
    	this.unEchequier= new Echequier(echequier);	
	}



	public Arbitre getArbitre() {
		return arbitre;
	}



	public void setArbitre(Arbitre arbitre) {
		this.arbitre = arbitre;
	}



	public Affichage getA() {
		return a;
	}



	public void setA(Affichage a) {
		this.a = a;
	}

    
}
