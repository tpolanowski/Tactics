package tactics;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class KnightActor extends Image implements Character{

    protected Animation animation;
    static protected Animation animationStanding;
    static protected Animation animationWalking;
    static protected Animation animationReady;
    static protected Animation animationAttack;
    static protected Texture knightSheet;
    static protected TextureRegion[] knightFrames;
    static protected TextureRegion currentFrame;
    private float stateTime = 0;

    public String name;

    public int x;
    public int y;

    public int speed;
    public int strength;
    public int health;

    public boolean alive = true;

    static {
        initAnimations();
    }

    public KnightActor (String name, int x, int y, int speed, int strength, int health) {
        this(animationStanding);
        this.name = name;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.strength = strength;
        this.health = health;
    }

    public KnightActor (Animation animation) {
        super(animation.getKeyFrame(0));
        this.animation = animation;
        initAnimations();
    }

    static private void initAnimations() {
        int FRAME_COLS1 = 6;
        int FRAME_ROWS1 = 1;
        knightSheet = new Texture("Sprites/Knight/Standing.png");
        TextureRegion[][] tmp1 = TextureRegion.split(knightSheet, knightSheet.getWidth()/FRAME_COLS1, knightSheet.getHeight()/FRAME_ROWS1);              // #10
        knightFrames = new TextureRegion[FRAME_COLS1 * FRAME_ROWS1];
        int index1 = 0;
        for (int i = 0; i < FRAME_ROWS1; i++) {
            for (int j = 0; j < FRAME_COLS1; j++) {
                knightFrames[index1++] = tmp1[i][j];
            }
        }
        animationStanding = new Animation(0.025f, knightFrames);

        int FRAME_COLS2 = 6;
        int FRAME_ROWS2 = 1;
        knightSheet = new Texture("Sprites/Knight/Walking.png");
        TextureRegion[][] tmp2 = TextureRegion.split(knightSheet, knightSheet.getWidth()/FRAME_COLS2, knightSheet.getHeight()/FRAME_ROWS2);              // #10
        knightFrames = new TextureRegion[FRAME_COLS2 * FRAME_ROWS2];
        int index2 = 0;
        for (int i = 0; i < FRAME_ROWS2; i++) {
            for (int j = 0; j < FRAME_COLS2; j++) {
                knightFrames[index2++] = tmp2[i][j];
            }
        }
        animationWalking = new Animation(0.025f, knightFrames);

        int FRAME_COLS3 = 3;
        int FRAME_ROWS3 = 2;
        knightSheet = new Texture("Sprites/Knight/Ready.png");
        TextureRegion[][] tmp3 = TextureRegion.split(knightSheet, knightSheet.getWidth()/FRAME_COLS3, knightSheet.getHeight()/FRAME_ROWS3);              // #10
        knightFrames = new TextureRegion[FRAME_COLS3 * FRAME_ROWS3];
        int index3 = 0;
        for (int i = 0; i < FRAME_ROWS3; i++) {
            for (int j = 0; j < FRAME_COLS3; j++) {
                knightFrames[index3++] = tmp3[i][j];
            }
        }
        animationReady = new Animation(0.025f, knightFrames);

        int FRAME_COLS4 = 6;
        int FRAME_ROWS4 = 1;
        knightSheet = new Texture("Sprites/Knight/Attack.png");
        TextureRegion[][] tmp = TextureRegion.split(knightSheet, knightSheet.getWidth()/FRAME_COLS4, knightSheet.getHeight()/FRAME_ROWS4);              // #10
        knightFrames = new TextureRegion[FRAME_COLS4 * FRAME_ROWS4];
        int index4 = 0;
        for (int i = 0; i < FRAME_ROWS4; i++) {
            for (int j = 0; j < FRAME_COLS4; j++) {
                knightFrames[index4++] = tmp[i][j];
            }
        }
        animationAttack = new Animation(0.025f, knightFrames);


    }

    public void stand () {
        this.animation = animationStanding;
    }

    public void walk () {
        this.animation = animationWalking;
    }

    public void ready () {
        this.animation = animationReady;
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
        return "KnightActor{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", speed=" + speed +
                ", health=" + health +
                ", strength=" + strength +
                '}';
    }

    @Override
    public void checkIfAlive() {
        if (health < 1) {
            alive = false;
        }
    }
}
