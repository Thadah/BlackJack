package org.pmoo.blackjack;

public class Karta {
		private int zenbakia;
		private int palua;
		
		public Karta(int pZenbakia, int pPalua){
			
			this.palua=pPalua; 
			this.zenbakia=pZenbakia;
			
		}
		

		public String idatziPalua(){
			String paluIzena = null;
			switch(this.palua){
				case 1:
					paluIzena = "Pika";
					break;
				case 2:
					paluIzena = "Hirusta";
					break;
				case 3:
					paluIzena = "Erronbo";
					break;
				case 4:
					paluIzena = "Bihotz";
					break;
			}
			return paluIzena;
			
		}
		
		public int getKartaBalioa(){
			return this.zenbakia;
		}
		
		public int getKartaPalua(){
			return this.palua;
		}
		
		public boolean batekoa(){
			if (this.zenbakia == 1){
				return true;
			}else{return false;}
		}
}
