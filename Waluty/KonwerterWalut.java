package Waluty;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;

public class KonwerterWalut extends Applet implements ActionListener {

	//variables
	TextField pole_wejsciowe;
	TextField pole_wyjsciowe;
	
	//metoda init (zaminast metody main)
	@Override
	public void init() {
		
		setLayout(new BorderLayout());		//Border Layout to Layout typu góra, lewo, œrodek, prawo, do³
		
		//gorna czesc appletu		
		//GridLayout to layout typu siatka
		Panel gora = new Panel();
		gora.setLayout(new GridLayout(1,2));		//górna czeœæ appletu: podzia³ na 1 wiersz i 2 kolumny
		
		Panel g1 = new Panel();						//panel góra1
		g1.setLayout(new BorderLayout());
		g1.add(new Label("Z³ote"), BorderLayout.LINE_START);
		pole_wejsciowe = new TextField("0", 10);
		g1.add(pole_wejsciowe, BorderLayout.CENTER);
		
		Panel g2 = new Panel();
		g2.setLayout(new BorderLayout());
		g2.add(new Label("Euro"), BorderLayout.LINE_START);
		pole_wyjsciowe = new TextField("0", 10);
		g2.add(pole_wyjsciowe, BorderLayout.CENTER);
		//pole_wyjsciowe.setEnabled(false);
		
		//do panelu góra dodajemy g1 i g2
		gora.add(g1);
		gora.add(g2);
		
		Button przelicz1 = new Button("przelicz >");		
		przelicz1.addActionListener(this);
		Button przelicz2 = new Button("< przelicz");		
		przelicz2.addActionListener(this);

		Panel dol = new Panel();
		dol.setLayout(new GridLayout(1,2));
		dol.add(przelicz1);
		dol.add(przelicz2);
		
		add(gora, BorderLayout.PAGE_START);
		add(dol, BorderLayout.PAGE_END);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		TextField skad, dokad;
		double mnoznik;
		
		if(e.getActionCommand().equals("przelicz >"))
		{
			skad = pole_wejsciowe;
			dokad = pole_wyjsciowe;
			mnoznik = 1.0 / 4.2;
		}
		else
		{
			skad = pole_wyjsciowe;
			dokad = pole_wejsciowe;
			mnoznik = 4.2;
		}

		String kwota_str = skad.getText();
		if( kwota_str.equals(""))
		{
			dokad.setText("pusto");
		}else
		{
			try{
				DecimalFormat df = new DecimalFormat("0.00");
				double kwota = df.parse(kwota_str).doubleValue();
				//double kwota = Double.parseDouble(kwota_str);
				dokad.setText( df.format( kwota * mnoznik));
			}
			catch(NumberFormatException ex)
			{
				dokad.setText("b³¹d");
			}
			catch (ParseException e1) 
			{
				dokad.setText("b³¹d");
			}
		}
	}
}
