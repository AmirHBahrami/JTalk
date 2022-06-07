package net;

import java.net.Socket;

import java.io.IOException;
import java.io.BufferedReader;

/**
	reads user input, and puts them to the SocketRW.
	supposed usage: share the srw from the serverthread
	and a br (which perhaps reads from console) or any other
	input and upon reading from br, write it to srw!

	NOTE in single threaded application, manually give this
	class'es object in a SocConsumer's consume()'s body...

	TODO srw needs to be thread-safe.

	QUESTION where should the srw be closed? (supposedly a manager class
	called chat should do them both)
 */
public class UserInputRunnable implements Runnable{

	protected SocketRW srw;
	protected BufferedReader br;

	public UserInputRunnable(SocketRW srw,BufferedReader br){
		this.srw=srw;
		this.br=br;
	}

	@Override
	public void run(){
		while(true){
			try{
				srw.writeLine(br.readLine());
			}catch(IOException ioe){
				System.err.println("[reading user input thread] - problem reading or writing :"+ioe.getMessage());
				try{
					srw.close();
				}catch(IOException ioe1){
					System.err.println("[reading user input thread] - problem closing the socket :"+ioe1.getMessage());
					System.exit(1); // better safe than sorry!
				}
			}
		}
	}

}
