package road.movemententities.entities.enumerations;

public enum EdgeFunction {

    normal,
    internal,
    connector;

    public static EdgeFunction fromString(String s) {
        if ("normal".equals(s)) {
            return EdgeFunction.normal;
        } else if ("internal".equals(s)) {
            return EdgeFunction.internal;
        } else if ("connector".equals(s)) {
            return EdgeFunction.connector;
        } else {
            return null;
        }
    }
}
