package org.tj.block.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.tj.block.model.Block;
import org.tj.block.model.bus.CreateNewMoveBlockBusModel;

import com.google.common.eventbus.Subscribe;

/**
 * 提示当前是什么形状的面板
 * 
 * @author 唐靖
 *
 * @date 2018年6月16日下午1:23:25
 *
 */
@SuppressWarnings("serial")
public class AlertBlackPanel extends JPanel {
	public static int BLOCK_W_COUNT = 4;

	public static int BLOCK_H_COUNT = 4;

	/**
	 * 格子宽度
	 */
	protected static int BLOCK_WIDTH = 20;

	/**
	 * 格子高度
	 */
	protected static int BLOCK_HEIGHT = 20;

	/**
	 * 左侧距离
	 */
	protected int leftDistance;

	/**
	 * 顶部距离
	 */
	protected int topDistance;

	private List<Block> currentBlocks = new ArrayList<Block>();

	public AlertBlackPanel() {
		setVisible(true);
		setLayout(null);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		BufferedImage bi = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB); // 双缓冲技术防止闪屏
		Graphics jg = bi.createGraphics();
		try {
			drawBlocks(jg);
			g.drawImage(bi, 0, 0, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void drawBlocks(Graphics jg) {
		// 设置线条颜色为红色
		// 绘制外层矩形框
		for (Block b : currentBlocks) {
			jg.setColor(b.color);
			jg.fillRoundRect(b.bgArrayX * BLOCK_WIDTH + leftDistance, b.bgArrayY * BLOCK_HEIGHT + topDistance,
					BLOCK_WIDTH, BLOCK_WIDTH, BLOCK_WIDTH, BLOCK_WIDTH);
			// jg.fillRoundRect(b.bgArrayX * BLOCK_WIDTH, b.bgArrayY *
			// BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_WIDTH, BLOCK_WIDTH,
			// BLOCK_WIDTH);
		}
	}

	@Subscribe
	private void updateCurrentBlocks(CreateNewMoveBlockBusModel createNewMoveBlockBusModel) {

		int xRightMove = 0;
		int yDownMove = 0;

		// 分别需要最大最小xy，用来计算左右和上下的距离，要把这个图案显示在面板正中间
		int minX = Integer.MAX_VALUE;
		int maxX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxY = Integer.MAX_VALUE;

		// 1，获取x需要右移的数目，以及y需要下移的数目
		this.currentBlocks.clear();
		for (Block b : createNewMoveBlockBusModel.blocks) {
			// 这个地方传过来的是方块面板已经调整过x位置的，需要先还原一波
			b.bgArrayX = b.bgArrayX - createNewMoveBlockBusModel.startX;
			// System.out.println(b.bgArrayX + "--------" + b.bgArrayY);
			if (b.bgArrayX < 0) {
				xRightMove = Math.min(b.bgArrayX, xRightMove);
			}
			if (b.bgArrayY < 0) {
				yDownMove = Math.min(b.bgArrayY, yDownMove);
			}
			if (minX == Integer.MAX_VALUE) { // 初始化赋值
				minX = b.bgArrayX;
			}
			if (maxX == Integer.MAX_VALUE) { // 初始化赋值
				maxX = b.bgArrayX;
			}
			if (minY == Integer.MAX_VALUE) { // 初始化赋值
				minY = b.bgArrayY;
			}
			if (maxY == Integer.MAX_VALUE) { // 初始化赋值
				maxY = b.bgArrayY;
			}
			minX = Math.min(minX, b.bgArrayX);
			maxX = Math.max(maxX, b.bgArrayX);

			minY = Math.min(minY, b.bgArrayY);
			maxY = Math.max(maxY, b.bgArrayY);

			this.currentBlocks.add((Block) b.clone());
		}
		if (xRightMove < 0) { // 说明需要调整
			for (int i = 0; i < this.currentBlocks.size(); i++) {
				this.currentBlocks.get(i).bgArrayX = this.currentBlocks.get(i).bgArrayX - xRightMove;
			}
		}

		if (yDownMove < 0) { // 说明需要调整
			for (int i = 0; i < this.currentBlocks.size(); i++) {
				this.currentBlocks.get(i).bgArrayY = this.currentBlocks.get(i).bgArrayY - yDownMove;
			}
		}
		// 先还原为0
		this.leftDistance = 0;
		this.topDistance = 0;
		// 计算左右间距
		//System.out.println("maxX=" + maxX + "---" + "minX=" + minX);
		this.leftDistance = (this.getWidth() - (maxX - minX + 1) * BLOCK_WIDTH) / 2;
		this.topDistance = (this.getHeight() - (maxY - minY + 1) * BLOCK_HEIGHT) / 2;
		// System.out.println("left=" + leftDistance + "------" + "top" +
		// topDistance);
		repaint();

	}

}
