import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TimeFormatter {

    public static String formatDuration(int seconds) {
        LinkedHashMap<String, Integer> durationConstruct = new LinkedHashMap<String, Integer>() {
            private static final long serialVersionUID = 6952500897013968144L;

            {
                put("year", 31536000);
                put("day", 86400);
                put("hour", 3600);
                put("minute", 60);
                put("second", 1);
            }
        };

        Iterator<Map.Entry<String, Integer>> durationIterator = durationConstruct.entrySet().iterator();

        String returnValue;

        if (seconds == 0) {
            returnValue = "now";
        } else {
            returnValue = extractFormattedString(seconds, durationIterator);
        }
        return returnValue;
    }

    private static String extractFormattedString(int seconds, Iterator<Map.Entry<String, Integer>> durationIterator) {
        String returnValue = "";

        while (durationIterator.hasNext()) {
            Map.Entry<String, Integer> unitConversionPair = durationIterator.next();
            int numberOfUnits = seconds / unitConversionPair.getValue();
            String formattedUnit = extractFormattedString(seconds - (numberOfUnits * unitConversionPair.getValue()), durationIterator);

            if (numberOfUnits < 1) {
                returnValue = formattedUnit;
            } else {
                returnValue = numberOfUnits + " " + unitConversionPair.getKey();
                if (numberOfUnits > 1) {
                    returnValue += "s";
                }
                returnValue = endOfLine(returnValue, formattedUnit);
            }
        }
        return returnValue;
    }

    private static String endOfLine(String returnValue, String unitValueToFormat) {
        if (unitValueToFormat.equals("")) {
            returnValue += "";
        } else {
            if (unitValueToFormat.contains(" and ")) {
                returnValue += ", " + unitValueToFormat;
            } else {
                returnValue += " and " + unitValueToFormat;
            }
        }
        return returnValue;
    }
}
