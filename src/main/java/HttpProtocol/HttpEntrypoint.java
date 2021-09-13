package HttpProtocol;

import java.io.IOException;
import java.net.Socket;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import issuetracker.IConnectionHandler;

public class HttpEntrypoint implements IConnectionHandler {

	Socket soc;
	String publicDirectory;
	
	public HttpEntrypoint(Socket socket,String publicDirectory) {
		this.soc = socket;
		this.publicDirectory = publicDirectory;
	}
		
	private void send(HttpRespond res) {
		try {
			soc.getOutputStream().write(res.getByteArray());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		System.out.println("aaaa");
		if (soc == null) return ;

		try {
			
			var req = RequestParser.parse(soc.getInputStream());
			
			System.out.println(req.method);
			//if(req.method.contentEquals("GET") != true) throw new NotImplementedException();
			System.out.println(req.uri);
			HttpRespond res = new HttpRespond(HttpCodes.OK);
			res.addHeader("Content-Type", "image/png");
			res.setBody(Files.readAllBytes(Paths.get(publicDirectory + req.uri)));
			
			this.send(res);
			
		}catch(AccessDeniedException e) {
			System.out.println(1);
			this.send(new HttpRespond(HttpCodes.Unauthorized));
		}catch (NoSuchFileException e){
			System.out.println(2);
			this.send(new HttpRespond(HttpCodes.NotFound));
//		}catch (NotImplementedException e) {
//			System.out.println(3);
//			this.send(new HttpRespond(HttpCodes.NotImplemented));
		}catch (BadProtocolException e) {
			System.out.println(4);
			this.send(new HttpRespond(HttpCodes.BadRequest));
		}catch (IOException e) {
			// TODO Auto-generated catch block
			this.send(new HttpRespond(HttpCodes.InternalServerError));
			e.printStackTrace();
		}
		finally {
			try {
				soc.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}

}
