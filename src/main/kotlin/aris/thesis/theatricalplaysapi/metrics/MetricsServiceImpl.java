package aris.thesis.theatricalplaysapi.metrics;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service(value = "metricsService")
public class MetricsServiceImpl implements MetricsService {

    private final ConcurrentMap<String, ConcurrentHashMap<Integer, Integer>> metricMap = new ConcurrentHashMap<>();

    public void increaseCount(String request, int status) {
        ConcurrentHashMap<Integer, Integer> statusMap = metricMap.get(request);
        if (statusMap == null) {
            statusMap = new ConcurrentHashMap<>();
        }

        Integer count = statusMap.get(status);
        if (count == null) {
            count = 1;
        } else {
            count++;
        }
        statusMap.put(status, count);
        metricMap.put(request, statusMap);
    }

    public Object[][] getGraphData() {
        int colCount = metricMap.keySet().size() + 1;
        Set<String> allRequests = metricMap.keySet();
        allRequests.remove("GET /status-metric");
        allRequests.remove("OPTIONS /status-metric");
        Object[][] result = new Object[colCount][2];

        int j = 0;
        ConcurrentMap<Integer, Integer> tempMapNew;
        for (String status : allRequests) {
            tempMapNew = metricMap.get(status);

            result[j][0] = status;
            result[j][1] = tempMapNew.values().stream().mapToInt(it -> it).sum();
            j++;
        }

        return result;
    }

}
