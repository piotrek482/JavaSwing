package NotatnikWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Notatnik extends JFrame implements ActionListener, KeyListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//variables
	private Pomoc pomoc;
	private Zamien zamien;
	
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private JMenu mPlik, mEdycja, mFormat, mWidok, mPomoc;
	private JMenuItem miNowy, miOtworz, miZapisz, miZapiszJako, miUstawieniaStrony, miDrukuj, miZakoncz,
						miCofnij, miWytnij, miKopiuj, miWklej, miUsun, miZnajdz, miZnajdzNastepny, miZamien, miPrzejdzDo, miZaznaczWszystko, miGodzinaData,
						miCzcionka,
						miPasekStanu,
						miWyswietlPomoc, miNotatnikInformacje,
						
						miCofnij2, miWytnij2, miKopiuj2, miWklej2, miUsun2, miZaznaczWszytsko2;
	private JCheckBoxMenuItem chbmiZawijanieWierszy;
	private JPopupMenu pmMenuPrawyPrzycisk;
	
	private JFileChooser fileChooser;
	private File file;
	private Scanner s;
	private PrintWriter pw;
	
	private String kopiuj, kopiujZaznaczenie, cofnij, wytnij;
	

	//constructor
	public Notatnik()
	{
		setSize(500, 500);
		setTitle("Nowy dokument tekstowy.txt - Notatnik");
		setLayout(null);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		
		//dodanie TextArea do ScrollPane 
		textArea = new JTextArea();
		textArea.addKeyListener(this);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, 0, 485, 440);
		add(scrollPane);
		
		//utworzenie MenuBar
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
			//Menu mPlik
			mPlik = new JMenu("Plik");
			
				//menuItem
				miNowy = new JMenuItem("Nowy");
				miOtworz = new JMenuItem("Otworz");
				miZapisz = new JMenuItem("Zapisz");
				miZapiszJako = new JMenuItem("Zapisz jako...");
				miUstawieniaStrony = new JMenuItem("Ustawienia strony");
				miDrukuj = new JMenuItem("Drukuj");
				miZakoncz = new JMenuItem("Zakoncz");
				
				//dodanie do mPlik
				mPlik.add(miNowy);
				mPlik.add(miOtworz);
				mPlik.add(miZapisz);
				mPlik.add(miZapiszJako);
				mPlik.addSeparator();
				mPlik.add(miUstawieniaStrony);
				mPlik.add(miDrukuj);
				mPlik.addSeparator();
				mPlik.add(miZakoncz);

				
				//dodanie Akceleratorów (Skroty klawiszowe)
				miNowy.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
				miOtworz.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
				miZapisz.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
				miDrukuj.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
				miZakoncz.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
				
				//dodanie ActionListenerow
				miNowy.addActionListener(this);
				miOtworz.addActionListener(this);
				miZapisz.addActionListener(this);
				miZapiszJako.addActionListener(this);
				miUstawieniaStrony.addActionListener(this);
				miDrukuj.addActionListener(this);
				miZakoncz.addActionListener(this);
				
			
			//Menu mEdycja
			mEdycja = new JMenu("Edycja");
			
				//menuItem
				miCofnij = new JMenuItem("Cofnij");
				miWytnij = new JMenuItem("Wytnij");
				miKopiuj = new JMenuItem("Kopiuj");
				miWklej = new JMenuItem("Wklej");
				miUsun = new JMenuItem("Usuñ");
				miZnajdz = new JMenuItem("Znajdz");
				miZnajdzNastepny = new JMenuItem("Znajdz nastêpny");
				miZamien = new JMenuItem("Zamien...");
				miPrzejdzDo = new JMenuItem("Przejdz do...");
				miZaznaczWszystko = new JMenuItem("Zaznacz wszystko");
				miGodzinaData = new JMenuItem("Godzina/Data");
				
				//dodanie do mEdycja
				mEdycja.add(miCofnij);
				mEdycja.addSeparator();
				mEdycja.add(miWytnij);
				mEdycja.add(miKopiuj);
				mEdycja.add(miWklej);
				mEdycja.add(miUsun);
				mEdycja.addSeparator();
				mEdycja.add(miZnajdz);
				mEdycja.add(miZnajdzNastepny);
				mEdycja.add(miZamien);
				mEdycja.add(miPrzejdzDo);
				mEdycja.addSeparator();
				mEdycja.add(miZaznaczWszystko);
				mEdycja.add(miGodzinaData);
				
				//dodanie Akceleratorów (skróty klawiszowe)
				miCofnij.setAccelerator(KeyStroke.getKeyStroke("ctrl Z"));
				miWytnij.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
				miKopiuj.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
				miWklej.setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
				miUsun.setAccelerator(KeyStroke.getKeyStroke("Del"));
				miZnajdz.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
				miZnajdzNastepny.setAccelerator(KeyStroke.getKeyStroke("F3"));
				miZamien.setAccelerator(KeyStroke.getKeyStroke("ctrl H"));
				miPrzejdzDo.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
				miZaznaczWszystko.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
				miGodzinaData.setAccelerator(KeyStroke.getKeyStroke("F5"));
				
				//dodanie ActionListenerow
				miCofnij.addKeyListener(this);
				miWytnij.addActionListener(this);
				miKopiuj.addActionListener(this);
				miWklej.addActionListener(this);
				miUsun.addActionListener(this);
				miZnajdz.addActionListener(this);
				miZnajdzNastepny.addActionListener(this);
				miZamien.addActionListener(this);
				miPrzejdzDo.addActionListener(this);
				miZaznaczWszystko.addActionListener(this);
				miGodzinaData.addActionListener(this);
				
				//wylaczenie aktywnosci przyciskow po wlaczeniu apki
				miCofnij.setEnabled(false);
				miWytnij.setEnabled(false);
				miKopiuj.setEnabled(false);				
				miUsun.setEnabled(false);
				miZnajdz.setEnabled(false);
				miZnajdzNastepny.setEnabled(false);
				miZnajdzNastepny.setEnabled(false);
			
			
			//menu Format
			mFormat = new JMenu("Format");
			
				//menu Item
				chbmiZawijanieWierszy = new JCheckBoxMenuItem("Zawijanie wierszy");
				miCzcionka = new JMenuItem("Czcionka...");
				
				//
				chbmiZawijanieWierszy.setSelected(true);
				
				//dodanie do mFormat
				mFormat.add(chbmiZawijanieWierszy);
				mFormat.add(miCzcionka);
				
				//dodanie ActionListenerów
				chbmiZawijanieWierszy.addActionListener(this);
				miCzcionka.addActionListener(this);
			
			
			//menu Widok
			mWidok = new JMenu("Widok");
			
				//menu Item
				miPasekStanu = new JMenuItem("Pasek Stanu");
				miPasekStanu.setEnabled(false);
				
				//dodanie do mWidok
				mWidok.add(miPasekStanu);
				
				//dodanie ActionListenera
				miPasekStanu.addActionListener(this);
				
			//menu Pomoc
			mPomoc = new JMenu("Pomoc");
			
				//menu Item
				miWyswietlPomoc = new JMenuItem("Wyswietl Pomoc");
				miNotatnikInformacje = new JMenuItem("Notatnik - informacje");
				
				//dodanie do mPomoc
				mPomoc.add(miWyswietlPomoc);
				mPomoc.addSeparator();
				mPomoc.add(miNotatnikInformacje);
				
				//dodanie ActionListenera
				miWyswietlPomoc.addActionListener(this);
				miNotatnikInformacje.addActionListener(this);
		
		//dodanie Menu do MenuBar
		menuBar.add(mPlik);
		menuBar.add(mEdycja);
		menuBar.add(mFormat);
		menuBar.add(mWidok);
		menuBar.add(mPomoc);
		
		//////////////////////////////////////////////////////////////////////////////////////////
		
		//Menu prawego przycisku myszy JPopupMenu
		pmMenuPrawyPrzycisk = new JPopupMenu();
		
			//utworzenie obiektów Menu Item
			miCofnij2 = new JMenuItem("Cofnij");		
			miWytnij2 = new JMenuItem("Wytnij");
			miKopiuj2 = new JMenuItem("Kopiuj");
			miWklej2 = new JMenuItem("Wklej");
			miUsun2 = new JMenuItem("Usuñ");
			miZaznaczWszytsko2 = new JMenuItem("Zaznacz wszytsko");
			
			//brak aktywnosci
