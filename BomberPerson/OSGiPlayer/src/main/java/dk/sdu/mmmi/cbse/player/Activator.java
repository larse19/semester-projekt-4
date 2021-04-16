package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        context.registerService(IGamePluginService.class, new PlayerPlugin(), null);
        context.registerService(IEntityProcessingService.class, new PlayerControlSystem(), null);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
