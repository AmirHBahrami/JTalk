package net;

import java.net.Socket;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;

import util.TextModifier;

/** just to automate the process of reading socket all over the program.*/
public class SocketRW{

	private Socket soc;
	private BufferedReader br;
	private BufferedWriter bw;

	/* to modify each text before sending - encryption might be one usage, simply customizing is another */
	private TextModifier tm;

	public SocketRW(Socket soc){
		this.soc=soc;
		this.tm=tm;
	}

	public void setTextModifier(TextModifier tm){
		this.tm=tm;
	}

	public String readLine() throws IOException{
		if(br==null)
			br=new BufferedReader(new InputStreamReader(this.soc.getInputStream()));
		String s=br.readLine();
		return s;
	}

	public void writeLine(String s) throws IOException{
		if(this.soc==null)
			return;
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
