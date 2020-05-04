package PackageThreads;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import PackageClass.*;

public class ThreadPlayer implements Runnable {
	private Entity player;
	private int framDelay;
	private boolean running;
	
	public ThreadPlayer(Entity e) {
		player = e;
	}
	
	public void run()
	{
		running = true;
		long now;
		long lastTime = System.nanoTime();
		long second = 1000000000;
		
	} 
	
	
		
	
}
