package issuetracker;

import java.net.Socket;

public interface IConnectionHandlerFactory {
	IConnectionHandler creat(Socket socket);
}
