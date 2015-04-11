package org.pmoo.blackjack;

public class Karta {
		private int zenbakia;
		private int palua;
		
		public Karta(int pZenbakia, int pPalua){
			
			this.palua=pPalua; 
			this.zenbakia=pZenbakia;
			
		}
		
		public void idatziKarta(){
			switch(this.palua){
				case 1:
					System.out.println(this.zenbakia + "-ko Pika atera duzu.");
					break;
				case 2:
					System.out.println(this.zenbakia + "-ko Hirusta atera duzu.");
					break;
				case 3:
					System.out.println(this.zenbakia + "-ko Erronboa atera duzu.");
					break;
				case 4:
					System.out.println(this.zenbakia + "-ko Bihotza atera duzu.");
					break;
			}
		}
		
		public int getKartaBalioa(){
			return this.zenbakia;
		}
		
		public boolean batekoa(){
			if (this.zenbakia == 1){
				return true;
			}else{return false;}
		}
}
