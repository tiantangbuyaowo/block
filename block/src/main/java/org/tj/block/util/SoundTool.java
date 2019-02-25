package org.tj.block.util;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * @author 这是一个包含了所有声音的类
 */
public class SoundTool {

	// 背景音乐
	public static Clip backSound = null;
	public static AudioInputStream backSoundLoad = null;

	// 成功音乐
	public static Clip scoreSound = null;
	public static AudioInputStream scoreSoundLoad = null;

	static {

		try {// 背景音乐
			backSoundLoad = AudioSystem
					.getAudioInputStream(SoundTool.class.getClassLoader().getResource("sound/bg_music.wav"));
			backSound = AudioSystem.getClip();

			// 成功音乐
			scoreSoundLoad = AudioSystem
					.getAudioInputStream(SoundTool.class.getClassLoader().getResource("sound/pay_result_success.wav"));
			scoreSound = AudioSystem.getClip();

		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 播放背景音乐
	 */
	public static void back() {
		try {
			backSound.open(backSoundLoad);
			// getit
			// backSound.start();
			// backSound.stop();
			// backSound.setMicrosecondPosition(0);
			play(backSound);
			backSound.loop(Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 播放得分音乐
	 */
	public static void scoreSound() {
		try {
			if (!scoreSound.isOpen()) {
				scoreSound.open(scoreSoundLoad);
			}
			// getit
			// backSound.start();
			// backSound.stop();
			// backSound.setMicrosecondPosition(0);
			play(scoreSound);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 结束背景音乐
	 */
	public static void over(Clip clip) {
		clip.stop();
		clip.setMicrosecondPosition(0);
	}

	/**
	 * @param clip
	 *            音乐文件对应的clip 播放音乐
	 */
	public static void play(Clip clip) {
		clip.setMicrosecondPosition(0);
		clip.start();
	}

}
