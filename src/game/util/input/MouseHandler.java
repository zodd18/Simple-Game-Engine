package game.util.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import game.GamePanel;

public class MouseHandler implements MouseListener, MouseMotionListener {

	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;

	public int clicked = -1;

	public MouseHandler(GamePanel game) {
		game.addMouseListener(this);
		game.addMouseMotionListener(this);
	}

	public int getX() {
		return mouseX;
	}

	public int getY() {
		return mouseY;
	}

	public int getButton() {
		return mouseB;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		if (clicked == 0) {
//			clicked = 1;
//		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();
		if (clicked == -1) {
			clicked = 0;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (clicked == 1) {
			clicked = -1;
		} else if (clicked == 0) {
			clicked = 1;
		}
		mouseB = -1;
	}

	public boolean mouseAtLeftSide() {
		return mouseX <= (GamePanel.width / 2);
	}

//	3 estados: no-click --> click --> suelta-click 
//               -1           0            1

}
