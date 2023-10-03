package core;

import com.electron.BasicElectricFurnaceBlock;
import com.electron.BasicElectricFurnaceItem;
import com.electron.HandleEngineBlock;
import com.electron.HandleEngineItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
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
				.strength(4.0f)
				);
		public final BlockItem HANDLE_ENGINE_ITEM = new HandleEngineItem(HANDLE_ENGINE_BLOCK, new FabricItemSettings());

		public static final Block BASIC_ELECTRIC_FURNACE_BLOCK = new BasicElectricFurnaceBlock(FabricBlockSettings.create()
				.strength(4.0f)
		);
		public final BlockItem BASIC_ELECTRIC_FURNACE_ITEM = new BasicElectricFurnaceItem(BASIC_ELECTRIC_FURNACE_BLOCK, new FabricItemSettings());

		public static final RegistryKey<ItemGroup> POWER_PRODUCERS = RegistryKey.of(RegistryKeys.ITEM_GROUP,
				new Identifier(MOD_ID, "power_producers"));

		public static final MinecraftClient mc = MinecraftClient.getInstance();

		@Override
		public void onInitialize() {
			// register custom block and item
			Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "handle_engine"), HANDLE_ENGINE_BLOCK);
			Registry.register(Registries.ITEM, new Identifier(MOD_ID, "handle_engine"), HANDLE_ENGINE_ITEM);

			Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "basic_electric_furnace"), BASIC_ELECTRIC_FURNACE_BLOCK);
			Registry.register(Registries.ITEM, new Identifier(MOD_ID, "basic_electric_furnace"), BASIC_ELECTRIC_FURNACE_ITEM);

			// register custom item group
			Registry.register(Registries.ITEM_GROUP, POWER_PRODUCERS, FabricItemGroup.builder()
					.icon(() -> new ItemStack(HANDLE_ENGINE_ITEM))
					.displayName(Text.translatable("pe.group.power_producers"))
					.build());

			// add the entries to the item groups
			ItemGroupEvents.modifyEntriesEvent(POWER_PRODUCERS).register(entry -> {
				entry.add(HANDLE_ENGINE_ITEM);
			});

		}
	}