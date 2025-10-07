package fun.sunrisemc.jump.config;

import java.util.HashSet;
import java.util.Optional;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.checkerframework.checker.nullness.qual.NonNull;

import fun.sunrisemc.jump.DoubleJumpPlugin;
import fun.sunrisemc.jump.file.ConfigFile;

public class MainConfig {

    public final boolean ENABLED_BY_DEFAULT;
    public final double FORWARD_VELOCITY;
    public final double UP_VELOCITY;
    public final double FALL_DAMAGE_REDUCTION;
    public final HashSet<String> DISABLE_ON_COMMAND = new HashSet<>();
    public final Optional<Sound> SOUND;
    public final boolean SOUND_ENABLED;
    public final float SOUND_VOLUME;
    public final float SOUND_PITCH;

    public MainConfig() {
        FileConfiguration config = ConfigFile.get("config", true);

        ENABLED_BY_DEFAULT = config.getBoolean("enabled-by-default");
        FORWARD_VELOCITY = config.getDouble("forward-velocity");
        UP_VELOCITY = config.getDouble("up-velocity");
        FALL_DAMAGE_REDUCTION = config.getDouble("fall-damage-reduction");
        DISABLE_ON_COMMAND.addAll(config.getStringList("disable-on-command"));
        
        String soundName = config.getString("sound.name");
        SOUND = getSoundByName(soundName);
        if (SOUND.isEmpty() && !soundName.isEmpty()) {
            DoubleJumpPlugin.logWarning("Invalid sound name: " + soundName);
        }
        SOUND_ENABLED = config.getBoolean("sound.enabled") && SOUND.isPresent();
        SOUND_VOLUME = (float) config.getDouble("sound.volume");
        SOUND_PITCH = (float) config.getDouble("sound.pitch");
    }

    private static Optional<Sound> getSoundByName(@NonNull String soundName) {
        String formattedSoundName = soundName.toLowerCase().replace('_', '.');
        NamespacedKey key = NamespacedKey.fromString(formattedSoundName);
        if (key == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(Registry.SOUNDS.get(key));
    }
}