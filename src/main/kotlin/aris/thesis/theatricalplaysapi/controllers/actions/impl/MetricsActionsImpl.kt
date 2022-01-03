package aris.thesis.theatricalplaysapi.controllers.actions.impl

import aris.thesis.theatricalplaysapi.controllers.actions.ActionExecutor
import aris.thesis.theatricalplaysapi.controllers.actions.Actions
import aris.thesis.theatricalplaysapi.metrics.MetricsService
import aris.thesis.theatricalplaysapi.services.proto.ModelServiceConsumer
import org.springframework.stereotype.Component

@Component
class MetricsActionsImpl: ActionExecutor<Actions.Metrics>, ModelServiceConsumer<MetricsService>() {

    fun getStatusMetric(): Array<out Array<Any>>? {
        return service.getGraphData()
    }
}