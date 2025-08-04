package org.ht.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.springframework.stereotype.Component;

@Component
public class TimeUtil {

    public static long getTodayRemainSeconds() {
        DateTime tomorrowStart = DateUtil.beginOfDay(DateUtil.offsetDay(DateUtil.date(), 1));
        long now = DateUtil.currentSeconds();
        long tomorrow = tomorrowStart.getTime() / 1000;
        return tomorrow - now;
    }

}
