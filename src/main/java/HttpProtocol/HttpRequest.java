package HttpProtocol;

import java.util.HashMap;

public class HttpRequest {
	public String method;
	public String uri;
	public String httpVersion;
	public HashMap<String,String> header;
	
	public HttpRequest() {
		header = new HashMap<>();
	}
}
