package aris.thesis.theatricalplaysapi.controllers.base

import aris.thesis.theatricalplaysapi.controllers.actions.Action
import aris.thesis.theatricalplaysapi.controllers.actions.ActionExecutor

abstract class TheatricalPlaysRestController<E: ActionExecutor<out Action>> : RestActionController<E>() {
}