import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class ManipulaJSon {
	
	
	public void criarJSon() { 

		JSONObject jsonObjeto;
		
		List lista = new LinkedList(); 
		
		jsonObjeto = new JSONObject();
		jsonObjeto.put("codigo", "1"); 
		jsonObjeto.put("nome","sidney francisco");
		lista.add( jsonObjeto ); 
		
		jsonObjeto = new JSONObject();
		jsonObjeto.put("codigo", "2"); 
		jsonObjeto.put("nome","jose de souza");
		
		lista.add( jsonObjeto );
		
		// Converter Lista JSON para o formato String 
		String sJson = JSONValue.toJSONString(lista); 
		
		
		// Converte a String para o Formato de Lista JSON
		lista = (List) JSONValue.parse( sJson );
		
		jsonObjeto = (JSONObject) lista.get(1);
		
		System.out.print( jsonObjeto.toString() );
		
		//System.out.println(sJson);

		//System.out.print( jsonObjeto.toString() ); 
		
	}
	
	
	public String obterJSon() { 
		
		HttpClient httpclient = HttpClients.createDefault();
		//HttpGet httpget = new HttpGet("http://www.botecodigital.info/exemplos/teste_requisicao.php");
		
		HttpGet httpget = new HttpGet("http://localhost:8080/api.php");	
		
		String sJSon = null; 
		
	
		try {
		    HttpResponse response = httpclient.execute(httpget);
	    
		    StatusLine status = response.getStatusLine();
		    
		    
		    if( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
		        HttpEntity entity = response.getEntity();
		       
		        
		        //System.out.println("Tamanho: "+entity.getContentLength() );
		        //System.out.println("Content-type: "+entity.getContentType().getValue() );
		                   
		        InputStream in = entity.getContent();             
		        Scanner scan = new Scanner( in );
		        
		        while( scan.hasNext() ){
		            //System.out.println( scan.nextLine() );
		            sJSon = scan.nextLine(); 
		        } 
		  }
    
		    
		    //System.out.print( status.toString() ); 

		    
		 
		   // System.out.print( response.toString() ); 
		 
		} catch (ClientProtocolException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    httpget.releaseConnection();;
		}
		
		
		return sJSon;

	}
	

	public void converterJSon() { 

		JSONObject jsonObjeto;
		
		List lista = new LinkedList(); 
		
		String sJson; 
		sJson = obterJSon();
		
		lista = (List) JSONValue.parse( sJson );
		
		jsonObjeto = (JSONObject) lista.get(1);
		
		System.out.print( jsonObjeto.toString() );
		
	}

	
	

}
