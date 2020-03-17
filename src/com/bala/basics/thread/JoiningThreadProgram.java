package com.bala.basics.thread;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoiningThreadProgram {

  public static void main(String[] args) throws InterruptedException {
    
     List<Long> inputNumbers = Arrays.asList(10000000l, 3435l, 2324l, 4656l, 23l, 2435l, 5566l);
     // we want to calculate the  0!, 3435!, 2324!, 4656!, 23!, 2435!, 5566!
     
     List<Thread> threads = new ArrayList<>();
     
     for (Long inputNumber : inputNumbers) {
          threads.add(new FactorialThread(inputNumber));          
     }
     for (Thread thread : threads) {
          thread.setDaemon(true);
          thread.start();
     }
     for (Thread thread : threads) {
          thread.join(2000); 
     }
     
     for (int i = 0; i < inputNumbers.size(); i++) {
          FactorialThread thread = (FactorialThread) threads.get(i);
          if (thread.isFinished) {
              System.out.println("Factorial of "+inputNumbers.get(i)+" is "+thread.result);
          } else {
              System.out.println("The Calculation for "+inputNumbers.get(i)+" is still in progress");

          }
    }
     
  }
  
  private static class FactorialThread extends Thread {
       
       private BigInteger result = BigInteger.ONE   ;
       private Long inputNumber;
       private boolean isFinished = false;
       
      public FactorialThread(Long inputNumber) {
          this.inputNumber = inputNumber;
      }
      
      
      public BigInteger factorial(long n) {
          BigInteger tempResult = BigInteger.ONE;
          for (long i = n; i > 0; i--) {
               tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
          }
          return tempResult;
      }
       
      @Override
      public void run() {
          this.result = factorial(inputNumber);
          this.isFinished = true;
      }
       
  }

}
