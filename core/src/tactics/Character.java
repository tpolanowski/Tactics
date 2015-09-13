package tactics;

public interface Character {
    public int getXField();
    public int getYField();
    public String getName();
    public int getSpeed();
    public int getStrength();
    public int getHealth();
    public void setHealth(int health);
    public String toString();
    public boolean moved = false;
    public void checkIfAlive();
}
