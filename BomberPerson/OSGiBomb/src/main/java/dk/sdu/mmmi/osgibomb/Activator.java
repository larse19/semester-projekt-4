package dk.sdu.mmmi.osgibomb;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.osgicommonbomb.BombSPI;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        BombController bc = new BombController();
        context.registerService(IEntityProcessingService.class, bc, null);
        context.registerService(BombSPI.class, bc, null);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
