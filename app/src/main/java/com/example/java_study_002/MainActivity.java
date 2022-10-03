package com.example.java_study_002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

interface Interface1 {
    void funcA();
}

interface Interface2 {
    void funcA();
}

abstract class Parent1 {
    abstract void funcA();
}

class Child1 extends Parent1 implements Interface1, Interface2 {
    @Override
    public void funcA() {
        Log.i("hsson", "Child1.funcA"); // 함수의 구현부가 없는 경우에는 구분할 필요가 없다.
        // 구분하지 않아도 된다.
        // 구분해도 의미가 없다.
    }
}

interface Interface3 {
    default void funcB() {
        Log.i("hsson", "Interface3.funcB");
    }
}

abstract class Parent2 {
    abstract void funcB();
}

class Child2 extends Parent2 implements Interface3 {

    @Override
    public void funcB() { // Parent2 상속
        Log.i("hsson", "Child2.funcB");
    }

    public void funcC() {
        Interface3.super.funcB(); // Interface3 구현
        Log.i("hsson", "Child2.funcC");
    }
}

interface Interface4 {
    default void funcA() {
        Log.i("hsson", "Interface4.funcA");
    }
}

interface Interface5 {
    default void funcA() {
        Log.i("hsson", "Interface5.funcA");
    }
}

class Child3 implements Interface4, Interface5 {

    @Override
    public void funcA() { // 동일한 함수명 가지 인터페이스 구현하는 경우
        Interface4.super.funcA();
        Interface5.super.funcA();
        Log.i("hsson", "Child3.funcA");
    }

    void funcB() {
        Interface4.super.funcA();
        Interface5.super.funcA();
        Log.i("hsson", "Child3.funcB");
    }
}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Child1 child1 = new Child1();
        child1.funcA();
        // result : Child.funcA

        Child2 child2 = new Child2();
        child2.funcB(); // result : Child2.funcB
        child2.funcC();
        // result :
        // Interface3.funcB
        // Child2.funcC

        Child3 child3 = new Child3();
        child3.funcA();
        // result :
        // Interface4.funcA
        // Interface5.funcA
        // Child3.funcA
        child3.funcB();
        // result :
        // Interface4.funcA
        // Interface5.funcA
        // Child3.funcB
    }
}