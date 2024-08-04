package com.yyz.stock.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间类工具
 *
 * @author yangyz
 * @version $ Id: DateUtil.java, v 1.0 2024年08月04日 17:19 yangyz Exp $
 * @since 1.0
 */
public class DateUtil {

    public static String getDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sdf.format(new Date());
        return date;
    }
}
