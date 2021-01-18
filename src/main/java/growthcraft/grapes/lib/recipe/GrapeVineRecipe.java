package growthcraft.grapes.lib.recipe;

import growthcraft.grapes.init.GrowthcraftGrapesRecipeSerializers;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class GrapeVineRecipe implements IGrapeVineRecipe {

    private final ResourceLocation id;
    private final ItemStack output;
    private final Ingredient input;

    public GrapeVineRecipe(ResourceLocation id, Ingredient input, ItemStack output) {
        this.id = id;
        this.input = input;
        this.output = output;
    }

    @Override
    public boolean matches(RecipeWrapper inv, World worldIn) {
        return this.input.test(inv.getStackInSlot(0));
    }

    @Override
    public Ingredient getInput() {
        return this.input;
    }

    @Override
    public ItemStack getCraftingResult(RecipeWrapper inv) {
        return this.output;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return GrowthcraftGrapesRecipeSerializers.GRAPE_VINE_SERIALIZER.get();
    }
}
