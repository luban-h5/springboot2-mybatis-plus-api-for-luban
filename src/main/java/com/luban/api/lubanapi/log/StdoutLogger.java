package com.luban.api.lubanapi.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 修改SQL执行输出到 日志，便于分析
 *
 * @author WeiHongBin
 */
@Slf4j
@Component
public class StdoutLogger extends com.p6spy.engine.spy.appender.StdoutLogger {
    @Override
    public void logText(String text) {
        if (log.isDebugEnabled()) {
            log.debug(text);
        }
    }
}
