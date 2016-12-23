package com.tupster24.tcdrill;

import net.minecraft.block.state.IBlockState;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.AoeToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.tools.tools.Hatchet;
import slimeknights.tconstruct.tools.tools.Pickaxe;
import slimeknights.tconstruct.tools.tools.Shovel;

import java.util.List;

/**
 * @author tupster24
 */
public class Drill extends AoeToolCore {
	public Drill() {
		this(PartMaterialType.head(TcDrill.drillHead),
			PartMaterialType.handle(TcDrill.drillHandle),
			PartMaterialType.extra(TcDrill.drillCollar));
	}

	public Drill(PartMaterialType... parts) {
		super(parts);
		addCategory(Category.HARVEST);
		this.setHarvestLevel("pickaxe", 0);
	}

	@Override
	public boolean isEffective(IBlockState state) {
		return Pickaxe.effective_materials.contains(state.getMaterial()) ||
			Shovel.effective_materials.contains(state.getMaterial()) ||
			Hatchet.effective_materials.contains(state.getMaterial());
	}

	@Override
	public float damagePotential() {
		return 1f;
	}

	@Override
	public double attackSpeed() {
		return 1.2f;
	}

	@Override
	protected ToolNBT buildTagData(List<slimeknights.tconstruct.library.materials.Material> materials) {
		return buildDefaultTag(materials);
	}
}
