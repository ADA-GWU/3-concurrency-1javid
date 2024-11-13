package src.main.concurrency;

import java.util.Arrays;
import java.util.List;

public class Utils {
    public static int colorAverage(List<List<Integer>> rgbList) {
        Integer r = 0, g = 0, b = 0;

        for (List<Integer> rgb : rgbList) {
            r += rgb.get(0);
            g += rgb.get(1);
            b += rgb.get(2);
        }

        int size = rgbList.size();

        r /= size;
        g /= size;
        b /= size;
    
        return (r << 16) | (g << 8) | b;
    }
}
