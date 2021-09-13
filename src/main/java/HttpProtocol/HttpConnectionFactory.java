package HttpProtocol;

import java.net.Socket;

import issuetracker.IConnectionHandler;
import issuetracker.IConnectionHandlerFactory;

public class HttpConnectionFactory implements IConnectionHandlerFactory {

	String publicDirectory;
	
	public HttpConnectionFactory(String publicDirectory) {
		this.publicDirectory = publicDirectory;
	}
	
	public IConnectionHandler creat(Socket socket) {
		
		return new HttpEntrypoint(socket,this.publicDirectory);
	}

}
