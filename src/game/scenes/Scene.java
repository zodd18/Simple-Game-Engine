package game.scenes;

import java.awt.Graphics2D;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Objects;

import game.entities.Entity;
import game.graphics.UI;
import game.util.input.KeyHandler;
import game.util.input.MouseHandler;

public abstract class Scene {

	protected SceneManager gsm;
	protected String name;

	public LinkedList<Entity> entities;
	public LinkedList<UI> uis;

	public Scene(String name, SceneManager gsm) {
		this.name = name;
		this.gsm = gsm;
		this.entities = new LinkedList<>();
		this.uis = new LinkedList<>();
	}

	public abstract void update();

	public abstract void input(MouseHandler mouse, KeyHandler key);

	public abstract void render(Graphics2D g);

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Scene scene = (Scene) o;
		return Objects.equals(name, scene.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
