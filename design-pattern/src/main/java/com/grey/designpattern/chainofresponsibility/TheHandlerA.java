package com.grey.designpattern.chainofresponsibility;

/**
 * 责任链模式测试客户端
 */
public class TheHandlerA extends Handler {


    public TheHandlerA(Handler nextHandler, String name) {
        super(name,nextHandler);
    }

    public TheHandlerA(String name) {
        super.name=name;
    }

    @Override
    public void  handle(String request) {
        if (request.equals("A")) {
            System.out.println(this.name+"正在处理");
        }else if(super.nextHandler!=null) {
            super.nextHandler.handle(request);
        }else {
            System.out.println(this.name+"已经是最后一个节点；");
        }
    }
}