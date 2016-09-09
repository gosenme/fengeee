/**
 * Bestpay.com.cn Inc.
 * Copyright (c) 2011-2016 All Rights Reserved.
 */
package com.fengeee.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 启动加载配置文件
 *
 * @author gaoxiang
 * @version Id: CustomPropertyPlaceholder.java, v 0.1 2016/1/22 17:55 gaoxiang Exp $$
 */
@Slf4j
public class CustomPropertyPlaceholder extends PropertyPlaceholderConfigurer {

    /**
     * 上下文信息MAP
     */
    private static Map<String, String> contextMap = new HashMap<String, String>();

    /**
     * 加载spring配置文件执行该方法
     *
     * @param beanFactoryToProcess beanFactory
     * @param props                配置信息
     * @throws BeansException
     */
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess,
                                     Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        log.info("加载配置文件开始...");
        StringBuilder sb = new StringBuilder();
        String _key = "";
        String value = "";
        for (Object key : props.keySet()) {
            _key = key + "";
            value = props.get(key) + "";
            sb.append("[  " + key + "\t:\t" + value + "  ]\r\n");
            contextMap.put(_key, value);
        }
        log.info("加载配置文件结束:{}", sb.toString());
    }

    /**
     * 获取配置参数
     *
     * @param key 参数key值
     * @return int
     */
    public static String getStrPro(String key) {
        return contextMap.get(key);
    }

    /**
     * 获取配置参数并转换为int类型
     *
     * @param key 参数key值
     * @return int
     */
    public static int getIntPro(String key) {
        try {
            String value = contextMap.get(key);
            if (null != value && !"".equals(value)) {
                return Integer.parseInt(value);
            }
        } catch (Exception e) {
            log.error("处理异常:{}", e.getMessage());
        }
        return 0;
    }
}
