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
import net.minecraftforge.registries.ForgeRegistries;

public class BrewKettleRecipe implements IRecipe<IInventory> {

    public static final BrewKettleRecipeSerializer serializer = new BrewKettleRecipeSerializer();
    public static final IRecipeType<BrewKettleRecipe> recipeType = IRecipeType.register(UnlocalizedName.RECIPE_WORT_FLUID);

    private final ResourceLocation id;
    private FluidStack resultFluidStack;
    private Ingredient ingredientItem;
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
        ResourceLocation resultFluidBucket = new ResourceLocation(this.getResult().getFluid().getRegistryName() + "_bucket");
        return new ItemStack(ForgeRegistries.ITEMS.getValue(resultFluidBucket));
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        ResourceLocation resultFluidBucket = new ResourceLocation(this.getResult().getFluid().getRegistryName() + "_bucket");
        return new ItemStack(ForgeRegistries.ITEMS.getValue(resultFluidBucket));
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