//			miCofnij2.setEnabled(false);
//			miWytnij2.setEnabled(false);
//			miKopiuj2.setEnabled(false);
//			miUsun2.setEnabled(false);
//			miZaznaczWszytsko2.setEnabled(false);

			
			//dodanie ActionListenerów
			miCofnij2.addKeyListener(this);
			miWytnij2.addActionListener(this);
			miKopiuj2.addActionListener(this);
			miWklej2.addActionListener(this);
			miUsun2.addActionListener(this);
			miZaznaczWszytsko2.addActionListener(this);
			
			//dodawanie MenuItem do JPopupMenu
			pmMenuPrawyPrzycisk.add(miCofnij2);
			pmMenuPrawyPrzycisk.addSeparator();
			pmMenuPrawyPrzycisk.add(miWytnij2);
			pmMenuPrawyPrzycisk.add(miKopiuj2);
			pmMenuPrawyPrzycisk.add(miWklej2);
			pmMenuPrawyPrzycisk.add(miUsun2);
			pmMenuPrawyPrzycisk.addSeparator();
			pmMenuPrawyPrzycisk.add(miZaznaczWszytsko2);

			
			//JPopupMenu bêdzie dzia³a³o na JTextArea 
			textArea.setComponentPopupMenu(pmMenuPrawyPrzycisk);
	}
	
	
	//methods
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		Object zrodlo = e.getSource();
		
		if (zrodlo == miNowy)
		{
			int i = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz otworzyc nowy dokument?", "Nowy dokument", JOptionPane.YES_NO_OPTION);
			if (i == JOptionPane.YES_OPTION)
			{
				
			}
			else if(i == JOptionPane.NO_OPTION)
			{

			}
		}
		else if (zrodlo == miOtworz)
		{
			fileChooser = new JFileChooser();	//1. utworzenie fileChoosera
			
			if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)		//2. na fileChooser wywyo³ujemy metode poka¿ okno dialogowe i wybierzemy ok
			{
				file = fileChooser.getSelectedFile();
			}
			
			
			try
			{
				s = new Scanner(file);				//3. do skanera wczytujemy zawartosc pliku
				while( s.hasNext() )					//4. dopóki w skanerze jest nast linia
				{
					textArea.append( s.nextLine() + "\n");		//5. do textArea dopisz kolej¹ linie
				}
				
			} 
			catch (FileNotFoundException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (zrodlo == miZapisz)
		{
			fileChooser = new JFileChooser();		//1. utworzenie fileChoosera
			
			if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) 	//2. na fileChooser wywy³aujemy metode do zapisu (ShowSaveDialog)
			{
				file = fileChooser.getSelectedFile();								//3. do zmiennej file przypisujemy zaznaczony plik
			}
			
			try
			{
				pw = new PrintWriter(file);							//4. do printWritera zapisujemy zawartoœæ pliku
				s = new Scanner( textArea.getText() );				//5. text który jest w textArea pobierz do skanera
				
				while( s.hasNext() )
				{
					pw.println( s.nextLine() + "\n" );
				}
				
				pw.close();				//6. zamkniecie PrintWritera, gdy tego nie damy nic sie do pliku nie zapisze
			} 
			catch (FileNotFoundException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (zrodlo == miZapiszJako)
		{
			fileChooser = new JFileChooser();		//1. utworzenie fileChoosera
			
			if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) 	//2. na fileChooser wywy³aujemy metode do zapisu (ShowSaveDialog)
			{
				file = fileChooser.getSelectedFile();								//3. do zmiennej file przypisujemy zaznaczony plik
			}
			
			try
			{
				pw = new PrintWriter(file);							//4. do printWritera zapisujemy zawartoœæ pliku
				s = new Scanner( textArea.getText() );				//5. text który jest w textArea pobierz do skanera
				
				while( s.hasNext() )
				{
					pw.println( s.nextLine() + "\n" );
				}
				
				pw.close();				//6. zamkniecie PrintWritera, gdy tego nie damy nic sie do pliku nie zapisze
			} 
			catch (FileNotFoundException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (zrodlo == miUstawieniaStrony)
		{
			
		}
		else if (zrodlo == miDrukuj)
		{
			
		}
		else if (zrodlo == miZakoncz)
		{
			int a = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz zakoñczyæ?", "Zamykanie programu", JOptionPane.YES_NO_OPTION);
			
			if(a == JOptionPane.YES_OPTION)
			{
				dispose();
			}
			else if(a == JOptionPane.NO_OPTION)
			{
				
			}
			
		}
		else if (zrodlo == miWytnij || zrodlo == miWytnij2)
		{
			wytnij = textArea.getSelectedText();		//pobranie textu
			textArea.cut();								//wyciêcie
			
		}
		else if (zrodlo == miKopiuj || zrodlo == miKopiuj2)
		{
			kopiujZaznaczenie = textArea.getSelectedText();
		}
		else if (zrodlo == miWklej || zrodlo == miWklej2)
		{
			//korzystamy z metody insert która ma 2 argumenty: 1 - co wstawiamy, 2 - gdzie
			textArea.insert(kopiujZaznaczenie, textArea.getCaretPosition()); //metoda getCaretPosition pobiera po³o¿enie kursora
		}
		else if (zrodlo == miUsun || zrodlo == miUsun2)
		{
			wytnij = textArea.getSelectedText();		//pobranie textu
			textArea.cut();
		}
		else if (zrodlo == miZnajdz)
		{
			
		}
		else if (zrodlo == miZnajdzNastepny)
		{
			
		}
		else if (zrodlo == miZamien)
		{
			if(zamien == null)
			{
				zamien = new Zamien(this);
			}
			
			zamien.setVisible(true);
		}
		else if (zrodlo == miPrzejdzDo)
		{
			
		}
		else if (zrodlo == miZaznaczWszystko)
		{
			textArea.selectAll();
		}
		else if (zrodlo == miGodzinaData)
		{
			textArea.setText(new Date().toString());
		}
		else if (zrodlo == chbmiZawijanieWierszy)
		{
			
		}
		else if (zrodlo == miCzcionka)
		{
			
		}
		else if (zrodlo == miPasekStanu)
		{
			
		}
		else if (zrodlo == miWyswietlPomoc)
		{
			if(pomoc == null)
				{
					pomoc = new Pomoc(this);
				}
			
			pomoc.setVisible(true);
		}
		else if (zrodlo == miNotatnikInformacje)
		{
			JOptionPane.showMessageDialog(null, "Autor: Piotr Lizak \nWesja 1", "Informacje o programie", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		Object zrodlo = e.getSource();
//		
//		kopiuj = textArea.getText();
//		
//		if (zrodlo == textArea && kopiuj != "" )
//		{
//			miKopiuj.setEnabled(true);
//			miKopiuj2.setEnabled(true);
//			
//		}
//		else if(zrodlo == textArea && kopiuj == "")
//		{
//			miKopiuj.setEnabled(false);
//			miKopiuj2.setEnabled(false);
//		}
		
		cofnij = textArea.getText();
		if (zrodlo == textArea)
		{
			if (zrodlo == miCofnij || zrodlo == miCofnij2)
			{
				textArea.setText(cofnij);
			}			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}


	//main method
	public static void main(String[] args)
	{
		Notatnik notatnik = new Notatnik();
		notatnik.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		notatnik.setVisible(true);

	}

}

class Pomoc extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//variables
	private JLabel lPomoc;
	
	//constructor
	public Pomoc(JFrame owner)
	{
		//metod¹ super odwo³ujemy siê do klasy nadrzednej JDialog
		//metoda ta przyjmuje 3 argumenty: 1 - owner; 2 - tytu³ dialogu; 3 - czy dalog ma byæ modalny czy niemodalny (modlany - musimy zatwierdziæ, aby aplikacja dalej dzia³a³a)
		super(owner, "Pomoc", true);
		
		setSize(400, 200);
		setLayout(null);
		
		
		lPomoc = new JLabel("Jeœli chcesz uzyskaæ wiêcej informacji, kliknij w link: \n Pomoc");
		lPomoc.setBounds(10, 40, 390, 40);
		add(lPomoc);
	}
	
}

class Zamien extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//variables
	private JLabel lZamien1, lZamien2;
	private JTextField tfCo, tfNaCo;
	private JButton bZamien;
	
	//constructor
	public Zamien(JFrame owner)
	{
		//metod¹ super odwo³ujemy siê do klasy nadrzednej JDialog
		//metoda ta przyjmuje 3 argumenty: 1 - owner; 2 - tytu³ dialogu; 3 - czy dalog ma byæ modalny czy niemodalny (modlany - musimy zatwierdziæ, aby aplikacja dalej dzia³a³a)
		super(owner, "Zamien", true);
		
		setSize(400, 200);
		setLayout(null);
		
		
		lZamien1 = new JLabel("S³owo: ");
		lZamien1.setBounds(10, 40, 50, 20);
		add(lZamien1);
		
		tfCo = new JTextField("");
		tfCo.setBounds(70, 40, 100, 20);
		add(tfCo);
		
		lZamien2 = new JLabel(" zamieñ na:  ");
		lZamien2.setBounds(180, 40, 80, 20);
		add(lZamien2);
		
		tfNaCo = new JTextField("");
		tfNaCo.setBounds(260, 40, 100, 20);
		add(tfNaCo);
		
		bZamien = new JButton("Zamien");
		bZamien.setBounds(150, 80, 100, 40);
		add(bZamien);
		bZamien.addActionListener(this);
	}

	//methods
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object zrodlo = e.getSource();
		
		if(zrodlo == bZamien)
		{
			
		}
		
	}
	
}
