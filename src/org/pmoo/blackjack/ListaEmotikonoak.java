package org.pmoo.blackjack;
import java.util.*;

public class ListaEmotikonoak {
		public static ListaEmotikonoak helbidea=null;
		private ArrayList<String> lista=null;
		
		private ListaEmotikonoak(){
			this.lista=new ArrayList<String>();
		}
		
		public static synchronized ListaEmotikonoak getNireListaEmotikonoak(){
			if(ListaEmotikonoak.helbidea==null){
				helbidea=new ListaEmotikonoak();
			}
			return helbidea;
		}
		private Iterator<String> getIteradorea(){
			return ListaEmotikonoak.getNireListaEmotikonoak().lista.iterator();
		}
		public void gehituEmotikonoa(String pEmo){
			boolean bai=false;
			bai=ListaEmotikonoak.getNireListaEmotikonoak().emotikonoaBadago(pEmo);
			if(!bai){
			ListaEmotikonoak.getNireListaEmotikonoak().lista.add(pEmo);
			}
			
		}
		
		private boolean emotikonoaBadago(String pEmo){
			boolean bai=false;
			Iterator<String> itr=ListaEmotikonoak.getNireListaEmotikonoak().getIteradorea();
			String emotikonoa="@";
			while(itr.hasNext()&&!bai){
				emotikonoa=itr.next();
				if(emotikonoa==pEmo){
					bai=true;
				}
			}
			return bai;
		}
		
		public int listarenTamaina(){
			return ListaEmotikonoak.getNireListaEmotikonoak().lista.size();
		}
}
