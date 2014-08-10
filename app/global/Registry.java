package global;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import play.Logger;
import bo.IMetadataDao;

import com.github.ddth.tsc.ICounter;
import com.github.ddth.tsc.ICounterFactory;

public class Registry {

    private static ApplicationContext applicationContext;
    private static ICounterFactory counterFactory;
    public static IMetadataDao metadataDao;

    public static ICounter getCounter(String name) {
        return counterFactory.getCounter(name);
    }

    public static void init() {
        initApplicationContext();
        initCounterFactory();
        initMetadataDao();
    }

    public static void destroy() {
        destroyApplicationContext();
    }

    private static void initCounterFactory() {
        counterFactory = applicationContext.getBean("COUNTER_FACTORY", ICounterFactory.class);
    }

    private static void initMetadataDao() {
        metadataDao = applicationContext.getBean("DAO_METADATA", IMetadataDao.class);
    }

    private static void initApplicationContext() {
        String configFile = null;
        String springBeansConfig = System.getProperty("spring.beans.config", null);
        if (springBeansConfig != null) {
            if (springBeansConfig.startsWith("/")) {
                configFile = "file:" + springBeansConfig;
            } else {
                configFile = springBeansConfig;
            }
        } else {
            String appHome = System.getProperty("app.home", null);
            if (appHome != null) {
                File f = new File(new File(appHome), "conf/spring/beans.xml");
                configFile = "file:" + f.getAbsolutePath();
            } else {
                configFile = "conf/spring/beans.xml";
            }
        }

        FileSystemXmlApplicationContext applicationContext = new FileSystemXmlApplicationContext(
                configFile);
        applicationContext.start();
        Registry.applicationContext = applicationContext;
    }

    private static void destroyApplicationContext() {
        try {
            ((AbstractApplicationContext) applicationContext).destroy();
        } catch (Exception e) {
            Logger.warn(e.getMessage(), e);
        } finally {
            applicationContext = null;
        }
    }
}
