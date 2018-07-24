package base;

import action.Action;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameObject {

    public Vector2D position;
    public Vector2D velocity;
    public int width;
    public int height;
    public boolean isAlive = true;
    public List<GameObjectAttributes> attributes;
    private List<Action> actions;

    public GameObject() {
        this.position = new Vector2D();
        this.attributes= new ArrayList<>();
        this.actions = new ArrayList<>();
    }

    public void run(){
        this.attributes.forEach(attribute -> attribute.run(this));
    }

    public void render(Graphics graphics) {

    }

    public void addAction(Action action) {
        this.actions.add(action);
    }
}
