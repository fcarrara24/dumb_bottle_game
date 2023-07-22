package graphics;

import java.util.ArrayList;

import java.util.List;

import javax.swing.*;

import objects.Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContenitoreConOggettiGrafici extends JFrame {

	private int lastClicked;
	private boolean selectable;
	private List<CostumObjectPanel> listaGraficaBottiglie;
	private boolean primoTurno;
	public int primoElemento;
	public int secondoElemento;
	private Game g;
	
	public ContenitoreConOggettiGrafici(){
		super("Contenitore con Oggetti Grafici");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new GridLayout(2, 5)); // 2 righe e 5 colonne
		g=new Game();  
		grafica();
	}
	
    public void grafica () {
    	
    	
       List<Color> riempimentoIniziale=g.coloriIniziali;
        //lista bottiglie inizializzata
        listaGraficaBottiglie=new ArrayList<CostumObjectPanel>();
        //selectable false
        selectable=false;
        this.primoTurno=true;
        // Creazione di 10 oggetti grafici personalizzati con MouseListener indipendente
        for (int i = 1; i <= 10; i++) {
        	//riempimento colori
        	Color[] coloriPerBott=new Color[4];
        	for(int j=0; j<4; j++) {
        		coloriPerBott[j]=riempimentoIniziale.get((i*4)-4+j);
        	}
            CostumObjectPanel objectPanel = new CostumObjectPanel(i, coloriPerBott);
            objectPanel.setFocusable(true);
            listaGraficaBottiglie.add(objectPanel);
            add(objectPanel); // Aggiunta del contenitore principale alla finestra
            objectPanel.addMouseListener(new MouseAdapter() {
            	@Override
            	public void mouseClicked(MouseEvent e) {
            		System.out.println("clicked on "+objectPanel.ID);
            		
            		if(primoTurno) {
            			primoElemento=objectPanel.ID;
            			primoTurno=false;
            		} else {
            			secondoElemento=objectPanel.ID;
            			if(primoElemento!=secondoElemento) {	
            				
            				g.primoElemento=primoElemento;
            				g.secondoElemento=secondoElemento;
            				primoTurno=true;
            				if(g.victory()) {
            					
            					System.out.println("complimenti!!");
            					setDefaultCloseOperation(ABORT);
            				}
            			}
            			aggiornaBottiglia(primoElemento, g.updateScreen(primoElemento-1));
            			aggiornaBottiglia(secondoElemento, g.updateScreen(secondoElemento-1));
            		}
            		
            		
            		
            	}
            });
            add(objectPanel); // Aggiunta dell'oggetto grafico al contenitore principale
        }
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * permette di selezionare una bottiglia a patto che 
     * selectable sia stata aggiornata, ritorna l'elemento selezioato
     * @return
     */
    public int selezionaBottigliaPescaggio() {
    	if(selectable) {
    		selectable=false;
    		return lastClicked;
    	}
    	else 
    		System.out.println("elemeto non selezonato, errore esecuzione");
    	return 0;
    }
    
    
    public void aggiornaBottiglia(int ID, Color[] colore) {
    	System.out.println(ID);
    	for(int j=0; j<4; j++) {
    		System.out.print(colore[j]);
    	}
    			listaGraficaBottiglie.get(ID-1).updateList(colore);
    			listaGraficaBottiglie.get(ID-1).update(getGraphics());
    			setSize(800, 500);
    			setSize(400, 250);
    			setSize(400, 250);
    			return;
    }
    
    
}

