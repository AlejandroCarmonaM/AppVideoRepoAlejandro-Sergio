package dominio;

import javax.swing.JLabel;

public class GrupoVideos {
	private JLabel video1;
	private JLabel video2;
	private JLabel video3;
	private JLabel video4;
	
	public GrupoVideos() {
		this.video1 = null;
		this.video2 = null;
		this.video3 = null;
		this.video4 = null;
	}
	
	public GrupoVideos(JLabel video1) {
		this.video1 = video1;
		this.video2 = null;
		this.video3 = null;
		this.video4 = null;
	}
	
	public GrupoVideos(JLabel video1, JLabel video2) {
		this.video1 = video1;
		this.video2 = video2;
		this.video3 = null;
		this.video4 = null;
	}
	
	public GrupoVideos(JLabel video1, JLabel video2, JLabel video3) {
		this.video1 = video1;
		this.video2 = video2;
		this.video3 = video3;
		this.video4 = null;
	}
	
	public GrupoVideos(JLabel video1, JLabel video2, JLabel video3, JLabel video4) {
		this.video1 = video1;
		this.video2 = video2;
		this.video3 = video3;
		this.video4 = video4;
	}

	public JLabel getVideo1() {
		return video1;
	}

	public void setVideo1(JLabel video1) {
		this.video1 = video1;
	}

	public JLabel getVideo2() {
		return video2;
	}

	public void setVideo2(JLabel video2) {
		this.video2 = video2;
	}

	public JLabel getVideo3() {
		return video3;
	}

	public void setVideo3(JLabel video3) {
		this.video3 = video3;
	}

	public JLabel getVideo4() {
		return video4;
	}

	public void setVideo4(JLabel video4) {
		this.video4 = video4;
	}
}
