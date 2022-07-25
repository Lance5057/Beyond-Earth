package net.mrscauthd.beyond_earth.common.entities;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.mrscauthd.beyond_earth.BeyondEarth;

import net.mrscauthd.beyond_earth.common.events.forge.SetRocketItemStackEvent;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;
import net.mrscauthd.beyond_earth.common.registries.ParticlesRegistry;
import net.mrscauthd.beyond_earth.common.registries.TagsRegistry;

public class RocketTier3Entity extends IRocketEntity {

	public RocketTier3Entity(EntityType type, Level world) {
		super(type, world);
	}

	@Override
	public double getRocketSpeed() {
		return 0.8;
	}

	@Override
	public int getTier() {
		return 3;
	}

	@Override
	public double getPassengersRidingOffset() {
		return super.getPassengersRidingOffset() - 2.65;
	}

	@Override
	public ItemStack getRocketItem() {
		ItemStack itemStack = new ItemStack(ItemsRegistry.TIER_3_ROCKET_ITEM.get(), 1);
		itemStack.getOrCreateTag().putInt(BeyondEarth.MODID + ":fuel", this.getEntityData().get(FUEL));
		itemStack.getOrCreateTag().putInt(BeyondEarth.MODID + ":buckets", this.getEntityData().get(BUCKETS));
		MinecraftForge.EVENT_BUS.post(new SetRocketItemStackEvent(this, itemStack));

		return itemStack;
	}

	@Override
	public void spawnParticle() {
		Vec3 vec = this.getDeltaMovement();

		if (this.level instanceof ServerLevel) {
			if (this.entityData.get(START_TIMER) == 200) {
				for (ServerPlayer p : ((ServerLevel) this.level).getServer().getPlayerList().getPlayers()) {
					float f2 = Mth.cos(this.getYRot() * ((float) Math.PI / 180F)) * (0.7F + 0.21F * (float) 1.0F);
					float f3 = Mth.sin(this.getYRot() * ((float) Math.PI / 180F)) * (0.7F + 0.21F * (float) 1.0F);

					((ServerLevel) this.level).sendParticles(p, (ParticleOptions) ParticlesRegistry.LARGE_FLAME_PARTICLE.get(), true, this.getX() - vec.x, this.getY() - vec.y - 2.6, this.getZ() - vec.z, 20, 0.1, 0.1, 0.1, 0.001);
					((ServerLevel) this.level).sendParticles(p, (ParticleOptions) ParticlesRegistry.LARGE_SMOKE_PARTICLE.get(), true, this.getX() - vec.x, this.getY() - vec.y - 3.6, this.getZ() - vec.z, 10, 0.1, 0.1, 0.1, 0.04);

					((ServerLevel) this.level).sendParticles(p, (ParticleOptions) ParticlesRegistry.SMALL_FLAME_PARTICLE.get(), true, this.getX() + f2, this.getY() - vec.y - 2.5, this.getZ() + f3, 20, 0.1, 0.1, 0.1, 0.001);
					((ServerLevel) this.level).sendParticles(p, (ParticleOptions) ParticlesRegistry.SMALL_SMOKE_PARTICLE.get(), true, this.getX() + f2, this.getY() - vec.y - 3.5, this.getZ() + f3, 10, 0.1, 0.1, 0.1, 0.04);

					((ServerLevel) this.level).sendParticles(p, (ParticleOptions) ParticlesRegistry.SMALL_FLAME_PARTICLE.get(), true, this.getX() - f2, this.getY() - vec.y - 2.5, this.getZ() - f3, 20, 0.1, 0.1, 0.1, 0.001);
					((ServerLevel) this.level).sendParticles(p, (ParticleOptions) ParticlesRegistry.SMALL_SMOKE_PARTICLE.get(), true, this.getX() - f2, this.getY() - vec.y - 3.5, this.getZ() - f3, 10, 0.1, 0.1, 0.1, 0.04);
				}
			} else {
				for (ServerPlayer p : ((ServerLevel) this.level).getServer().getPlayerList().getPlayers()) {
					((ServerLevel) this.level).sendParticles(p, ParticleTypes.CAMPFIRE_COSY_SMOKE, true, this.getX() - vec.x, this.getY() - vec.y - 0.1, this.getZ() - vec.z, 6, 0.1, 0.1, 0.1, 0.023);
				}
			}
		}
	}

	@Override
	public void fillUpRocket() {
		if (this.getInventory().getStackInSlot(0).getItem() instanceof BucketItem) {
			if (((BucketItem) this.getInventory().getStackInSlot(0).getItem()).getFluid().is(TagsRegistry.FLUID_VEHICLE_FUEL_TAG) && this.entityData.get(BUCKETS) < 3) {
				if (this.entityData.get(FUEL) == 0 && this.entityData.get(BUCKETS) == 0) {
					this.getInventory().setStackInSlot(0, new ItemStack(Items.BUCKET));
					this.getEntityData().set(BUCKETS, this.getEntityData().get(BUCKETS) + 1);
				} else if (this.getEntityData().get(FUEL) == 1000 && this.getEntityData().get(BUCKETS) == 1) {
					this.getInventory().setStackInSlot(0, new ItemStack(Items.BUCKET));
					this.getEntityData().set(BUCKETS, this.getEntityData().get(BUCKETS) + 1);
				} else if (this.getEntityData().get(FUEL) == 2000 && this.getEntityData().get(BUCKETS) == 2) {
					this.getInventory().setStackInSlot(0, new ItemStack(Items.BUCKET));
					this.getEntityData().set(BUCKETS, this.getEntityData().get(BUCKETS) + 1);
				}
			}
		}

		if (this.getEntityData().get(BUCKETS) == 1 && this.getEntityData().get(FUEL) < 1000) {
			this.getEntityData().set(FUEL, this.getEntityData().get(FUEL) + 10);
		} else if (this.getEntityData().get(BUCKETS) == 2 && this.getEntityData().get(FUEL) < 2000) {
			this.getEntityData().set(FUEL, this.getEntityData().get(FUEL) + 10);
		} else if (this.getEntityData().get(BUCKETS) == 3 && this.getEntityData().get(FUEL) < 3000) {
			this.getEntityData().set(FUEL, this.getEntityData().get(FUEL) + 10);
		}
	}
}