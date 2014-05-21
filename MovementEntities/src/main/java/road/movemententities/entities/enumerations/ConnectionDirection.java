package road.movemententities.entities.enumerations;

public enum ConnectionDirection {

    STRAIGHT("s"),
    TURN("t"),
    LEFT("l"),
    RIGHT("r"),
    PARTIALLY_LEFT("L"),
    PARTIALLY_RIGHT("R"),
    INVALID("XX");

    private final String text;

    ConnectionDirection(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    /**
     * Generate ConnectionDirection from its string mapping
     * @param s The string mapping value
     * @return ConnectionDirection
     */
    public static ConnectionDirection fromString(String s) {
        if (s != null) {
            for (ConnectionDirection cd : ConnectionDirection.values()) {
                if (s.equals(cd.text)) {
                    return cd;
                }
            }
        }
        return ConnectionDirection.INVALID;
    }
}
