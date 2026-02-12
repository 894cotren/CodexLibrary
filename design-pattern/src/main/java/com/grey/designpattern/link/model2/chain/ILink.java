package com.grey.designpattern.link.model2.chain;

/**
 * @author grey
 * @description 链接口
 */
public interface ILink<E> {

    boolean add(E e);

    boolean addFirst(E e);

    boolean addLast(E e);

    boolean remove(Object o);

    E get(int index);

    void printLinkList();

}
