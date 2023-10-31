package Extra.RunAway;

import java.util.ArrayList;

public class Game {
    public int width, height;
    public ArrayList<Entity> entities;

    Game(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<Entity>();
    }

    public void init() {
        for (int i = 0; i < 10; i ++) {
            Entity newEntity = new Entity(this, Math.random() * this.width, Math.random() * this.height, 10);
            newEntity.color = (int) (Math.random() * 10);
        }
    }

    public void update() {
        for (Entity entity : this.entities) {
            entity.update();
        }
    }
}
