package fun.sunrisemc.jump.utils;

import java.util.Optional;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.checkerframework.checker.nullness.qual.NonNull;

public class RegistryUtils {

    public static Optional<Sound> getSoundByName(@NonNull String soundName) {
        String formattedSoundName = soundName.toLowerCase().replace('_', '.');
        NamespacedKey key = NamespacedKey.fromString(formattedSoundName);
        if (key == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(Registry.SOUNDS.get(key));
    }
}