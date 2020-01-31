import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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


public class Principal {
	
	
	public static void enviar() {
		
		
		HttpClient httpclient = HttpClients.createDefault();
        
		HttpPost httppost = new HttpPost("http://localhost:8080/teste.php/funcionario/gravar");
		         
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
		
		HttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet("http://localhost:8080/api.php/estoque/mostrar");
		HttpDelete httpDelete = new HttpDelete("http://localhost:8080/api.php"); 
		HttpPost httpPost = new HttpPost("http://localhost:8080/api.php/funcionarios?cidade=saopaulo&bairro=centro");
		HttpPut httpPut = new HttpPut("http://localhost:8080/api.php");
		HttpOptions httpOptions = new HttpOptions("http://localhost:8080/api.php");
		
		httpget.addHeader("bairro", "arrozal");
		httpget.addHeader("bairro", "volta redonda");
		httpget.addHeader("bairro", "paraty");
		
		HttpMessage httpMessage;
		
		Header header; 
		
		header = httpget.getFirstHeader("bairro");
		System.out.print(  " - " + header.getValue() );
		
		header = httpget.getLastHeader("bairro");
		System.out.print(  " - " + header.getValue() );
		
		httpPost.addHeader("teste", "teste01");
	
		try {
		    // teste dos metodos  
			HttpResponse response = httpclient.execute( httpPost );
			
			Header[] cabecalhos = response.getAllHeaders(); 
			
			System.out.println("\n Total de Cabecalhos:" +  cabecalhos.length );
			
			System.out.println("Cabecalho:" +  cabecalhos[0] );
			System.out.println("Cabecalho:" +  cabecalhos[1] );
			System.out.println("Cabecalho:" +  cabecalhos[2] );
			System.out.println("Cabecalho:" +  cabecalhos[3] );
			System.out.println("Cabecalho:" +  cabecalhos[4] );
			
				
	    
		    StatusLine status = response.getStatusLine();
		    
		    //JOptionPane.showMessageDialog( null, status.getStatusCode() ); 
		    
		    
		    if( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
		        HttpEntity entity = response.getEntity();
		        
		        //System.out.println("Tamanho: "+entity.getContentLength() );
		        //System.out.println("Content-type: "+entity.getContentType().getValue() );
		                   
		        InputStream in = entity.getContent();             
		        Scanner scan = new Scanner( in );
		        
		        while( scan.hasNext() ){
		            System.out.println( scan.nextLine() );
		        } 
		  }
    
		    
		    //System.out.print( status.toString() ); 

		 
		    //System.out.print( response.toString() ); 
		 
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
