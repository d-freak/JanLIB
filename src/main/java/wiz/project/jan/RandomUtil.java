/**
 * RandomUtil.java
 */

package wiz.project.jan;

import java.security.SecureRandom;



/**
 * 乱数ユーティリティ
 */
public final class RandomUtil {
	
	/**
	 * コンストラクタ利用禁止
	 */
	private RandomUtil() {}
	
	
	
	/**
	 * 指定範囲内の乱数を取得
	 * 
	 * @param begin 範囲の開始位置。
	 * @param end 範囲の終了位置。
	 * @return 指定範囲内の乱数。
	 */
	public static int getInt(final int begin, final int end) {
		if (begin > end) {
			throw new IllegalArgumentException("Invalid scope - " + begin + " -> " + end);
		}
		if (begin == end) {
			return begin;
		}
		
		final int scope = end - begin;
		final SecureRandom random = new SecureRandom();
		final int result = random.nextInt(scope + 1);  // 0 ～ scope までの乱数を生成
		return result + begin;
	}
	
}

