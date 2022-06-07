package net;

import java.net.ServerSocket;

import java.io.IOException;

public class SSocRunnable implements Runnable{

	private int port;
	private SocConsumer consumer;

	public SSocRunnable(int port,SocConsumer consumer){
		this.port=port;
		this.consumer=consumer;
	}

	@Override
	public void run(){
		Thread curr=Thread.currentThread();
		ServerSocket ss=null;
		try{
			ss=new ServerSocket(port);

			// NOTE put any sort of loop or thread controlling in the consumer...
			this.consumer.consume(ss.accept());
		}catch(IOException ioe){
			System.err.println("[server thread] - listening failed : "+ioe.getMessage());
			return;
		}
	}

}
