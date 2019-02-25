package org.tj.block.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import org.tj.block.interfce.IBlockGroupChangeShape;
import org.tj.block.model.Block;
import org.tj.block.model.BlockGroup;
import org.tj.block.model.LBlockGroup;
import org.tj.block.model.MoveDirection;
import org.tj.block.model.bus.CreateNewMoveBlockBusModel;
import org.tj.block.util.BusUtil;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

@SuppressWarnings("serial")
public class BlockPanel extends JPanel implements Runnable {

	public static int BLOCK_W_COUNT = 11;

	public static int BLOCK_H_COUNT = 20;

	/**
	 * 最小左右空隙
	 */
	public static int LEFT_RIGHT_GAP = 5;

	/**
	 * 最小上下空隙
	 */
	public static int TOP_BOTTOM_GAP = 5;

	/**
	 * 格子宽度
	 */
	private static int BLOCK_WIDTH = 0;

	/**
	 * 格子高度
	 */
	private static int BLOCK_HEIGHT = 0;

	// private int a = 4;
	// private int b = -1;
	/**
	 * 游戏页面上的方块
	 */
	private Block[][] blocks;

	/**
	 * 页面上当前正在移动的方块，默认第一个是L型
	 */
	private IBlockGroupChangeShape currentMoveBlockgroup;

	/**
	 * 已经落地不动的方块
	 */
	private List<Block> finishMoveBlockList = new ArrayList<Block>();

