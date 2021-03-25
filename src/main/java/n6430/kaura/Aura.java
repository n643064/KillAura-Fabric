package n6430.kaura;


import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;




public class Aura {
    public static KeyBinding AuraKey;
    public static boolean Aura = false;
    public static KeyBinding AuraModeKey;
    public static boolean AuraMode = false;
    public static void init() {
        AuraKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.aura.t",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.n6430"
        ));
        AuraModeKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.aura-mode.t",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                "category.n6430"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world != null && client.player != null) {
                if (AuraModeKey.wasPressed()) {
                    AuraMode = !AuraMode;
                    if (AuraMode) {
                        client.player.sendMessage(Text.of("Aura cooldown: on"), true);
                    } else {
                        client.player.sendMessage(Text.of("Aura cooldown: off"), true);
                    }
                }
                if (AuraKey.wasPressed()) {
                    Aura = !Aura;
                    if (Aura) {
                        client.player.sendMessage(Text.of("Aura: on"), true);
                    } else {
                        client.player.sendMessage(Text.of("Aura: off"), true);
                    }

                }
                if (Aura) {
                    for (Entity entity : client.world.getEntities()) {
                        if (!(entity instanceof LivingEntity)) {
                            continue;
                        }
                        if (entity == client.player) {
                            continue;
                        }
                        float distance = client.player.distanceTo(entity);
                        if (distance <= client.interactionManager.getReachDistance()) {
                            if (AuraMode) {
                                if (client.player.getAttackCooldownProgress(0) >= 1) {
                                    client.interactionManager.attackEntity(client.player, entity);
                                }} else {
                                client.interactionManager.attackEntity(client.player, entity);
                            }


                        }
                    }
                }
            }

        });
    }}



