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
			// for if otherwise, multithreading will be a mess and you can't have
			// multiple sockets open, each time a client sends a req, a new one is made...
			this.consumer.consume(ss.accept());
		}catch(IOException ioe){
			System.err.println("[server thread] - listening failed : "+ioe.getMessage());
			return;
		}
	}

}
