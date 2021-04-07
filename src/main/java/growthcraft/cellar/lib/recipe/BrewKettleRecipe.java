package growthcraft.cellar.lib.recipe;

import growthcraft.cellar.shared.UnlocalizedName;
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

public class BrewKettleRecipe implements IRecipe<IInventory> {

    public static final BrewKettleRecipeSerializer serializer = new BrewKettleRecipeSerializer();
    public static final IRecipeType<BrewKettleRecipe> recipeType = IRecipeType.register(UnlocalizedName.RECIPE_WORT_FLUID);

    private final ResourceLocation id;
    private FluidStack resultFluidStack;
    private Ingredient ingredientItem;
    private int ingredientItemAmount;
    private FluidStack ingredientFluidStack;
    private int processingTime;
    private boolean requiresLid;

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

    public Ingredient getItemIngredient() {
        return ingredientItem;
    }

    public void setIngredientFluidStack(FluidStack ingredientFluidStack) {
        this.ingredientFluidStack = ingredientFluidStack;
    }

    public void setItemIngredient(Ingredient ingredientItemStack) {
        this.ingredientItem = ingredientItemStack;
    }

    public void setItemIngredientAmount(int amount) {
        ingredientItemAmount = amount;
    }

    public void setFluidStackResult(FluidStack resultFluidStack) {
        this.resultFluidStack = resultFluidStack;
    }

    public void setRequiresLid(boolean requiresLid) {
        this.requiresLid = requiresLid;
    }

    public boolean isLidRequired() {
        return requiresLid;
    }

    @Override
    public boolean matches(IInventory inv, World worldIn) {
        // TODO: finish this

        return false;
    }

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        // TODO: Change to universal bucket?

        return ItemStack.EMPTY;
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
        NonNullList<Ingredient> ingredientsList = NonNullList.create();

        ingredientsList.add(ingredientItem);
        return ingredientsList;
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
        return recipeType;
    }
}
