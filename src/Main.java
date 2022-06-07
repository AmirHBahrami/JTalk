import net.SSocRunnable;
import net.SocketRW;
import net.UserInputRunnable;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Map;
import java.util.HashMap;

public class Main{

	public static void main(String[] args){
		Map<String,String> argses=parseArgs(args);
		String username=argses.containsKey("username")?"["+argses.get("username")+"]":"[CLIENT]";
		int PORT;
		try{
			PORT=argses.containsKey("port")?Integer.parseInt(argses.get("key")):2022;
		}catch(NumberFormatException nfe){
			PORT=2022;
		}
		// System.out.println(argses.containsKey("port"));

		SSocRunnable ssr=new SSocRunnable(PORT,(socket)->{

			// WARNING this is just a demonstration and should change asap
			BufferedReader br;
			br=new BufferedReader(new InputStreamReader(System.in));
			SocketRW srw=new SocketRW(socket);

			// the loop goes here instead of run() of the runnable
			while(true){
				try{

					// NOTE the user needs to wait for a response before he's able to write his answer
					System.out.println(srw.readLine());
					srw.writeLine(username+" - "+br.readLine());
				}catch(IOException ioe){
					System.err.println("[connecting br and srw] - IOException :"+ioe.getMessage());
					System.exit(1);
				}
			}
		});

		System.out.println("listening on port "+PORT+"...");
		ssr.run();
	}


	public static Map<String,String> parseArgs(String[] args){
		Map<String,String> m=new HashMap<String,String>();
		for(int i=0;i<args.length;i++){
			// System.out.println("parsing '"+args[i]+"'");
			if((args[i].equals("-p") || args[i].equals("--port") )&& i<=args.length-2)
				m.put("port",args[i+1]);

			if((args[i].equals("-u") || args[i].equals("--username") )&& i<=args.length-2)
				m.put("username",args[i+1]);
		}
		return m;
	}

}
