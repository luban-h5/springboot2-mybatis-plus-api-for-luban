package com.luban.api.lubanapi.log;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * P6Spy 日志自定义处理
 *
 * @author WeiHongBin
 */
@Slf4j
@Component
public class P6SpyLogger implements MessageFormattingStrategy {
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotEmpty(sql) ? " Consume Time：" + elapsed + " ms " + now +
                "\n Execute SQL：" + sql.replaceAll("[\\s]+", " ") + "\n" : null;
    }
}
