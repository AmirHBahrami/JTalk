package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
	this runnable reads commands and input, exceutes commands
	and writes the input into the client's socket.
*/
@Deprecated
public class SocketRWResponder implements Runnable{

	private SocketRW srw;
	private String username;

	public SocketRWResponder(SocketRW srw){
		this.srw=srw;
		this.username=null;
	}

	public void setUsername(String username){
		if(this.username==null && username!=null)
			this.username=username;
	}

	@Override
	public void run(){

		// get the user input reader
		BufferedReader br;
		String line=""; // to get the commands as well...
		try{
			System.out.print(username+" : ");
			br=new BufferedReader(new InputStreamReader(System.in));
			while(true){

				// this should be threaded
				line=br.readLine();
				System.out.println(srw.readLine()+"\n");
				/*
				switch(line){
					case ".quit":
					case ".exit":
						System.out.println("[closing connection]");
						srw.close();
					default:
						// single threaded temporary solution : mid sentence
						// before comitting your line to client,
						// you first read what they have sent

					break;
				}*/
				srw.writeLine(username!=null?username+line:line);
			}
		}catch(IOException ioe1){
			System.err.println("[SocketRWResponder]- IOException : "+ioe1.getMessage());
			System.exit(1);
		}
	}

}
