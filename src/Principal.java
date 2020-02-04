import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParamConfig;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class Principal {
	
	
	public static void enviar() {
		
		
		HttpClient httpclient = HttpClients.createDefault();
        
		//HttpPost httppost = new HttpPost("http://localhost:8080/teste.php/funcionario/gravar");
		HttpPost httppost = new HttpPost("http://www.meuappfinanceiro.com.br/api.php");
		         
		try { 
		    ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
		    valores.add(new BasicNameValuePair("nome", "rodrigo"));
		    valores.add(new BasicNameValuePair("sobrenome", "aramburu"));
		             
		    httppost.setEntity( new UrlEncodedFormEntity( valores ) );
		    HttpResponse response = httpclient.execute( httppost );
		              
		    HttpEntity entity = response.getEntity();
		    String content = EntityUtils.toString(entity);
		    System.out.println( content );
		             
		} catch (ClientProtocolException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    httppost.releaseConnection();;
		}
		
	}
	
	public static void requisitar() { 
		
		String sCaminho = "http://www.meuappfinanceiro.com.br/api.php";
		HttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet( sCaminho );
		
		HttpDelete httpDelete = new HttpDelete("http://localhost:8080/api.php"); 
		HttpPost httpPost = new HttpPost("http://localhost:8080/api.php/funcionarios?cidade=saopaulo&bairro=centro");
		HttpPut httpPut = new HttpPut("http://localhost:8080/api.php");
		HttpOptions httpOptions = new HttpOptions("http://localhost:8080/api.php");
		
		
		HttpMessage httpMessage;
		
	
		try {
		    // teste dos metodos  
			HttpResponse response = httpclient.execute( httpget );
			
		    
			
			StatusLine status = response.getStatusLine();
		    
		    //JOptionPane.showMessageDialog( null, status.getStatusCode() ); 
		    
		    if( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
		        HttpEntity entity = response.getEntity();
		        
          
		        InputStream in = entity.getContent();             
		        Scanner scan = new Scanner( in );
		        
		        int iSeq = 0;
		        String sLinha = ""; 
		        
		        while( scan.hasNext() ){
		            
		        	iSeq++; 
		        	
		        	sLinha = scan.nextLine(); 
		        	
		        	if ( ! sLinha.isEmpty() ) { 
		        	  System.out.println( sLinha );
		        	}
		        }
		        
		        
		        List lista = new LinkedList(); 
		        lista = (List) JSONValue.parse( sLinha );
		        
		        JSONObject jsonObjeto;
		        
		         		        
		        for ( int i=0; i < lista.size(); i++ ) { 
		        
		        	jsonObjeto = (JSONObject) lista.get(i);
		        	
		        	System.out.println(	jsonObjeto.get("id") + " - " + jsonObjeto.get("data") ); 
		        	
		        }
		        	
		        	
		          
		        
		         
		        
		        
		        
		        
		        
		        
		        
		        
		        
		  }
    
		 
		} catch (ClientProtocolException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    httpget.releaseConnection();
		}
		
		
	}
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//enviar();
		
		requisitar();
		
		
		/*
		ManipulaJSon manipulaJSon = new ManipulaJSon();
		//manipulaJSon.criarJSon();

		
		manipulaJSon.converterJSon();
		
		*/

		
		
		
		
	}

}
