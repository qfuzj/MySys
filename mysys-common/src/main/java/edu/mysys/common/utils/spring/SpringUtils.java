package edu.mysys.common.utils.spring;

import edu.mysys.common.utils.StringUtils;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring工具类 方便在非spring管理环境中获取bean
 */
@Component
public class SpringUtils implements BeanFactoryPostProcessor, ApplicationContextAware {
    // Spring应用上下文环境
    private static ConfigurableListableBeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    /**
     * 获取对象
     *
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) beanFactory.getBean(name);
    }

    /**
     * 获取类型为requiredType的对象
     *
     * @param clz
     * @return
     * @throws BeansException
     */
    public static <T> T getBean(Class<T> clz) throws BeansException {
        return (T) beanFactory.getBean(clz);
    }

    /**
     * BeanFactory是否包含一个与所给名称匹配的bean定义
     *
     * @param name
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    /**
     * 是否是singleton,如果没有此bean则会抛出一个异常{@link NoSuchBeanDefinitionException}
     *
     * @param name
     * @return
     * @throws NoSuchBeanDefinitionException
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.isSingleton(name);
    }

    /**
     * 根据bean名称获取对应的Class类型对象
     * <p>此方法通过BeanFactory查找指定名称的bean定义，并返回其类型对象
     *
     * @param name bean的名称
     * @return 对应bean的Class类型对象
     * @throws NoSuchBeanDefinitionException 如果找不到指定名称的bean定义
     */
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.getType(name);
    }

    /**
     * 获取指定名称的Bean的所有别名
     * <p>该方法通过调用BeanFactory的getAliases方法，返回与给定名称关联的所有别名。
     * 在Spring框架中，一个Bean可以有多个别名，这些别名都可以用来引用同一个Bean实例。
     *
     * @param name Bean的名称，可以是主名称或任何一个别名
     * @return 包含所有别名的字符串数组，如果没有别名则返回空数组
     * @throws NoSuchBeanDefinitionException 如果指定的名称没有对应的Bean定义
     */
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return beanFactory.getAliases(name);
    }

    /**
     * 获取当前AOP代理对象
     *
     * <p>该方法用于获取当前线程绑定的AOP代理对象。如果当前代理对象的目标类与传入对象的类相同，
     * 则返回代理对象，否则返回原始对象。
     *
     * @param invoker 原始对象，用于比较代理对象的目标类
     * @param <T>     泛型类型参数
     * @return 如果代理对象的目标类与传入对象的类相同，则返回代理对象，否则返回原始对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAopProxy(T invoker) {
        Object proxy = AopContext.currentProxy();
        if (((Advised) proxy).getTargetSource().getTargetClass() == invoker.getClass()) {
            return (T) proxy;
        }
        return invoker;
    }

    /**
     * 获取当前的环境配置，无配置返回null
     *
     * @return 当前的环境配置
     */
    public static String[] getActiveProfiles() {
        return applicationContext.getEnvironment().getActiveProfiles();
    }

    /**
     * 获取当前的环境配置，当有多个环境配置时，只获取第一个
     *
     * @return 当前的环境配置
     */
    public static String getActiveProfile() {
        final String[] activeProfiles = getActiveProfiles();
        return StringUtils.isNotEmpty(activeProfiles) ? activeProfiles[0] : null;
    }

    /**
     * 从应用程序上下文环境中获取必需的属性值
     *
     * <p>该方法通过指定的键从环境配置中获取属性值，如果该属性不存在，则会抛出异常</p>
     *
     * @param key 要获取的属性键
     * @return 对应键的属性
     */
    public static String getRequiredProperty(String key) {
        return applicationContext.getEnvironment().getRequiredProperty(key);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtils.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

}
