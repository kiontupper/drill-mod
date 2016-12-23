package com.tupster24.tcdrill;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.TinkerRegistryClient;
import slimeknights.tconstruct.library.client.ToolBuildGuiInfo;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.Pattern;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;

import static slimeknights.tconstruct.tools.TinkerTools.pattern;

@Mod(modid = TcDrill.MODID, version = TcDrill.VERSION, dependencies = "required-after:tconstruct@[1.10.2-2.6.1,)", acceptedMinecraftVersions = "[1.10.2, 1.11)")
public class TcDrill {
	public static final String MODID = "tcdrill";
	public static final String VERSION = "1.0";

	public static ToolPart drillHead;
	public static ToolPart drillCollar;
	public static ToolPart drillHandle;
	public static ToolCore drill;

	protected static <T extends Item> T registerItem(T part, String name) {
		part.setUnlocalizedName("tcdrill:" + name);
		part.setRegistryName(new ResourceLocation("tcdrill", name));
		GameRegistry.register(part);
		return part;
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		drillHead = registerItem(new ToolPart(Material.VALUE_Ingot * 3), "drill_head");
		drillCollar = registerItem(new ToolPart(Material.VALUE_Ingot * 2), "drill_collar");
		drillHandle = registerItem(new ToolPart(Material.VALUE_Ingot * 3), "drill_handle");
		drillHead.setCreativeTab(TinkerRegistry.tabParts);
		drillCollar.setCreativeTab(TinkerRegistry.tabParts);
		drillHandle.setCreativeTab(TinkerRegistry.tabParts);

		drill = registerItem(new Drill(), "drill");
		drill.setCreativeTab(TinkerRegistry.tabTools);
		TinkerRegistry.registerTool(drill);
		TinkerRegistry.registerToolForgeCrafting(drill);

		ItemStack stencil;
		stencil = new ItemStack(pattern);
		Pattern.setTagForPart(stencil, drillHead);
		TinkerRegistry.registerStencilTableCrafting(stencil);
		stencil = new ItemStack(pattern);
		Pattern.setTagForPart(stencil, drillCollar);
		TinkerRegistry.registerStencilTableCrafting(stencil);
		stencil = new ItemStack(pattern);
		Pattern.setTagForPart(stencil, drillHandle);
		TinkerRegistry.registerStencilTableCrafting(stencil);
		if (event.getSide().isClient()) {
			ModelRegisterUtil.registerPartModel(drillHead);
			ModelRegisterUtil.registerPartModel(drillCollar);
			ModelRegisterUtil.registerPartModel(drillHandle);
			ModelRegisterUtil.registerToolModel(drill);
		}
	}

	@EventHandler
	@SideOnly(Side.CLIENT)
	public void init(FMLInitializationEvent event) {
		ToolBuildGuiInfo info = new ToolBuildGuiInfo(drill);
		info.addSlotPosition(33 - 18, 42 + 18);
		info.addSlotPosition(33 + 18, 42 - 18);
		info.addSlotPosition(33, 42);
		TinkerRegistryClient.addToolBuilding(info);
	}
}
