package dk.sdu.mmmi.ogsiai;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        context.registerService(IGamePluginService.class, new AIPlugin(), null);
        context.registerService(IEntityProcessingService.class, new AISystem(), null);
        
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
