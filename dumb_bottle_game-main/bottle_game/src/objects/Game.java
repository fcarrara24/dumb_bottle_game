package objects;

import graphics.*;

import java.awt.Color;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class Game {
	
	List<Integer> colori;
	public List<Color> coloriIniziali;
	List<Bottle> bottiglie;
	int[]filler = new int[4];
	Scanner sc=new Scanner(System.in);
	ContenitoreConOggettiGrafici grafica;
	public int primoElemento;
	public int secondoElemento;
	
	public Game(){
		newMatch();
		
	}
	
	
	/**
	 * controlla se tutte le bottiglie sono ugualmente riempite
	 * @return
	 */
	public boolean victory() {
		for (int i=0; i<bottiglie.size(); i++) {
			if(!bottiglie.get(i).allItemsEquals()) {
				turnSelector();
				return false;
			}
		}
		System.out.println("complimenti, hai vinto!!");
		return true;
	}



	/**
	 * genera una nuova plancia di gioco e resetta quelle precedenti
	 */
	void newMatch() {
		colorFiller();
		startBottleFiller();
		
	}
	
	/**
	 * crea la lista di colori iniziali dalla quale estrarre
	 */
	void colorFiller(){
		 colori= new ArrayList<Integer>();
		 for (int coloriDiversi=1; coloriDiversi<=9; coloriDiversi++) {
			 for(int i=0; i<4; i++) {
				 colori.add(coloriDiversi);
			 }
		 }
	}
	
	/**
	 * crea dei numeri randomici e assegna a ciascuna bottiglia un numero da 1 a 9 rappresentante i colori diversi
	 */
	private void startBottleFiller() {
		Color item;
		Random r=new Random();
		coloriIniziali=new ArrayList<Color>();
		bottiglie=new ArrayList<Bottle>();
		for(int j=1; j<=9; j++) {
			for(int i=0; i<4; i++) {
				int numeroEstratto=r.nextInt(0, colori.size());
				filler[i]=colori.get(numeroEstratto);
				colori.remove(numeroEstratto);
				
				//aggiungi a lista
				item=numberToColor(filler[i]);
				coloriIniziali.add(item);
			}
			Bottle b=new Bottle(j, filler);
			bottiglie.add(b);
		}
		
		//void bottle
		for (int i=0; i<4; i++) {
			filler[i]=0;
			item=numberToColor(filler[i]);
			coloriIniziali.add(item);
		}
		Bottle b=new Bottle(10, filler);
		bottiglie.add(b);
	}
	

	
	

	public void turnSelector() {
		printTurn();
		
		
		
		
		int startBottle=primoElemento-1;
		
	
		System.out.println("selezionare una bottiglia di destinaz da 1 a 10:\t");
		
		int finBottle=secondoElemento-1;
		//get item
		//la bottiglia deve essere nell'array
		if(finBottle<0 || finBottle>9) {
			System.out.println("seleziona un elemento valido");
			return;
		}
		//la bottiglia finaledeve essere diversa da quella di partenza
		if(finBottle==startBottle) {
			System.out.println("non puoi selezionare la stessa bottiglia");
			return;
		}
		//inizializzazione variabili
		Bottle s=bottiglie.get(startBottle);
		Bottle f=bottiglie.get(finBottle);
		
		f.fillBottle(s.topColor(), s.countRemoveTopItems(f.countTopItemsFree(), s.topColor()));
		
		
	}
	
	/**
	 * stampa una lista di bottiglie (ciascuna bottiglia occupa una riga, l'elemento
	 * più in alto, quindi quello prendibile è a destra
	 */
	void printTurn() {
		for (int i = 0; i < bottiglie.size(); i++) {
			bottiglie.get(i).printBottle();
		}
	}
	
	/**
	 * funzione usata per aggiornare la grafica di ciascuna bottiglia quando passata
	 * @param bttNumber
	 */
	public Color[] updateScreen(int bttNumber){
		Color[] coloriAggiornati=new Color[4];
		for(int i=0; i<4; i++) {
			coloriAggiornati[i]= numberToColor(bottiglie.get(bttNumber).colori[i]);
		}
		return coloriAggiornati;
	}
	
	
	public Color numberToColor(int number){
		switch(number) {
		case 0:
			return Color.white;
		case 1:
			return Color.red;
		case 2:
			return Color.yellow;
		case 3:
			return Color.green;
		case 4:
			return Color.blue;
		case 5:
			return Color.cyan;
		case 6:
			return Color.magenta;
		case 7:
			return Color.pink;
		case 8:
			return Color.LIGHT_GRAY;
		case 9:
			return Color.black;
		default:
			return Color.white;
		}
	}


}

