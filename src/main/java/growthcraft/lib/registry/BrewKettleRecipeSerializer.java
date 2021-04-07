package growthcraft.lib.registry;

import com.google.gson.JsonObject;
import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.lib.recipe.BrewKettleRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BrewKettleRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<BrewKettleRecipe> {

    public List<Item> ingredients = new ArrayList<>();

    @Override
    public BrewKettleRecipe read(ResourceLocation recipeId, JsonObject json) {

        BrewKettleRecipe recipe = new BrewKettleRecipe(recipeId);

        ResourceLocation ingredientItemResourceLocation = ResourceLocation.tryCreate(JSONUtils.getString(
                json.get("ingredient_item").getAsJsonObject(), "item", "minecraft:empty"));
        int ingredientItemAmount = JSONUtils.getInt(json.get("ingredient_item").getAsJsonObject(), "count", 0);
        ItemStack ingredientItemStack = new ItemStack(ForgeRegistries.ITEMS.getValue(ingredientItemResourceLocation), ingredientItemAmount);

        recipe.setItemIngredient(Ingredient.fromStacks(ingredientItemStack));
        //recipe.setItemIngredientAmount(JSONUtils.getInt(json.get("ingredient_item").getAsJsonObject(), "count", 1));

        // Input Fluid
        ResourceLocation ingredientFluidResourceLocation = ResourceLocation.tryCreate(JSONUtils.getString(
                json.get("ingredient_fluid").getAsJsonObject(), "fluid", "minecraft:empty"));
        int ingredientFluidAmount = JSONUtils.getInt(json.get("ingredient_fluid").getAsJsonObject(), "amount", 0);
        recipe.setIngredientFluidStack(new FluidStack(ForgeRegistries.FLUIDS.getValue(ingredientFluidResourceLocation), ingredientFluidAmount));

        GrowthcraftCellar.LOGGER.error(recipe.getFluidIngredient().getFluid().toString());

        // Output Fluid
        ResourceLocation resultResourceLocation = ResourceLocation.tryCreate(JSONUtils.getString(
                json.get("result").getAsJsonObject(), "fluid", "minecraft:empty"));
        int resultAmount = JSONUtils.getInt(json.get("result").getAsJsonObject(), "amount", 0);
        recipe.setFluidStackResult(new FluidStack(ForgeRegistries.FLUIDS.getValue(resultResourceLocation), resultAmount));

        GrowthcraftCellar.LOGGER.error(recipe.getResult().getFluid().toString());

        recipe.setRequiresLid(JSONUtils.getBoolean(json, "requiresLid", false));
        recipe.setProcessingTime(JSONUtils.getInt(json, "processTime", 200));

        return recipe;
    }

    @Nullable
    @Override
    public BrewKettleRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        BrewKettleRecipe recipe = new BrewKettleRecipe(recipeId);
        recipe.setItemIngredient(Ingredient.read(buffer));
        recipe.setIngredientFluidStack(buffer.readFluidStack());
        recipe.setFluidStackResult(buffer.readFluidStack());
        recipe.setProcessingTime(buffer.readInt());
        recipe.setRequiresLid(buffer.readBoolean());
        return recipe;
    }

    @Override
    public void write(PacketBuffer buffer, BrewKettleRecipe recipe) {
        recipe.getItemIngredient().write(buffer);
        buffer.writeFluidStack(recipe.getFluidIngredient());
        buffer.writeFluidStack(recipe.getResult());
        buffer.writeInt(recipe.getProcessingTime());
        buffer.writeBoolean(recipe.isLidRequired());
    }
}
