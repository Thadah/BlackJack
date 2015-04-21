package org.pmoo.blackjack;

public class Croupier extends Jokalaria {

	public Croupier(String pIzena) {
		super(pIzena);
		this.izena = "Croupier";
		this.eskua = new ListaKartak();
		this.dirua = Integer.MAX_VALUE;
		this.erretiratua = false;
	}
	
	public void txanda() throws InterruptedException{
		BlackJack mahaia = BlackJack.getNireBlackJack();
		boolean plantatuta = false;
		boolean pasatuta = false;
		this.apostua = mahaia.getApostuMax();
		while(!plantatuta && !pasatuta){
			if (this.eskuaKalkulatu() < 17){
				this.kartaEskatu();
			}
			
			if(this.eskuaKalkulatu() == 17){
				plantatuta = true;
			}
			
			if(this.eskuaKalkulatu() > 17){
				if(Math.random() > 0.5){
					this.kartaEskatu();
				}
				else{
					plantatuta = true;
				}
			}
			
			if(this.eskuaKalkulatu() > 21){
				pasatuta = true;
				this.erretiratua = true;
			}
			
		}
	}

}
