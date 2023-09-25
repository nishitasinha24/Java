package queues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MonitorQueues implements Runnable {

	Queue<Object> generalPurposeQueue;
	Queue<Object> integerQueue;
	Queue<Object> stringQueue;
	
	private IntegerMiddleMan m1,m2, m3,m4,m5;
	private StringMiddleMan m6,m7,m8,m9,m10;
	private Consumer c1;
	private Consumer c2;
	private Producer p;
	private static final int DELAY = 10;
	
	public MonitorQueues() {
		generalPurposeQueue = new LinkedList<Object>();
		integerQueue = new ConcurrentLinkedQueue<Object>();
		stringQueue = new ConcurrentLinkedQueue<Object>();
		
		p = new Producer(generalPurposeQueue);
		m1 = new IntegerMiddleMan(generalPurposeQueue, integerQueue);
		m2 = new IntegerMiddleMan(generalPurposeQueue, integerQueue);
		m3 = new IntegerMiddleMan(generalPurposeQueue, integerQueue);
		m4 = new IntegerMiddleMan(generalPurposeQueue, integerQueue);
		m5 = new IntegerMiddleMan(generalPurposeQueue, integerQueue);
		m6 = new StringMiddleMan(generalPurposeQueue, stringQueue);
		m7 = new StringMiddleMan(generalPurposeQueue, stringQueue);
		m8 = new StringMiddleMan(generalPurposeQueue, stringQueue);
		m9 = new StringMiddleMan(generalPurposeQueue, stringQueue);
		m10 = new StringMiddleMan(generalPurposeQueue, stringQueue);
		c1 = new Consumer(integerQueue);
		c2 = new Consumer(stringQueue);
		
	}

	@Override
	public void run() {
		new Thread(c1).start();
		new Thread(c2).start();
		new Thread(m1).start();
		new Thread(m2).start();
		new Thread(m3).start();
		new Thread(m4).start();
		new Thread(m5).start();
		new Thread(m6).start();
		new Thread(m7).start();
		new Thread(m8).start();
		new Thread(m9).start();
		new Thread(m10).start();
		new Thread(p).start();
		while (true) {
			if (integerQueue.size() > 10) {
				System.out.println("Alert. Queue 1  > 10. Shouldn't happen");
			}
			if (stringQueue.size() > 10) {
				System.out.println("Alert. Queue 2 > 10. Shouldn't happen");
			}
			
			if (generalPurposeQueue.size() > Producer.MAX_QUEUE_SIZE) {
				System.out.println("Alert. Input Queue > " + Producer.MAX_QUEUE_SIZE + ". Shouldn't happen");
			}
			
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		MonitorQueues mq = new MonitorQueues();
		Thread t = new Thread(mq);
		t.start();
	}
}
