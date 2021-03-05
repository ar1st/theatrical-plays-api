package aris.thesis.theatricalplaysapi.controllers.base

import aris.thesis.theatricalplaysapi.controllers.actions.Action
import aris.thesis.theatricalplaysapi.controllers.actions.ActionExecutor
import aris.thesis.theatricalplaysapi.controllers.actions.ActionExecutorConsumer
import org.springframework.beans.factory.annotation.Autowired

abstract class RestActionController<E: ActionExecutor<out Action>>: ActionExecutorConsumer<E> {

    @Autowired
    override lateinit var executor: E
}