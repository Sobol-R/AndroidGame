package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

import java.util.LinkedList;

public class MyGame extends ApplicationAdapter {
	OrthographicCamera camera;
	SpriteBatch batch;

	World world;

	Player player;
	Stage ui;

	LinkedList <GameObject> gameObjects;

	Actor leftButton;
	Actor rightButton;
	Actor topButton;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		ui = new Stage();
//		leftButton = new Button(new Texture("left.png"), 200, 300, 300, 300);
//		rightButton = new Button(new Texture("right.png"), 300, 300, 300, 300);
//		//topButton = new Button(new Texture("up.png"), 600, 300, 300, 300);
//		ui.addActor(leftButton);
//		ui.addActor(rightButton);
//		//ui.addActor(topButton);

		leftButton.addListener(new ActorGestureListener() {
			@Override
			public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
				player.move(Player.Direction.LEFT);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				player.stop();
			}
		});

		rightButton.addListener(new ActorGestureListener() {
			@Override
			public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
				player.move(Player.Direction.RIGHT);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				player.stop();
			}
		});
		Gdx.input.setInputProcessor(ui);

		world = new World(new Vector2(0, 9.8f), true);

		gameObjects = new LinkedList<GameObject>();

		gameObjects.push(new Platform(world, 100, 120, 160, 40));
		gameObjects.push(player = new Player(world, 100, 60, 10));

		camera.zoom = 0.2f;
		camera.update();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		world.step(1/30f * Gdx.graphics.getDeltaTime(), 6, 2);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		for (GameObject gameObject : gameObjects)
			gameObject.render(batch);

		batch.end();

		ui.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		camera.setToOrtho(true, width, height);
	}
}
