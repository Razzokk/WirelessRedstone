package rzk.wirelessredstone.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import rzk.wirelessredstone.item.SnifferItem;
import rzk.wirelessredstone.render.WorldOverlayRenderer;

public class WRClientEventsNeo
{
	public static void handleSnifferHighlightPacket(long timestamp, Hand hand, BlockPos[] coords)
	{
		PlayerEntity player = MinecraftClient.getInstance().player;
		ItemStack stack = player.getStackInHand(hand);
		SnifferItem.setHighlightedBlocks(timestamp, stack, coords);
	}

	@SubscribeEvent
	public static void renderWorld(RenderLevelStageEvent event)
	{
		if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) return;
		var world = MinecraftClient.getInstance().world;
		WorldOverlayRenderer.render(world, event.getCamera().getPos(), event.getPoseStack(), event.getPartialTick());
	}
}
