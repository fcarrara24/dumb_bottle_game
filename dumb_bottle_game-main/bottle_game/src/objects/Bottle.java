package objects;

public class Bottle {
	public int ID;
	public int[] colori=new int[4];
	
	Bottle(int ID, int[]coloriScelti){
		
		this.ID=ID;
		for(int i=0; i<4; i++) {
			this.colori[i]=coloriScelti[i];
		}
		
	}
	
	/**
	 * stampa tutti gli elementi della bottiglia, quello più a destra ha indice 0
	 */
	public void printBottle() {
		System.out.print("\nbottiglia "+ID+":\t");
		for (int i=3; i>=0; i--) {
			System.out.print(colori[i]+" ");
		}
	}
	
	/**
	 * ritorna gli spazi vuoti(da dx a sx)
	 * @return
	 */
	public int countTopItemsFree() {
		int counter=0;
		for (int i=0; i<4; i++) {
			if(colori[i]!=0) {
				
				return counter;
				
			}
			counter++;
			
		}
		
		return counter;
	}
	
	/**
	 * ritorna il colore che è in posizione più alta; 
	 * 0 se la bottiglia è vuota
	 * @return
	 */
	public int topColor() {
		for (int i=0; i<4; i++) {
			if(colori[i]!=0) {
				
				return colori[i];
			}
		}
		return 0;
	}
	/**
	 * la bottiglia viene riempita se vengono rispettati gi altri parametri
	 * @param actualColor
	 * @param number
	 */
	public void fillBottle(int actualColor, int number) {
		int countNumbers=0;
		if(number!=0) {
			for(int i=3; i>=0; i--) {
				if(colori[i]==0) {
					if(countNumbers<number) {
						colori[i]=actualColor;
						countNumbers++;
					}
				}
			}
		}
	}
	
	/**
	 * ritorna il numero di colori copiabili nell'altra bottiglia
	 * 
	 * ritorna 0 in caso di errore, bottiglia vuota o colore assente
	 * @param maxItemsFree
	 * @return
	 */
	public int countRemoveTopItems(int maxItemsFree, int colorConsidered){
		int counter=0;
		int prevColor=0;
		Boolean prevTurnExtract=false;
		
	
		for(int i=0; i<4; i++) {
			if(prevTurnExtract) {
				if(colori[i]!=prevColor) {
					//controllo per la traslazione
					if(counter>maxItemsFree) {
						removeItems(maxItemsFree);
						return maxItemsFree;
					}
					removeItems(counter);
					return counter;
					
				}
			}
			if(colorConsidered==0 || colori[i]==colorConsidered) {
				counter++;
				prevColor=colori[i];
				prevTurnExtract=true;
			}
		}
		if(counter==0) {
			System.out.println("l'elemento cercato non è qui presente, si prega di selezionare un'altra bottiglia");
			return 0;
		}
		//controllo per la traslazione
		if(counter>maxItemsFree) {
			removeItems(maxItemsFree);
			return maxItemsFree;
		}
		removeItems(counter);
		return counter;
	}
	
	/**
	 * rimuove n elementi dalla cima della lista
	 * @param counter
	 */
	private void removeItems(int counter) {
		int i=0;
		if (colori[3] != 0) {
			// scorro la lista
			while (colori[i] == 0) {
				i++;
			}
		}
		for(int j=0; j<counter; j++) {
			colori[i+j]=0;
		}
	}
	
	public boolean allItemsEquals() {
		int actualColor=colori[0];
		for (int i=1; i<4; i++) {
			if(colori[i]!=actualColor) {
				return false;
			}
		}
		return true;
	}
}
