package net.wasabo.tutorialmod.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class MagicAxe extends Item {

    public MagicAxe(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()){

            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();



            for (int i = 0; i <= positionClicked.getY() + 64; i++){
                BlockState state = context.getWorld().getBlockState(positionClicked.up(i));

                if(isWoodLog(state)){
                    context.getWorld().breakBlock(positionClicked.up(i), true);
                }
            }

        }

        context.getStack().damage(1, context.getPlayer(),
                playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));

        return ActionResult.SUCCESS;
    }


    private boolean isWoodLog(BlockState state){
        return state.isIn(BlockTags.LOGS);
    }


}
