package growthcraft.lib.common.block;

import growthcraft.lib.common.block.rope.IBlockRope;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.math.AxisAlignedBB;

public class GrowthcraftRopeBlock extends Block implements IBlockRope {

    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");

    private static final AxisAlignedBB KNOT_BOUNDING_BOX = new AxisAlignedBB(0.0625 * 7, 0.0625 * 7, 0.0625 * 7, 0.0625 * 9, 0.0625 * 9, 0.0625 * 9);
    private static final AxisAlignedBB NORTH_BOUNDING_BOX = new AxisAlignedBB(0.0625 * 7, 0.0625 * 7, 0.0625 * 0, 0.0625 * 9, 0.0625 * 9, 0.0625 * 7);
    private static final AxisAlignedBB EAST_BOUNDING_BOX = new AxisAlignedBB(0.0625 * 9, 0.0625 * 7, 0.0625 * 7, 0.0625 * 16, 0.0625 * 9, 0.0625 * 9);
    private static final AxisAlignedBB SOUTH_BOUNDING_BOX = new AxisAlignedBB(0.0625 * 7, 0.0625 * 7, 0.0625 * 9, 0.0625 * 9, 0.0625 * 9, 0.0625 * 16);
    private static final AxisAlignedBB WEST_BOUNDING_BOX = new AxisAlignedBB(0.0625 * 0, 0.0625 * 7, 0.0625 * 7, 0.0625 * 7, 0.0625 * 9, 0.0625 * 9);
    private static final AxisAlignedBB UP_BOUNDING_BOX = new AxisAlignedBB(0.0625 * 7, 0.0625 * 9, 0.0625 * 7, 0.0625 * 9, 0.0625 * 16, 0.0625 * 9);
    private static final AxisAlignedBB DOWN_BOUNDING_BOX = new AxisAlignedBB(0.0625 * 7, 0.0625 * 0, 0.0625 * 7, 0.0625 * 9, 0.0625 * 7, 0.0625 * 9);

    public GrowthcraftRopeBlock() {
        this(getInitProperties(Material.WOOL));
    }

    public GrowthcraftRopeBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(NORTH, Boolean.valueOf(false))
                .with(EAST, Boolean.valueOf(false))
                .with(SOUTH, Boolean.valueOf(false))
                .with(WEST, Boolean.valueOf(false))
                .with(UP, Boolean.valueOf(false))
                .with(DOWN, Boolean.valueOf(false)));
    }

    private static Properties getInitProperties(Material material) {
        Properties properties = Properties.create(material);
        properties.sound(SoundType.CLOTH);
        return properties;
    }

}
