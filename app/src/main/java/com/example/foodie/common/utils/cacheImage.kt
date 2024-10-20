package com.example.foodie.common.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import java.io.File
import java.io.FileOutputStream

suspend fun cacheImages(context: Context, imageUrls: List<String>): List<String> {
    val savedImagePaths = mutableListOf<String>() // Список для сохранённых путей

    for (imageUrl in imageUrls) {
        // Построение запроса для каждого изображения
        val request = ImageRequest.Builder(context)
            .data(imageUrl)
            .build()

        // Получение результата запроса изображения
        val result = (context.imageLoader.execute(request) as SuccessResult).drawable
        val bitmap = (result as BitmapDrawable).bitmap

        // Сохранение изображения в кэш
        val cacheDir = File(context.cacheDir, "images")
        cacheDir.mkdirs() // Создание папки, если её нет

        // Создание уникального имени файла на основе текущего времени
        val file = File(cacheDir, "${System.currentTimeMillis()}.jpg")
        val fos = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
        fos.flush()
        fos.close()

        // Добавляем путь к файлу в список
        savedImagePaths.add(file.absolutePath)
    }

    // Возвращаем список путей к сохранённым файлам
    return savedImagePaths
}
