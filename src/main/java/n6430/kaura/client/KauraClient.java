package n6430.kaura.client;

import n6430.kaura.Aura;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class KauraClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Aura.init();
    }
}
