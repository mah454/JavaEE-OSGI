package ir.moke.jee.osgi;

import org.osgi.framework.Bundle;

import javax.ejb.Local;
import java.util.List;

@Local
public interface BundleManager {
    void start(Bundle bundle);

    void stop(Bundle bundle);

    void update(Bundle bundle);

    Bundle getBundle(long id);

    Bundle getBundle(String name);

    List<Bundle> getAllBundles();

    void uninstallBundle(Bundle bundle);

    Bundle installBundle(String path);
}
