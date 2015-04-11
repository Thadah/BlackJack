package org.pmoo.blackjack;

public class Karta {
		private int zenbakia;
		private int palua;
		
		public Karta(int pZenbakia, int pPalua){
			
			this.palua=pPalua; 
			this.zenbakia=pZenbakia;
			
		}
		
		public void idatziKarta(){
			switch(palua){
				case 1:
					System.out.println(this.zenbakia + "-ko Pika atera duzu.");
				case 2:
					System.out.println(this.zenbakia + "-ko Hirusta atera duzu.");
				case 3:
					System.out.println(this.zenbakia + "-ko Erronboa atera duzu.");
				case 4:
					System.out.println(this.zenbakia + "-ko Bihotza atera duzu.");
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
