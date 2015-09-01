package tactics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class LichActor extends Image implements Character {

    protected Animation animation;
    static protected Animation animationStanding;
    static protected Animation animationFlying;
    static protected Animation animationAttack;
    static protected Texture lichSheet;
    static protected TextureRegion[] lichFrames;
    static protected TextureRegion currentFrame;
    private float stateTime = 0;

    public String name;

    public int x;
    public int y;

    public int speed;
    public int strength;
    public int health;

    static {
        initAnimations();
    }

    public LichActor (String name, int x, int y, int speed, int strength, int health) {
        this(animationStanding);
        this.name = name;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.strength = strength;
        this.health = health;
    }

    public LichActor (Animation animation) {
        super(animation.getKeyFrame(0));
        this.animation = animation;
        initAnimations();
    }

    static private void initAnimations() {
        int FRAME_COLS1 = 4;
        int FRAME_ROWS1 = 1;
        lichSheet = new Texture("Sprites/Lich/Standing.png");
        TextureRegion[][] tmp1 = TextureRegion.split(lichSheet, lichSheet.getWidth()/FRAME_COLS1, lichSheet.getHeight()/FRAME_ROWS1);              // #10
        lichFrames = new TextureRegion[FRAME_COLS1 * FRAME_ROWS1];
        int index1 = 0;
        for (int i = 0; i < FRAME_ROWS1; i++) {
            for (int j = 0; j < FRAME_COLS1; j++) {
                lichFrames[index1++] = tmp1[i][j];
            }
        }
        animationStanding = new Animation(0.025f, lichFrames);

        int FRAME_COLS2 = 3;
        int FRAME_ROWS2 = 1;
        lichSheet = new Texture("Sprites/Lich/Flying.png");
        TextureRegion[][] tmp2 = TextureRegion.split(lichSheet, lichSheet.getWidth()/FRAME_COLS2, lichSheet.getHeight()/FRAME_ROWS2);              // #10
        lichFrames = new TextureRegion[FRAME_COLS2 * FRAME_ROWS2];
        int index2 = 0;
        for (int i = 0; i < FRAME_ROWS2; i++) {
            for (int j = 0; j < FRAME_COLS2; j++) {
                lichFrames[index2++] = tmp2[i][j];
            }
        }
        animationFlying = new Animation(0.025f, lichFrames);

        int FRAME_COLS3 = 5;
        int FRAME_ROWS3 = 2;
        lichSheet = new Texture("Sprites/Lich/Attack.png");
        TextureRegion[][] tmp3 = TextureRegion.split(lichSheet, lichSheet.getWidth()/FRAME_COLS3, lichSheet.getHeight()/FRAME_ROWS3);              // #10
        lichFrames = new TextureRegion[FRAME_COLS3 * FRAME_ROWS3];
        int index3 = 0;
        for (int i = 0; i < FRAME_ROWS3; i++) {
            for (int j = 0; j < FRAME_COLS3; j++) {
                lichFrames[index3++] = tmp3[i][j];
            }
        }
        animationAttack = new Animation(0.025f, lichFrames);
    }

    public void stand () {
        this.animation = animationStanding;
    }

    public void fly () {
        this.animation = animationFlying;
    }
    
    public void attack () {
        this.animation = animationAttack;
    }

    @Override
    public void act (float delta) {
        ((TextureRegionDrawable)getDrawable()).setRegion(animation.getKeyFrame(0.2f*(stateTime+=delta), true));
        super.act(delta);
    }
    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getXField() {
        return x;
    }

    @Override
    public int getYField() {
        return y;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LichActor{" +
                "x=" + x +
                ", name='" + name + '\'' +
                ", y=" + y +
                ", speed=" + speed +
                ", strength=" + strength +
                ", health=" + health +
                '}';
    }
}