	public BlockPanel() {
		setVisible(true);
		setLayout(null);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (BLOCK_WIDTH == 0 && BLOCK_HEIGHT == 0) {
			BLOCK_WIDTH = (int) ((this.getWidth() - (LEFT_RIGHT_GAP * 2)) / BLOCK_W_COUNT);
			BLOCK_HEIGHT = (int) ((this.getHeight() - (TOP_BOTTOM_GAP * 2)) / BLOCK_H_COUNT);
			if (BLOCK_WIDTH != BLOCK_HEIGHT) {
				// System.out.println(BLOCK_WIDTH + "-------" + BLOCK_HEIGHT);
				int temp = Math.max(BLOCK_WIDTH, BLOCK_HEIGHT);
				BLOCK_WIDTH = temp;
				BLOCK_HEIGHT = temp;
			}
			blocks = new Block[BLOCK_W_COUNT][BLOCK_H_COUNT];
			initBlocks();
		}
		BufferedImage bi = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB); // 双缓冲技术防止闪屏
		Graphics jg = bi.createGraphics();
		try {
			drawBlock(jg);
			drawMoveBlock(jg);// 画当前正在移动的方块
			drawFinishMoveBlock(jg);// 画已经落地的方块
			g.drawImage(bi, 0, 0, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 画已经落地的方块
	 * 
	 * @author 唐靖
	 *
	 * @date 2018年6月16日下午4:43:57
	 *
	 * @param jg
	 */
	private void drawFinishMoveBlock(Graphics jg) {
		for (Block b : finishMoveBlockList) { // 落地的方块
			if (b.bgArrayX >= 0 && b.bgArrayX < BLOCK_W_COUNT && //
					b.bgArrayY >= 0 && b.bgArrayY < BLOCK_H_COUNT) { // 如果这个方块是包含在当前屏幕的，画，如果不包含，不画
				jg.setColor(b.color);
				jg.fillRoundRect(blocks[b.bgArrayX][b.bgArrayY].x, blocks[b.bgArrayX][b.bgArrayY].y, BLOCK_WIDTH,
						BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT); // 使用地图上的block带的参数，覆盖画一个正在移动的方块
			}
		}
	}

	/**
	 * 画当前正在移动的方块
	 * 
	 * @author 唐靖
	 *
	 * @date 2018年6月16日下午4:43:17
	 *
	 * @param jg
	 */
	private void drawMoveBlock(Graphics jg) {
		if (null != currentMoveBlockgroup && currentMoveBlockgroup.getShapeBlockList().size() > 0) {
			for (Block b : currentMoveBlockgroup.getShapeBlockList()) { // 移动的方块
				if (b.bgArrayX >= 0 && b.bgArrayX < BLOCK_W_COUNT && //
						b.bgArrayY >= 0 && b.bgArrayY < BLOCK_H_COUNT) { // 如果这个方块是包含在当前屏幕的，画，如果不包含，不画
					jg.setColor(b.color);
					jg.fillRoundRect(blocks[b.bgArrayX][b.bgArrayY].x, blocks[b.bgArrayX][b.bgArrayY].y, BLOCK_WIDTH,
							BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT); // 使用地图上的block带的参数，覆盖画一个正在移动的方块

				}
			}
		}

	}

	/**
	 * 初始化默认的方块
	 * 
	 * @author 唐靖
	 *
	 * @date 2018年6月16日下午3:32:07
	 *
	 */
	private void initBlocks() {
		for (int x = 0; x < BLOCK_H_COUNT; x++) {
			for (int i = 0; i < BLOCK_W_COUNT; i++) {
				Block blo = new Block();
				blo.bgArrayX = i;
				blo.bgArrayY = x;
				blocks[i][x] = blo;
			}
		}

	}

	private void drawBlock(Graphics jg) {
		// 设置线条颜色为红色

		// 绘制外层矩形框

		for (int x = 0; x < BLOCK_H_COUNT; x++) {
			for (int i = 0; i < BLOCK_W_COUNT; i++) {

				blocks[i][x].x = (this.getWidth() - (BLOCK_WIDTH * BLOCK_W_COUNT)) / 2 + BLOCK_WIDTH * i; // 这里需要记下对应的像素位置
				blocks[i][x].y = ((this.getHeight() - BLOCK_HEIGHT * BLOCK_H_COUNT)) / 2 + BLOCK_HEIGHT * x;// 这里需要记下对应的像素位置
				// jg.setColor(blocks[i][x].color);
				// jg.fillRect(blocks[i][x].x, blocks[i][x].y, BLOCK_WIDTH,
				// BLOCK_HEIGHT);
				jg.setColor(Color.DARK_GRAY);
				jg.drawRect(blocks[i][x].x, blocks[i][x].y, BLOCK_WIDTH, BLOCK_HEIGHT);
			}
		}
	}

	/**
	 * 线程更新页面
	 * 
	 * @author 唐靖
	 *
	 * @date 2018年6月16日下午4:04:09
	 *
	 */
	public void run() {
		int x = 0;
		while (true) {
			try {
				repaint();
				TimeUnit.MILLISECONDS.sleep(200);
				x++;
				if (x == 4) {
					blockMove(MoveDirection.DOWN);
					x = 0;
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 下移
	 * 
	 * @author 唐靖
	 *
	 * @date 2018年6月16日下午5:33:06
	 *
	 */
	@Subscribe
	private void blockMove(MoveDirection move) {

		synchronized (this) {
			if (null == currentMoveBlockgroup) {
				this.currentMoveBlockgroup = new LBlockGroup((BLOCK_W_COUNT / 2) - 1);
				BusUtil.createNewMoveBlockBus
						.post(new CreateNewMoveBlockBusModel(
								((BlockGroup) currentMoveBlockgroup).getCloneShapePool()
										.get(((BlockGroup) currentMoveBlockgroup).getCurrentShapeIndex()),
								(BLOCK_W_COUNT / 2) - 1));
				return;
			}

			if (MoveDirection.UP.equals(move)) { // 变形

				List<Block> newBlockList = currentMoveBlockgroup.changeShape();
				// 这是变形之后的列表，但是该列表未必一定可用，还要判断是否能行
				int temp = 0;
				for (Block bl : newBlockList) {
					// 1判断变形后是否会和别人撞到一起
					if (positionCover(finishMoveBlockList, bl)) {// 变形会碰到，就不变,到此为止
						return;
					}
					// 判断变形后下边出界，不变
					if (bl.bgArrayY > (BLOCK_H_COUNT - 1)) {
						return;
					}

					if (bl.bgArrayX < 0) { // 往左出界
						// temp用来记录最小的，即出界最多的是多少
						temp = Math.min(bl.bgArrayX, temp);
					}
					if (bl.bgArrayX > (BLOCK_W_COUNT - 1)) { // 往右出界
						// temp用来记录最大的，即出界最多的是多少
						temp = Math.max(bl.bgArrayX - BLOCK_W_COUNT + 1, temp);
					}

				}

				// 能到这说明能变但是出界，需要调整坐标
				for (int i = 0; i < newBlockList.size(); i++) { // 整体调整，如果是左边界出界，temp是负数，会增大，否则就是变小
					newBlockList.get(i).bgArrayX = newBlockList.get(i).bgArrayX - temp;
				}

				// 结束了再判断是否撞到
				for (Block bl : newBlockList) { // 如果还是状态，变不了
					// 1判断变形后是否会和别人撞到一起
					if (positionCover(finishMoveBlockList, bl)) {// 变形会碰到，就不变,到此为止
						return;
					}
				}

				currentMoveBlockgroup.updateShape(newBlockList);
				BusUtil.createNewMoveBlockBus
						.post(new CreateNewMoveBlockBusModel(
								((BlockGroup) currentMoveBlockgroup).getCloneShapePool()
										.get(((BlockGroup) currentMoveBlockgroup).getCurrentShapeIndex()),
								(BLOCK_W_COUNT / 2) - 1));

				return;
			}

			boolean flag = allowToMove(move);
			if (flag) { // 允许移动
				List<Block> newBlockList = new ArrayList<Block>();
				for (Block old : currentMoveBlockgroup.getShapeBlockList()) {
					Block nw = (Block) old.clone();
					if (MoveDirection.DOWN.equals(move)) { // 下移
						nw.bgArrayY = nw.bgArrayY + 1;
					} else if (MoveDirection.LEFT.equals(move)) { // 左
						nw.bgArrayX = nw.bgArrayX - 1;
					} else if (MoveDirection.RIGHT.equals(move)) { // 右
						nw.bgArrayX = nw.bgArrayX + 1;
					}
					newBlockList.add(nw);

				}

				((BlockGroup) currentMoveBlockgroup).replaceBlockList(newBlockList);
			} else { // 说明不能这样移动，左右下边都挡住了

			}
		}
	}

	private boolean allowToMove(MoveDirection move) {
		// System.out.println(currentMoveBlockgroup.getShapeBlockList().get(0).bgArrayY);
		for (Block old : currentMoveBlockgroup.getShapeBlockList()) {

			if (old.bgArrayY == (BLOCK_H_COUNT - 1)) {// 到底了
				changeFinishMoveBlock(); // 刷新停止移动和正在移动的方块
				return false;
			} else {
				Block temp = (Block) old.clone();
				if (MoveDirection.DOWN.equals(move)) { // 下移，既不能到底，也不能是下边有移动结束的方块
					temp.bgArrayY = temp.bgArrayY + 1;
					// 如果是往下的碰到了，就要刷新了

					if (positionCover(finishMoveBlockList, temp)) {
						changeFinishMoveBlock(); // 刷新停止移动和正在移动的方块
						return false;
					}

				} else if (MoveDirection.LEFT.equals(move)) { // 左
					if (temp.bgArrayX == 0) { // 到达左边界
						return false;
					}
					temp.bgArrayX = temp.bgArrayX - 1;

				} else if (MoveDirection.RIGHT.equals(move)) { // 右移
					temp.bgArrayX = temp.bgArrayX + 1;
					if (temp.bgArrayX == BLOCK_W_COUNT) {// 到达右边界
						return false;
					}

				}
				// 如果上边的都能OK，判断是否碰到下边已经不能移动的方块
				if (positionCover(finishMoveBlockList, temp)) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * 刷新停止移动和正在移动的方块
	 * 
	 * @author 唐靖
	 *
	 * @date 2018年6月16日下午6:59:59
	 *
	 */
	private void changeFinishMoveBlock() {
		finishMoveBlockList.addAll(currentMoveBlockgroup.getShapeBlockList());

		currentMoveBlockgroup = new LBlockGroup((BLOCK_W_COUNT / 2) - 1);
		BusUtil.createNewMoveBlockBus
				.post(new CreateNewMoveBlockBusModel(((BlockGroup) currentMoveBlockgroup).getCloneShapePool()
						.get(((BlockGroup) currentMoveBlockgroup).getCurrentShapeIndex()), (BLOCK_W_COUNT / 2) - 1));
		checkNeedDisappearBlock();
	}

	/**
	 * 校验是否已经拼成完整行，消掉那些方块
	 * 
	 * @author 唐靖
	 *
	 * @date 2018年6月21日下午10:28:03
	 *
	 */
	private void checkNeedDisappearBlock() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Map<Integer, List<Block>> blockMap = new HashMap<Integer, List<Block>>();
		List<Integer> removeYList = new ArrayList<Integer>();
		// 1，把x相同的方块，合并起来
		for (Block finish : finishMoveBlockList) {
			map.put(finish.bgArrayY, (null == map.get(finish.bgArrayY)) ? 1 : (map.get(finish.bgArrayY) + 1));
			if (blockMap.get(finish.bgArrayY) == null) {
				List<Block> yBlocks = new ArrayList<Block>();
				yBlocks.add(finish);
				blockMap.put(finish.bgArrayY, yBlocks);
			} else { // 已经有了
				blockMap.get(finish.bgArrayY).add(finish);
			}
			// System.out.println(map.get(finish.bgArrayY));
			if (map.get(finish.bgArrayY) == BLOCK_W_COUNT) { // 满了
				blockMap.remove(finish.bgArrayY);
				removeYList.add(finish.bgArrayY);

			}
		}

		// 完了之后重新赋值
		this.finishMoveBlockList.clear();
		for (Integer key : blockMap.keySet()) { // y要全部增加count
			for (Block list : blockMap.get(key)) {
				int count = 0;
				for (Integer in : removeYList) {
					if (list.bgArrayY < in) // 说明在他上边需要下降一格
					{
						count++;
					}

				}
				list.bgArrayY = list.bgArrayY + count;
				this.finishMoveBlockList.add(list);

			}

		}
		if (removeYList.size() > 0) {
			BusUtil.getScoreBus.post(removeYList.size());
		}

	}

	/**
	 * 判断位置是否重合
	 * 
	 * @author 唐靖
	 *
	 * @date 2018年6月24日下午10:28:21
	 *
	 * @param block
	 * @param b
	 * @return
	 */
	public boolean positionCover(List<Block> block, Block b) {
		for (Block a : block) {
			if (a.samePosition(b)) {
				return true;
			}
		}
		return false;

	}

}
