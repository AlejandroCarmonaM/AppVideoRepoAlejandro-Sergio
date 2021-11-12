package dominio;

import javax.swing.JLabel;

public class CuartetoVideos {
	private Video video1;
	private Video video2;
	private Video video3;
	private Video video4;
	
	public CuartetoVideos() {
		this.video1 = null;
		this.video2 = null;
		this.video3 = null;
		this.video4 = null;
	}
	
	public CuartetoVideos(Video video1) {
		this.video1 = video1;
		this.video2 = null;
		this.video3 = null;
		this.video4 = null;
	}
	
	public CuartetoVideos(Video video1, Video video2) {
		this.video1 = video1;
		this.video2 = video2;
		this.video3 = null;
		this.video4 = null;
	}
	
	public CuartetoVideos(Video video1, Video video2, Video video3) {
		this.video1 = video1;
		this.video2 = video2;
		this.video3 = video3;
		this.video4 = null;
	}
	
	public CuartetoVideos(Video video1, Video video2, Video video3, Video video4) {
		this.video1 = video1;
		this.video2 = video2;
		this.video3 = video3;
		this.video4 = video4;
	}

	public Video getVideo1() {
		return video1;
	}

	public void setVideo1(Video video1) {
		this.video1 = video1;
	}

	public Video getVideo2() {
		return video2;
	}

	public void setVideo2(Video video2) {
		this.video2 = video2;
	}

	public Video getVideo3() {
		return video3;
	}

	public void setVideo3(Video video3) {
		this.video3 = video3;
	}

	public Video getVideo4() {
		return video4;
	}

	public void setVideo4(Video video4) {
		this.video4 = video4;
	}
}
