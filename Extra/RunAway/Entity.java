package Extra.RunAway;

public class Entity {
    public Game game;
    public double x, y, width, height;
    public int color;
    public Vector2D velocity;

    Entity(Game game, double x, double y, double size) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = size;
        this.height = size;
        this.color = 0;

        this.velocity = new Vector2D(0, 0);

        this.game.entities.add(this);
    }

    Entity(Game game, double x, double y, double width, double height, int color) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;

        this.velocity = new Vector2D(0, 0);

        this.game.entities.add(this);
    }

    public void update() {
        this.x += this.velocity.x;
        this.y += this.velocity.y;

        this.velocity.mult(.8);
    }
}
