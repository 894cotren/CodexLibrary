package com.grey.designpattern.chainofresponsibility;


/**
 * 抽象处理者，定义处理请求的接口
 */
public abstract class Handler {
    
    protected Handler nextHandler;
    protected String name;

    public Handler(String name) {
        this.name = name;
    }

    public Handler(String name, Handler nextHandler) {
        this.name = name;
        this.nextHandler = nextHandler;
    }

    public Handler() {
    }

    /**
     * 设置下一个处理者
     * @param nextHandler 下一个处理者
     */
    public void setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
    
    /**
     * 处理请求
     * @param request 请求对象
     * @return 是否成功处理
     */
    public abstract void handle(String request);


}