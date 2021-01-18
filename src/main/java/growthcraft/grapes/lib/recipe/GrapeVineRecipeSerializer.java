package growthcraft.grapes.lib.recipe;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class GrapeVineRecipeSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<GrapeVineRecipe> {

    @Override
    public GrapeVineRecipe read(ResourceLocation recipeId, JsonObject json) {
        ItemStack result = CraftingHelper.getItemStack(JSONUtils.getJsonObject(json, "result"), true);
        Ingredient ingredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "source"));
        return new GrapeVineRecipe(recipeId, ingredient, result);
    }

    @Override
    public GrapeVineRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        ItemStack result = buffer.readItemStack();
        Ingredient ingredient = Ingredient.read(buffer);
        return new GrapeVineRecipe(recipeId, ingredient, result);
    }

    @Override
    public void write(PacketBuffer buffer, GrapeVineRecipe recipe) {
        Ingredient ingredient = recipe.getIngredients().get(0);
        ingredient.write(buffer);
        buffer.writeItemStack(recipe.getRecipeOutput(), false);
    }

}
