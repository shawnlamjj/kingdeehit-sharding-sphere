package com.kingdeehit.cloud.autoconfigure.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.kingdeehit.cloud.autoconfigure.datasource.DataSourceContextHolder;
import com.kingdeehit.cloud.autoconfigure.datasource.DatabaseType;

@Aspect
@Order(-10)//保证在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {

    Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Pointcut("execution(* com..controller..*.*(..))")
    private void anyMethod(){}//定义一个切入点
 
    @Before("anyMethod()")
    public void dataSourceChange() {
    	DataSourceContextHolder.setDBType(DatabaseType.DYNAMIC);
        logger.info("use change dataSource：" + DatabaseType.DYNAMIC);
    }


    @AfterReturning("anyMethod()")
    public void restoreDataSource() {
        //方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
    	DataSourceContextHolder.clearDBType();
        logger.info("clear change dataSource："+ DatabaseType.DYNAMIC);
    }



}
