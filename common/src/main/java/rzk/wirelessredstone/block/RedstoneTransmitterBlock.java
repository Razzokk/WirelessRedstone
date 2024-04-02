package rzk.wirelessredstone.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;
import rzk.wirelessredstone.api.Connectable;
import rzk.wirelessredstone.block.entity.ModBlockEntities;
import rzk.wirelessredstone.block.entity.RedstoneTransmitterBlockEntity;

import static net.minecraft.state.property.Properties.POWERED;

public class RedstoneTransmitterBlock extends RedstoneTransceiverBlock
{
	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx)
	{
		var world = ctx.getWorld();
		var pos = ctx.getBlockPos();
		var state = super.getPlacementState(ctx);
		return state.with(POWERED, isReceivingRedstonePower(world, pos));
	}

	@Override
	public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify)
	{
		world.getBlockEntity(pos, ModBlockEntities.redstoneTransmitterBlockEntityType)
			.ifPresent(entity -> entity.onBlockPlaced(state, world, pos));
	}

	@Override
	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved)
	{
		world.getBlockEntity(pos, ModBlockEntities.redstoneTransmitterBlockEntityType)
			.ifPresent(entity -> entity.onBlockRemoved(state, world, pos));
		super.onStateReplaced(state, world, pos, newState, moved);
	}

	public boolean isReceivingRedstonePower(WorldAccess world, BlockPos pos)
	{
		if (!(world.getBlockEntity(pos) instanceof Connectable connectable)) return false;

		for (Direction side : DIRECTIONS)
			if (connectable.isConnectable(side) && world.isEmittingRedstonePower(pos.offset(side), side))
				return true;

		return false;
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos)
	{
		boolean powered = isReceivingRedstonePower(world, pos);
		return state.with(POWERED, powered);
	}

	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state)
	{
		return new RedstoneTransmitterBlockEntity(pos, state);
	}
}
