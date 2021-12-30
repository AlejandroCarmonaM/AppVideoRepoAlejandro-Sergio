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
	
	public CuartetoVideos(Video ... videos) {
		this();
		if (videos.length <= 4) {
			for (int i = 0; i < videos.length; i++) {
				
				switch (i) {
				case 0:
					this.video1 = videos[0];
					break;
				case 1:
					this.video2 = videos[1];
					break;
				case 2:
					this.video3 = videos[2];
					break;
				case 3:
					this.video4 = videos[3];
					break;
				default:
					break;
				}
			}
		}
	}

	public Video getVideo1() {
		return video1;
	}

	public Video getVideo2() {
		return video2;
	}

	public Video getVideo3() {
		return video3;
	}
	
	public Video getVideo4() {
		return video4;
	}
}
