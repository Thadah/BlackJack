package org.pmoo.blackjack;

import java.util.Iterator;

public class Croupier extends Jokalaria {

	public Croupier(String pIzena) {
		super(pIzena);
		this.izena = "Croupier";
		this.eskua = new ListaKartak();
		this.dirua = Integer.MAX_VALUE;
		this.erretiratua = false;
	}
	
	private Iterator<Karta> getIteradorea(){
		return this.eskua.getIteradorea();
	}
	
	private boolean sartu(double pProb){
		boolean bai = false;
		double probabilitatea = Math.random();
		if(probabilitatea <= pProb){
			bai = true;
		}
		return bai;		
	}
	
	public void apostuaEgin(){
		BlackJack mahaia = BlackJack.getNireBlackJack();
		mahaia.setBotea(mahaia.getBotea() + (mahaia.getApostuMax() - this.apostua));
	}
	
	public void apostuaIkusi(){
		this.apostuaEgin();
	}
	
	public void txanda() throws InterruptedException{
		boolean plantatuta = false;
		boolean pasatuta = false;
		while(!plantatuta && !pasatuta){
			if (this.eskuaKalkulatu() < 17){
				this.kartaEskatu();
			}
			
			else if(this.eskuaKalkulatu() == 17){
				plantatuta = true;
			}
			
			else if(this.eskuaKalkulatu() > 17 && this.eskuaKalkulatu() < 21){
				double prob = 12/52;
				switch (this.eskuaKalkulatu()){
					case 19:
						prob = 8/52;
						break;
					case 20:
						prob = 4/52;
						break;
					default:
						break;
				}
				Iterator<Karta> itr = this.getIteradorea();
				Karta nireKarta = null;
				while(itr.hasNext()){
					nireKarta = itr.next();
					if(this.eskuaKalkulatu() + nireKarta.getKartaBalioa() <= 21){
						prob = prob - 1/52;
					}
				}
				if(sartu(prob)){
					this.kartaEskatu();
				}
			}
			else if (this.eskuaKalkulatu() == 21){
				plantatuta = true;
			}
			else{
				pasatuta = true;
				this.erretiratua = true;
			}
		}
	}

}
