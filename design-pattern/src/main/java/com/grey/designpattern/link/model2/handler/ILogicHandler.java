package com.grey.designpattern.link.model2.handler;

/**
 * @author grey
 * @description 逻辑处理器接口 （类比model1，这就是提取出来的结点逻辑，可以理解为model2该责任链的结点本体）
 */
public interface ILogicHandler<T, D, R> {

    /**
     * 这个是可读性用的，虽然本质也是return null， 但是中间结点调用这个方法return next(x,x),语义上可以更好诶。
     */
    default R next(T requestParameter, D dynamicContext) {
        return null;
    }

    /**
     * 具体处理逻辑的接口
     *
     */
    R apply(T requestParameter, D dynamicContext) throws Exception;

}
