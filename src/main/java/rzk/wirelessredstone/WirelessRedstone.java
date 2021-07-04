package rzk.wirelessredstone;

import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import rzk.wirelessredstone.client.gui.ClientScreens;
import rzk.wirelessredstone.network.PacketHandler;
import rzk.wirelessredstone.registry.ModBlocks;
import rzk.wirelessredstone.registry.ModItems;
import rzk.wirelessredstone.registry.ModTiles;
import rzk.wirelessredstone.util.WRCommands;
import rzk.wirelessredstone.util.WRConfig;
import rzk.wirelessredstone.util.WRItemGroup;

@Mod(WirelessRedstone.MOD_ID)
public final class WirelessRedstone
{
	public static final String MOD_ID = "wirelessredstone";
	public static final ItemGroup ITEM_GROUP = new WRItemGroup();

	public WirelessRedstone()
	{
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

		eventBus.addListener(this::setup);
		eventBus.addListener(this::loadComplete);
		eventBus.register(ModBlocks.class);
		eventBus.register(ModTiles.class);
		eventBus.register(ModItems.class);

		MinecraftForge.EVENT_BUS.register(WRCommands.class);

		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> ClientScreens::openConfigGui);
	}

	private void setup(FMLCommonSetupEvent event)
	{
		PacketHandler.instance = NetworkRegistry.newSimpleChannel(
				new ResourceLocation(WirelessRedstone.MOD_ID, "main_channel"),
				() -> PacketHandler.PROTOCOL_VERSION,
				PacketHandler.PROTOCOL_VERSION::equals,
				PacketHandler.PROTOCOL_VERSION::equals);

		PacketHandler.registerMessages();
	}

	private void loadComplete(FMLLoadCompleteEvent event)
	{
		WRConfig.save();
	}
}
