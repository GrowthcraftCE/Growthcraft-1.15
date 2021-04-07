package growthcraft.cellar.lib.recipe;

import growthcraft.lib.registry.BrewKettleRecipeSerializer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.LinkedHashMap;
import java.util.Map;

public class BrewKettleRecipe implements IRecipe<IInventory> {

    public static final BrewKettleRecipeSerializer serializer = new BrewKettleRecipeSerializer();

    private final ResourceLocation id;
    private FluidStack resultFluidStack;
    private Map<Ingredient, Integer> ingredientItemStacks = new LinkedHashMap<>();
    private FluidStack ingredientFluidStack;
    private int processingTime;

    public BrewKettleRecipe(ResourceLocation id) {
        this.id = id;
    }

    public FluidStack getFluidIngredient() {
        return ingredientFluidStack;
    }

    public FluidStack getResult() {
        return resultFluidStack;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public Map<Ingredient, Integer> getItemIngredients() {
        return ingredientItemStacks;
    }

    public void setIngredientFluidStack(FluidStack ingredientFluidStack) {
        this.ingredientFluidStack = ingredientFluidStack;
    }

    public void setIngredientItemStacks(Map<Ingredient, Integer> ingredientItemStacks) {
        this.ingredientItemStacks = ingredientItemStacks;
    }

    public void addIngredientItemStack(Ingredient ingredient, Integer count) {
        ingredientItemStacks.put(ingredient, count);
    }

    public void setFluidStackResult(FluidStack resultFluidStack) {
        this.resultFluidStack = resultFluidStack;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        // TODO: finish this

        return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        // TODO: Change to universal bucket?

        return null;
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        // TODO: Change to Universal bucket?
        return ItemStack.EMPTY;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        // TODO: Finish this.
        return null;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return serializer;
    }

    @Override
    public IRecipeType<?> getType() {
        return null;
    }
}
