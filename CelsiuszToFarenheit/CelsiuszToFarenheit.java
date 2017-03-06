package CelsiuszToFarenheit;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//ActionListener natychmiast zmienia wlaœciwoœci obiektu (np po zaznaczeniu chcek
public class CelsiuszToFarenheit extends JFrame implements ActionListener
{

	// variables
	private JButton bKonwertuj;
	private JLabel lCelsiusz, lFarenheit;
	private JTextField tfCelsiusz, tfFarenheit;
	private ButtonGroup buttonGroup;
	private JRadioButton rbToFarenheit, rbToCelsiusz;
	//private JCheckBox chbWielkie;
	private double Celsiusz, Farenheit;

	// constructor
	public CelsiuszToFarenheit()
	{
		setSize(330, 280); 									// ustaw rozmiar
		setTitle("Przeliczanie stopni");					//ustaw tytul
		setLayout(null);									//ustaw layout

		// label lCelsiusz
		lCelsiusz = new JLabel("Stopnie Celsiusza: "); 		// utworzenie przyciusku
		lCelsiusz.setBounds(20, 30, 120, 20); 				// polozenie + wymiary
		add(lCelsiusz);										//dodanie labela do layoutu

		// label lFarenheit
		lFarenheit = new JLabel("Stopnie Farenheita: "); 	// utworzenie
		lFarenheit.setBounds(20, 80, 120, 20); 				// polozenie + wymiary
		add(lFarenheit);
		

		// textField tfCelsiusz
		tfCelsiusz = new JTextField("");
		tfCelsiusz.setBounds(150, 30, 100, 20);
		tfCelsiusz.setBackground(Color.cyan);
		add(tfCelsiusz);
		tfCelsiusz.addActionListener(this);										//dodanie actionListenera
		tfCelsiusz.setToolTipText("Wpisz temperature w stopniach Celsiusza");	//setToolTip umo¿liwia wpisanie podpowiedzi

		// textField tfFarenheit
		tfFarenheit = new JTextField("");
		tfFarenheit.setBounds(150, 80, 100, 20);
		tfFarenheit.setBackground(Color.cyan);
		add(tfFarenheit);
		tfFarenheit.addActionListener(this);
		tfFarenheit.setToolTipText("Wpisz temperature w stopniach Farenheita");
		
		//button bKonwertuj
		bKonwertuj = new JButton("Konwertuj");
		bKonwertuj.setBounds(20, 120, 100, 20);
		add(bKonwertuj);
		bKonwertuj.addActionListener(this);
		
		//chcekBox chWielkie
//		chbWielkie = new JCheckBox("Wielka czcionka");
//		chbWielkie.setBounds(130, 120, 120, 20);
//		add(chbWielkie);
		//chbWielkie.addActionListener(this);					//czyli mo¿na zaimplementowaæ przez ActionListenera albo poprzez sprawdzenie czy jest zaznaczony i dodanie go do insrtukcji przycisku konwertuj
		
		//utworzenie ButtonGroup
		buttonGroup = new ButtonGroup();
		
		//dodawanie radioButtonow do ButtonGroup
		rbToFarenheit = new JRadioButton("Przelicz z Celsiusza na Farenheita", true);
		rbToFarenheit.setBounds(30, 160, 220, 20);
		buttonGroup.add(rbToFarenheit);							//do buttonGroup dodaj rbToFarenheit
		add(rbToFarenheit);
		
		rbToCelsiusz = new JRadioButton("Przelicz z Farenheita na Celsiusza", false);
		rbToCelsiusz.setBounds(30, 200, 220, 20);
		buttonGroup.add(rbToCelsiusz);
		add(rbToCelsiusz);
		

	}

	public static void main(String[] args)
	{
		CelsiuszToFarenheit celsiusz2farenheit = new CelsiuszToFarenheit();
		celsiusz2farenheit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		celsiusz2farenheit.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object zrodlo = e.getSource();
		
		if(zrodlo == bKonwertuj)
		{
			if(rbToFarenheit.isSelected())				//jezeli radioButton jest zaznaczony
			{
				Celsiusz = Double.parseDouble(tfCelsiusz.getText());		//odczytanie wartoœci Celsiusza z textField i parsowanie Stringa na Double
				Farenheit = 32.0 + (9.0/5.0) * Celsiusz;
				
				tfFarenheit.setText(String.valueOf(Farenheit));				//parsowanie Double na Stringa
				
				//tfFarenheit.setFont(new Font("SansSerf", Font.BOLD, 20));
			}
			else if(rbToCelsiusz.isSelected())
			{
				Farenheit = Double.parseDouble(tfFarenheit.getText());
				Celsiusz = Math.round((Farenheit - 32.0) * (5.0/9.0));
				
				tfCelsiusz.setText(String.valueOf(Celsiusz));
				
				//tfFarenheit.setFont(new Font("SansSerf", Font.PLAIN, 12));
			}
		}
		
		if (zrodlo == tfCelsiusz)
		{

			Celsiusz = Double.parseDouble(tfCelsiusz.getText());		//odczytanie wartoœci Celsiusza z textField i parsowanie Stringa na Double
			Farenheit = 32.0 + (9.0/5.0) * Celsiusz;
			
			tfFarenheit.setText(String.valueOf(Farenheit));				//parsowanie Double na Stringa
		}
		else if (zrodlo == tfFarenheit)
		{
			Farenheit = Double.parseDouble(tfFarenheit.getText());
			Celsiusz = Math.round((Farenheit - 32.0) * (5.0/9/0));
			
			tfCelsiusz.setText(String.valueOf(Celsiusz));
		}
		
	}

}
