package com.xt.juc.blokingqueue.priorityqueue;

import lombok.Data;

@Data
public class Task implements Comparable<Task> {

    private int id;
    private String name;

    @Override
    public int compareTo(Task task) {
        return this.id > task.id ? 1 : (this.id < task.id ? -1 : 0);
    }
}