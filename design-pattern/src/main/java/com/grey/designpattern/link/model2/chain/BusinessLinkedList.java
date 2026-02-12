package com.grey.designpattern.link.model2.chain;

import com.grey.designpattern.link.model2.handler.ILogicHandler;

/**
 * @author grey
 * @description 业务链路 （责任链类，而且还是责任链启动执行类）
 */
public class BusinessLinkedList<T, D, R> extends LinkedList<ILogicHandler<T, D, R>> implements ILogicHandler<T, D, R>{

    public BusinessLinkedList(String name) {
        super(name);
    }

    /**
     *  这里，业务责任链表类BusinessLinkedList实现了ILogicHandler接口的apply方法，作为整个责任链启动的入口。
     *  可以看作这个类自己即继承了链表作为责任链容器
     *  又自己通过实现ILogicHandler的apply方法，作为入口，作为执行器执行整个责任链。
     */
    @Override
    public R apply(T requestParameter, D dynamicContext) throws Exception {
        Node<ILogicHandler<T, D, R>> current = this.first;
        //责任链的执行
        do {
            //获取到责任链结点本体
            ILogicHandler<T, D, R> item = current.item;
            //执行责任链结点 （传入参数对象和上下文对象。参数对象和上下文对象，都是一个责任链一个的，从头传到尾）
            R apply = item.apply(requestParameter, dynamicContext);
            //如果有返回结果，那么说明责任链已经到尾节点了 （中间结点只处理，然后返回null）
            if (null != apply) return apply;
            //否则迭代，继续执行下一个结点
            current = current.next;
        } while (null != current);
        //如果责任链执行完毕，没有返回结果，那么返回null
        return null;
    }

}
