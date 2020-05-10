package growthcraft.bamboo.init.config;

import growthcraft.core.init.config.GrowthcraftConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

public class GrowthcraftBambooConfig {

    private static ForgeConfigSpec.BooleanValue enableBambooTrees;
    private static ForgeConfigSpec.BooleanValue enableBambooTreeSpread;
    private static ForgeConfigSpec.IntValue maxBambooTreesHeight;

    static {
        initWorldGenConfig(GrowthcraftConfig.SERVER_BUILDER, GrowthcraftConfig.CLIENT_BUILDER);
    }

    private GrowthcraftBambooConfig() { /* Prevent Default Public Constructor */ }

    public static void loadConfig() {
        GrowthcraftConfig.loadConfig(GrowthcraftConfig.SERVER, FMLPaths.CONFIGDIR.get().resolve(GrowthcraftConfig.SERVER_CONFIG).toString());
        GrowthcraftConfig.loadConfig(GrowthcraftConfig.CLIENT, FMLPaths.CONFIGDIR.get().resolve(GrowthcraftConfig.CLIENT_CONFIG).toString());
    }

    public static void initWorldGenConfig(ForgeConfigSpec.Builder server, ForgeConfigSpec.Builder client) {
        // This will include worldgen configuration settings for bamboo trees.
        enableBambooTrees = server.comment("Configure Growthcraft Bamboo Trees").define("worldgen.growthcraft_bamboo_trees.enable", true);
        enableBambooTreeSpread = server.comment("Can bamboo trees automatically spread and grow other trees?").define("worldgen.growthcraft_bamboo_trees.enable_spread", true);
        maxBambooTreesHeight = server.comment("Set the maximum height that bamboo trees will grow.").defineInRange("worldgen.growthcraft_bamboo_trees.maxHeight", 20, 5, 250);
    }

    public boolean bambooTreesEnabled() { return enableBambooTrees.get(); }

}
