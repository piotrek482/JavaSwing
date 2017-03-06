package CelsiuszToFarenheit;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyWindow extends JFrame implements ActionListener, MouseListener
{
	
	//zmienne
	private JButton bPodajDate, bWyjscie;
	private JLabel lWyswietlDate;
	
	//konstruktor
	public MyWindow()
	{
		
		setSize(300, 200);						//ustawienie rozmiaru ramki
		setTitle("Moja pierwsze okienko");		//ustawienie tytu³u
		setLayout(null);						//ustwienie layoutu
		
		
		//bPodajDate
		bPodajDate = new JButton("Podaj date");		//utworzenie przycisku 1
		bPodajDate.setBounds(50, 50, 100, 20);		//ustaienie na ekranie
		add(bPodajDate);							///dodanie do layoutu
		bPodajDate.addActionListener(this);			//ustawienie actionListenera na siebie
		
		//bWyjscie
		bWyjscie = new JButton("Wyjscie");
		bWyjscie.setBounds(150,	50, 100, 20);
		add(bWyjscie);
		bWyjscie.addActionListener(this);
		
		//lWyjscie
		lWyswietlDate = new JLabel("Data: ");							//utworzenie Labelki -> wyswietlanie tekstu
		lWyswietlDate.setBounds(50, 100, 250, 20);						//ustawienie pozycji
		lWyswietlDate.setFont(new Font("SansSerif", Font.BOLD, 15));	//ustawienie Fontu
		lWyswietlDate.setForeground(new Color(100, 200, 255));			//ustawienie koloru czcionki
		//lWyswietlDate.setForeground(Color.RED);						
		add(lWyswietlDate);												//dodanie przycisku do okinka
		
		
		
		
		//addMouseListener(this);
	}

	public static void main(String[] args)
	{
		MyWindow okienko = new MyWindow();					//utworzenie okienka
		okienko.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//dodanie opcji zamknij/wyjscie
		okienko.setVisible(true);									//ustawienie widocznoœci okinka

	}

	@Override
	public void actionPerformed(ActionEvent e)			//nadpisanie metody actionPerformed z interfejsu ActionListener
	{
		Object zrodlo = e.getSource();					//dadanie obiektu z klasy Object(nadklasy)
		if(zrodlo == bPodajDate)
		{
			lWyswietlDate.setText(new Date().toString());	//metoda setText przyjmuje jako argument text a nie date dlatego trzeba przekonwertowaæ date na text za pomoc¹ metody toString();
			//System.out.println(new Date());
		} 
		else if(zrodlo == bWyjscie)
		{
			dispose();									//wyjscie z programu
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent me)
	{
		if(me.getButton() == MouseEvent.BUTTON1)
		{
			System.out.println("Lewy przycisk");
		}
		else if(me.getButton() == MouseEvent.BUTTON3)
		{
			System.out.println("Prawy przycisk");
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
