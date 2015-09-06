package tactics;

public class Coord {
    private float x;
    private float y;
    private int noX;
    private int noY;

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    private boolean occupied;

    public int getNoX() {
        return noX;
    }

    public void setNoX(int noX) {
        this.noX = noX;
    }

    public int getNoY() {
        return noY;
    }

    public void setNoY(int noY) {
        this.noY = noY;
    }


    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }
    public Coord () {
        x = 0;
        y = 0;
    }
    public Coord (float x, float y, int noX, int noY) {
        this.x = x;
        this.y = y;
        this.noX = noX;
        this.noY = noY;
    }
    public Coord (float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                ", noX=" + noX +
                ", noY=" + noY +
                '}';
    }
}
