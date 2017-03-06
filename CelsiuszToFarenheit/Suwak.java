package CelsiuszToFarenheit;

import java.awt.event.ActionEvent;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Suwak extends JFrame implements ChangeListener
{
	//variables
	private JSlider sCelsiusz, sFarenheit;
	private JLabel lCelsiusz, lFarenheit;
	private int tempCelsiusz, tempFarenheit;
	
	//constructor
	public Suwak()
	{
		setSize(500, 300);
		setTitle("Suwak");
		setLayout(null);
		
		sCelsiusz = new JSlider(0,100,0);		//3 argumenty: 1-wartosc poczatkowa, 2-wartosc koncowa, 3-gdzie ustawic suwak
		sCelsiusz.setBounds(20, 40, 300, 50);
		sCelsiusz.setMajorTickSpacing(20);		//interwa³ g³ówny co 20
		sCelsiusz.setMinorTickSpacing(5);		//interwa³ poboczny co 5
		sCelsiusz.setPaintTicks(true);			//poka¿ interwa³ kreski interwa³u g³ównego i pobocznego
		sCelsiusz.setPaintLabels(true);		//poka¿ cyfry interwa³u g³ównego i pobocznego
		sCelsiusz.addChangeListener(this);		//dodajemy ChangeListenera
		add(sCelsiusz);	
		
		sFarenheit = new JSlider(30,212,30);		//3 argumenty: 1-wartosc poczatkowa, 2-wartosc koncowa, 3-gdzie ustawic suwak
		sFarenheit.setBounds(20, 120, 300, 50);
		sFarenheit.setMajorTickSpacing(20);		//interwa³ g³ówny co 20
		sFarenheit.setMinorTickSpacing(5);		//interwa³ poboczny co 5
		sFarenheit.setPaintTicks(true);			//poka¿ interwa³ kreski interwa³u g³ównego i pobocznego
		sFarenheit.setPaintLabels(true);			//poka¿ cyfry interwa³u g³ównego i pobocznego
		//sFarenheit.setEnabled(false);			//ustawiamy mu enabled, zeby nie mo¿na by³o nim ruszaæ
		sFarenheit.addChangeListener(this);
		add(sFarenheit);
		
		lCelsiusz = new JLabel("Celsiusz: ");
		lCelsiusz.setBounds(350, 40, 100, 20);
		add(lCelsiusz);
		
		lFarenheit = new JLabel("Farenheit: ");
		lFarenheit.setBounds(350, 120, 100, 20);
		add(lFarenheit);
	}
	
	//methods
	
	//metoda z interfejsu ChangeListener; to metoda do nas³uchu ZMIAN
	@Override
	public void stateChanged(ChangeEvent e)
	{
		Object zrodlo = e.getSource();
		
		if (zrodlo == sCelsiusz)
		{
			tempCelsiusz = sCelsiusz.getValue();			//do zmiennej tempCelsiusz przypisz wartoœæ z suwaka Celsiusz za pomoc¹ metody getValue
			lCelsiusz.setText("Celsiusz: " + tempCelsiusz);	//wyswieltlenie w labelce stopni Celsiusza
			
			tempFarenheit = (int) Math.round(32 + (9.0/5.0) * tempCelsiusz);  	//wylicznie temperatury Farenheita 
			lFarenheit.setText("Farenheit: " + tempFarenheit);					//pwyswietlenie temp w Labelce
			
			sFarenheit.setValue(tempFarenheit);					//ustawienie suwaka Farenheit
		}
		else if (zrodlo == sFarenheit)
		{
			tempFarenheit = sFarenheit.getValue();
			lFarenheit.setText("Farenheit: " + tempFarenheit);
			
			tempCelsiusz = (int) Math.round((tempFarenheit - 32.0) * (5.0/9.0));
			lCelsiusz.setText("Celsiusz: " + tempCelsiusz);
			
			sCelsiusz.setValue(tempCelsiusz);
		}
		
		
		
	}

	//main method
	public static void main(String[] args)
	{
		Suwak suwak = new Suwak();
		suwak.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		suwak.setVisible(true);

	}

}