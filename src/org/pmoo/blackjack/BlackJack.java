package org.pmoo.blackjack;

public class BlackJack {
	
	private static BlackJack helbidea = null;
	private int apostuMax;
	private int botea;
	private int txanda;

	private BlackJack(){}
	
	public static synchronized BlackJack getNireBlackJack(){
		if(helbidea == null){
			helbidea = new BlackJack();
		}
		return helbidea;
	}
	
	public void partidaJolastu(){
		//TODO
	}
	
	private void irabazleaKalkulatu(){
		//TODO
	}
	
	private void partidaAmaitu(){
		//TODO
	}
	
	private void apostuaEgin(){
		//TODO
	}
	
	private void kartakJolastu(){
		//TODO
	}
	
	
	//TODO
}

