package org.tj.block.util;

import com.google.common.eventbus.EventBus;

/**
 * 所有的eventbus放在这里边
 * 
 * @author 唐靖
 *
 * @date 2016年10月10日下午6:27:22
 *
 */
public class BusUtil {
	/**
	 * 方块移动的eventbus
	 */
	public static final EventBus blockMoveBus = new EventBus("blockMove");

	/**
	 * 生成新的移动方块
	 */
	public static final EventBus createNewMoveBlockBus = new EventBus("createNewMoveBlockBus");

	/**
	 * 得分
	 */
	public static final EventBus getScoreBus = new EventBus("getScoreBus");

}
