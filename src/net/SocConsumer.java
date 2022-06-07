package net;

import java.net.Socket;

import java.io.IOException;

@FunctionalInterface
public interface SocConsumer{	// consumes a socket!

	public abstract void consume(Socket soc) throws IOException;
}
