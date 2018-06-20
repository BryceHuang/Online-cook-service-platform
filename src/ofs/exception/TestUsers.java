package ofs.exception;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ofs.SysThread.RecieveOrderThread;

public class TestUsers {  
    public static void main(String[] args) {  
         int count = 1000;  
         CyclicBarrier cyclicBarrier = new CyclicBarrier(count);  
         ExecutorService executorService = Executors.newFixedThreadPool(count);  
         for (int i = 0; i < count; i++)  
              executorService.execute(new TestUsers().new Task(cyclicBarrier,i));  
 
         executorService.shutdown();  
         while (!executorService.isTerminated()) {  
              try {  
                   Thread.sleep(10);  
              } catch (InterruptedException e) {  
                   e.printStackTrace();  
              }  
         }  
    }  
 
    public class Task implements Runnable {  
         private CyclicBarrier cyclicBarrier;  
         private int cid;
         
 		public int getCid() {
			return cid;
		}

		public void setCid(int cid) {
			this.cid = cid;
		}  
         public Task(CyclicBarrier cyclicBarrier,int cookId) {  
              this.cyclicBarrier = cyclicBarrier;  
              this.setCid(cookId);
         }  
 
         @Override  
         public void run() {  
              try {  
                   // 等待所有任务准备就绪  
                   cyclicBarrier.await();  
                   // 测试内容
                   RecieveOrderThread a=new RecieveOrderThread();
                   
                   a.setOid(98);
                   a.run();
                   //System.out.println("test");
              } catch (Exception e) {  
                   e.printStackTrace();  
              }  
         }


    }  
}  