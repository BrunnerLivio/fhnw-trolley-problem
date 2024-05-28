package ch.fhnw.webec.trolleyproblem.entities;

public enum Directional {
    LEFT("left"),
    RIGHT("right");

    private final String text;

    Directional(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
