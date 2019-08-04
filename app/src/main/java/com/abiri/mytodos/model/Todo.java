package com.abiri.mytodos.model;

import java.util.ArrayList;

public class Todo {
    String text;
    Boolean isDone;

    public Todo(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public Boolean isDone() {
        return isDone;
    }

    private static int lastContactId = 0;

    public static ArrayList<Todo> createTodoList(int number) {
        ArrayList<Todo> todos = new ArrayList<Todo>();

        for (int i = 1; i <= number; i++) {
            todos.add(new Todo("Todo " + ++lastContactId));
        }

        return todos;
    }
}
