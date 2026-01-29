package com.grey.designpattern.chainofresponsibility;

public class TestMain {
    public static void main(String[] args) {
        TheHandlerA theHandlerA = new TheHandlerA("handlerA");
        TheHandlerB theHandlerB = new TheHandlerB("handlerB");

        theHandlerA.setNext(theHandlerB);

        theHandlerA.handle("A");
        theHandlerA.handle("B");
        theHandlerA.handle("C");
    }
}
