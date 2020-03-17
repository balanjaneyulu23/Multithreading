package com.bala.basics.thread;

import java.math.BigInteger;

public class StoppingThreadMain {

  public static void main(String[] args) {
    
     //Thread thread = new Thread(new LongComputationTask(new BigInteger("200000"), new BigInteger("1000000000")));
    Thread thread = new Thread(new BlockingThread());
     thread.start();
     thread.interrupt();

  }

  private static class BlockingThread implements Runnable {
    
    @Override
    public void run() {
      try {
        Thread.sleep(5000000);
      } catch (InterruptedException e) {
         System.out.println("Exiting blocking thread");
      }
      
    }
  }
  
  private static class LongComputationTask implements Runnable {
       private BigInteger base;
       private BigInteger power;
       
      public LongComputationTask(BigInteger base, BigInteger power) {
          this.base = base;
          this.power = power;
      }

      @Override
      public void run() {
          System.out.println(base+" AND "+power+" = "+pow(base, power)); 
      }
      
      private BigInteger pow(BigInteger base, BigInteger power) {
        BigInteger result = BigInteger.ONE;
        
        for (BigInteger i = BigInteger.ZERO;  i.compareTo(power) != 0; i=i.add(BigInteger.ONE)) {
             if (Thread.currentThread().isInterrupted()) {
                 System.out.println("Computation takes lot more time than usual:Interrupted");
                 return BigInteger.ZERO;
            }
             
             result = result.multiply(base);
        }
        
        return result;
      }
       
       
       
       
  }
}
