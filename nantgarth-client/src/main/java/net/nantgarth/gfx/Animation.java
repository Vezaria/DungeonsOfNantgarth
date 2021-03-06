package net.nantgarth.gfx;

public class Animation {

	private int fTime = 100;
	private boolean playing = true;
	
	private long lastTime = System.currentTimeMillis();
	
	private int currentFrame = 0;
	
	private String[] frames;
	
	public Animation(String[] frames) {
		this.frames = frames;
	}

	public void update() {
		if(playing) {
			if(System.currentTimeMillis() - lastTime >= fTime) {
				lastTime = System.currentTimeMillis();
				if(currentFrame + 1 > frames.length - 1) {
					currentFrame = 0;
				} else {
					currentFrame++;
				}
			}
		}
	}
	
	public String currentFrame() {
		return frames[currentFrame];
	}
	
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
}
