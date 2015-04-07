package org.pmoo.blackjack;

public class Karta {
		private int zenbakia;
		private int palua;
		
		public Karta(int pZenbakia, int pPalua){
			
			this.palua=pPalua; 
			this.zenbakia=pZenbakia;
			
		}
		
		public void idatziKarta(){
			System.out.println("zure kartaren balioa hau da:"+this.zenbakia);
			System.out.println("zure kartaren palua hau da:"+this.palua);
		}
		
		public int getKartaBalioa(){
			return this.zenbakia;
		}
}
