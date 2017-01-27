import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class HTTPRequest{
	private URL url;
	/**
	 * 
	 * @param url A donde hacer la peticion
	 * @throws MalformedURLException
	 */
	public HTTPRequest(String url) throws MalformedURLException{
		this.url = new URL(url);
	}

	/**
	 * 
	 * @param headers parejas de valores a insertar en la peticion
	 * @return String con la informacion
	 * @throws Exception
	 */
	public String sendGet(String[][] headers) throws Exception {

		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

		for(int i=0;i< headers.length;i++)
			con.setRequestProperty(headers[i][0], headers[i][1]);
		

		return doRequest(con);
	}
	/**
	 * 
	 * @param headers  parejas de valores a insertar en la peticion
	 * @param params String con la informacion en la url
	 * @return String con la informacion
	 * @throws Exception
	 */
	public String sendPost(String[][] headers, String params) throws Exception {

		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

		con.setRequestMethod("POST");
		for(int i=0;i< headers.length;i++)
			con.setRequestProperty(headers[i][0], headers[i][1]);
		
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(params);
		wr.flush();
		wr.close();

		return doRequest(con);

	}
	
	private String doRequest(HttpsURLConnection con) throws IOException{
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) 
			response.append(inputLine);
		
		in.close();
		
		return response.toString();
	}

}
