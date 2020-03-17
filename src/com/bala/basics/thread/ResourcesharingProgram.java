package com.bala.basics.thread;

public class ResourcesharingProgram {

    public static void main(String[] args) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter();
    
        IncrementingThread incrementingThread = new IncrementingThread(inventoryCounter);
        decrementingThread decrementingThread = new decrementingThread(inventoryCounter);
    
        incrementingThread.start();
        decrementingThread.start();
    
        incrementingThread.join();
        decrementingThread.join();
    
        System.out.println("We currently have "+inventoryCounter.getItem()+" Items");

   }
  
   public static class IncrementingThread extends Thread {
       private InventoryCounter inventoryCounter;

       public IncrementingThread(InventoryCounter inventoryCounter) {
           this.inventoryCounter = inventoryCounter;
       }
      
      @Override
      public void run() {
          for (int i = 0; i < 10000; i++) {
               inventoryCounter.increment();
          }
      }
      
  }
  
 private static class decrementingThread extends Thread {
      private InventoryCounter inventoryCounter;

      public decrementingThread(InventoryCounter inventoryCounter) {
        this.inventoryCounter = inventoryCounter;
      }
      
      @Override
      public void run() {
          for (int i = 10000; i > 0; i--) {
               inventoryCounter.decrement();
          }
      }
      
}
  
 private static class InventoryCounter {
      private int item = 0;
      
      Object lock = new Object();

      public  void increment() {
          synchronized (this.lock) {
              item++;
          }
          
      }
      
      public  void decrement() {
          synchronized (this.lock) {
              item--;
          }
      }
      
      public  int getItem() {
          synchronized (this.lock) {
              return item;
          }
      }
      
  }

}
