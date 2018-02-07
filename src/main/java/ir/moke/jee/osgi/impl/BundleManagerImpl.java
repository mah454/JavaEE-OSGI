package ir.moke.jee.osgi.impl;

import ir.moke.jee.osgi.BundleManager;
import ir.moke.jee.osgi.OsgiEngine;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Arrays;
import java.util.List;

@Stateless
@LocalBean
public class BundleManagerImpl implements BundleManager {

    @EJB
    private OsgiEngine osgiEngine;

    @Override
    public void start(Bundle bundle) {
        try {
            bundle.start();
        } catch (BundleException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop(Bundle bundle) {
        try {
            bundle.stop();
        } catch (BundleException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Bundle bundle) {
        try {
            bundle.update();
        } catch (BundleException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bundle getBundle(long id) {
        BundleContext context = osgiEngine.getBundleContext();
        return context.getBundle(id);
    }

    @Override
    public Bundle getBundle(String name) {
        BundleContext context = osgiEngine.getBundleContext();
        return context.getBundle(name);
    }

    @Override
    public List<Bundle> getAllBundles() {
        BundleContext context = osgiEngine.getBundleContext();
        return Arrays.asList(context.getBundles());
    }

    @Override
    public void uninstallBundle(Bundle bundle) {
        try {
            bundle.uninstall();
        } catch (BundleException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bundle installBundle(String path) {
        Bundle bundle = null;
        try {
            BundleContext context = osgiEngine.getBundleContext();
            bundle = context.installBundle(path);
        } catch (BundleException e) {
            e.printStackTrace();
        }
        return bundle;
    }
}
