package concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import java.io.IOException;

import net.SocketRW;

/**
	this Runnable is the main funcitonality of this program.
	It starts the chat between two clients, by getting any Socket,
	and automating the chat process.

	TODO take threads from a pool
*/
public class Chat implements Runnable{

	private SocketRW srw;
	private String username; // this is a bad habit, it's passed down waay too deep : this will be given to SocketWriter so it can add to aesthetics
	// TODO private InetAddress parnterIAddr;

	public Chat(SocketRW srw,String username){
		this.srw=srw;
		this.username=username!=null?username:"[YOU]";
	}

	@Override
	public void run(){

		BlockingQueue<String> inputFeed = new LinkedBlockingQueue<String>();

		// TODO make these threads' priority lower than the current, so they will
		// close as soon as the close one has come to an end
		Thread tReader=new Thread(new ConcurrentConsoleReader(inputFeed));
		ConcurrentSocketWriter csw=new ConcurrentSocketWriter(inputFeed,srw);
		csw.setUsername(this.username);
		Thread tWriter=new Thread(csw);

		tReader.start();
		tWriter.start();

		System.out.println("Threads started...");
		String line=null;
		try{

			// ((NOTE CLOSE SRW AT THE USER COSE MANUALLY))
			// no need to create a new thread for this one
			while((line=srw.readLine())!=null)
				System.out.println(line);
			System.out.println("[Connection Closed]");
			System.exit(0); // TODO (obviously)
		}catch(IOException ioe){
			System.err.println("[Chat] - IOException : "+ioe.getMessage()); // TODO include chat with whom (parnterIAddr)
			return;
		}
	}

}
