package RownanieKwadratowe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RownanieGUI extends JFrame implements ActionListener
{

	// variables
	private JLabel lA, lB, lC, lWynik;
	private JTextField tfA, tfB, tfC;
	private JButton bRozwiaz, bWyjscie;

	// construktor
	public RownanieGUI()
	{
		setSize(400, 300);
		setTitle("Równanie Kwadratowe");
		setLayout(null);

		// Label i TextField A
		lA = new JLabel("A: ");
		lA.setBounds(20, 50, 20, 20);
		add(lA);

		tfA = new JTextField("");
		tfA.setBounds(45, 50, 50, 20);
		add(tfA);
		tfA.setToolTipText("Podaj wartosc wspó³czynnika A");
		tfA.setBackground(new Color(100, 200, 255));

		// Label i TextField B
		lB = new JLabel("B: ");
		lB.setBounds(120, 50, 20, 20);
		add(lB);

		tfB = new JTextField("");
		tfB.setBounds(145, 50, 50, 20);
		add(tfB);
		tfB.setToolTipText("Podaj wartosc wspó³czynnika B");
		tfB.setBackground(new Color(100, 200, 255));

		// Label i TextField C
		lC = new JLabel("C: ");
		lC.setBounds(220, 50, 20, 20);
		add(lC);

		tfC = new JTextField("");
		tfC.setBounds(245, 50, 50, 20);
		add(tfC);
		tfC.setToolTipText("Podaj wartosc wspó³czynnika C");
		tfC.setBackground(new Color(100, 200, 255));
		
		//Buttony
		bRozwiaz = new JButton("Rozwia¿ równanie");
		bRozwiaz.setBounds(20, 125, 150, 20);
		add(bRozwiaz);
		bRozwiaz.addActionListener(this);
		
		bWyjscie = new JButton("Wyjscie");
		bWyjscie.setBounds(250, 125, 100, 20 );
		add(bWyjscie);
		bWyjscie.addActionListener(this);
		
		//Label lWynik
		lWynik = new JLabel();
		lWynik.setBounds(10, 150, 390, 30);
		add(lWynik);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object zrodlo = e.getSource();
		
		if (zrodlo == bWyjscie)
		{
			dispose();
		}
		else if(zrodlo == bRozwiaz)
			{
//				//pobieram wartosci z pol textfirld i przypisuje je do zmiennych lokalnych
				int a = Integer.parseInt(tfA.getText());
				int b = Integer.parseInt(tfB.getText());
				int c = Integer.parseInt(tfC.getText());
				
//				
//				//najpierw musze stworzyæ OBIEKT KLASY RowanieKwadratowe ze zmiennymi a,b,c
				RownanieKwadratowe rownanie = new RownanieKwadratowe(a,b,c);
				
//				//wywolanie metody rozwiaz z klasy RownanieKwadratowe
				String rozwiazanie = rownanie.rozwiaz();
				
				//wyswietlenie rozwiazania w lWynik
				lWynik.setText(rozwiazanie);
			}
	}
	
	public static void main(String[] args)
	{
		RownanieGUI rownanie = new RownanieGUI();
		rownanie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rownanie.setVisible(true);

	}



}
