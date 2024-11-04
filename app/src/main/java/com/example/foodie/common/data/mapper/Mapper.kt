package com.example.foodie.common.data.mapper

import com.example.foodie.common.data.model.Chocdf
import com.example.foodie.common.data.model.Fat
import com.example.foodie.common.data.model.FoodResponce
import com.example.foodie.common.data.model.Hit
import com.example.foodie.common.data.model.Images
import com.example.foodie.common.data.model.Procnt
import com.example.foodie.common.data.model.Recipe
import com.example.foodie.common.data.model.TotalNutrients
import com.example.foodie.common.data.modelDTO.ChocdfDTO
import com.example.foodie.common.data.modelDTO.FatDTO
import com.example.foodie.common.data.modelDTO.FoodResponseDTO
import com.example.foodie.common.data.modelDTO.HitDTO
import com.example.foodie.common.data.modelDTO.ImagesDTO
import com.example.foodie.common.data.model.Large
import com.example.foodie.common.data.modelDTO.LargeDTO
import com.example.foodie.common.data.modelDTO.ProcntDTO
import com.example.foodie.common.data.modelDTO.RecipeDTO
import com.example.foodie.common.data.modelDTO.TotalNutrientsDTO


fun FoodResponseDTO.toFoodResponce(): FoodResponce {
    return FoodResponce(
        count = this.count ?: 0,
        from = this.from ?: 0,
        hits = this.hits?.map { it.toHit() } ?: emptyList(),
        to = this.to ?: 0)
}

fun HitDTO.toHit(): Hit {
    return Hit(
        recipe = this.recipe.toRecipe()
    )
}

fun RecipeDTO?.toRecipe(): Recipe {
    return Recipe(
        calories = this?.calories ?: 0.0,
        image = this?.image ?: "",
        images = this?.images.toImages(),
        ingredientLines = this?.ingredientLines ?: emptyList(),
        label = this?.label ?: "",
        mealType = this?.mealType ?: emptyList(),
        totalNutrients = this?.totalNutrients.toTotalNutrients(),
        totalTime = this?.totalTime ?: 0.0,
        totalWeight = this?.totalWeight ?: 0.0,
        url = this?.url ?: "",
        uri = this?.uri ?: "",
    )
}

fun TotalNutrientsDTO?.toTotalNutrients(): TotalNutrients {
    return TotalNutrients(
        CHOCDF = this?.CHOCDF.toCHOCDF(),
        FAT = this?.FAT.toFAT(),
        PROCNT = this?.PROCNT.toPROCNT()
    )
}

fun ImagesDTO?.toImages(): Images {
    return Images(
        large = this?.LARGE.toLarge(),
    )
}

fun LargeDTO?.toLarge(): Large {
    return Large(
        height = this?.height ?: 0,
        url = this?.url ?: "",
        width = this?.width ?: 0
    )
}

fun ChocdfDTO?.toCHOCDF(): Chocdf {
    return Chocdf(
        label = this?.label ?: "",
        quantity = this?.quantity ?: 0.0,
        unit = this?.unit ?: ""
    )
}

fun FatDTO?.toFAT(): Fat {
    return Fat(
        label = this?.label ?: "",
        quantity = this?.quantity ?: 0.0,
        unit = this?.unit ?: ""
    )
}

fun ProcntDTO?.toPROCNT(): Procnt {
    return Procnt(
        label = this?.label ?: "",
        quantity = this?.quantity ?: 0.0,
        unit = this?.unit ?: ""
    )
}