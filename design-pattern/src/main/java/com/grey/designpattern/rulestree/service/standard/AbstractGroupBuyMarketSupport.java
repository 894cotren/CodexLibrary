package com.grey.designpattern.rulestree.service.standard;


import com.grey.designpattern.rulestree.service.standard.factory.DefaultActivityStrategyFactory;
import com.grey.designpattern.rulestree.treebase.AbstractStrategyRouter;

/**
 *  抽象的拼团营销支撑类
 *  继承规则树抽象，并定义好入参、出参、上下文。
 *  入参：商品信息实体
 *  出参：试算后的结果
 *  上下文：待定
 */
public abstract class AbstractGroupBuyMarketSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractStrategyRouter<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext,TrialBalanceEntity> {



}
