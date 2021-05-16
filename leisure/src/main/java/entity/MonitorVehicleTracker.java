package entity;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import entity.MutablePoint;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class MonitorVehicleTracker {

    @GuardedBy("this")
    private final Map<String, MutablePoint> locations;


    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    private static Map<String, MutablePoint> deepCopy(
            Map<String, MutablePoint> m) {
        Map<String, MutablePoint> result = new HashMap<String, MutablePoint>();
        for( String id : m.keySet()) {
            result.put(id, new MutablePoint(m.get(id).getX(),m.get(id).getY()));
        }
        return Collections.unmodifiableMap(result);

    }
}
