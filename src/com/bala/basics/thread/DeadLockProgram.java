package com.bala.basics.thread;

import java.util.Random;

public class DeadLockProgram {

  public static void main(String[] args) {
    Intersection intersection = new Intersection();
    
    Thread trainA = new Thread(new TrainA(intersection));
    Thread trainB = new Thread(new TrainB(intersection));
    
    trainA.start();
    trainB.start();

  }
  
  public static class TrainA implements Runnable{
    private Intersection intersection;
    private Random random = new Random();
    
    public TrainA(Intersection intersection) {
      this.intersection = intersection;
    }
    

    @Override
    public void run() {
      while (true) {
         long sleepingTime = random.nextInt(5);
         try {
          Thread.sleep(sleepingTime);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        intersection.takeRoadA(); 
         
      }
      
    }
  }
  
  public static class TrainB implements Runnable {
       private Intersection intersection;
       private Random random = new Random();
       public TrainB(Intersection intersection) {
          this.intersection = intersection;
       }
       
      @Override
      public void run() {
         while (true) {
           long sleepingTime = random.nextInt(5);
           try {
             Thread.sleep(sleepingTime);
           } catch (InterruptedException e) {
             e.printStackTrace();
           }
           intersection.takeRoadB();
         }
        }
       
  }
  
  public static class Intersection {
      private Object roadA = new Object();
      private Object roadB = new Object();
      
      public void takeRoadA() {
          synchronized (roadA) {
              System.out.println("RoadA has been blocked by thread : "+Thread.currentThread().getName());
              synchronized (roadB) {
                System.out.println("Train is passing through RoadA");
                try {
                  Thread.sleep(1);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
              }
          }
          
      }
      
      public void takeRoadB() {
          synchronized (roadA) {
            System.out.println("RoadA has been locked by thread : "+Thread.currentThread().getName());
            synchronized (roadB) {
              System.out.println("Train is passing through RoadB");
              try {
                Thread.sleep(1);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
            
          }
      }
    
  }

}
