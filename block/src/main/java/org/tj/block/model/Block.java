package org.tj.block.model;

import java.awt.Color;

/**
 * 方块对象
 * 
 * @author 唐靖
 *
 * @date 2018年6月16日下午3:25:06
 *
 */
public class Block implements Cloneable {

	public static final Color DEFAULT_COLOR = Color.WHITE;

	/**
	 * 每个方块有个颜色
	 */
	public Color color = DEFAULT_COLOR;
	/**
	 * 在背景上x所处的像素位置
	 */
	public int x;
	/**
	 * 在背景上所处的y所处的像素位置
	 */
	public int y;
	/**
	 * 再地图二维数组所在的x位置
	 */
	public int bgArrayX;
	/**
	 * 再地图二维数组所在的y位置
	 */
	public int bgArrayY;

	@Override
	public Object clone() {
		Block blo = null;
		try {
			blo = (Block) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return blo;
	}

	public boolean samePosition(Block block) {

		return this.bgArrayX == block.bgArrayX && this.bgArrayY == block.bgArrayY;
	}

}
