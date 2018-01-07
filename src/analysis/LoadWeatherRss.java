package analysis;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LoadWeatherRss {

	private String nowWeather;
	private Map<String, String> weekWeather = new TreeMap<String, String>();


//	public static void main(String[] args){
//		testLoadXML testLoadXML = new testLoadXML();
//		System.out.println("======"+testLoadXML.getNowWeather());
//		Map<String, String> map1 = testLoadXML.getWeekWeather();
//		for(String key : map1.keySet()){
//			System.out.println("Key : "+ key.toString() + " Value : "+ map1.get(key));
//		}
//	}
	
	public Map<String, String> getWeekWeather(){
		return this.weekWeather;
	}
	
	public String getNowWeather(){
		return this.nowWeather;
	}
	
	public LoadWeatherRss() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		Document doc = null;
		try {
			db = dbf.newDocumentBuilder();
			doc = db.parse("http://www.cwb.gov.tw/rss/forecast/36_01.xml");
			// doc = db.parse("http://news.ltn.com.tw/rss/sports.xml");
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			System.out.println("error");
			e.printStackTrace();
		}
		// 取得所有item元素

		NodeList nList = doc.getElementsByTagName("item");
		String title;
		String[] description;
		for (int temp = 0; temp < nList.getLength(); temp++) {

			// 取得該item裡的元素

			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				String descriptions = getTagValue("description", eElement);
				if (temp == 0) {
					title = getTagValue("title", eElement);
					// System.out.println(title);
					title = title.trim().split(" ")[2];
					if (title.endsWith("雨")) {
						nowWeather = "雨";
						
						// System.out.println("雨");
					} else {
						nowWeather = "晴";
						// System.out.println("晴");
					}
				}
				if (temp == 1) {
					description = descriptions.trim().split("<BR>");
					for (int i = 0; i < description.length; i = i + 2) {

						String date = description[i].trim().split(" ")[0];
						if ((description[i].trim().endsWith("雨")) || (description[i + 1].trim().endsWith("雨"))) {
							weekWeather.put(date, "雨");
						} else {
							weekWeather.put(date, "晴");
						}

					}
					

				}
			}
		}
		

	}

	// 取得元素的值(過濾掉cdata-section等標記)

	private static String getTagValue(String sTag, Element eElement) {

		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();

	}

}
