package com.nickdnepr.panzermarch.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.Random;

public class SquareActor extends Actor {
    private Sprite sprite;
    private Body body;

    public SquareActor(final Body body) {
        this.body = body;
        setBounds(body.getPosition().x, body.getPosition().y, 100, 100);
        sprite = new Sprite(new Texture("badlogic.jpg"));
        sprite.setBounds(getX(), getY(), getWidth(), getHeight());
        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        setOrigin(getWidth() / 2, getHeight() / 2);
        final Random random = new Random();
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
//                body.applyLinearImpulse(random.nextInt(500000), random.nextInt(500000), 0, 0, true);
//                body.applyLinearImpulse(0, 500000, getX()+50, getY()+50, true);
                body.setLinearVelocity(10000000,10000000);
                System.out.println("clicked");
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        setPosition(body.getPosition().x - 50, body.getPosition().y - 50);
        setRotation((float) ((360.0f * body.getAngle()) / (2 * Math.PI)));
        sprite.setPosition(getX(), getY());
        sprite.setRotation(getRotation());

    }

    public Body getBody() {
        return body;
    }
}
