package aris.thesis.theatricalplaysapi.rest

abstract class RestEntityRegistration<E: RestEntity<E, ID>,ID: Any>(private val entityClass: Class<out E>) {

    val entityName: String = entityClass.simpleName
    open val entityIDKey = "id"


    private var idType: Class<ID>? = null

    @Suppress("unused")
    fun idClass(): Class<ID> {
        if (idType == null) {
            @Suppress("UNCHECKED_CAST")
            idType = entityClass.declaredFields
                .first { it.name == entityIDKey }.type as Class<ID>
        }

        @Suppress("UNCHECKED_CAST")
        return idType ?: entityClass.declaredFields
            .first { it.name == entityIDKey }.type as Class<ID>
    }

}