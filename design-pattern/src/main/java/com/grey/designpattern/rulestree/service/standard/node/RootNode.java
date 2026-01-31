package com.grey.designpattern.rulestree.service.standard.node;


import com.grey.designpattern.rulestree.entity.MarketProductEntity;
import com.grey.designpattern.rulestree.entity.TrialBalanceEntity;
import com.grey.designpattern.rulestree.service.standard.AbstractGroupBuyMarketSupport;
import com.grey.designpattern.rulestree.service.standard.factory.DefaultActivityStrategyFactory;
import com.grey.designpattern.rulestree.treebase.StrategyHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *   根节点
 */
@Slf4j
@Service
public class RootNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private SwitchRoot switchRoot;

    @Override
    public TrialBalanceEntity apply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        System.out.println("根节点RootNode 正在进行基本的参数校验逻辑");
        //调用路由方法执行下一个结点
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        //根节点后是开关结点
        System.out.println("根节点RootNode 执行完毕，下一个结点get方法被调用。");
        return switchRoot;
    }

}
