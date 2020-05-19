package growthcraft.lib.worldgen;

import growthcraft.core.init.GrowthcraftBlocks;
import growthcraft.core.init.config.GrowthcraftConfig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class OreGeneration {

    private OreGeneration() { /* Disable default public constructor */ }

    public static void setupOreGeneration() {
        for(Biome biome : ForgeRegistries.BIOMES) {
            if(GrowthcraftConfig.getSaltOreGenerate()) {
                CountRangeConfig countRangeConfig = new CountRangeConfig(
                        GrowthcraftConfig.getSaltOreGenCount(),
                        GrowthcraftConfig.getSaltOreGenMinHeight(),
                        0,
                        GrowthcraftConfig.getSaltOreGenMaxHeight()
                );

                biome.addFeature(
                        GenerationStage.Decoration.UNDERGROUND_ORES,
                        Feature.ORE.withConfiguration(
                                new OreFeatureConfig(
                                        OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                                        GrowthcraftBlocks.rockSaltOre.get().getDefaultState(),
                                        GrowthcraftConfig.getSaltOreGenChance()
                                )
                        ).withPlacement(Placement.COUNT_RANGE
                                .configure(countRangeConfig))
                );
            }
        }
    }
}
