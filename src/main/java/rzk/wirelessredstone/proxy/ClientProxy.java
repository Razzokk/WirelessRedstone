package rzk.wirelessredstone.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import rzk.wirelessredstone.client.gui.GuiFrequency;
import rzk.wirelessredstone.client.render.TERFrequency;
import rzk.wirelessredstone.registry.ModItems;
import rzk.wirelessredstone.tile.TileFrequency;

public class ClientProxy implements IProxy
{
	private static final IItemPropertyGetter POWERED = (stack, world, entity) ->
			stack.getOrCreateTag().getBoolean("powered") ? 1.0F : 0.0F;

	@Override
	public void clientSetup(FMLClientSetupEvent event)
	{
		ClientRegistry.bindTileEntityRenderer(TileFrequency.TYPE, TERFrequency::new);
		ItemModelsProperties.registerProperty(ModItems.REMOTE, new ResourceLocation("powered"), POWERED);
	}

	@Override
	public void openFrequencyGuiBlock(int frequency, BlockPos pos)
	{
		Minecraft.getInstance().displayGuiScreen(new GuiFrequency(frequency, pos));
	}

	@Override
	public void openFrequencyGuiItem(int frequency, Hand hand)
	{
		Minecraft.getInstance().displayGuiScreen(new GuiFrequency(frequency, hand));
	}
}
