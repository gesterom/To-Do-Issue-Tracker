package HttpProtocol;

import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.BiConsumer;

public class HttpRespond {
	public HttpCodes code;
	public HashMap<String,String> headers;
	public byte[] body;
	public HttpRespond(HttpCodes code) {
		this.code = code;
		this.headers = new HashMap<>();
		this.body = null;
	}
	
	public void setBody(String body) {
		this.body = body.getBytes();
		headers.put("Content Length", String.valueOf( this.body.length ) ) ;
	}
	public void setBody(byte[] array) {
		this.body = array;
		headers.put("Content Length", String.valueOf( this.body.length ) ) ;
	}
	public void addHeader(String key, String value) {
		headers.put(key,value) ;
	}
	
	public byte[] getByteArray() {
		String res = new String();
		
		res += code.statusLine();
		class Func implements BiConsumer<String,String>{

			private String res;
			
			public Func() {
				res = new String();
			}
			
			@Override
			public void accept(String key, String value) {
				res += key + ":" + value + "\r\n";
			}
			public String getHeaders() {
				return res;
			}
		};
		
		Func func = new Func();
		this.headers.forEach( func );
		res += func.getHeaders();
		res+= "\r\n";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			baos.write(res.getBytes());
			baos.write(body);
			baos.write("\r\n".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baos.toByteArray();
	}
}
