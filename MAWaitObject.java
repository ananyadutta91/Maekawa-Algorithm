//  WaitObject for synchronization with event handlers.
package MaekawaAlgorithm;
class MAWaitObject {
	public synchronized void waitOK()   { 
		try { wait(); } catch (InterruptedException e) {}; 
	}
	public synchronized void signalOK() { 
		notify(); 
	}
}

