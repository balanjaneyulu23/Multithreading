package com.bala.basics.thread;

public class DataRaceProgram {

  public static void main(String[] args) {

    sharedData data = new sharedData();

    Thread thread1 = new Thread(()-> {

      for (int i = 0; i < Integer.MAX_VALUE; i++) {
           data.increment();
      }
    });

    Thread thread2 = new Thread(() -> {

      for (int i = 0; i < Integer.MAX_VALUE; i++) {
           data.checkData();
      }
    });
    
    thread1.start();
    thread2.start();

  }

  private static class sharedData {

    private volatile int x = 0;
    private volatile int y = 0;

    public void increment() {
      x++;
      y++;
    }

    public void checkData() {
      if (y>x) {
        System.out.println("Data race problem detected!");
      }
    }
  }

}
