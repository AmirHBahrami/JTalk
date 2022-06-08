package concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.Scanner;

import java.io.IOException;
import java.io.InputStreamReader;

public class ConcurrentConsoleReader implements Runnable{

	private BlockingQueue<String> bq;

	public ConcurrentConsoleReader(BlockingQueue<String> bq){
		this.bq=bq;
	}

	@Override
	public void run(){
		Scanner sc=null;
		try{
			sc=new Scanner(new InputStreamReader(System.in));
			String line=null;
			boolean goOn=true;
			while(goOn){

				// read the input, see if there's any command given,
				line=sc.nextLine(); // NOTE weird thing: java says it throws no IOException
				switch(line){
					case ".quit":
					case ".exit":
						goOn=false;
					break;
					// case ".chuname": // change username
					default:
						bq.put(line);
					break;
				}
			}
		}catch(InterruptedException ie){
			System.err.println("[ConcurrentConsoleReader] - InterruptedException : "+ie.getMessage());
			return; // close the thread
		}
		/*}catch(IOException ioe){
			System.err.println("[ConcurrentConsoleReader] - IOException : "+ioe.getMessage());
			return; // close the thread
		}finally{
			try{
				if(sc!=null)
					sc.close();
			}catch(IOException ioe1){
				System.err.println("[ConcurrentConsoleReader] - exception for closing Scanner : "+ioe1.getMessage());
				return; // close the thread
			}*/
		//}

	}

}
