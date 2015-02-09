import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class GetStockImpl implements GetStock{
	
	private String URL;
	private Document doc = null;
	
	public GetStockImpl(String URLforStock, Document docH){
		URL = URLforStock;
		doc = docH;
		try{
			doc = Jsoup.connect(URL).timeout(0).get();	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public String getName() {
		Element shareboxData = doc.getElementById("sharebox-data");
		// \r and \n covers both UNIX and Windows
		String[] splitShareBoxIntoLines = shareboxData.toString().split("\\r?\\n");
		
		int counter = 0;
		
		for(String s : splitShareBoxIntoLines){
			if(counter == 1){
				int endIndex = s.length();
				int beginIndex = 32;
				return s.substring(beginIndex, endIndex-3);
			}else{
				counter++;
			}
		}
		
		return "Something went wrong in getName()";
	}

	@Override
	public String getCurrentPrice() {
		try {
			doc = Jsoup.connect(URL).timeout(0).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements pr = doc.getElementsByClass("pr");
		String toParse = pr.toString();
		return Jsoup.parse(toParse).text();
	}

	@Override
	public String getHigh() {
		Elements snapData = doc.getElementsByClass("snap-data");
		String rawHTML = snapData.toString();
		String processedHTML = Jsoup.parse(rawHTML).text();
		String[] output = processedHTML.split(" ");
		
		int counter = 0;
				
		for(String s : output){
			if(counter == 3){
				return s;
			}else{
				counter ++ ;
			}
		}
		return null;
	}

	@Override
	public String getLow() {
		Elements snapData = doc.getElementsByClass("snap-data");
		String rawHTML = snapData.toString();
		String processedHTML = Jsoup.parse(rawHTML).text();
		String[] output = processedHTML.split(" ");
		
		int counter = 0;
				
		for(String s : output){
			if(counter == 1){
				return s;
			}else{
				counter ++ ;
			}
		}
		return null;
	}
	// For test-purpose
	@Override
	public String getFullHTMLPage() {
		return doc.toString();
	}

	// Some pretty shizzle
	@Override
	public String prettyPrint() {
		return "\n\t" + getName() + "\nCurrent: " + "\t" + getCurrentPrice() + 
				"\nHigh: " + "\t\t" + getHigh() + "\nLow: " + "\t\t" + getLow();
	}
}
