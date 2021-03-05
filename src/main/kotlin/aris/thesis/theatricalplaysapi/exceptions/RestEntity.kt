package aris.thesis.theatricalplaysapi.exceptions


interface RestEntity<E:RestEntity<E,ID>,ID: Any> {
    var id: ID?

}