package com.example.foodie.dishes.data.mapper

import com.example.foodie.dishes.data.model.FoodRandom
import com.example.foodie.dishes.data.model.Hit
import com.example.foodie.dishes.data.model.Images
import com.example.foodie.dishes.data.model.LARGE
import com.example.foodie.dishes.data.model.Links
import com.example.foodie.dishes.data.model.REGULAR
import com.example.foodie.dishes.data.model.Recipe
import com.example.foodie.dishes.data.model.SMALL
import com.example.foodie.dishes.data.model.Self
import com.example.foodie.dishes.data.model.THUMBNAIL
import com.example.foodie.dishes.data.modelDTO.FoodRandomDTO
import com.example.foodie.dishes.data.modelDTO.HitDTO
import com.example.foodie.dishes.data.modelDTO.ImagesDTO
import com.example.foodie.dishes.data.modelDTO.LARGEDTO
import com.example.foodie.dishes.data.modelDTO.LinksDTO
import com.example.foodie.dishes.data.modelDTO.REGULARDTO
import com.example.foodie.dishes.data.modelDTO.RecipeDTO
import com.example.foodie.dishes.data.modelDTO.SMALLDTO
import com.example.foodie.dishes.data.modelDTO.SelfDTO
import com.example.foodie.dishes.data.modelDTO.THUMBNAILDTO


fun FoodRandomDTO.toFoodRandom(): FoodRandom {
    return FoodRandom(
        _links = this._links?.toLinks() ?: Links(
            self = Self(
                "",
                ""
            )
        ), // Обработка отсутствующего _links
        count = this.count ?: 0,
        from = this.from ?: 0,
        hits = this.hits?.map { it.toHit() } ?: emptyList(),
        to = this.to ?: 0
    )
}

fun LinksDTO.toLinks(): Links {
    return Links(
        self = this.self?.toSelf() ?: Self(href = "", title = "")
    )
}

fun SelfDTO.toSelf(): Self {
    return Self(
        href = this.href ?: "",
        title = this.title ?: ""
    )
}


fun HitDTO.toHit(): Hit {
    return Hit(
        recipe = this.recipe?.toRecipe() ?: Recipe(
            "",
            Images(
                large = LARGE(0, "", 0),
                REGULAR(0, "", 0),
                SMALL(0, "", 0),
                THUMBNAIL(0, "", 0)
            ),
            "",
            emptyList(),
            0.0,
            ""
        )
    )
}

fun RecipeDTO.toRecipe(): Recipe {
    return Recipe(
        image = this.image ?: "",
        images = this.images?.toImages() ?: Images(
            large = LARGE(0, "", 0),
            REGULAR(0, "", 0),
            SMALL(0, "", 0),
            THUMBNAIL(0, "", 0)
        ),
        label = this.label ?: "",
        mealType = this.mealType ?: emptyList(),
        totalTime = this.totalTime ?: 0.0,
        uri = this.uri ?: ""
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
        height = this.height ?: 0,
        url = this.url ?: "",
        width = this.width ?: 0
    )
}

fun REGULARDTO.toRegular(): REGULAR {
    return REGULAR(
        height = this.height ?: 0,
        url = this.url ?: "",
        width = this.width ?: 0
    )
}

fun SMALLDTO.toSmall(): SMALL {
    return SMALL(
        height = this.height ?: 0,
        url = this.url ?: "",
        width = this.width ?: 0
    )
}

fun THUMBNAILDTO.toThumbnail(): THUMBNAIL {
    return THUMBNAIL(
        height = this.height ?: 0,
        url = this.url ?: "",
        width = this.width ?: 0
    )
}
