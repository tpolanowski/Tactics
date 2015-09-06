package tactics;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tactics extends ApplicationAdapter implements InputProcessor{
	TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	OrthographicCamera camera;
	Tiles tiles;

	Stage stage;
	ArrayList<KnightActor> knights;
	ArrayList<LichActor> liches;
	ArrayList<SquareActor> squares;
	Group squareGroup;

	ShapeRenderer shapeRenderer;
	Vector3 lastTouchDown = new Vector3();

	int turn = 0;

	//TODO create array with occupied fields

	@Override
	public void create () {
		tiles = new Tiles(810, 800, 20);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false,w/1.5f,h);

		tiledMap = new TmxMapLoader().load("test.tmx");
		tiledMapRenderer = new IsometricTiledMapRenderer(tiledMap);
		Gdx.input.setInputProcessor(this);

		shapeRenderer = new ShapeRenderer();

		stage = new Stage(new StretchViewport(1000,1000,camera)); //600,600
		//camera.translate(150,-180);
		camera.translate(-50,-430);
		camera.update();

		// Actors

		knights = new ArrayList<KnightActor>(5);
		liches = new ArrayList<LichActor>(5);
		squares = new ArrayList<SquareActor>(24);
		squareGroup = new Group();

		knights.add(new KnightActor("Knight1",17, 5,23,43,57));
		knights.add(new KnightActor("Knight2",16, 7,25,35,65));
		knights.add(new KnightActor("Knight3",17, 9,27,47,86));
		knights.add(new KnightActor("Knight4",16,11,24,39,64));
		knights.add(new KnightActor("Knight5",17,13,19,40,49));

		liches.add(new LichActor("Lich1",3, 5,31,45, 65));
		liches.add(new LichActor("Lich2",2, 7,32,48, 71));
		liches.add(new LichActor("Lich3",3, 9,34,54,107));
		liches.add(new LichActor("Lich4",2,11,33,50, 87));
		liches.add(new LichActor("Lich5",3,13,30,45, 59));

		for(int i = 0; i < 24; i++) {
			SquareActor squareActor = new SquareActor();
			squareActor.setX(i);
			squareActor.setY(i);
			//squareActor.setVisible(false); TODO
			squares.add(squareActor);
			squareGroup.addActor(squareActor);

		}

		initActors();

		//knightActor.addAction(moveAction);
		//knightActor.addAction(sequence(moveTo(200, 100, 2), color(com.badlogic.gdx.graphics.Color.RED, 6), delay(0.5f), rotateTo(90, 2)));

		turn();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0.1f, 0.05f, 1);
		//Gdx.gl.glClearColor(0.3f, 0.6f, 0.9f, 1);

		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_CONSTANT_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		stage.draw();
		stage.act(Gdx.graphics.getDeltaTime());

