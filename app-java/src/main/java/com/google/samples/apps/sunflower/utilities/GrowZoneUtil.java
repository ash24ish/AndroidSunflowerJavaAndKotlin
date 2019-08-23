

package com.google.samples.apps.sunflower.utilities;

public class GrowZoneUtil {
    public static int getZoneForLatitude(Double latitude) {
        int minuend =  (int) (latitude / 7) - (latitude > 0 && (latitude % 7) == 0 ? 1 : 0);
        return 13 - minuend;
    }
}
