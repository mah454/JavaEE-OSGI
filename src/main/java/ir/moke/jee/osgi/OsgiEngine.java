package ir.moke.jee.osgi;

import org.osgi.framework.BundleContext;
import org.osgi.framework.launch.Framework;

import javax.ejb.Local;

@Local
public interface OsgiEngine {
    void launch();

    void shutdown();

    Framework getFramework();

    BundleContext getBundleContext();

}
