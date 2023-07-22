package graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CostumObjectPanel extends JPanel  {

    public int ID; 
	private static final long serialVersionUID = 1L;
	public Color[] listaColori;
	
	public CostumObjectPanel(int identificativo, Color[] colori) {
		
        super();
        setPreferredSize(new Dimension(100,100));
        
        this.listaColori=colori;
        this.ID=identificativo;
    }

	public void updateList (Color[] colori){
		this.listaColori=colori;
	}
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Disegna 4 elementi grafici impilati uno sull'altro
        g.setColor(listaColori[0]);
        g.fillRect(10, 10, 50, 20);

        g.setColor(listaColori[1]);
        g.fillRect(10, 30, 50, 20);

        g.setColor(listaColori[2]);
        g.fillRect(10, 50, 50, 20);

        g.setColor(listaColori[3]);
        g.fillRect(10, 70, 50, 20);
    }

    
}
