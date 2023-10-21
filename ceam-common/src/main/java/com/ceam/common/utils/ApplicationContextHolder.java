package com.ceam.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.function.Consumer;

/**
 * @author CeaM
 * 2023/01/19 22:08
 **/
@Slf4j
@SuppressWarnings("all")
public class ApplicationContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    /**
     * 根据给定bean的class从applicationContext上下文获取Bean, 强制类型转换为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 根据给定beanName从applicationContext上下文获取Bean, 强制类型转换为所赋值对象的类型.
     */
    public static <T> T getBean(String beanName) {
        assertContextInjected();
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 可以使用别名，以防冲突
     * @param name 别名beanName
     * @param requiredType bean的class
     */
    public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }

    public static <T> void getBeanIfExist(Class<T> requiredType,
                                          Consumer<T> consumer) throws BeansException {
        try {
            T bean = applicationContext.getBean(requiredType);
            consumer.accept(bean);
        } catch (NoSuchBeanDefinitionException ignore) {
        }
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicationContext属性未注入, 请在applicationContext" +
                    ".xml中定义ApplicationContextHolder或在SpringBoot启动类中注册ApplicationContextHolder.");
        }
    }

    /**
     * 清除ApplicationContextHolder中的ApplicationContext为Null.
     */
    private static void clearHolder() {
        log.debug("清除ApplicationContextHolder中的ApplicationContext:" + applicationContext);
        applicationContext = null;
    }

    @Override
    public void destroy() {
        ApplicationContextHolder.clearHolder();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (ApplicationContextHolder.applicationContext != null) {
            log.warn("ApplicationContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:"
                    + ApplicationContextHolder.applicationContext);
        }
        ApplicationContextHolder.applicationContext = applicationContext;
    }
}

