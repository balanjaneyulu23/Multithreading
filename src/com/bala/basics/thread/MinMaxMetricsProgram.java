package com.bala.basics.thread;

import java.util.Random;

public class MinMaxMetricsProgram {

  public static void main(String[] args) {
      MinMaxMetrics minMaxMetrics = new MinMaxMetrics();
      
      MatricBusinessLogic thread1 = new MatricBusinessLogic(minMaxMetrics);
      MatricBusinessLogic thread2 = new MatricBusinessLogic(minMaxMetrics);
      
      MinmaxMatricsPrinting thread3 = new MinmaxMatricsPrinting(minMaxMetrics);
      
      thread1.start();
      thread2.start();
      thread3.start();

  }
  
  private static class MinmaxMatricsPrinting extends Thread {
       private MinMaxMetrics minMaxMetrics;

       public MinmaxMatricsPrinting(MinMaxMetrics minMaxMetrics) {
           this.minMaxMetrics = minMaxMetrics;
       }
       
       @Override
      public void run() {
          while (true) {
            try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            
            System.out.println("Min Value : "+minMaxMetrics.getMin()+" Max Value : "+minMaxMetrics.getMax());
          }
      }
       
            
  }
  
  private static class MatricBusinessLogic extends Thread {
       private MinMaxMetrics minmaxMetrics;
       Random random = new Random();
       
      public MatricBusinessLogic(MinMaxMetrics minmaxMetrics) {
        this.minmaxMetrics = minmaxMetrics;
      }
      
      @Override
      public void run() {
          while (true) {
            long start = System.currentTimeMillis();
            try {
              Thread.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            long end = System.currentTimeMillis();
            minmaxMetrics.addSample(end-start);
          }
      }
       
  }
  
  private static class MinMaxMetrics {
       private volatile long min;
       private volatile long max;
       
       public MinMaxMetrics() {
        this.min = Long.MIN_VALUE;
        this.max = Long.MAX_VALUE;
      }
      public synchronized void addSample(long newSample) {
            
              this.min = Math.min(newSample, this.min);
              this.max = Math.max(newSample, this.max);
            
       }
      public long getMin() {
        return this.min;
      }
      public long getMax() {
        return this.max;
      }
       
       
  }

}
