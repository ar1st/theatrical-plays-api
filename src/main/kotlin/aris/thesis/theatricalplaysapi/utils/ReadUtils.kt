package aris.thesis.theatricalplaysapi.utils

import org.springframework.core.io.ClassPathResource
import java.io.File
import java.nio.file.Files

object ReadUtils {

    fun loadAsString(path: String?): String? {
        return try {
            val resource: File = ClassPathResource(path!!).file
            String(Files.readAllBytes(resource.toPath()))
        } catch (e: Exception) {
            null
        }
    }
}