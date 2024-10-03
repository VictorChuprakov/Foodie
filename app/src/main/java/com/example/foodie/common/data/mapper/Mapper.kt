package com.example.foodie.common.data.mapper

import com.example.foodie.common.data.model.CHOCDF
import com.example.foodie.common.data.model.FAT
import com.example.foodie.common.data.model.FoodResponce
import com.example.foodie.common.data.model.Hit
import com.example.foodie.common.data.model.Images
import com.example.foodie.common.data.model.Links
import com.example.foodie.common.data.model.PROCNT
import com.example.foodie.common.data.model.REGULAR
import com.example.foodie.common.data.model.Recipe
import com.example.foodie.common.data.model.Self
import com.example.foodie.common.data.model.TotalNutrients
import com.example.foodie.common.data.model.CHOCDFDTO
import com.example.foodie.common.data.model.FATDTO
import com.example.foodie.common.data.model.FoodResponseDTO
import com.example.foodie.common.data.model.HitDTO
import com.example.foodie.common.data.model.LinksDTO
import com.example.foodie.common.data.model.ImagesDTO
import com.example.foodie.common.data.model.LARGE
import com.example.foodie.common.data.model.LARGEDTO
import com.example.foodie.common.data.model.PROCNTDTO
import com.example.foodie.common.data.model.REGULARDTO
import com.example.foodie.common.data.model.RecipeDTO
import com.example.foodie.common.data.model.SelfDTO
import com.example.foodie.common.data.model.TotalNutrientsDTO


fun FoodResponseDTO.toFoodResponce(): FoodResponce {
    return FoodResponce(_links = this._links?.toLinks() ?: Links(
        self = Self(
            "", ""
        )
    ),
        count = this.count ?: 0,
        from = this.from ?: 0,
        hits = this.hits?.map { it.toHit() } ?: emptyList(),
        to = this.to ?: 0)
}

fun LinksDTO.toLinks(): Links {
    return Links(
        self = this.self?.toSelf() ?: Self(href = "", title = "")
    )
}

fun SelfDTO.toSelf(): Self {
    return Self(
        href = this.href ?: "", title = this.title ?: ""
    )
}


fun HitDTO.toHit(): Hit {
    return Hit(
        recipe = this.recipe?.toRecipe() ?: Recipe(
            0.0, "", Images(
                LARGE(0, "", 0),
                REGULAR(0, "", 0),
            ),

            emptyList(), "", emptyList(), TotalNutrients(
                CHOCDF("", 0.0, ""),
                FAT("", 0.0, ""),
                PROCNT("", 0.0, ""),
            ), 0.0, 0.0, "", ""
        )
    )
}


fun RecipeDTO.toRecipe(): Recipe {
    return Recipe(
        calories = this.calories ?: 0.0,
        image = this.image ?: "",
        images = this.images?.toImages() ?: Images(
            LARGE(0, "", 0), REGULAR(0, "", 0)
        ),
        ingredientLines = this.ingredientLines ?: emptyList(),
        label = this.label ?: "",
        mealType = this.mealType ?: emptyList(),
        totalNutrients = this.totalNutrients?.toTotalNutrients() ?: TotalNutrients(
            CHOCDF("", 0.0, ""),
            FAT("", 0.0, ""),
            PROCNT("", 0.0, ""),
        ),

        totalTime = this.totalTime ?: 0.0,
        totalWeight = this.totalWeight ?: 0.0,
        url = this.url ?: "",
        uri = this.uri ?: "",
    )
}


fun TotalNutrientsDTO.toTotalNutrients(): TotalNutrients {
    return TotalNutrients(
        CHOCDF = this.CHOCDF?.toCHOCDF() ?: CHOCDF("", 0.0, ""),
        FAT = this.FAT?.toFAT() ?: FAT("", 0.0, ""),
        PROCNT = this.PROCNT?.toPROCNT() ?: PROCNT("", 0.0, ""),
    )
}


fun CHOCDFDTO.toCHOCDF(): CHOCDF {
    return CHOCDF(
        label = this.label ?: "", quantity = this.quantity ?: 0.0, unit = this.unit ?: ""
    )
}


fun FATDTO.toFAT(): FAT {
    return FAT(
        label = this.label ?: "", quantity = this.quantity ?: 0.0, unit = this.unit ?: ""
    )
}

fun PROCNTDTO.toPROCNT(): PROCNT {
    return PROCNT(
        label = this.label ?: "", quantity = this.quantity ?: 0.0, unit = this.unit ?: ""
    )
}

fun ImagesDTO.toImages(): Images {
    return Images(
        large = this.LARGE?.toLarge() ?: LARGE(0, "", 0),
        regular = this.REGULAR?.toRegular() ?: REGULAR(0, "", 0),
    )
}


fun REGULARDTO.toRegular(): REGULAR {
    return REGULAR(
        height = this.height ?: 0, url = this.url ?: "", width = this.width ?: 0
    )
}

fun LARGEDTO.toLarge(): LARGE {
    return LARGE(
        height = this.height ?: 0, url = this.url ?: "", width = this.width ?: 0
    )
}
