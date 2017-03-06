package TwoWindows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PasswordTest extends JFrame implements ActionListener
{
	
	//variables
	private PanelHaslo panelHaslo;		//obiekt z klasy ponizej
	
	private JTextArea taNotatnik;
	private JScrollPane scrollPane;
	private JButton bDodajUzytkownika;
	
	//constructor
	public PasswordTest()
	{
		setSize(400, 400);
		setTitle("Aplikacja z dwoma okienkami");
		setLayout(null);
		
		//utworzenie obiektów
		taNotatnik = new JTextArea();
		taNotatnik.setWrapStyleWord(true);				//lamanie linii - wordWrap
		
		scrollPane = new JScrollPane(taNotatnik); 
		scrollPane.setBounds(0, 0, 300, 300);
		add(scrollPane);
		
		bDodajUzytkownika = new JButton("Dodaj u¿ytkownika");
		bDodajUzytkownika.setBounds(0, 330, 200, 20);
		add(bDodajUzytkownika);
		bDodajUzytkownika.addActionListener(this);
		
	}
	
	//methods
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (panelHaslo == null)				//jezeli nie mamy obiektu panelHaslo to go tworzymy
		{
			panelHaslo = new PanelHaslo(this);
		}
		
		panelHaslo.setVisible(true);		//ustawiamy go na widoczny 
		panelHaslo.setFocus();				//focus czyli kursor jest ustawiony na to pole tekstowe ¿eby nie trzeba by³o klikaæ tylko od razu pisaæ 
		
		if(panelHaslo.isOK())				//jezeli w oknie obiektu 2 (panelHaslo) klikniemy OK to
		{
			taNotatnik.append(panelHaslo.getUser() + ", ");		//dodanie (append) do notatnika nazwy usera z klasy panelHaslo
			taNotatnik.append(panelHaslo.getHaslo() + "\n");
		}
	}
	
	
	public static void main(String[] args)
	{
		PasswordTest pt = new PasswordTest();
		pt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pt.setVisible(true);

	}

}


//tworzymy 2 klase - czyli 2 okienko
class PanelHaslo extends JDialog implements ActionListener
{
	//variables
	private JLabel lUser, lHaslo;
	private JTextField tfUser;
	private JPasswordField pfHaslo;
	private JButton bOK, bCancel;
	private boolean okData;
	
	//constructor
	public PanelHaslo(JFrame owner)		//JFrame owner mówi, ¿e odwo³ujemy sie do elementu nadrzednego
	{
		//metod¹ super odwo³ujemy siê do klasy nadrzednej JDialog
		//metoda ta przyjmuje 3 argumenty: 1 - owner; 2 - tytu³ dialogu; 3 - czy dalog ma byæ modalny czy niemodalny (modlany - musimy zatwierdziæ, aby aplikacja dalej dzia³a³a)
		super(owner, "Wprowadzenie has³a", true);	
		
		setSize(300, 200);
		setLayout(null);
		
		//dodanie obiektow
		lUser = new JLabel("U¿ytkownik: ");
		lUser.setBounds(20, 10, 100, 20);
		add(lUser);
		
		lHaslo = new JLabel("Has³o: ");
		lHaslo.setBounds(20, 50, 100, 20);
		add(lHaslo);
		
		tfUser = new JTextField("");
		tfUser.setBounds(150, 10, 100, 20);
		add(tfUser);
		tfUser.addActionListener(this);
		
		pfHaslo = new JPasswordField("");
		pfHaslo.setBounds(150, 50, 100, 20);
		add(pfHaslo);
		pfHaslo.addActionListener(this);
		
		bOK = new JButton("OK");
		bOK.setBounds(20, 100, 100, 20);
		add(bOK);
		bOK.addActionListener(this);
		
		bCancel = new JButton("Cancel");
		bCancel.setBounds(150, 100, 100, 20);
		add(bCancel);
		bCancel.addActionListener(this);
	}

	//methods
	
	//aby przekazaæ dane z okienka Panel Has³o do PasswordTest nie mo¿emy sie odwo³aæ bezpoœrednio do pól tego okna, tylko trzeba stworzyæ metody
	public String getUser()
	{
		return tfUser.getText(); 
	}
	
	public String getHaslo()
	{
		return new String(pfHaslo.getPassword());		//musi byæ tak, czyli nowy ³añcuch
	}
	
	public boolean isOK()
	{
		return okData;
	}
	
	public void setFocus()
	{
		tfUser.requestFocusInWindow();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object zrodlo = e.getSource();
		
		if(zrodlo == bOK)
		{
			okData = true;				//nacisniecie buttona ok
		}
		else
		{
			okData = false;				//naciesniecie cancel
		}
		
		setVisible(false);		//ukrycie tego 2 okna gdy nacisniety zostanie button OK lub Cancel
	}
	
}
