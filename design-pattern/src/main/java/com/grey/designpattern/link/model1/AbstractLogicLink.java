package com.grey.designpattern.link.model1;

/**
 * @author grey
 * @description  责任链抽象类
 */
public abstract class AbstractLogicLink<T, D, R> implements ILogicLink<T, D, R> {

    private ILogicLink<T, D, R> next;

    @Override
    public ILogicLink<T, D, R> getNext() {
        return next;
    }

    @Override
    public ILogicLink<T, D, R> appendNext(ILogicLink<T, D, R> next) {
        this.next = next;
        return next;
    }

    // 封装执行下一个结点的方法 （然后参数、上下文对象也是一个责任链所有结点共用的）
    protected R invokeNext(T requestParameter, D dynamicContext) throws Exception {
        return next.apply(requestParameter, dynamicContext);
    }

}
