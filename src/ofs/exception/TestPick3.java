package ofs.exception;

public class TestPick3 implements Runnable {
	// 变量
	int num = 0;// 票数
	int count = 10;// 总量

	@Override
	public void run() {
	boolean flag = true;
	while(flag) {
	flag = sale();
	}
	}
	// 售票方法 一次卖一张
	public synchronized boolean sale() {
	if (count <= 0) {
	return false;
	}

	num++;
	count--;

	try {
	Thread.sleep(500);
	} catch (InterruptedException e) {
	e.printStackTrace();
	}

	System.out.println(Thread.currentThread().getName() + "买到了第" + num + "张票，剩余" + count + "张票！");

	return true;
	}

	}