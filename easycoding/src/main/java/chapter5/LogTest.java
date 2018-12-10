/**
 * @description: 日志框架 测试
 * @author: fancying
 * @create: 2018-12-10 11:27
 **/
package chapter5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

    // 通过slf4j 接口创建Logger对象
    private static final Logger  logger = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        logger.warn(logger.getClass().toString());
        logger.info("this is a info");
        logger.warn("warn");
        logger.trace("trace");
        logger.error("error");
    }

}