package launcher;

import java.awt.EventQueue;

import view.WindowBuilder;

public class Application {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					@SuppressWarnings("unused")
					WindowBuilder frame = new WindowBuilder();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
