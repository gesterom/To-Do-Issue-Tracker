package HttpProtocol;

import java.io.IOException;
import java.io.InputStream;

public class RequestParser {
	
	private static String parseString(InputStream in, char seperator) throws IOException {
		String res = "";
		char last = '\0';
		while(true) {
			int ch = in.read();
			if(ch == seperator ) break;
			if(ch >= 32 && ch < '~'  ) {
				res+= (char)ch;
			}
			if(last == '\r' && ch == '\n') break;
			last = (char) ch;
		}
		return res;
	}
	
	private static boolean parseHeader(InputStream in,HttpRequest req) {
		try {
			String key = parseString(in, ':');
			if(key.isEmpty()) {
				return false;
			}
			String value = parseString(in, '\0');
			req.header.put(key, value);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static HttpRequest parse(InputStream in) throws BadProtocolException{
		HttpRequest req = new HttpRequest();
		try {
			req.method = parseString(in, ' ');
			req.uri = parseString(in, ' ');
			req.httpVersion = parseString(in, '\r');
			if(in.read() != '\n') throw new BadProtocolException();
			while(parseHeader(in, req)) ;
			return req;
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-2);
		}
		return req;
	}
}
