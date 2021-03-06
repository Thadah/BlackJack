package org.pmoo.blackjack;

import java.util.Iterator;

public class Croupier extends Jokalaria {

	//Eraikitzailea
	public Croupier(String pIzena) {
		super(pIzena);
		this.izena = "Croupier";
		this.eskua = new ListaKartak();
		this.dirua = (Integer.MAX_VALUE)-10000;
		this.erretiratua = false;
		this.apostua = 0;
	}
	
	//Beste metodoak
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
		this.apostua = mahaia.getApostuMax()  - this.apostua;
		mahaia.setBotea(mahaia.getBotea() + this.apostua);
		this.dirua = this.dirua - this.apostua;
	}
	
	public void apostuaIkusi(){
		this.apostuaEgin();
	}
	
	public boolean galdetuJoan(){
		return false;
	}
	
	public void txanda() throws InterruptedException{
		boolean plantatuta = false;
		boolean pasatuta = false;
		double prob;
		System.out.println(this.izena + "-ren txanda da.\n");
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
			else if(this.eskuaKalkulatu() == 21){
				plantatuta = true;
			}
			else{
				System.out.println("Croupierra pasatu egin da \n");
				pasatuta = true;
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
	
	public void diruaInprimatu(){
		System.out.print("\n");
	}


}
