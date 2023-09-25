package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

	public class ProjectElectron implements ModInitializer {

		public static final Logger LOGGER = LoggerFactory.getLogger("modid");
		public static final String MOD_ID = "pe";
		public static final Block HANDLE_ENGINE_BLOCK = new HandleEngineBlock(FabricBlockSettings.create()
				.strength(4.0f));
		public final BlockItem HANDLE_ENGINE_ITEM = new HandleEngineItem(HANDLE_ENGINE_BLOCK, new FabricItemSettings());
		public static final RegistryKey<ItemGroup> POWER_PRODUCERS = RegistryKey.of(RegistryKeys.ITEM_GROUP,
				new Identifier(MOD_ID, "power_producers"));

		@Override
		public void onInitialize() {
			// This code runs as soon as Minecraft is in a mod-load-ready state.
			// However, some things (like resources) may still be uninitialized.
			// Proceed with mild caution.

			// register custom block
			Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "handle_engine"), HANDLE_ENGINE_BLOCK);
			Registry.register(Registries.ITEM, new Identifier(MOD_ID, "handle_engine"), HANDLE_ENGINE_ITEM);
			// register custom item group
			Registry.register(Registries.ITEM_GROUP, POWER_PRODUCERS, FabricItemGroup.builder()
					.icon(() -> new ItemStack(HANDLE_ENGINE_ITEM))
					.displayName(Text.translatable("pe.group.power_producers"))
					.build());

			ItemGroupEvents.modifyEntriesEvent(POWER_PRODUCERS).register(entry -> {
				entry.add(HANDLE_ENGINE_ITEM);
			});

		}
	}