//		shapeRenderer.setProjectionMatrix(camera.combined);
//
//		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//		//shapeRenderer.rotate(1,0,0,90);
////		shapeRenderer.rect(float x, float y, float width, float height)
////		shapeRenderer.rect(float x, float y, float width, float height, Color col1, Color col2, Color col3, Color col4)
////		shapeRenderer.rect(float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float degrees)
////		shapeRenderer.rect(float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float degrees, Color col1, Color col2, Color col3, Color col4)
//		shapeRenderer.rect(400,400,40,40);
//
//		//shapeRenderer.rect(-250, -250, 500, 500, Color.RED, Color.BLUE, Color.CYAN, Color.RED);
//		shapeRenderer.end();
	}
	/* Initialize actors' positions */
	public void initActors() {
		for (KnightActor knightActor : knights) {
			knightActor.addAction(moveTo(tiles.getCoord(knightActor.x,knightActor.y).getX(),tiles.getCoord(knightActor.x,knightActor.y).getY()));
			knightActor.stand();
			tiles.getCoord(knightActor.x,knightActor.y).setOccupied(true);
			stage.addActor(knightActor);
		}
		for (LichActor lichActor : liches) {
			lichActor.addAction(moveTo(tiles.getCoord(lichActor.x, lichActor.y).getX(), tiles.getCoord(lichActor.x, lichActor.y).getY()));
			lichActor.stand();
			tiles.getCoord(lichActor.x,lichActor.y).setOccupied(true);
			stage.addActor(lichActor);
		}
	}

	public void turn() {
		ArrayList<Character> characters = new ArrayList<Character>(knights.size() + liches.size());
		for (KnightActor knightActor : knights) {
			characters.add(knightActor);
		}
		for (LichActor lichActor : liches) {
			characters.add(lichActor);
		}

		Collections.sort(characters, new Comparator<Character>() {
			@Override
			public int compare(Character character1, Character character2) {
				if (character1.getSpeed() > character2.getSpeed()) {
					return -1;
				} else if (character1.getSpeed() < character2.getSpeed()){
					return 1;
				} else {
					return 0;
				}
			}
		});

		// Each character gets one move during this turn
		int moves = 0;

		for (Character character : characters) {

			if(character instanceof LichActor) {
				// AI goes here
				LichActor lichActor = (LichActor) character;
				lichActor.fly();
				//TODO add actual AI
				tiles.getCoord(lichActor.x,lichActor.y).setOccupied(false);
				int destX = lichActor.x + 4;
				int destY = lichActor.y;
				tiles.getCoord(destX,destY).setOccupied(true);
				lichActor.addAction(sequence(
						delay(moves*2.5f)
						,moveTo(tiles.getCoord(destX, destY).getX(), tiles.getCoord(destX, destY).getY(), 2)
				));
				//lichActor.x = lichActor.x + 4; //TODO check if actions automatically change actors coords!
				System.out.println(lichActor.x);


			} else {
//				for (LichActor lichActor : liches) {
//					lichActor.stand();
//				}
				// player control
				System.out.println("Player turn! " + character.toString());
				KnightActor knightActor = (KnightActor) character;
				if (moves == 9) {
					float x = tiles.getCoord(knightActor.x,knightActor.y).getX();
					float y = tiles.getCoord(knightActor.x,knightActor.y).getY();
					//shapeRenderer.setProjectionMatrix(camera.combined);
    				stage.addActor(squareGroup);



				}
			}

			System.out.println(character.toString());
			moves++;
		}
		turn++;
	}

	/**
	 * Called when a key was pressed
	 *
	 * @param keycode one of the constants in {@link com.badlogic.gdx.Input.Keys}
	 * @return whether the input was processed
	 */
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	/**
	 * Called when a key was released
	 *
	 * @param keycode one of the constants in {@link com.badlogic.gdx.Input.Keys}
	 * @return whether the input was processed
	 */
	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Input.Keys.LEFT)
			camera.translate(-64,0);
		if(keycode == Input.Keys.RIGHT)
			camera.translate(64,0);
		if(keycode == Input.Keys.UP)
			camera.translate(0,64);
		if(keycode == Input.Keys.DOWN)
			camera.translate(0,-64);
		if(keycode == Input.Keys.A)
			camera.translate(-64,0);
		if(keycode == Input.Keys.D)
			camera.translate(64,0);
		if(keycode == Input.Keys.W)
			camera.translate(0,64);
		if(keycode == Input.Keys.S)
			camera.translate(0,-64);
		if(keycode == Input.Keys.NUM_1)
			tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
		if(keycode == Input.Keys.NUM_2)
			tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
		return false;
	}

	/**
	 * Called when a key was typed
	 *
	 * @param character The character
	 * @return whether the input was processed
	 */
	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	/**
	 * Called when the screen was touched or a mouse button was pressed. The button parameter will be  on iOS.
	 *
	 * @param screenX The x coordinate, origin is in the upper left corner
	 * @param screenY The y coordinate, origin is in the upper left corner
	 * @param pointer the pointer for the event.
	 * @param button  the button
	 * @return whether the input was processed
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	/**
	 * Called when a finger was lifted or a mouse button was released. The button parameter will be  on iOS.
	 *
	 * @param screenX
	 * @param screenY
	 * @param pointer the pointer for the event.
	 * @param button  the button   @return whether the input was processed
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	/**
	 * Called when a finger or the mouse was dragged.
	 *
	 * @param screenX
	 * @param screenY
	 * @param pointer the pointer for the event.  @return whether the input was processed
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		moveCamera(screenX-1280/2, screenY-720/2);
		return false;
	}

	/**
	 * Called when the mouse was moved without any buttons being pressed. Will not be called on iOS.
	 *
	 * @param screenX
	 * @param screenY
	 * @return whether the input was processed
	 */
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	/**
	 * Called when the mouse wheel was scrolled. Will not be called on iOS.
	 *
	 * @param amount the scroll amount, -1 or 1 depending on the direction the wheel was scrolled.
	 * @return whether the input was processed.
	 */
	@Override
	public boolean scrolled(int amount) {
		stage.getCamera().viewportWidth = stage.getCamera().viewportWidth + 20*amount;
		stage.getCamera().viewportHeight = stage.getCamera().viewportHeight + 20*amount;
		return false;
	}

	private void moveCamera(int touchX, int touchY) {
		Vector3 newPosition = getNewCameraPosition(touchX, touchY);
		//if( !cameraOutOfLimit( newPosition ) ) //TODO debug
		stage.getCamera().translate( newPosition.sub( stage.getCamera().position ) );

		lastTouchDown.set( touchX, touchY, 0);
	}

	private Vector3 getNewCameraPosition(int x, int y) {
		Vector3 newPosition = lastTouchDown;
		newPosition.sub(x,y,0);
		newPosition.y = -newPosition.y;
		newPosition.add(stage.getCamera().position);

		return newPosition;
	}
	private boolean cameraOutOfLimit( Vector3 position ) {
		int x_left_limit = 1280 / 2;
		int x_right_limit = 1000 - 1280 / 2;
		int y_bottom_limit = 720 / 2;
		int y_top_limit = 1000 - 720 / 2;

		if( position.x < x_left_limit || position.x > x_right_limit )
			return true;
		else if( position.y < y_bottom_limit || position.y > y_top_limit )
			return true;
		else
			return false;
	}
}
