package ch.fhnw.webec.trolleyproblem.dtos;

public enum Position {
    LEFT("left"),
    RIGHT("right");

    private final String text;

    Position(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
