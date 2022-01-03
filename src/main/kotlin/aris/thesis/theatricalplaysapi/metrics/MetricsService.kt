package aris.thesis.theatricalplaysapi.metrics

import aris.thesis.theatricalplaysapi.services.proto.ModelService

interface MetricsService: ModelService {
    fun increaseCount(request: String?, status: Int)

    fun getGraphData(): Array<out Array<Any>>?
}