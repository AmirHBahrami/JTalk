package concurrent;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import java.net.Socket;

import java.util.concurrent.BlockingQueue;

import net.SocketRW;

public class ConcurrentSocketWriter implements Runnable{

	private BlockingQueue<String> bq;
	private SocketRW srw;

	public ConcurrentSocketWriter(BlockingQueue<String> bq,SocketRW srw){
		this.bq=bq;
		this.srw=srw;
	}

	@Override
	public void run(){
		try{
			while(true)
				srw.writeLine(bq.take());
		}catch(IOException ioe){
			System.err.println("[ConcurrentSocketWriter] - IOException : "+ioe.getMessage());
			return;
		}catch(InterruptedException ie){
			System.err.println("[ConcurrentSocketWriter] - InterruptedException : "+ie.getMessage());
			return;
		}
	}

}
