package mine;

public class Time implements Runnable{
	Thread thread;
	private boolean flag = true;
	public long time;
	public String result;
	
	Time(){
		thread = new Thread(this); //创建一个线程对象
		time = 0;
		result = new String();
	}

	public void run() {
		long beginTime = System.currentTimeMillis(); //获取当前时间
		time = 0;
		while (flag){
				time = System.currentTimeMillis() - beginTime;
				int sec = 0,min = 0;
				min =(int) ((time / 1000) / 60);		
				sec =(int) ((time - min * 60 * 1000) / 1000);
				result = min + ":" + sec;
				Title.lab3.setText("       TIME      " +Title.time.result + "      ");
		}
	}

	void stop(){
		flag = false;		
	}
	
	void start(){
		thread.start();
	}
}
