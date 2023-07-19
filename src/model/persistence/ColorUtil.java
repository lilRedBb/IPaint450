package model.persistence;

/**
 * @author lilred
 * @date 2023/07/12
 **/
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class ColorUtil {
    private static final Map<String, Color> COLOR_MAP = createColorMap();

    private static Map<String, Color> createColorMap() {
        Map<String, Color> colorMap = new HashMap<>();
        colorMap.put("BLUE", Color.BLUE);
        colorMap.put("RED", Color.RED);
        colorMap.put("CYAN", Color.CYAN);
        colorMap.put("DARK_GRAY", Color.DARK_GRAY);
        colorMap.put("GRAY", Color.GRAY);
        colorMap.put("GREEN", Color.GREEN);
        colorMap.put("LIGHT_GRAY", Color.LIGHT_GRAY);
        colorMap.put("MAGENTA", Color.MAGENTA);
        colorMap.put("ORANGE", Color.ORANGE);
        colorMap.put("PINK", Color.PINK);
        colorMap.put("WHITE", Color.WHITE);
        colorMap.put("YELLOW", Color.YELLOW);

        return colorMap;
    }

    public static Color getColor(String colorName) {
        return COLOR_MAP.get(colorName.toUpperCase());
    }
}
