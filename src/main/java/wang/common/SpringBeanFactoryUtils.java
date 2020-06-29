package main.java.wang.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <p>
 * ��ͨ�����Springע�ⷽʽ��Service��bean
 * </p>
 * @author wangweibin
 * @version V1.0
 */
public class SpringBeanFactoryUtils implements ApplicationContextAware {
	
	private static ApplicationContext appCtx;
	
	/**
	 * �˷������԰�ApplicationContext����inject����ǰ������Ϊһ����̬��Ա������
	 * 
	 * @param applicationContext ApplicationContext ����.
	 * @throws BeansException
	 * @author hzc
	 */
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		appCtx = applicationContext;
	}
	
	/**
	 * ��ȡApplicationContext
	 * 
	 * @return
	 * @author hzc
	 */
	public static ApplicationContext getApplicationContext() {
		return appCtx;
	}
	
	/**
	 * ����һ�������ķ������������ǿ��ٵõ�һ��BEAN
	 * 
	 * @param beanName bean������
	 * @return ����һ��bean����
	 * @author hzc
	 */
	public static Object getBean(String beanName) {
		return appCtx.getBean(beanName);
	}
	
	/**
	 * ����һ�������ķ������������ǿ��ٵõ�һ��BEAN
	 * 
	 * @param Class ����bean
	 * @return ����һ��bean����
	 * @author hzc
	 */
	public static Object getBean(Class c) {
		return appCtx.getBean(c);
	}
}
