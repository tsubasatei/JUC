package com.xt.juc.cas;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Phaser ：阶段、栅栏组
 * 婚礼：到达、吃、离开、洞房
 */
public class T09_Phaser {
    static Marriage phaser = new Marriage();
    static class Marriage extends Phaser{
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase) {
                case 0: {
                    System.out.println("所有人都到了。。" + registeredParties);
                    System.out.println();
                    return false;
                }
                case 1: {
                    System.out.println("所有人都吃完了。。" + registeredParties);
                    System.out.println();
                    return false;
                }
                case 2: {
                    System.out.println("所有人都离开了。。" + registeredParties);
                    System.out.println();
                    return false;
                }
                case 3: {
                    System.out.println("新郎新娘入洞房呀,不许参观了。。" + registeredParties);
                    System.out.println();
                    return true;
                }
                default:
                    return true;
            }
        }
    }
    static class Person extends Thread{
        String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }

        private void hug() {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            if (this.name.equals("新郎") || this.name.equals("新娘")) {
                System.out.println(this.name + "进洞房");
                phaser.arriveAndAwaitAdvance();
            } else {
                phaser.arriveAndDeregister();
            }
        }

        private void leave() {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(this.name + "离开了");
            phaser.arriveAndAwaitAdvance();
        }

        private void eat() {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(this.name + "吃了");
            phaser.arriveAndAwaitAdvance();
        }

        private void arrive() {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(this.name + "到了");
            phaser.arriveAndAwaitAdvance();
        }
    }

    public static void main(String[] args) {
        phaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Person("客人" + (i+1)).start();
        }
        new Person("新郎").start();
        new Person("新娘").start();
    }
}
