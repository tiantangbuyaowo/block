package org.tj.block.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * 许多block的集合，他们有相同的部分属性
 * 
 * @author 唐靖
 *
 * @date 2018年6月16日下午5:43:20
 *
 */
public abstract class BlockGroup {

	/**
	 * 固定几种颜色池
	 */
	protected static Color[] ColorPool;

	/**
	 * 形态池
	 */
	protected List<List<Block>> shapePool = new ArrayList<List<Block>>();
	/**
	 * 当前状态的序号
	 */
	protected int currentShapeIndex = -1;

	static {

		// 颜色池的初始化
		ColorPool = new Color[5];
		ColorPool[0] = Color.BLUE;
		ColorPool[1] = Color.green;
		ColorPool[2] = Color.ORANGE;
		ColorPool[3] = Color.PINK;
		ColorPool[4] = Color.RED;

	}

	/**
	 * 组当中包含的方块
	 */
	protected List<Block> blockList = new ArrayList<Block>();

	/**
	 * 这个组合当中的方块颜色
	 */
	protected Color groupBlockColor = ColorPool[(int) (Math.random() * 5)];

	/**
	 * 形状的最开始的x位置
	 */
	public int startX;

	public BlockGroup(int startX) {
		super();
		this.startX = startX;
		this.shapePool = initShapePool();
	}

	/**
	 * 初始化形态池
	 * 
	 * @author 唐靖
	 *
	 * @date 2018年6月17日下午12:08:57
	 *
	 * @return
	 */
	protected abstract List<List<Block>> initShapePool();

	public Color getGroupBlockColor() {
		return groupBlockColor;
	}

	public void replaceBlockList(List<Block> newBlocks) {
		this.blockList.clear();
		this.blockList.addAll(newBlocks);

	}

	public List<List<Block>> getCloneShapePool() {
		List<List<Block>> bs = new ArrayList<List<Block>>();

		for (List<Block> bl : this.shapePool) {
			List<Block> b = new ArrayList<Block>();
			for (Block a : bl) {
				b.add((Block) a.clone());
			}
			bs.add(b);

		}
		return bs;
	}

	public int getCurrentShapeIndex() {
		return currentShapeIndex;
	}

}
