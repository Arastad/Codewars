import java.util.HashMap;

public class Parser {

    public static int parseInt(String numStr) {
        int returnValue = 0;

        HashMap<String, Integer> baseValues = new HashMap<String, Integer>();
        baseValues.put("zero", 0);
        baseValues.put("one", 1);
        baseValues.put("two", 2);
        baseValues.put("three", 3);
        baseValues.put("thir", 3);
        baseValues.put("four", 4);
        baseValues.put("five", 5);
        baseValues.put("fif", 5);
        baseValues.put("six", 6);
        baseValues.put("seven", 7);
        baseValues.put("eight", 8);
        baseValues.put("eigh", 8);
        baseValues.put("nine", 9);
        baseValues.put("ten", 10);
        baseValues.put("eleven", 11);
        baseValues.put("twelve", 12);
        baseValues.put("twenty", 20);
        baseValues.put("thirty", 30);
        baseValues.put("forty", 40);
        baseValues.put("fifty", 50);
        baseValues.put("sixty", 60);
        baseValues.put("seventy", 70);
        baseValues.put("eighty", 80);
        baseValues.put("ninety", 90);

        HashMap<String, Integer> multipliers = new HashMap<String, Integer>();
        multipliers.put("teen", 10);
        multipliers.put("hundred", 100);
        multipliers.put("thousand", 1000);
        multipliers.put("million", 1000000);

        String currentValue = "";
        int accumulator = 0;

        numStr = numStr.toLowerCase();
        numStr = numStr.replace("-", " ");
        numStr = numStr.replace(" and ", " ");

        while (!numStr.isEmpty()) {
            if (numStr.contains(" ")) {
                currentValue = numStr.substring(0, numStr.indexOf(" "));
                numStr = numStr.substring((numStr.indexOf(" ") + 1));
            } else {
                currentValue = numStr;
                numStr = "";
            }
            if (baseValues.containsKey(currentValue)) {
                accumulator += baseValues.get(currentValue);
            } else if (multipliers.containsKey(currentValue)) {
                if (currentValue.equals("thousand") || currentValue.equals("million")) {
                    returnValue += (accumulator * multipliers.get(currentValue));
                    accumulator = 0;
                } else if (currentValue.equals("hundred")) {
                    accumulator *= multipliers.get(currentValue);
                }
            }  else if (currentValue.contains("teen")){
                accumulator += multipliers.get("teen") + baseValues.get(currentValue.substring(0, currentValue.indexOf("teen")));
            }
        }

        if (accumulator > 0) {
            returnValue += accumulator;
        }
        return returnValue;
    }
}
