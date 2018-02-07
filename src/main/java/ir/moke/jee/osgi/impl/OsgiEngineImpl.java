package ir.moke.jee.osgi.impl;

import ir.moke.jee.osgi.OsgiEngine;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.Constants;
import org.osgi.framework.launch.Framework;
import org.osgi.framework.launch.FrameworkFactory;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

@Stateless
@LocalBean
public class OsgiEngineImpl implements OsgiEngine {


    private Map<String, String> configuration;
    private Framework framework;


    @PostConstruct
    private void init() {
        setConfiguration();
        FrameworkFactory factory = ServiceLoader.load(FrameworkFactory.class).iterator().next();
        framework = factory.newFramework(configuration);
    }

    private void setConfiguration() {
        configuration = new HashMap<>();
        configuration.put(Constants.FRAMEWORK_STORAGE, "/tmp/osgi");
        configuration.put(Constants.FRAMEWORK_STORAGE_CLEAN, "onFirstInit");
        configuration.put(Constants.FRAMEWORK_SYSTEMPACKAGES_EXTRA, "javax.servlet");
    }

    @Override
    public void launch() {
        try {
            System.out.println("#############################");
            System.out.println("##### Start OSGI Engine #####");
            System.out.println("#############################");
            framework.start();
        } catch (BundleException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void shutdown() {
        try {
            framework.stop();
        } catch (BundleException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Framework getFramework() {
        return framework;
    }

    @Override
    public BundleContext getBundleContext() {
        return framework.getBundleContext();
    }
}
