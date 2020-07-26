package growthcraft.trapper.init;

import growthcraft.trapper.common.tileentity.TileEntityFishtrap;
import growthcraft.trapper.shared.Reference;
import growthcraft.trapper.shared.UnlocalizedName;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class GrowthcraftTrapperTileEntities {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Reference.MODID);

    public static RegistryObject<TileEntityType<TileEntityFishtrap>> oakFishtrapTileEntity;
    public static RegistryObject<TileEntityType<TileEntityFishtrap>> acaciaFishtrapTileEntity;

    static {
        oakFishtrapTileEntity = TILE_ENTITIES.register(
                UnlocalizedName.FISHTRAP_OAK,
                () -> TileEntityType.Builder.create(
                        () -> new TileEntityFishtrap(oakFishtrapTileEntity.get()), GrowthcraftTrapperBlocks.oakFishtrap.get()
                ).build(null)
        );
        acaciaFishtrapTileEntity = TILE_ENTITIES.register(
                UnlocalizedName.FISHTRAP_ACACIA,
                () -> TileEntityType.Builder.create(
                        () -> new TileEntityFishtrap(acaciaFishtrapTileEntity.get()), GrowthcraftTrapperBlocks.acaciaFishtrap.get()
                ).build(null)
        );
    }

}
