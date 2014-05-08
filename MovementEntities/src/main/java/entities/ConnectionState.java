package entities;

public enum ConnectionState {

    DEAD_END("-"),
    EQUAL("="),
    MINOR_LINK("m"),
    MAJOR_LINK("M"),
    CONTROLLER_OFF("O"),
    YELLOW_FLASHING("o"),
    YELLOW_MINOR_LINK("y"),
    YELLOW_MAJOR_LINK("Y"),
    RED("r"),
    GREEN_MINOR("g"),
    GREEN_MAJOR("G");

    private final String text;

    ConnectionState(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }
    
    /**
     * Generate ConnectionState from its string mapping
     * @param s The string mapping value
     * @return ConnectionState
     */
    public static ConnectionState fromString(String s) {
        if (s != null) {
            for (ConnectionState cs : ConnectionState.values()) {
                if (s.equals(cs.text)) {
                    return cs;
                }
            }
        }
        return ConnectionState.DEAD_END;
    }
}
