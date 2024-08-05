package com.yyz.stock.model;

/**
 * 股票结果返回
 *
 * @author yangyz
 * @version $ Id: GetStockPriceResponse.java, v 1.0 2024年08月04日 15:24 yangyz Exp $
 * @since 1.0
 */
public class GetStockPriceResponse {
    /**
     * 股票名称
     */
    private String stockName;
    /**
     * 股票编号
     */
    private String stockNo;
    /**
     * 当前价格
     */
    private String lastTrade;
    /**
     * 涨跌幅
     */
    private String change;
    /**
     * 昨收
     */
    private String prevClose;
    /**
     * 今开
     */
    private String open;

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockNo() {
        return stockNo;
    }

    public void setStockNo(String stockNo) {
        this.stockNo = stockNo;
    }

    public String getLastTrade() {
        return lastTrade;
    }

    public void setLastTrade(String lastTrade) {
        this.lastTrade = lastTrade;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getPrevClose() {
        return prevClose;
    }

    public void setPrevClose(String prevClose) {
        this.prevClose = prevClose;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }
}
