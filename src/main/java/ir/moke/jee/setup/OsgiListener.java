package ir.moke.jee.setup;

import ir.moke.jee.osgi.OsgiEngine;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class OsgiListener {

    @EJB
    private OsgiEngine osgiEngine;

    @PostConstruct
    public void start() {
        osgiEngine.launch();
    }

    @PreDestroy
    public void stop() {
        osgiEngine.shutdown();
    }
}
