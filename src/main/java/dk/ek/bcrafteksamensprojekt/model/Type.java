package dk.ek.bcrafteksamensprojekt.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Type {
    KITCHEN ("Køkken"),
    BATH ("Bad"),
    FLOOR ("Gulve"),
    TERRACE ("Terrasse"),
    RENOVATION ("Renovering"),
    WOODCRAFT("Snedker");

    private final String prettyprint;

    Type(String prettyprint) {
        this.prettyprint = prettyprint;
    }

    @Override
    @JsonValue
    public String toString() {
        return prettyprint;
    }

    public static Type fromString(String value) {
        for (Type t : Type.values()) {
            if (t.prettyprint.equalsIgnoreCase(value) || t.name().equalsIgnoreCase(value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Unknown type: " + value);
    }

}
