package aris.thesis.theatricalplaysapi.controllers.actions

interface ActionExecutorConsumer<E: ActionExecutor<out Action>> {
    var executor: E
}