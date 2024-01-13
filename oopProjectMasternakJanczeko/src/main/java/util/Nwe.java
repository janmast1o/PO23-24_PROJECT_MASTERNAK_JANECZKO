package util;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Nwe {

    private class MyInt {

        private int a;

        private MyInt (int a) {
            this.a = a;
        }

        private int getA () {
            return a;
        }

    }

    public void showcase () {
        PriorityQueue<MyInt> priorityQueue = new PriorityQueue<>(Comparator.comparing(MyInt:: getA).reversed());
        priorityQueue.offer (new MyInt(10));
        priorityQueue.offer (new MyInt(11));
        priorityQueue.offer (new MyInt (9));
        System.out.println(priorityQueue.peek().getA());

    }

}
