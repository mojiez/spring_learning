package org.myspringframework.core;

/**
 * MySpring框架应用上下文接口
 * @author uneasyDrinker
 */
public interface ApplicationContext {
    /**
     * 根据bean的名称获取对应的单例bean对象
     * @param beanName
     * @return
     */
    public Object getBean(String beanName);
}
