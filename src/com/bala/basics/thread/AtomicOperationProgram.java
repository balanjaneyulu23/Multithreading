package com.bala.basics.thread;

import java.util.Random;

public class AtomicOperationProgram {

  public static void main(String[] args) {
    
    Matrics matrics = new Matrics();
      
    BusinessLogic thread1 = new BusinessLogic(matrics);
    BusinessLogic thread2 = new BusinessLogic(matrics);
    MatricsPrinter thread3 = new MatricsPrinter(matrics);
    
    thread1.start();
    thread2.start();
    thread3.start();
     

  }
  
  private static class MatricsPrinter extends Thread {
      private Matrics matrics;

      public MatricsPrinter(Matrics matrics) {
        this.matrics = matrics;
      }
      
      @Override
      public void run() {
           while (true) {
            
             
             try {
              Thread.sleep(100);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
             
             double currentAverage = matrics.getAverage();
             System.out.println("Current average is "+currentAverage);
          }
      }
      
  }
  
  private static class BusinessLogic extends Thread {
      private Matrics matrics;
      Random random = new Random();
      
      public BusinessLogic(Matrics matrics) {
        this.matrics = matrics;
      }
      
      @Override
      public void run() {
        
           while (true) {
           long start = System.currentTimeMillis();
           
           try {
            Thread.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
             e.printStackTrace();
            }
           
           long end = System.currentTimeMillis();
           
           matrics.addSample(end-start);
          }
      }
      
      
  }
  
  private static class Matrics {
       private long count = 0;
       private volatile double average = 0.0;
       
       
       
       public long getCount() {
        return count;
      }



      public double getAverage() {
        return average;
      }



      public synchronized void addSample(long sample) {
            double currentSum = average*count;
            count++;
            average = (currentSum+sample)/count;
       }
    
  }

}
