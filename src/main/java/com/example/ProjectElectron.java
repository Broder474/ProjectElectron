package com.example;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

	public class ProjectElectron implements ModInitializer {
		// This logger is used to write text to the console and the log file.
		// It is considered best practice to use your mod id as the logger's name.
		// That way, it's clear which mod wrote info, warnings, and errors.
		public static final Logger LOGGER = LoggerFactory.getLogger("modid");
		public static final String MOD_ID = "pe";
		public final Item handle_engine = new HandleEngine(new FabricItemSettings());
		public static final RegistryKey<ItemGroup> POWER_PRODUCERS = RegistryKey.of(RegistryKeys.ITEM_GROUP,
				new Identifier(MOD_ID, "power_producers"));

		@Override
		public void onInitialize() {
			// This code runs as soon as Minecraft is in a mod-load-ready state.
			// However, some things (like resources) may still be uninitialized.
			// Proceed with mild caution.

			// register custom block
			Registry.register(Registries.ITEM, new Identifier(MOD_ID, "handle_engine"),
					handle_engine);
			// register custom item group
			Registry.register(Registries.ITEM_GROUP, POWER_PRODUCERS, FabricItemGroup.builder()
					.icon(() -> new ItemStack(handle_engine))
					.displayName(Text.translatable("pe.group.power_producers"))
					.build());

			ItemGroupEvents.modifyEntriesEvent(POWER_PRODUCERS).register(entry -> {
				entry.add(handle_engine);
			});

		}
	}