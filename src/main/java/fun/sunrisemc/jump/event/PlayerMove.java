package fun.sunrisemc.jump.event;

import org.bukkit.GameRule;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import fun.sunrisemc.jump.DoubleJumpPlugin;
import fun.sunrisemc.jump.player.PlayerProfile;

public class PlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        PlayerProfile playerProfile = PlayerProfile.get(player);
        if (playerProfile.hitTheGroundInFlyMode(player)) {
            double damage = ((player.getFallDistance() - 3.0) * 0.9);
            damage -= DoubleJumpPlugin.getSettings().FALL_DAMAGE_REDUCTION;
            damage *= getFeatherFallMultiplier(player);
            if (damage > 0 && player.getWorld().getGameRuleValue(GameRule.FALL_DAMAGE)) {
                player.damage(damage);
            }
        }
        else if (playerProfile.isDoubleJumpReady()) {
            if (!player.getAllowFlight()) {
                player.setAllowFlight(true);
            }
        }
        else if (player.isOnGround()) {
            playerProfile.setDoubleJumpReady(true);
        }
    }

    private double getFeatherFallMultiplier(Player player) {
        double multiplier = 1.0;
        int level = getFeatherFallLevel(player);
        multiplier -= (0.12 * level);
        if (multiplier < 0.2) {
            multiplier = 0.2;
        }
        return multiplier;
    }

    private int getFeatherFallLevel(Player player) {
        ItemStack boots = player.getEquipment().getBoots();
        if (boots != null && boots.hasItemMeta() && boots.getItemMeta().hasEnchant(Enchantment.FEATHER_FALLING)) {
            return boots.getEnchantmentLevel(Enchantment.FEATHER_FALLING);
        }
        return 0;
    }
}