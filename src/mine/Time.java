package mine;

public class Time implements Runnable{
	Thread thread;
	private boolean flag = true;
	public long time;
	public String result;
	
	Time(){
		thread = new Thread(this); //����һ���̶߳���
		time = 0;
		result = new String();
	}

	public void run() {
		long beginTime = System.currentTimeMillis(); //��ȡ��ǰʱ��
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
