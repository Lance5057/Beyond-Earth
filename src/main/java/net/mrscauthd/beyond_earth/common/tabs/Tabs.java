package net.mrscauthd.beyond_earth.common.tabs;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import net.mrscauthd.beyond_earth.common.registries.BlockRegistry;
import net.mrscauthd.beyond_earth.common.registries.ItemsRegistry;

public class Tabs {
	public static CreativeModeTab tab_normal = new CreativeModeTab("tab_normal") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemsRegistry.TIER_1_ROCKET_ITEM.get(), 1);
		}
	};
	public static CreativeModeTab tab_machines = new CreativeModeTab("tab_machines") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemsRegistry.NASA_WORKBENCH_ITEM.get(), 1);
		}
	};
	public static CreativeModeTab tab_basics = new CreativeModeTab("tab_basics") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemsRegistry.DESH_ENGINE.get(), 1);
		}
	};
	public static CreativeModeTab tab_materials = new CreativeModeTab("tab_materials") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemsRegistry.IRON_PLATE.get(), 1);
		}
	};
	public static CreativeModeTab tab_globes = new CreativeModeTab("tab_globes") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(BlockRegistry.GLACIO_GLOBE_BLOCK.get(), 1);
		}
	};
	public static CreativeModeTab tab_blocks = new CreativeModeTab("tab_blocks") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(BlockRegistry.MOON_IRON_ORE.get(), 1);
		}
	};
	public static CreativeModeTab tab_spawn_eggs = new CreativeModeTab("tab_spawn_eggs") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemsRegistry.ALIEN_SPAWN_EGG.get(), 1);
		}
	};
}
