package com.yyz.stock.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.http.HttpUtil;
import com.yyz.stock.model.GetStockPriceResponse;
import com.yyz.stock.service.IStockService;

/**
 * 股票价格
 *
 * @author yangyz
 * @version $ Id: StockPriceHandler.java, v 1.0 2024年08月04日 15:25 yangyz Exp $
 * @since 1.0
 */
public class StockServiceImpl implements IStockService {

    /**
     * 腾讯的股票API接口
     */
    private String STOCK_API_TX = "https://qt.gtimg.cn/q=";

    public List<GetStockPriceResponse> fetchStockPrice(String stockSymbol) {
        String url = STOCK_API_TX + stockSymbol;
        String responseData = "";
        List<GetStockPriceResponse> stockPriceResponseList = new ArrayList<>();
        try {
            responseData = HttpUtil.get(url);
            stockPriceResponseList = dealData(responseData);
            System.out.println(responseData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockPriceResponseList;
    }

    /**
     * 接口数据处理，获取到腾讯的接口后会拿到这样一串字符串，如：
     * v_sh000001="1~上证指数~000001~2971.30~2970.39~2965.61~303713014~0~0~0.00~0~0.00~0~0.00~0~0.00~0~0.00~0~0.00~0~0.00~0~0.00~0~0.00~0~0.00~0~~20240712153511~0.91~0.03~2977.14~2963.30~2971.30/303713014/302581575979~303713014~30258158~0.65~13.27~~2977.14~2963.30~0.47~428104.13~563894.26~0.00~-1~-1~1.02~0~2970.97~~~~~~30258157.5979~0.0000~0~ ~ZS~-0.12~0.72~~~~3322.13~2635.09~0.13~-2.02~-2.82~4534435145087~~-16.75~-3.64~4534435145087~~~-8.19~0.01~~CNY~0~~0.00~0";
     * 以 ~ 进行分割，一些重要的数据
     * 0: 未知
     * 1: 名字
     * 2: 代码
     * 3: 当前价格
     * 4: 昨收
     * 5: 今开
     * 6: 成交量（手）
     * 7: 外盘
     * 8: 内盘
     * 29: 最近逐笔成交
     * 30: 时间
     * 31: 涨跌
     * 32: 涨跌%
     * 33: 最高
     * 34: 最低
     * 35: 价格/成交量（手）/成交额
     * 36: 成交量（手）
     * 37: 成交额（万）
     * 38: 换手率
     * 39: 市盈率
     * 41: 最高
     * 42: 最低
     * 43: 振幅
     * 44: 流通市值
     * 45: 总市值
     * 46: 市净率
     * 47: 涨停价
     * 48: 跌停价
     *
     * @param responseData
     * @return
     */
    private List<GetStockPriceResponse> dealData(String responseData) {
        List<GetStockPriceResponse> getStockPriceResponseList = new ArrayList<>();
        // 如果有多个股票号，先通过;进行拆分
        responseData = responseData.substring(0, responseData.length() - 1);
        String[] stockArray = responseData.split(";");
        for (String stockInfo : stockArray) {
            GetStockPriceResponse response = new GetStockPriceResponse();
            String[] split = stockInfo.split("~");
            response.setStockName(split[1]);
            response.setStockNo(split[2]);
            response.setLastTrade(split[3]);
            response.setChange(split[32]);
            response.setPrevClose(split[4]);
            response.setOpen(split[5]);
            getStockPriceResponseList.add(response);
        }
        return getStockPriceResponseList;
    }
}

