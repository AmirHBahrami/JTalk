package concurrent;

import net.SocketRW;

public class ConcurrentSRWReader implements Runnable{

	@Override
	public static void run(){
		System.out.println("ConcurrentSRWReader");
	}

}
