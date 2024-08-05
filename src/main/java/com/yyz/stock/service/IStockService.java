package com.yyz.stock.service;

import com.yyz.stock.model.GetStockPriceResponse;

import java.util.List;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author yangyz
 * @version $ Id: IStockService.java, v 1.0 2024年08月05日 16:22 yangyz Exp $
 * @since 1.0
 */
public interface IStockService {

    /**
     * 获取股票信息
     * @param stockSymbol
     * @return
     */
    List<GetStockPriceResponse> fetchStockPrice(String stockSymbol);

}
