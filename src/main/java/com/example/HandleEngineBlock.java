package com.example;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HandleEngineBlock extends Block {

    public static final Integer MAX_ACCUMULATED_POWER = 20;
    public static final IntProperty ACCUMULATED_POWER = IntProperty.of("accumulated_power", 0, 20);


    public HandleEngineBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(ACCUMULATED_POWER, 10));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(ACCUMULATED_POWER);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        Integer accumulated_power = world.getBlockState(pos).get(ACCUMULATED_POWER);
        player.sendMessage(Text.of("Accumulated power: " + accumulated_power), false);
        if (accumulated_power < MAX_ACCUMULATED_POWER)
            world.setBlockState(pos, state.with(ACCUMULATED_POWER, accumulated_power + 1));
        return ActionResult.SUCCESS;
    }
}