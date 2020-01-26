package game.scenes;

import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedHashSet;

import game.util.input.KeyHandler;
import game.util.input.MouseHandler;

public class SceneManager {

	private LinkedHashSet<Scene> scenes;

	public static Scene currentScene;

	public SceneManager() {
		scenes = new LinkedHashSet<>();

		scenes.add(new Scene0(this));
		currentScene = getScene("SCENE_0");
	}

	private Scene getScene(String sceneName) {
		boolean found = false;
		Iterator<Scene> it = scenes.iterator();
		Scene scene = null;
		while (it.hasNext() && !found) {
			scene = it.next();
			if(scene.name.equals(sceneName)) found = true;
		}

		if (!found) throw new SceneException("Scene: " + sceneName + " could not be found");
		else return scene;
	}

	public void update() { currentScene.update(); }

	public void input(MouseHandler mouse, KeyHandler key) {
		currentScene.input(mouse, key);
	}

	public void render(Graphics2D g) {
		currentScene.render(g);
	}
}
