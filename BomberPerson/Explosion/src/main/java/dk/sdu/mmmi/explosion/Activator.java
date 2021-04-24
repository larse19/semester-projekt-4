package dk.sdu.mmmi.explosion;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.commonexplosion.ExplosionSPI;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        ExplosionSystem bc = new ExplosionSystem();
        context.registerService(IEntityProcessingService.class, bc, null);
        context.registerService(ExplosionSPI.class, bc, null);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}
