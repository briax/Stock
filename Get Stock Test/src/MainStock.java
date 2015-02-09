import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;

public class MainStock {
	public static void main(String[] args) throws IOException, InterruptedException {
		
		ArrayList<GetStock> stocks = new ArrayList<GetStock>();
		
		Document doc = null;
		
		// Get the URL via google.com/finance
		GetStockImpl Vestas = new GetStockImpl("https://www.google.com/finance?q=CPH%3AVWS&ei=47PYVOGVGKeZwQOd8oCYCQ", doc);
		GetStockImpl BAS = new GetStockImpl("https://www.google.com/finance?q=NYSE%3ABAS&ei=WLzTVPnsIqGCwAP23oHoDA", doc);
		GetStockImpl Pandora = new GetStockImpl("https://www.google.com/finance?q=CPH%3APNDORA&ei=QbrYVPnsIuL3wAOYtYCYDQ", doc);
		
		stocks.add(BAS);
		stocks.add(Vestas);
		stocks.add(Pandora);
		
		while(true){
			for(GetStock stock : stocks){
				System.out.println(stock.prettyPrint());
			}
			
			Thread.sleep(3000);
		}
	}
}