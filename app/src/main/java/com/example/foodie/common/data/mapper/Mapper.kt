package com.example.foodie.common.data.mapper

import com.example.foodie.common.data.model.CHOCDF
import com.example.foodie.common.data.model.FAT
import com.example.foodie.common.data.model.FoodResponce
import com.example.foodie.common.data.model.Hit
import com.example.foodie.common.data.model.Images
import com.example.foodie.common.data.model.PROCNT
import com.example.foodie.common.data.model.Recipe
import com.example.foodie.common.data.model.TotalNutrients
import com.example.foodie.common.data.modelDTO.CHOCDFDTO
import com.example.foodie.common.data.modelDTO.FATDTO
import com.example.foodie.common.data.modelDTO.FoodResponseDTO
import com.example.foodie.common.data.modelDTO.HitDTO
import com.example.foodie.common.data.modelDTO.ImagesDTO
import com.example.foodie.common.data.model.LARGE
import com.example.foodie.common.data.modelDTO.LARGEDTO
import com.example.foodie.common.data.modelDTO.PROCNTDTO
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
    if (this == null) {
        return Recipe(
            calories = 0.0,
            image = "",
            images = toImages(),
            ingredientLines = emptyList(),
            label = "",
            mealType = emptyList(),
            totalNutrients = toTotalNutrients(),
            totalTime = 0.0,
            totalWeight = 0.0,
            url = "",
            uri = "",
        )
    }
    return Recipe(
        calories = this.calories ?: 0.0,
        image = this.image ?: "",
        images = this.images.toImages(),
        ingredientLines = this.ingredientLines ?: emptyList(),
        label = this.label ?: "",
        mealType = this.mealType ?: emptyList(),
        totalNutrients = this.totalNutrients.toTotalNutrients(),
        totalTime = this.totalTime ?: 0.0,
        totalWeight = this.totalWeight ?: 0.0,
        url = this.url ?: "",
        uri = this.uri ?: "",
    )
}


fun TotalNutrientsDTO?.toTotalNutrients(): TotalNutrients {
    if (this == null) {
        return TotalNutrients(
            CHOCDF("", 0.0, ""),
            FAT("", 0.0, ""),
            PROCNT("", 0.0, ""),
        )
    }
    return TotalNutrients(
        CHOCDF = this.CHOCDF.toCHOCDF(),
        FAT = this.FAT.toFAT(),
        PROCNT = this.PROCNT.toPROCNT()
    )
}


fun ImagesDTO?.toImages(): Images {
    if (this == null) {
        return Images(
            LARGE(0, "", 0),
        )
    }
    return Images(
        large = this.LARGE.toLarge(),
    )
}


fun LARGEDTO?.toLarge(): LARGE {
    if (this == null) {
        return LARGE(0, "", 0)
    }
    return LARGE(
        height = this.height ?: 0, url = this.url ?: "", width = this.width ?: 0
    )
}


fun CHOCDFDTO?.toCHOCDF(): CHOCDF {
    if (this == null) {
        return CHOCDF("", 0.0, "")
    }
    return CHOCDF(
        label = this.label ?: "", quantity = this.quantity ?: 0.0, unit = this.unit ?: ""
    )
}


fun FATDTO?.toFAT(): FAT {
    if (this == null) {
        return FAT("", 0.0, "")
    }
    return FAT(
        label = this.label ?: "", quantity = this.quantity ?: 0.0, unit = this.unit ?: ""
    )
}

fun PROCNTDTO?.toPROCNT(): PROCNT {
    if (this == null) {
        return PROCNT("", 0.0, "")
    }
    return PROCNT(
        label = this.label ?: "", quantity = this.quantity ?: 0.0, unit = this.unit ?: ""
    )
}