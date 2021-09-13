package issuetracker;

import java.io.IOException;
import java.net.ServerSocket;

import HttpProtocol.HttpConnectionFactory;

public class IssueTrackerMain {

	public static void main(String[] args) {
		if(args.length < 1 ) {
			System.err.println("Usage :\n\tserver path_to_config_file");
			System.exit(-2);
		}
		Configuration conf = new Configuration().load(args);
		ServerSocket serverSocket;
		try {
			System.out.print("Start");
			serverSocket = new ServerSocket(conf.port());
			System.out.print(".");
			ConnectionListener lisener = new ConnectionListener(serverSocket,conf,new HttpConnectionFactory(conf.getPublicDirectory()));
			System.out.print(".\n");
			Thread t = new Thread(lisener);
			t.start();
			System.in.read();
			serverSocket.close();
		} catch (IOException e) {
			System.err.println("Unalble to start socket");
			e.printStackTrace();
			System.exit(-3);
		}
		System.exit(1);
	}

}
