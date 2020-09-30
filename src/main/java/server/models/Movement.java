package server.models;

public enum Movement {
    DOWN("D", new int[]{-1, 0}),
    UP("U", new int[]{1, 0}),
    LEFT("L", new int[]{0, 1}),
    RIGHT("R", new int[]{0, -1});

    private final int[] movement;
    private final String typeOfMove;

    Movement(String u, int[] move) {
        this.typeOfMove = u;
        this.movement = move;
    }

    public int[] getMovement() {
        return movement;
    }

    public String getTypeOfMove() { return  typeOfMove; }

    public static Movement fromString(String text) {
        for (Movement movement : Movement.values()) {
            if (movement.typeOfMove.equalsIgnoreCase(text)) {
                return movement;
            }
        }
        return null;
    }

}
