package Menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Notepad extends JFrame implements ActionListener 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//variables
	private JButton bSzukaj, bWybierzKolor;
	private JTextField tfSzukaj;
	private JMenuBar menuBar;
	private JMenu mPlik, mNarzedzia, mOpcje, mLookAndFeel, mPomoc;
	private JMenuItem miOtworz, miZapisz, miZakoncz, miNarzedzia1, miNarzedzia2, miOpcja1, miOpcja2, miMetal, miNimbus, miWindows, miOprogramie, miOautorze, pmKopiuj, pmWklej, pmDolacz;
	private JCheckBoxMenuItem chbmiOpcja3;
	private JFileChooser fchFile;
	private File plik;
	private JTextArea notatnik;
	private JScrollPane scrollPane;
	private JPopupMenu popupMenu;
	private JComboBox<String> cbKolor;
	private String kopiuj;
	
	
	//constructor
	public Notepad()
	{
		setSize(400,400);
		setTitle("Aplikacja Menu");
		setLayout(null);
		
		
		menuBar = new JMenuBar();		//utworzenie menuBar
		setJMenuBar(menuBar);			//dodanie JMenuBar do ramki --> polecenie SET a nie add
		
		//Menu Plik
		mPlik = new JMenu("Plik");
			
			//MenuItem
			miOtworz = new JMenuItem("Otworz", 'O');		//to Z to Mnemonik czyli pierwsza litera, musi byæ w pojedynczym apostrofie
			miZapisz = new JMenuItem("Zapisz", 'Z');
			miZakoncz = new JMenuItem("Zakoñcz");		
			
			//dodanie do menu mPlik
			mPlik.add(miOtworz);
			mPlik.add(miZapisz);
			mPlik.addSeparator();
			mPlik.add(miZakoncz);
			
			//dodanie ActionListenerow
			miOtworz.addActionListener(this);
			miZapisz.addActionListener(this);
			miZakoncz.addActionListener(this);
			
			
			//dodanie Akceleratorów (skrótów klawiszowych)
			miOtworz.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
			miZapisz.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
			miZakoncz.setAccelerator( KeyStroke.getKeyStroke("ctrl Q") );		//ctrl ma³ymi literami, Q DUZE, bez przecinków, sredników...
			
			
		//Menu Narzedzia
		mNarzedzia = new JMenu("Narzedzia");
		
			//MenuItem
			miNarzedzia1 = new JMenuItem("Narzedzia1");
			miNarzedzia2 = new JMenuItem("Narzedzia2");
			
			miNarzedzia2.setEnabled(false);					//nieaktywna opcja (nie da siê jej kliknn¹æ/wybrac)
			
			//dodanie do ramki
			mNarzedzia.add(miNarzedzia1);
			mNarzedzia.add(miNarzedzia2);
			
				//podmenu
				mOpcje = new JMenu("Opcje");
					
					//MenuItem
					miOpcja1 = new JMenuItem("Opcja1");
					miOpcja2 = new JMenuItem("Opcja2");
					chbmiOpcja3 = new JCheckBoxMenuItem("Opcja3");		//dodanie menu checkBox
					
					//dodanie do menu Narzedzia
					mOpcje.add(miOpcja1);
					mOpcje.add(miOpcja2);
					mOpcje.add(chbmiOpcja3);
					
					//dodanie ActionListenera
					chbmiOpcja3.addActionListener(this);
					miNarzedzia1.addActionListener(this);
					
				//dodanie ca³ego podmenu Opcje do menu Narzedzia
				mNarzedzia.add(mOpcje);
		
		
		//menu LookAndFeel
		mLookAndFeel = new JMenu("Wyglad/Styl");
			
			//menu Item
			miMetal = new JMenuItem("Metal");
			miNimbus = new JMenuItem("Nimbus");
			miWindows = new JMenuItem("Windows");
			
			//dodanie do menu
			mLookAndFeel.add(miMetal);
			mLookAndFeel.add(miNimbus);
			mLookAndFeel.add(miWindows);
			
			//dodanie ActionListenerow
			miMetal.addActionListener(this);
			miNimbus.addActionListener(this);
			miWindows.addActionListener(this);
				
		//Menu Pomoc
		mPomoc = new JMenu("Pomoc");
			
			//MenuItem
			miOprogramie = new JMenuItem("O programie");
			miOautorze = new JMenuItem("O autorze");
			
			//dodanie do ramki
			mPomoc.add(miOprogramie);
			mPomoc.add(miOautorze);
			
			//dodanie ActionListenera
			 miOprogramie.addActionListener(this);
			 miOautorze.addActionListener(this);
			 

		
		//dodanie menu do ManuBar
		menuBar.add(mPlik);
		menuBar.add(mNarzedzia);
		menuBar.add(mLookAndFeel);
		menuBar.add(mPomoc);
		
		//dodanie TextArea notatnik do scrollPane
		notatnik = new JTextArea();
		scrollPane = new JScrollPane(notatnik);
		scrollPane.setBounds(0,0, 350, 200);
		add(scrollPane);							//dodanie scrollPane do ramki
		
		tfSzukaj = new JTextField("");
		tfSzukaj.setBounds(30, 210, 100, 20);
		add(tfSzukaj);
		
		bSzukaj = new JButton("Szukaj");
		bSzukaj.setBounds(150, 210, 100, 20);
		add(bSzukaj);
		bSzukaj.addActionListener(this);
		
		//tworzymy menu popup - klik prawym przyciskiem myszy
		popupMenu = new JPopupMenu();
			
			//utworzenie obiektów
			pmKopiuj = new JMenuItem("Kopiuj");
			pmWklej = new JMenuItem("Wklej");
			pmDolacz = new JMenuItem("Dolacz");
			
			//dodanie ActionListenerow
			pmKopiuj.addActionListener(this);
			pmWklej.addActionListener(this);
			pmDolacz.addActionListener(this);
			
			//dodaie ich do popupmenu
			popupMenu.add(pmKopiuj);
			popupMenu.add(pmWklej);
			popupMenu.add(pmDolacz);
			
		//dodanie popupMenu do textArea za pomoc¹ metody setComponentPopupMenu
		notatnik.setComponentPopupMenu(popupMenu);
			
		
		//dodanie JComboBox cbKolor
		cbKolor = new JComboBox<String>();
		cbKolor.setBounds(270, 210, 100, 20);
		add(cbKolor);
		cbKolor.setEnabled(false);
		cbKolor.addActionListener(this);
			
			//dodanie Item-ów
			cbKolor.addItem("Czarny");
			cbKolor.addItem("Czerwony");
			cbKolor.addItem("Zielony");
			cbKolor.addItem("Niebieski");
			
		//button bWybierzKolor
		bWybierzKolor = new JButton("Wybierz Kolor");
		bWybierzKolor.setBounds(30, 250, 150, 20);
		add(bWybierzKolor);
		bWybierzKolor.addActionListener(this);
	}
	
	//methods
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object zrodlo = e.getSource();
		
		if (zrodlo == miOtworz)
		{
			fchFile = new JFileChooser();		//tworzymy obiekt z klasy FileChooser
			
			if(fchFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)		//je¿eli wybrano opcje otwórz i naciœniêto ok
			{
				plik = fchFile.getSelectedFile();
				//JOptionPane.showMessageDialog(null, "Wybrany plik to: " + plik.getAbsolutePath());
				
				try
				{
					Scanner s = new Scanner(plik);		//tworzymy skaner i wczytujemy do niego zawartoœæ pliku
					while( s.hasNext() )
					{
						notatnik.append( s.nextLine() + "\n" );		//do notatnika do³¹cz(append) kolejn¹ linie; na koñcu \n czyli znak koñca linii
					}
				} 
				catch (FileNotFoundException e1)
				{
					e1.printStackTrace();
				}
			}
		}
		else if (zrodlo == miZapisz)
		{
			fchFile = new JFileChooser();		// tworzymy obiekt z klasy FileChooser
			
			if(fchFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				plik = fchFile.getSelectedFile();
				//JOptionPane.showMessageDialog(null, "Wybrany plik to: " + plik);
				
				try
				{
					PrintWriter pw = new PrintWriter(plik);			//zapisuj do obiktu plik
					Scanner s = new Scanner(notatnik.getText());	//text który jest w notatniku pobierz do skanera
					
					while (s.hasNext())
					{
						pw.println( s.nextLine() + "\n" );
					}
					
					pw.close();				//zamkniecie PrintWritera, gdy tego nie damy nic sie do pliku nie zapisze
				} 
				catch (FileNotFoundException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
				
			}
		}
		else if(zrodlo == miZakoncz)
		{
			//metoda show confirm dialog zwraca inta
			int a = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wyjœæ?", "Pytanie", JOptionPane.YES_NO_OPTION);
			
			if(a == JOptionPane.YES_OPTION)
			{
				dispose();		//jezeli wybrane tak to zamknij
			}
			else if (a == JOptionPane.NO_OPTION)
			{
				JOptionPane.showMessageDialog(null, "Wiedzia³em");		//je¿eli nie to poka¿ komunikat
			}
			else if (a == JOptionPane.CLOSED_OPTION)
			{
				JOptionPane.showMessageDialog(null, "Nie zamyka sie w ten sposób");		//je¿eli zamkniety na x to poka¿ komunikat
			}
		}
		else if (zrodlo == chbmiOpcja3)
		{
			if (chbmiOpcja3.isSelected())
			{
				miNarzedzia2.setEnabled(true);
			}
			else
			{
				miNarzedzia2.setEnabled(false);
			}
		}
		else if (zrodlo == miOprogramie)
		{
			//Klasa JOptionPane wywo³uje okna dialogowe
			//a metoda showMessageDialog wywo³uje okinko informacyjne (1 argument wskazuje na siebie - this, 2 argument to treœæ
			// \n to z³amanie lini i przejœcie do nowej linii
			// 3 opcja to tytu³ okienka
			// 4 opcja to ikonka obok informacji
			JOptionPane.showMessageDialog(this, "Program demonstruje wykorzystanie JMenuBar i JMenu \n Wersja 1.0", "Tytu³", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (zrodlo == miNarzedzia1)
		{
			String sMetry = JOptionPane.showInputDialog("Podaj d³ugoœæ w metrach");
			double dMetry = Double.parseDouble(sMetry);
			
			double dStopy = dMetry/0.3048;
			String sStopy = String.format("%.2f", dStopy);			//%.2f oznacza, ¿e cyfra bêdzie obciêta do 2 miejsc po przecinku typu float
			
			JOptionPane.showMessageDialog(null, dMetry + "metrów = " + sStopy + " stóp" );
		}
		else if(zrodlo == miOautorze)
		{
			JOptionPane.showMessageDialog(null, "Piotr Lizak \nBieñkówka", "Autor", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (zrodlo == bSzukaj)
		{
			String text = notatnik.getText();		//pobieramy do zmiennej text zawartosc notatnika
			String szukane = tfSzukaj.getText();	//pobieramy do zmiennej szukaj zawartoœæ szukanego s³owa
			String wynik = "";						//tworzymy zmienna wynik
			
			int i = 0;									//ilosc wyst¹pieñ danego s³owa
			int index;								//numer indexu od któewgo zaczyna sie szukane s³owo w tekscie
			int startIndex = 0;							//numer indexu pocz¹tkowego od którego zaczynamy szukanie
			
			while ( (index = text.indexOf(szukane, startIndex)) != -1)		
			{
				startIndex = index + szukane.length();
				i++;
				wynik = wynik + " " + index;
			}
			
			JOptionPane.showMessageDialog(null, szukane + " wyst¹pi³o " + i + " razy na pozycji: " + wynik);
		}
		else if (zrodlo == pmKopiuj)
		{
			kopiuj = notatnik.getSelectedText();
		}
		else if (zrodlo == pmWklej)
		{
			//korzystamy z metody insert która ma 2 argumenty: 1 - co wstawiamy, 2 - gdzie
			notatnik.insert(kopiuj, notatnik.getCaretPosition());	//metoda getCaretPosition pobiera po³o¿enie kursora 	
		}
		else if (zrodlo == pmDolacz)
		{
			notatnik.append("\n" + kopiuj);		//do³¹czanie skopiowanego tekstu na pocz¹tku nowej linii
		}
		else if (zrodlo == cbKolor)
		{
			String kolor = cbKolor.getSelectedItem().toString();		//za zmienna kolor podstaw to co by³o zaznaczone i konwertuj na stringa
			
			if(kolor.equals("czarny"))
			{
				notatnik.setForeground(Color.BLACK);
			}
			else if(kolor.equals("czerwony"))
			{
				notatnik.setForeground(Color.RED);
			}
			else if(kolor.equals("niebieski"))
			{
				notatnik.setForeground(Color.BLUE);
			}
			else if(kolor.equals("zielony"))
			{
				notatnik.setForeground(Color.GREEN);
			}
		}
		else if (zrodlo == bWybierzKolor)
		{
			Color wybranyKolor = JColorChooser.showDialog(null, "Wybór koloru", Color.GREEN);
			notatnik.setForeground(wybranyKolor);
		}
//		else if (zrodlo == miMetal)
//		{
//			try
//			{
//				UIManager.setLookAndFeel("java.swing.plaf.metal.MetalLookAndFeel");
//			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//					| UnsupportedLookAndFeelException e1)
//			{
//				e1.printStackTrace();
//			}
//			SwingUtilities.updateComponentTreeUI(this);
//		}
//		else if (zrodlo == miNimbus)
//		{
//			try
//			{
//				UIManager.setLookAndFeel("java.swing.plaf.nimbus.NimbusLookAndFeel");
//			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//					| UnsupportedLookAndFeelException e1)
//			{
//				e1.printStackTrace();
//			}
//			SwingUtilities.updateComponentTreeUI(this);
//		}
//		else if (zrodlo == miWindows)
//		{
//			try
//			{
//				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFell");
//			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//					| UnsupportedLookAndFeelException e1)
//			{
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			SwingUtilities.updateComponentTreeUI(this);
//		}
	}
	
	//main method
	public static void main(String[] args)
	{
		Notepad menu = new Notepad();
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setVisible(true);

	}

	

}
