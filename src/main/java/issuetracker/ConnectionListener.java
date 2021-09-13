package issuetracker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionListener implements Runnable{

	private ServerSocket serverSocket;
	private IConnectionHandlerFactory factory;
	private ExecutorService executor;
	
	public ConnectionListener(ServerSocket serverSocket,Configuration conf,IConnectionHandlerFactory factory) {
		this.serverSocket = serverSocket;
		this.factory = factory;
		executor = Executors.newFixedThreadPool(conf.httpThredsNumber());
	}
	public void startLisningSync() {
		this.run();
	}

	public void run() {
		try {
			while(serverSocket.isBound() && !serverSocket.isClosed() ) {
				Socket socket = this.serverSocket.accept();
				System.out.println("Accepted : " + socket.getInetAddress());
				executor.execute(this.factory.creat(socket));
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			if(serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
}
