package RownanieKwadratowe;

import java.util.Scanner;

public class RownanieKwadratowe
{
	//variables
	private int a, b, c;
	double delta, x1, x2;

	
	//konstruktor
	public RownanieKwadratowe(int a, int b, int c)
	{
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	//methods
	public String rozwiaz()
	{
		String wynik = "";
		
		delta = b*b - (4*a*c);
		if(delta > 0)
		{
			delta = Math.sqrt(delta);
			x1 = Math.round((b-delta)/2*a);
			x2 = Math.round((b+delta)/2*a);
			wynik = "To rownanie ma dwa rozwiazania: " + x1 + ", oraz " + x2;
		}
		else if(delta == 0)
		{
			delta = Math.sqrt(delta);
			double x0 = Math.round( -b /(2*a) );
			wynik = "To równanie ma jedno rozwi¹zanie: " + x0;
		}
		else if (delta < 0)
		{
			wynik = "To równanie nie ma rozwi¹zañ w ramach liczb rzeczywistych";
		}
		return wynik;
	}

	public static void main(String[] args)
	{
		RownanieKwadratowe rownanie = new RownanieKwadratowe(1,4,1);
		System.out.println(rownanie.rozwiaz());

	}

}
