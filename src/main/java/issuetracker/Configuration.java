package issuetracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Configuration {

	Long _port;
	Long _httpThreds;
	String _webroot;
	String _publicPath;
	String configfilePath;
	String workingDirectoryPath;
	
	private String solveToAbsolutePath(String configPath, String path) {
		String res = new String(); 
		
		for(int i = 0 ; i < path.length() ; i++) {
			if(path.charAt(i) == '.') {
				continue;
			}
			res += path.charAt(i);
		}
		if(path.contentEquals(res) == false) {			
			System.err.println("Paths can not contain '.' symbol ");
		}
		
		var f = new File(configPath);
		return f.getParent() + "\\" + res ;
	}
	
	public Configuration load(String args[]) {
		
		JSONParser parser = new JSONParser();
		try {
			this.configfilePath = args[0];
			JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(args[0]));

			//TODO add default values
			this._port = (Long) jsonObject.get("port");
			this._webroot = (String) jsonObject.get("webroot");
			this._httpThreds = (Long) jsonObject.get("httpThreds");
			this._publicPath = solveToAbsolutePath(this.configfilePath, (String) jsonObject.get("public"));
			
			this.workingDirectoryPath = solveToAbsolutePath(configfilePath, "");
			
			System.out.println("Working directory : " + this.workingDirectoryPath);
			
			if(_port == null) _port = (long) 80;
			if(_webroot == null) _webroot = "/";
			if(_httpThreds == null) _httpThreds = (long) 5;
			if(_publicPath == null) _publicPath= "public";

		} catch (FileNotFoundException fe) {
			// TODO warning and load default configuration
			System.err.println("Configuration file (" + args[0] + ") not found");
			System.err.println(fe.getMessage());
			System.exit(-5);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Unexpected Error");
			System.exit(-1);
		}
		return this;
	}

	public int port() {
		return _port.intValue();
	}

	public int httpThredsNumber() {
		// TODO Auto-generated method stub
		return _httpThreds.intValue();
	}
	
	public String getPublicDirectory() {
		return this._publicPath;
	}
	
	public String getRelativePath(String path) {
		return this.workingDirectoryPath + path;
	}

}
