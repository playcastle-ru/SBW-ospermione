/*
 * Copyright 2022 ScreamingSandals
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.screamingsandals.lib.bukkit;

import lombok.Data;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.world.*;
import org.bukkit.plugin.Plugin;
import org.screamingsandals.lib.Core;
import org.screamingsandals.lib.bukkit.event.AbstractBukkitEventHandlerFactory;
import org.screamingsandals.lib.bukkit.event.block.*;
import org.screamingsandals.lib.bukkit.event.chunk.*;
import org.screamingsandals.lib.bukkit.event.entity.*;
import org.screamingsandals.lib.bukkit.event.player.*;
import org.screamingsandals.lib.bukkit.event.world.*;
import org.screamingsandals.lib.bukkit.spectator.SpigotBackend;
import org.screamingsandals.lib.event.EventPriority;
import org.screamingsandals.lib.event.SEvent;
import org.screamingsandals.lib.event.block.*;
import org.screamingsandals.lib.event.chunk.*;
import org.screamingsandals.lib.event.entity.*;
import org.screamingsandals.lib.event.player.*;
import org.screamingsandals.lib.event.world.*;
import org.screamingsandals.lib.spectator.Spectator;
import org.screamingsandals.lib.utils.annotations.Service;
import org.screamingsandals.lib.utils.annotations.methods.OnEnable;

import java.util.*;
import java.util.function.Function;

import static org.screamingsandals.lib.utils.reflect.Reflect.has;

@Service
public class BukkitCore extends Core {
    @Getter
    private static SpigotBackend spectatorBackend;
    @Getter
    private static Plugin plugin;

    public BukkitCore(Plugin plugin) {
        BukkitCore.plugin = plugin;
        spectatorBackend = new SpigotBackend();
        Spectator.setBackend(spectatorBackend);
    }

    @OnEnable
    public void onEnable() {
        // entity
        if (has("org.bukkit.event.entity.ArrowBodyCountChangeEvent")) {
            try { constructDefaultListener(ArrowBodyCountChangeEvent.class, SArrowBodyCountChangeEvent.class, SBukkitArrowBodyCountChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        if (has("org.bukkit.event.entity.BatToggleSleepEvent")) {
            try { constructDefaultListener(BatToggleSleepEvent.class, SBatToggleSleepEvent.class, SBukkitBatToggleSleepEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(CreeperPowerEvent.class, SCreeperPowerEvent.class, SBukkitCreeperPowerEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(EnderDragonChangePhaseEvent.class, SEnderDragonChangePhaseEvent.class, SBukkitEnderDragonChangePhaseEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.entity.EntityAirChangeEvent")) {
            try { constructDefaultListener(EntityAirChangeEvent.class, SEntityAirChangeEvent.class, SBukkitEntityAirChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        if (has("org.bukkit.event.entity.EntityBreedEvent")) {
            try { constructDefaultListener(EntityBreedEvent.class, SEntityBreedEvent.class, SBukkitEntityBreedEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(EntityChangeBlockEvent.class, SEntityChangeBlockEvent.class, SBukkitEntityChangeBlockEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        constructDefaultListener(EntityCombustEvent.class, SEntityCombustEvent.class, factory(SBukkitEntityCombustEvent::new)
                .sub(EntityCombustByBlockEvent.class, SBukkitEntityCombustByBlockEvent::new)
                .sub(EntityCombustByEntityEvent.class, SBukkitEntityCombustByEntityEvent::new)
        );
        try { constructDefaultListener(EntityCreatePortalEvent.class, SEntityCreatePortalEvent.class, SBukkitEntityCreatePortalEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        constructDefaultListener(EntityDamageEvent.class, SEntityDamageEvent.class, factory(SBukkitEntityDamageEvent::new)
                .sub(EntityDamageByEntityEvent.class, SBukkitEntityDamageByEntityEvent::new)
                .sub(EntityDamageByBlockEvent.class, SBukkitEntityDamageByBlockEvent::new)
        );
        constructDefaultListener(EntityDeathEvent.class, SEntityDeathEvent.class, factory(SBukkitEntityDeathEvent::new)
                .sub(PlayerDeathEvent.class, SBukkitPlayerDeathEvent::new)
        );
        if (has("org.bukkit.event.entity.EntityDropItemEvent")) {
            try { constructDefaultListener(EntityDropItemEvent.class, SEntityDropItemEvent.class, SBukkitEntityDropItemEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        if (has("org.bukkit.event.entity.EntityEnterBlockEvent")) {
            try { constructDefaultListener(EntityEnterBlockEvent.class, SEntityEnterBlockEvent.class, SBukkitEntityEnterBlockEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        if (has("org.bukkit.event.entity.EntityEnterLoveModeEvent")) {
            try { constructDefaultListener(EntityEnterLoveModeEvent.class, SEntityEnterLoveModeEvent.class, SBukkitEntityEnterLoveModeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        if (has("org.bukkit.event.entity.EntityExhaustionEvent")) {
            try { constructDefaultListener(EntityExhaustionEvent.class, SEntityExhaustionEvent.class, SBukkitEntityExhaustionEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(EntityExplodeEvent.class, SEntityExplodeEvent.class, SBukkitEntityExplodeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(EntityInteractEvent.class, SEntityInteractEvent.class, SBukkitEntityInteractEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.entity.EntityPickupItemEvent")) {
            constructDefaultListener(EntityPickupItemEvent.class, SEntityPickupItemEvent.class, event -> {
                if (event.getEntity() instanceof Player) {
                    return new SBukkitModernPlayerPickupItemEvent(event);
                }
                return new SBukkitEntityPickupItemEvent(event);
            });
        } else {
            try { constructDefaultListener(PlayerPickupItemEvent.class, SEntityPickupItemEvent.class, SBukkitLegacyPlayerPickupItemEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        if (has("org.bukkit.event.entity.EntityPlaceEvent")) {
            try { constructDefaultListener(EntityPlaceEvent.class, SEntityPlaceEvent.class, SBukkitEntityPlaceEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }

        // EntityTeleportEvent is a weird event, the child has its own HandlerList
        try { constructDefaultListener(EntityTeleportEvent.class, SEntityTeleportEvent.class, SBukkitEntityTeleportEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(EntityPortalEvent.class, SEntityTeleportEvent.class, SBukkitEntityPortalEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }

        try { constructDefaultListener(EntityPortalEnterEvent.class, SEntityPortalEnterEvent.class, SBukkitEntityPortalEnterEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(EntityPortalExitEvent.class, SEntityPortalExitEvent.class, SBukkitEntityPortalExitEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.entity.EntityPoseChangeEvent")) {
            try { constructDefaultListener(EntityPoseChangeEvent.class, SEntityPoseChangeEvent.class, SBukkitEntityPoseChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        if (has("org.bukkit.event.entity.EntityPotionEffectEvent")) {
            try { constructDefaultListener(EntityPotionEffectEvent.class, SEntityPotionEffectEvent.class, SBukkitEntityPotionEffectEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(EntityRegainHealthEvent.class, SEntityRegainHealthEvent.class, SBukkitEntityRegainHealthEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.entity.EntityResurrectEvent")) {
            try { constructDefaultListener(EntityResurrectEvent.class, SEntityResurrectEvent.class, SBukkitEntityResurrectEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(EntityShootBowEvent.class, SEntityShootBowEvent.class, SBukkitEntityShootBowEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        constructDefaultListener(EntitySpawnEvent.class, SEntitySpawnEvent.class, factory(SBukkitEntitySpawnEvent::new)
                .sub(ItemSpawnEvent.class, SBukkitItemSpawnEvent::new)
                .sub(ProjectileLaunchEvent.class, projectileLaunchEvent -> {
                    if (projectileLaunchEvent.getEntity().getShooter() instanceof Player) {
                        return new SBukkitPlayerProjectileLaunchEvent(projectileLaunchEvent);
                    }
                    return new SBukkitProjectileLaunchEvent(projectileLaunchEvent);
                })
                .sub(CreatureSpawnEvent.class, SBukkitCreatureSpawnEvent::new)
        );
        try { constructDefaultListener(EntityTameEvent.class, SEntityTameEvent.class, SBukkitEntityTameEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        constructDefaultListener(EntityTargetEvent.class, SEntityTargetEvent.class, factory(SBukkitEntityTargetEvent::new)
                .sub(EntityTargetLivingEntityEvent.class, SBukkitEntityTargetLivingEntityEvent::new)
        );
        try { constructDefaultListener(FoodLevelChangeEvent.class, SFoodLevelChangeEvent.class, SBukkitFoodLevelChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(HorseJumpEvent.class, SHorseJumpEvent.class, SBukkitHorseJumpEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(ItemDespawnEvent.class, SItemDespawnEvent.class, SBukkitItemDespawnEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(ItemMergeEvent.class, SItemMergeEvent.class, SBukkitItemMergeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }

        // ProjectileHitEvent is a weird event, the child has its own HandlerList
        try { constructDefaultListener(ProjectileHitEvent.class, SProjectileHitEvent.class, SBukkitProjectileHitEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.entity.ExpBottleEvent")) {
            try { constructDefaultListener(ExpBottleEvent.class, SProjectileHitEvent.class, SBukkitExpBottleEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }

        try { constructDefaultListener(SheepDyeWoolEvent.class, SSheepDyeWoolEvent.class, SBukkitSheepDyeWoolEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(SheepRegrowWoolEvent.class, SSheepRegrowWoolEvent.class, SBukkitSheepRegrowWoolEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(SlimeSplitEvent.class, SSlimeSplitEvent.class, SBukkitSlimeSplitEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.entity.StriderTemperatureChangeEvent")) {
            try { constructDefaultListener(StriderTemperatureChangeEvent.class, SStriderTemperatureChangeEvent.class, SBukkitStriderTemperatureChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(VillagerAcquireTradeEvent.class, SVillagerAcquireTradeEvent.class, SBukkitVillagerAcquireTradeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.entity.VillagerCareerChangeEvent")) {
            try { constructDefaultListener(VillagerCareerChangeEvent.class, SVillagerCareerChangeEvent.class, SBukkitVillagerCareerChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(VillagerReplenishTradeEvent.class, SVillagerReplenishTradeEvent.class, SBukkitVillagerReplenishTradeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(EntityToggleGlideEvent.class, SEntityToggleGlideEvent.class, SBukkitEntityToggleGlideEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.entity.EntityToggleSwimEvent")) {
            try { constructDefaultListener(EntityToggleSwimEvent.class, SEntityToggleSwimEvent.class, SBukkitEntityToggleSwimEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        constructDefaultListener(EntityUnleashEvent.class, SEntityUnleashEvent.class, factory(SBukkitEntityUnleashEvent::new)
                .sub(PlayerUnleashEntityEvent.class, SBukkitPlayerUnleashEntityEvent::new)
        );
        try { constructDefaultListener(ExplosionPrimeEvent.class, SExplosionPrimeEvent.class, SBukkitExplosionPrimeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(FireworkExplodeEvent.class, SFireworkExplodeEvent.class, SBukkitFireworkExplodeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(VehicleCreateEvent.class, SVehicleCreateEvent.class, SBukkitVehicleCreateEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }

        // player
        try { constructDefaultListener(AsyncPlayerPreLoginEvent.class, SAsyncPlayerPreLoginEvent.class, SBukkitAsyncPlayerPreLoginEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(AsyncPlayerChatEvent.class, SPlayerChatEvent.class, SBukkitPlayerChatEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerJoinEvent.class, SPlayerJoinEvent.class, SBukkitPlayerJoinEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerQuitEvent.class, SPlayerLeaveEvent.class, SBukkitPlayerLeaveEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(BlockPlaceEvent.class, SPlayerBlockPlaceEvent.class, SBukkitPlayerBlockPlaceEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(BlockDamageEvent.class, SPlayerBlockDamageEvent.class, SBukkitPlayerBlockDamageEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        /* we should register this only if someone exactly wants PlayerMoveEvent and not PlayerTeleportEvent */
        new AbstractBukkitEventHandlerFactory<>(PlayerMoveEvent.class, SPlayerMoveEvent.class, plugin, false, true) {
            @Override
            protected SPlayerMoveEvent wrapEvent(PlayerMoveEvent event, EventPriority priority) {
                return new SBukkitPlayerMoveEvent(event);
            }
        };
        // although PlayerTeleportEvent extends PlayerMoveEvent, it has its own HandlerList
        try { constructDefaultListener(PlayerTeleportEvent.class, SPlayerMoveEvent.class, SBukkitPlayerTeleportEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        // although PlayerPortalEvent extends PlayerTeleportEvent, it has its own HandlerList
        try { constructDefaultListener(PlayerPortalEvent.class, SPlayerMoveEvent.class, SBukkitPlayerPortalEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerChangedWorldEvent.class, SPlayerWorldChangeEvent.class, SBukkitPlayerWorldChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(SignChangeEvent.class, SPlayerUpdateSignEvent.class, SBukkitPlayerUpdateSignEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerRespawnEvent.class, SPlayerRespawnEvent.class, SBukkitPlayerRespawnEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerCommandPreprocessEvent.class, SPlayerCommandPreprocessEvent.class, SBukkitPlayerCommandPreprocessEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        constructDefaultListener(InventoryClickEvent.class, SPlayerInventoryClickEvent.class, factory(SBukkitPlayerInventoryClickEvent::new)
                        .sub(CraftItemEvent.class, SBukkitPlayerCraftItemEvent::new)
        );
        try { constructDefaultListener(FoodLevelChangeEvent.class, SPlayerFoodLevelChangeEvent.class, SBukkitPlayerFoodLevelChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerDropItemEvent.class, SPlayerDropItemEvent.class, SBukkitPlayerDropItemEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerBedEnterEvent.class, SPlayerBedEnterEvent.class, SBukkitPlayerBedEnterEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerAnimationEvent.class, SPlayerAnimationEvent.class, SBukkitPlayerAnimationEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }

        // PlayerInteractEntityEvent is a weird event, each child has its own HandlerList
        try { constructDefaultListener(PlayerInteractEntityEvent.class, SPlayerInteractEntityEvent.class, SBukkitPlayerInteractEntityEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerInteractAtEntityEvent.class, SPlayerInteractEntityEvent.class, SBukkitPlayerInteractAtEntityEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.player.PlayerArmorStandManipulateEvent")) {
            try { constructDefaultListener(PlayerArmorStandManipulateEvent.class, SPlayerInteractEntityEvent.class, SBukkitPlayerArmorStandManipulateEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(PlayerBedLeaveEvent.class, SPlayerBedLeaveEvent.class, SBukkitPlayerBedLeaveEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }

        // PlayerBucketEvent is abstract and doesn't have implemented handler list
        try { constructDefaultListener(PlayerBucketEmptyEvent.class, SPlayerBucketEvent.class, SBukkitPlayerBucketEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerBucketFillEvent.class, SPlayerBucketEvent.class, SBukkitPlayerBucketEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }

        if (has("org.bukkit.event.player.PlayerCommandSendEvent")) {
            try { constructDefaultListener(PlayerCommandSendEvent.class, SPlayerCommandSendEvent.class, SBukkitPlayerCommandSendEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(PlayerEggThrowEvent.class, SPlayerEggThrowEvent.class, SBukkitPlayerEggThrowEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerExpChangeEvent.class, SPlayerExpChangeEvent.class, SBukkitPlayerExpChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerFishEvent.class, SPlayerFishEvent.class, SBukkitPlayerFishEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerGameModeChangeEvent.class, SPlayerGameModeChangeEvent.class, SBukkitPlayerGameModeChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.player.PlayerHarvestBlockEvent")) {
            try { constructDefaultListener(PlayerHarvestBlockEvent.class, SPlayerHarvestBlockEvent.class, SBukkitPlayerHarvestBlockEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(PlayerInteractEvent.class, SPlayerInteractEvent.class, SBukkitPlayerInteractEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerItemConsumeEvent.class, SPlayerItemConsumeEvent.class, SBukkitPlayerItemConsumeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerItemDamageEvent.class, SPlayerItemDamageEvent.class, SBukkitPlayerItemDamageEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerItemHeldEvent.class, SPlayerItemHeldEvent.class, SBukkitPlayerItemHeldEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.player.PlayerItemMendEvent")) {
            try { constructDefaultListener(PlayerItemMendEvent.class, SPlayerItemMendEvent.class, SBukkitPlayerItemMendEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(PlayerKickEvent.class, SPlayerKickEvent.class, SBukkitPlayerKickEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerLevelChangeEvent.class, SPlayerLevelChangeEvent.class, SBukkitPlayerLevelChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.player.PlayerLocaleChangeEvent")) {
            try { constructDefaultListener(PlayerLocaleChangeEvent.class, SPlayerLocaleChangeEvent.class, SBukkitPlayerLocaleChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(PlayerLoginEvent.class, SPlayerLoginEvent.class, SBukkitPlayerLoginEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerShearEntityEvent.class, SPlayerShearEntityEvent.class, SBukkitPlayerShearEntityEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.player.PlayerSwapHandItemsEvent")) {
            try { constructDefaultListener(PlayerSwapHandItemsEvent.class, SPlayerSwapHandItemsEvent.class, SBukkitPlayerSwapHandItemsEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(PlayerToggleFlightEvent.class, SPlayerToggleFlightEvent.class, SBukkitPlayerToggleFlightEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerToggleSneakEvent.class, SPlayerToggleSneakEvent.class, SBukkitPlayerToggleSneakEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerToggleSprintEvent.class, SPlayerToggleSprintEvent.class, SBukkitPlayerToggleSprintEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(PlayerVelocityEvent.class, SPlayerVelocityChangeEvent.class, SBukkitPlayerVelocityChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(InventoryOpenEvent.class, SPlayerInventoryOpenEvent.class, SBukkitPlayerInventoryOpenEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(InventoryCloseEvent.class, SPlayerInventoryCloseEvent.class, SBukkitPlayerInventoryCloseEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }

        // block
        try { constructDefaultListener(BlockBurnEvent.class, SBlockBurnEvent.class, SBukkitBlockBurnEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.block.BlockCookEvent")) {
            try { constructDefaultListener(BlockCookEvent.class, SBlockCookEvent.class, SBukkitBlockCookEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(BlockDispenseEvent.class, SBlockDispenseEvent.class, SBukkitBlockDispenseEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.block.BlockDropItemEvent")) {
            try { constructDefaultListener(BlockDropItemEvent.class, SBlockDropItemEvent.class, SBukkitBlockDropItemEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        constructDefaultListener(BlockExpEvent.class, SBlockExperienceEvent.class, factory(SBukkitBlockExperienceEvent::new)
                .sub(BlockBreakEvent.class, SBukkitPlayerBlockBreakEvent::new)
        );
        try { constructDefaultListener(BlockExplodeEvent.class, SBlockExplodeEvent.class, SBukkitBlockExplodeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(BlockFadeEvent.class, SBlockFadeEvent.class, SBukkitBlockFadeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.block.BlockFertilizeEvent")) {
            try { constructDefaultListener(BlockFertilizeEvent.class, SBlockFertilizeEvent.class, SBukkitBlockFertilizeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }

        // children of BlockGrowEvent have their own HandlerList's (Bukkit is retarded, change my mind)
        try { constructDefaultListener(BlockGrowEvent.class, SBlockGrowEvent.class, SBukkitBlockGrowEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        constructDefaultListener(BlockFormEvent.class, SBlockGrowEvent.class, factory(SBukkitBlockFormEvent::new)
                .sub(EntityBlockFormEvent.class, SBukkitBlockFormedByEntityEvent::new)
        );
        try { constructDefaultListener(BlockSpreadEvent.class, SBlockGrowEvent.class, SBukkitBlockSpreadEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }

        try { constructDefaultListener(BlockFromToEvent.class, SBlockFromToEvent.class, SBukkitBlockFromToEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(BlockIgniteEvent.class, SBlockIgniteEvent.class, SBukkitBlockIgniteEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(BlockPhysicsEvent.class, SBlockPhysicsEvent.class, SBukkitBlockPhysicsEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(BlockRedstoneEvent.class, SRedstoneEvent.class, SBukkitRedstoneEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(LeavesDecayEvent.class, SLeavesDecayEvent.class, SBukkitLeavesDecayEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }

        // BlockPistonEvent is abstract and doesn't have implemented handler list
        try { constructDefaultListener(BlockPistonExtendEvent.class, SBlockPistonEvent.class, SBukkitBlockPistonExtendEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(BlockPistonRetractEvent.class, SBlockPistonEvent.class, SBukkitBlockPistonRetractEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }

        if (has("org.bukkit.event.block.BlockShearEntityEvent")) {
            try { constructDefaultListener(BlockShearEntityEvent.class, SBlockShearEntityEvent.class, SBukkitBlockShearEntityEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(CauldronLevelChangeEvent.class, SCauldronLevelChangeEvent.class, SBukkitCauldronLevelChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.block.FluidLevelChangeEvent")) {
            try { constructDefaultListener(FluidLevelChangeEvent.class, SFluidLevelChangeEvent.class, SBukkitFluidLevelChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        if (has("org.bukkit.event.block.MoistureChangeEvent")) {
            try { constructDefaultListener(MoistureChangeEvent.class, SMoistureChangeEvent.class, SBukkitMoistureChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(StructureGrowEvent.class, SPlantGrowEvent.class, SBukkitPlantGrowEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.block.SpongeAbsorbEvent")) {
            try { constructDefaultListener(SpongeAbsorbEvent.class, SSpongeAbsorbEvent.class, SBukkitSpongeAbsorbEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }

        // world
        try { constructDefaultListener(SpawnChangeEvent.class, SSpawnChangeEvent.class, SBukkitSpawnChangeEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        if (has("org.bukkit.event.world.TimeSkipEvent")) {
            try { constructDefaultListener(TimeSkipEvent.class, STimeSkipEvent.class, SBukkitTimeSkipEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        }
        try { constructDefaultListener(WorldInitEvent.class, SWorldInitEvent.class, SBukkitWorldInitEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(WorldLoadEvent.class, SWorldLoadEvent.class, SBukkitWorldLoadEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(WorldSaveEvent.class, SWorldSaveEvent.class, SBukkitWorldSaveEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(WorldUnloadEvent.class, SWorldUnloadEvent.class, SBukkitWorldUnloadEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }

        // chunk
        try { constructDefaultListener(ChunkLoadEvent.class, SChunkLoadEvent.class, SBukkitChunkLoadEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(ChunkPopulateEvent.class, SChunkPopulateEvent.class, SBukkitChunkPopulateEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
        try { constructDefaultListener(ChunkUnloadEvent.class, SChunkUnloadEvent.class, SBukkitChunkUnloadEvent::new); } catch(Throwable throwable) {System.out.println(throwable.getMessage()); }
    }

    /**
     * @param bukkitEvent the bukkit event
     * @param screamingEvent screaming event class, must be the interface from core module!!! (if it's a child event, you should specify the parent here)
     * @param function which returns the constructed screaming event
     */
    private <S extends SEvent, B extends Event> void constructDefaultListener(Class<B> bukkitEvent, Class<S> screamingEvent, Function<B, ? extends S> function) {
        new AbstractBukkitEventHandlerFactory<>(bukkitEvent, screamingEvent, plugin) {
            @Override
            protected S wrapEvent(B event, EventPriority priority) {
                return function.apply(event);
            }
        };
    }

    /**
     * @param bukkitEvent the bukkit event
     * @param screamingEvent screaming event class, must be the abstract class from core module!!!
     * @param factory which constructs screaming event
     */
    private <S extends SEvent, B extends Event> void constructDefaultListener(Class<B> bukkitEvent, Class<S> screamingEvent, EventFactory<? extends S, B> factory) {
        constructDefaultListener(bukkitEvent, screamingEvent, factory.finish());
    }

    private static <S extends SEvent, B extends Event> EventFactory<S, B> factory(Function<B, S> function) {
        return new EventFactory<>(function);
    }

    @Data
    private static class EventFactory<S extends SEvent, B extends Event>  {
        private final Function<B, S> defaultOne;
        private final List<Map.Entry<Class<?>, Function<? extends B, S>>> customSubEvents = new ArrayList<>();

        private <T extends B> EventFactory<S, B> sub(Class<T> clazz, Function<T, S> func) {
            customSubEvents.add(Map.entry(clazz, func));
            return this;
        }

        private Function<B, S> finish() {
            Collections.reverse(customSubEvents); // the last registered are the one that should run first because why not
            return event -> {
                for (var entry : customSubEvents) {
                    if (entry.getKey().isInstance(event)) {
                        return ((Function<B,S>) entry.getValue()).apply(event);
                    }
                }
                return defaultOne.apply(event);
            };
        }
    }
}
