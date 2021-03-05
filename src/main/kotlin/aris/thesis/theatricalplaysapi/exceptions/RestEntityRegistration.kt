package aris.thesis.theatricalplaysapi.exceptions

abstract class RestEntityRegistration<E:RestEntity<E,ID>,ID: Any>(val entityClass: Class<out E>) {

    val entityName: String = entityClass.simpleName
    open val entityIDKey = "id"


    private var idType: Class<ID>? = null

    fun idClass(): Class<ID> {
        if (idType == null) {
            idType = entityClass.declaredFields
                .first { it.name == entityIDKey }.type as Class<ID>
        }

        return idType ?: entityClass.declaredFields
            .first { it.name == entityIDKey }.type as Class<ID>
    }

}