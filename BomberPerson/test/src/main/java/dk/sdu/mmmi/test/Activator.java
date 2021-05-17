package dk.sdu.mmmi.test;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        context.registerService(IEntityProcessingService.class, new processor(), null);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
