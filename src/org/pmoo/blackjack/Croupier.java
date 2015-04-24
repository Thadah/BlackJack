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
		double prob;
		while(!plantatuta && !pasatuta){
			if(this.eskuaKalkulatu() < 21){
				switch(this.eskuaKalkulatu()){
					case 17:
						plantatuta = true;
						break;
					case 18:
						prob = this.probKalkulatu(18);
						if(sartu(prob)){
							this.kartaEskatu();
						}
						else{
							plantatuta = true;
						}
						break;
					case 19:
						prob = this.probKalkulatu(19);
						if(sartu(prob)){
							this.kartaEskatu();
						}
						else{
							plantatuta = true;
						}
						break;
					case 20:
						prob = this.probKalkulatu(20);
						if(sartu(prob)){
							this.kartaEskatu();
						}
						else{
							plantatuta = true;
						}
						break;
					default:
						this.kartaEskatu();
						break;
				}
			}
			else{
				System.out.println("Croupierra pasatu egin da \n");
				pasatuta = true;
				this.erretiratua = true;
			}
					
		}
	}
	
	private double probKalkulatu(int pEskua) throws InterruptedException{
		double prob = 12/52;
		Iterator<Karta> itr = this.getIteradorea();
		Karta nireKarta = null;
		while(itr.hasNext()){
			nireKarta = itr.next();
			if(pEskua + nireKarta.getKartaBalioa() <= 21){
				prob = prob - 1/52;
			}
		}
		return prob;
	}

}
