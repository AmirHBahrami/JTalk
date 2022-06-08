package net;

import java.net.Socket;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;

/** just to automate the process of reading socket all over the program.*/
public class SocketRW{

	protected Socket soc;
	protected BufferedReader br;
	protected BufferedWriter bw;

	public SocketRW(Socket soc){
		this.soc=soc;
	}
	
	public String readLine() throws IOException{
		if(br==null)
			br=new BufferedReader(new InputStreamReader(this.soc.getInputStream()));
		String s=br.readLine();
		return s;
	}

	@Deprecated
	public String readAll() throws IOException{
		if(br==null)
			br=new BufferedReader(new InputStreamReader(this.soc.getInputStream()));
		// System.out.println("readAll() - stream is :"+(this.soc.isInputShutdown()?"down":"up"));
		if(this.soc==null)
			return null; // TODO
		StringBuffer sb=new StringBuffer();
		String line=""; // thread-friendly!
		while((line=br.readLine())!=null && !line.equals("\n"))
			sb.append(line);
		return sb.toString();
	}

	public void writeLine(String s) throws IOException{
		if(this.soc==null)
			return;
		// System.out.println("write() - stream is :"+(this.soc.isOutputShutdown()?"down":"up"));
		if(bw==null)
			bw=new BufferedWriter( new OutputStreamWriter(this.soc.getOutputStream()));
		bw.write(s+"\n");
		bw.flush();
	}

	public void close()throws IOException{
		bw.close();
		br.close();
		this.soc.close();
	}

}
