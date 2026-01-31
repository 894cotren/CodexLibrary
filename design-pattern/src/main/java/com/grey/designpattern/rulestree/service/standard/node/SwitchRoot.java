package com.grey.designpattern.rulestree.service.standard.node;


import com.grey.designpattern.rulestree.treebase.StrategyHandler;
import com.grey.designpattern.rulestree.entity.MarketProductEntity;
import com.grey.designpattern.rulestree.entity.TrialBalanceEntity;
import com.grey.designpattern.rulestree.service.standard.AbstractGroupBuyMarketSupport;
import com.grey.designpattern.rulestree.service.standard.factory.DefaultActivityStrategyFactory;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *   开关节点
 */
@Slf4j
@Service
public class SwitchRoot extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private MarketNode marketNode;

    @Resource
    private EndNode endNode;

    @Override
    public TrialBalanceEntity apply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        System.out.println("开关结点SwitchRoot正在进行一些营销参与资格计算");
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        //开关结点后的下一个几点是营销折扣计算结点
        System.out.println("开关节点SwitchRoot 执行完毕，下一个结点get方法被调用");
        //伪跳转分支规则逻辑，体现规则树可以灵活的实现下一个结点跳转，区别于责任链。
        if (true){
            return marketNode;
        }else{
            return endNode;
        }
    }
}
