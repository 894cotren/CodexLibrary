package com.grey.designpattern.link.model1;

/**
 * @author grey
 * @description 略规则责任链接口
 */
public interface ILogicLink<T, D, R> extends ILogicChainArmory<T, D, R> {

    //执行当前责任结点策略
    R apply(T requestParameter, D dynamicContext) throws Exception;

}
