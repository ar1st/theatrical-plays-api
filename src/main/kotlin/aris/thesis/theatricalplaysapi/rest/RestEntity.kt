package aris.thesis.theatricalplaysapi.rest


interface RestEntity<E: RestEntity<E, ID>,ID: Any> {
    var id: ID?

}