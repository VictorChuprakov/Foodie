package com.example.foodie.details.data.mapper

import com.example.foodie.details.data.model.CHOCDF
import com.example.foodie.details.data.model.FAT
import com.example.foodie.details.data.model.FoodDetails
import com.example.foodie.details.data.model.Hit
import com.example.foodie.details.data.model.Images
import com.example.foodie.details.data.model.LARGE
import com.example.foodie.details.data.model.Links
import com.example.foodie.details.data.model.PROCNT
import com.example.foodie.details.data.model.REGULAR
import com.example.foodie.details.data.model.Recipe
import com.example.foodie.details.data.model.SMALL
import com.example.foodie.details.data.model.Self
import com.example.foodie.details.data.model.THUMBNAIL
import com.example.foodie.details.data.model.TotalNutrients
import com.example.foodie.details.data.modelDTO.CHOCDFDTO
import com.example.foodie.details.data.modelDTO.FATDTO
import com.example.foodie.details.data.modelDTO.FoodDetailsDTO
import com.example.foodie.details.data.modelDTO.HitDTO
import com.example.foodie.details.data.modelDTO.ImagesDTO
import com.example.foodie.details.data.modelDTO.LARGEDTO
import com.example.foodie.details.data.modelDTO.PROCNTDTO
import com.example.foodie.details.data.modelDTO.REGULARDTO
import com.example.foodie.details.data.modelDTO.RecipeDTO
import com.example.foodie.details.data.modelDTO.SMALLDTO
import com.example.foodie.details.data.modelDTO.THUMBNAILDTO
import com.example.foodie.details.data.modelDTO.TotalNutrientsDTO


fun FoodDetailsDTO.toFoodDetails(): FoodDetails {
    return FoodDetails(
        _links = this._links?.toLinks() ?: Links(
            self = Self(
                "", ""
            )
        ),
        count = this.count ?: 0,
        from = this.from ?: 0,
        hits = this.hits?.map { it.toHit() } ?: emptyList(),
        to = this.to ?: 0)
}

fun com.example.foodie.details.data.modelDTO.LinksDTO.toLinks(): Links {
    return Links(
        self = this.self?.toSelf() ?: Self(href = "", title = "")
    )
}

fun com.example.foodie.details.data.modelDTO.SelfDTO.toSelf(): Self {
    return Self(
        href = this.href ?: "", title = this.title ?: ""
    )
}


fun HitDTO.toHit(): Hit {
    return Hit(
        recipe = this.recipe?.toRecipe() ?: Recipe(
            0.0,
            "",
            Images(
                large = LARGE(0, "", 0),
                REGULAR(0, "", 0),
                SMALL(0, "", 0),
                THUMBNAIL(0, "", 0)
            ),

            emptyList(),
            "",
            emptyList(),
            TotalNutrients(
                CHOCDF("", 0.0, ""),
                FAT("", 0.0, ""),
                PROCNT("", 0.0, ""),
            ),
            0.0,
            0.0,
            ""
        )
    )
}


fun RecipeDTO.toRecipe(): Recipe {
    return Recipe(
        calories = this.calories ?: 0.0,
        image = this.image ?: "",
        images = this.images?.toImages() ?: Images(
            LARGE(0, "", 0), REGULAR(0, "", 0), SMALL(0, "", 0), THUMBNAIL(0, "", 0)
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
        url = this.url ?: ""
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
        small = this.SMALL?.toSmall() ?: SMALL(0, "", 0),
        thumbnail = this.THUMBNAIL?.toThumbnail() ?: THUMBNAIL(0, "", 0)
    )
}

fun LARGEDTO.toLarge(): LARGE {
    return LARGE(
        height = this.height ?: 0, url = this.url ?: "", width = this.width ?: 0
    )
}

fun REGULARDTO.toRegular(): REGULAR {
    return REGULAR(
        height = this.height ?: 0, url = this.url ?: "", width = this.width ?: 0
    )
}

fun SMALLDTO.toSmall(): SMALL {
    return SMALL(
        height = this.height ?: 0, url = this.url ?: "", width = this.width ?: 0
    )
}

fun THUMBNAILDTO.toThumbnail(): THUMBNAIL {
    return THUMBNAIL(
        height = this.height ?: 0, url = this.url ?: "", width = this.width ?: 0
    )
}
