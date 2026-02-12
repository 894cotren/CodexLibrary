package com.grey.designpattern.link.model1;

/**
 * @author grey
 * @description 责任链装配
 */
public interface ILogicChainArmory<T, D, R> {

    //获取下一个结点
    ILogicLink<T, D, R> getNext();
    //添加下一个结点
    ILogicLink<T, D, R> appendNext(ILogicLink<T, D, R> next);

}
