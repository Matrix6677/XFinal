package cn.ziav.xfinal.common.utils;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 位运算工具集
 * @author Z.avi
 */
public class BitUtils {

	/**
	 * 判断指定位置的比特位是否为1
	 * @param value
	 * @param pos
	 * @return
	 */
	public static boolean isOne(int value, int pos) {
		checkArgument(pos >= 0 && pos <= 31, "参数错误：pos=%s，位置范围应>=0且<=31", pos);
		return (value >>> pos & 1) == 1;
	}

	/**
	 * 将指定位置的比特位置为1
	 * @param value
	 * @param pos
	 * @return
	 */
	public static int setOne(int value, int pos) {
		checkArgument(pos >= 0 && pos <= 31, "参数错误：pos=%s，位置范围应>=0且<=31", pos);
		return value | (1 << pos);
	}

	/**
	 * 合并两个整型二进制数中的1
	 * @param val1
	 * @param val2
	 * @return
	 */
	public static int combineOne(int val1, int val2) {
		return val1 | val2;
	}

	/**
	 * 统计二进制数中1的个数
	 * @param value
	 * @return
	 */
	public static int countOne(int value) {
		int count = 0;
		while (value != 0) {
			value &= value - 1;
			++count;
		}
		return count;
	}

	/**
	 * 统计指定位置连续1的个数
	 * @param value
	 * @param pos
	 * @return
	 */
	public static int countLeftRightOne(int value, int pos) {
		int count = 0;
		if (!isOne(value, pos)) {
			return count;
		}
		for (int left = 1 << pos + 1, right = left >>> 1;;) {
			if ((value & left) != left && (value & right) != right) {
				break;
			}
			if ((value & left) == left) {
				++count;
				left <<= 1;
			}
			if ((value & right) == right) {
				++count;
				right >>>= 1;
			}
		}
		return count;
	}
}
