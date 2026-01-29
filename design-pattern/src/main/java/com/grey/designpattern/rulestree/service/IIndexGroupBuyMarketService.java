package com.grey.designpattern.rulestree.service;

import com.grey.designpattern.rulestree.entity.MarketProductEntity;
import com.grey.designpattern.rulestree.entity.TrialBalanceEntity;

/**
 *  首页营销服务接口
 */
public interface IIndexGroupBuyMarketService {

    TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception;

}
