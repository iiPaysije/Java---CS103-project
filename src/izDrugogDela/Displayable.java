package izDrugogDela;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Korisnik
 */
public interface Displayable {
		public int getX(); 

		public int getY();  
	}


/*U cilju
da se prikaže graf, neophodno je da se zna pozicija na ekranu gde če svaki od
čvorova biti prikazan. U tu svhu će biti definisan interfejs Displayable koji ima
metode za dobijanje informacija o X i Y koordinati, kao i imenu čvora (u ovom slučaju
grada), dok čvorovi treba da budu instance interfejsa.*/