package com.tupster24.tcdrill;

import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.AoeToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.tools.tools.Hatchet;
import slimeknights.tconstruct.tools.tools.Pickaxe;
import slimeknights.tconstruct.tools.tools.Shovel;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author tupster24
 */
public class Drill extends AoeToolCore {
	public Drill() {
		this(PartMaterialType.handle(TcDrill.drillHandle),
			PartMaterialType.head(TcDrill.drillHead),
			PartMaterialType.extra(TcDrill.drillCollar));
	}

	public Drill(PartMaterialType... parts) {
		super(parts);
		addCategory(Category.HARVEST);
		this.setHarvestLevel("pickaxe", 0);
	}

	@Override
	public void getSubItems(@Nonnull Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
		addDefaultSubItems(subItems);
		addInfiTool(subItems, "InfiDrill");
	}

	@Override
	public boolean isEffective(IBlockState state) {
		return Pickaxe.effective_materials.contains(state.getMaterial()) ||
			Shovel.effective_materials.contains(state.getMaterial()) ||
			Hatchet.effective_materials.contains(state.getMaterial());
	}

	@Override
	public float damagePotential() {
		return 0.5f;
	}

	@Override
	public double attackSpeed() {
		return 2f;
	}
	@Override
	protected ToolNBT buildTagData(List<slimeknights.tconstruct.library.materials.Material> materials) {
		ToolNBT tag = buildDefaultTag(materials);
		tag.modifiers += 2;
		tag.durability *= 1.5;
		return tag;
	}
}
