package com.wrg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.SocketException;
import java.text.FieldPosition;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class ServerUtils {
	static FTPClient ftpClient = null;
	static String hostName = "139.199.179.139";
	static String username = "root";
	static String password = "qq532780911";
	static String hostsDir = "/root/hosts/";
	static {
		ftpClient = new FTPClient();
		try {
			ftpClient.connect(hostName);
			ftpClient.login(username, password);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	};
	public static ArrayList<String> readHostsProviderName() throws Exception {
		ArrayList<String> list = new ArrayList<>();
		int replyCode = ftpClient.getReplyCode();
		if (FTPReply.isPositiveCompletion(replyCode)) {
			System.out.println("µÇÂ¼³É¹¦");
			FTPFile[] files = ftpClient.listFiles(hostsDir);
			
			for (FTPFile f : files ) {
				list.add(f.getName());
			}
		}else System.out.println("µÇÂ¼Ê§°Ü");
		return list;
	}
	
	public static String getLatestVersionNumber (String hostsProviderName) throws Exception {
		String latestVersionNumber = "";
		File localFile = new File("E:\\hosts\\version.txt");
	    String remoteFile = hostsDir + "/" + hostsProviderName + "/" + "version";
	    FileOutputStream fos = new FileOutputStream(localFile);
	    ftpClient.retrieveFile(remoteFile, fos);
	    fos.flush();
	    fos.close();
	    BufferedReader reader = new BufferedReader(new FileReader(localFile));
	    String line = null;
	    while ( (line = reader.readLine())!=null) {
	    	latestVersionNumber += line;
	    }
		return latestVersionNumber;
	}
    
	
	public static void getHosts (String hostsProviderName) throws Exception{
		File localFile = new File("E:\\hosts\\hosts.txt");
	    String remoteFile = hostsDir + "/" + hostsProviderName + "/" + hostsProviderName;
	    FileOutputStream fos = new FileOutputStream(localFile);
	    ftpClient.retrieveFile(remoteFile, fos);
	    fos.flush();
	    fos.close();
	}
}
