import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.nodes.Document;

public class MainStock {
	public static void main(String[] args) throws IOException, InterruptedException {

		Scanner scanner = new Scanner(System.in);

		ArrayList<GetStock> stocks = new ArrayList<GetStock>();

		System.out.println("How fast do you want updates? As an example: " +
				"\nIf you want updates every 5 seconds, press 5 and then press enter.");

		int intervalBetweenUpdatesUserInput = scanner.nextInt();

		System.out.println("You entered " + intervalBetweenUpdatesUserInput + ". The program will try to fetch updates every " + intervalBetweenUpdatesUserInput + " seconds.");
		int interval = intervalBetweenUpdatesUserInput * 1000;

		Document doc = null;
		
		// Get the URL via google.com/finance
		GetStockImpl Vestas = new GetStockImpl("https://www.google.com/finance?q=CPH%3AVWS&ei=47PYVOGVGKeZwQOd8oCYCQ", doc);
		GetStockImpl BAS = new GetStockImpl("https://www.google.com/finance?q=NYSE%3ABAS&ei=WLzTVPnsIqGCwAP23oHoDA", doc);
		GetStockImpl Pandora = new GetStockImpl("https://www.google.com/finance?q=CPH%3APNDORA&ei=QbrYVPnsIuL3wAOYtYCYDQ", doc);
		GetStockImpl Seadrill =  new GetStockImpl("https://www.google.com/finance?q=STO%3ASDRLO&ei=B3JKVfnmJIbi8QPzi4CgDQ", doc);
		
		stocks.add(BAS);
		stocks.add(Vestas);
		stocks.add(Pandora);
		stocks.add(Seadrill);
		
		while(true){
			for(GetStock stock : stocks){
				System.out.println(stock.prettyPrint());
			}
			
			Thread.sleep(interval);
		}
	}
}