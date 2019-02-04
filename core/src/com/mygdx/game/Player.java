package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends GameObject {

    private static float PLAYER_IMPULSE = 10;

    World world;
    float x, y, radius;
    private static Texture texture;
    private Sprite sprite;
    private Body body;

    Direction direction = Direction.NONE;

    public Player(World world, float x, float y, float radius) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.radius = radius;
        texture = new Texture("red-ball-md.png");
        sprite = new Sprite(texture);
        sprite.flip(false, true);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        body = world.createBody(bodyDef);

        Shape shape = new CircleShape();
        shape.setRadius(radius);
        body.createFixture(shape, .2f);
    }

    public void move(Direction direction) {
        this.direction = direction;
    }

    public void stop() {
        move(Direction.NONE);
    }

    public void jump() {

    }

    private void update() {
        if (direction == Direction.LEFT) {
            body.applyLinearImpulse(new Vector2(-PLAYER_IMPULSE, 0), body.getWorldCenter(), true);
        } else if (direction == Direction.RIGHT) {
            body.applyLinearImpulse(new Vector2(PLAYER_IMPULSE, 0), body.getWorldCenter(), true);
        }
    }

    public void render(Batch batch) {
        update();
        sprite.setPosition(body.getPosition().x - radius, body.getPosition().y - radius);
        sprite.setSize(radius*2, radius*2);
        sprite.setOriginCenter();
        sprite.setRotation((float) Math.toDegrees(body.getAngle()));
        sprite.draw(batch);
    }

    enum Direction {
        LEFT, RIGHT, NONE
    }
}
