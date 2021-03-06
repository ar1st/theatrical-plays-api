package aris.thesis.theatricalplaysapi.utils

fun <T> List<T>.paginated(page:Int, size: Int): List<T> {

    if (page <= 0 || size <= 0)
        return this

    val pageContents = mutableListOf<T>()

    val start = ((page - 1) * size)
    val end = start + size - 1

    for (i in start..end) {
        pageContents.add(getOrNull(i) ?: break)
    }

    return pageContents
}