package growthcraft.lib.registry;

import com.google.gson.JsonObject;
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

        JSONUtils.getJsonArray(json, "ingredient_items").forEach(element -> {
            Ingredient ingredient = Ingredient.deserialize(element);
            int count = JSONUtils.getInt(element.getAsJsonObject(), "count", 1);
            recipe.addIngredientItemStack(ingredient, count);

            for (ItemStack stack : ingredient.getMatchingStacks()) {
                if (!ingredients.contains(stack.getItem())) {
                    ingredients.add(stack.getItem());
                }
            }
        });

        ResourceLocation ingredientFluidResourceLocation = ResourceLocation.create(JSONUtils.getString(
                json.get("ingredient_fluid").getAsJsonObject(), "fluid", "minecraft:empty"), ':');

        int ingredientFluidAmount = JSONUtils.getInt(json.get("ingredient_fluid").getAsJsonObject(), "amount", 0);

        recipe.setIngredientFluidStack(new FluidStack(ForgeRegistries.FLUIDS.getValue(ingredientFluidResourceLocation), ingredientFluidAmount));

        ResourceLocation resultResourceLocation = ResourceLocation.create(JSONUtils.getString(json.get("result").getAsJsonObject(), "fluid", "minecraft:empty"), ':');

        int resultAmount = JSONUtils.getInt(json.get("result").getAsJsonObject(), "amount", 0);

        recipe.setFluidStackResult(new FluidStack(ForgeRegistries.FLUIDS.getValue(resultResourceLocation), resultAmount));

        recipe.setProcessingTime(JSONUtils.getInt(json, "processTime", 200));

        return recipe;
    }

    @Nullable
    @Override
    public BrewKettleRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        BrewKettleRecipe recipe = new BrewKettleRecipe(recipeId);
        int ingredientCount = buffer.readByte();
        for (int i = 0; i < ingredientCount; ++i) {
            Ingredient ingredient = Ingredient.read(buffer);
            int count = buffer.readVarInt();
            recipe.addIngredientItemStack(ingredient, count);
        }
        recipe.setIngredientFluidStack(buffer.readFluidStack());
        recipe.setFluidStackResult(buffer.readFluidStack());
        recipe.setProcessingTime(buffer.readInt());
        return recipe;
    }

    @Override
    public void write(PacketBuffer buffer, BrewKettleRecipe recipe) {
        buffer.writeByte(recipe.getItemIngredients().size());
        recipe.getItemIngredients().forEach((ingredient, count) -> {
            ingredient.write(buffer);
            buffer.writeVarInt(count);
        });
        buffer.writeFluidStack(recipe.getFluidIngredient());
        buffer.writeFluidStack(recipe.getResult());
        buffer.writeInt(recipe.getProcessingTime());
    }
}
