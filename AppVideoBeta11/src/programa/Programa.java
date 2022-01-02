package programa;

import pantallas.FrameBase;

import java.awt.EventQueue;

import dominio.AppVideo;



public class Programa {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppVideo appvideo = AppVideo.INSTANCE;
					FrameBase frame = new FrameBase(appvideo);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
