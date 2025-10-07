package com.thefishnextdoor.jump.toolkit;

import java.util.Optional;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;

public class RegistryUtils {

    public static Optional<Sound> getSoundByName(String soundName) {
        soundName = soundName.toLowerCase().replace('_', '.');
        NamespacedKey key = NamespacedKey.fromString(soundName.toLowerCase());
        if (key == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(Registry.SOUNDS.get(key));
    }
}