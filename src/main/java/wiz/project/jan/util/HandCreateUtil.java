/**
 * HandCreateUtil.java
 * 
 * @Author
 *   Yuki Kawata
 */

package wiz.project.jan.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import wiz.project.jan.JanPai;
import wiz.project.jan.MenTsu;



/**
 * 手牌作成ユーティリティ
 */
public final class HandCreateUtil {
    
    /**
     * コンストラクタ利用禁止
     */
    private HandCreateUtil() {}
    
    
    
    /**
     * 字牌刻子リストを取得
     * 
     * @param source 取得元。取得分の牌が削除される。
     * @return 字牌刻子リスト。
     */
    public static List<MenTsu> getJiKouTsuList(final Map<JanPai, Integer> source) {
        final List<MenTsu> resultList = new ArrayList<MenTsu>();
        for (final JanPai pai : JanPaiUtil.JI_LIST) {
            if (!source.containsKey(pai)) {
                continue;
            }
            final int count = source.get(pai);
            if (count == 3) {
                source.remove(pai);
                final List<JanPai> kouTsu = new ArrayList<JanPai>();
                for (int i = 0; i < 3; i++) {
                    kouTsu.add(pai);
                }
                resultList.add(new MenTsu(kouTsu));
            }
        }
        return resultList;
    }
    
    /**
     * 字牌四枚刻子リストを取得
     * 
     * @param source 取得元。取得分の牌が削除される。
     * @return 字牌四枚刻子リスト。
     */
    public static List<MenTsu> getJiKouTsuExList(final Map<JanPai, Integer> source) {
        final List<MenTsu> resultList = new ArrayList<MenTsu>();
        for (final JanPai pai : JanPaiUtil.JI_LIST) {
            if (!source.containsKey(pai)) {
                continue;
            }
            final int count = source.get(pai);
            if (count >= 4) {
                source.remove(pai);
                final List<JanPai> kouTsuEx = new ArrayList<JanPai>();
                for (int i = 0; i < count; i++) {
                    kouTsuEx.add(pai);
                }
                resultList.add(new MenTsu(kouTsuEx));
            }
        }
        return resultList;
    }
    
    /**
     * 一枚だけの字牌リストを取得
     * 
     * @param source 取得元。取得分の牌が削除される。
     * @return 一枚だけの字牌リスト。
     */
    public static List<JanPai> getJiTankiList(final Map<JanPai, Integer> source) {
        final List<JanPai> resultList = new ArrayList<JanPai>();
        for (final JanPai pai : JanPaiUtil.JI_LIST) {
            if (!source.containsKey(pai)) {
                continue;
            }
            final int count = source.get(pai);
            if (count == 1) {
                source.remove(pai);
                resultList.add(pai);
            }
        }
        return resultList;
    }
    
    /**
     * 刻子リストを取得
     * 
     * @param source 取得元。取得分の牌が削除される。
     * @return 刻子リスト。
     */
    public static List<MenTsu> getKouTsuList(final Map<JanPai, Integer> source) {
        final List<MenTsu> resultList = new ArrayList<MenTsu>();
        for (final JanPai pai : JanPai.values()) {
            if (!source.containsKey(pai)) {
                continue;
            }
            final int count = source.get(pai);
            if (count == 3) {
                source.remove(pai);
                final List<JanPai> kouTsu = new ArrayList<JanPai>();
                for (int i = 0; i < 3; i++) {
                    kouTsu.add(pai);
                }
                resultList.add(new MenTsu(kouTsu));
            }
        }
        return resultList;
    }
    
    /**
     * 四枚刻子リストを取得
     * 
     * @param source 取得元。取得分の牌が削除される。
     * @return 四枚刻子リスト。
     */
    public static List<MenTsu> getKouTsuExList(final Map<JanPai, Integer> source) {
        final List<MenTsu> resultList = new ArrayList<MenTsu>();
        for (final JanPai pai : JanPai.values()) {
            if (!source.containsKey(pai)) {
                continue;
            }
            final int count = source.get(pai);
            if (count >= 4) {
                source.remove(pai);
                final List<JanPai> kouTsuEx = new ArrayList<JanPai>();
                for (int i = 0; i < count; i++) {
                    kouTsuEx.add(pai);
                }
                resultList.add(new MenTsu(kouTsuEx));
            }
        }
        return resultList;
    }
    
    /**
     * 両面待ち可能な牌リストを取得
     * 
     * @param source 取得元。取得分の牌が削除される。
     * @return 両面待ち可能な牌リスト。
     */
    public static List<List<JanPai>> getRyanMenList(final Map<JanPai, Integer> source) {
        final List<List<JanPai>> resultList = new ArrayList<List<JanPai>>();
        final List<JanPai> keyList = new ArrayList<JanPai>(source.keySet());
        for (int i = 1; i < keyList.size(); i++) {
            final JanPai x = keyList.get(i - 1);
            final JanPai y = keyList.get(i);
            if (x.getNext() == y) {
                final List<JanPai> ryanMen = Arrays.asList(x, y);
                for (final JanPai pai : ryanMen) {
                    JanPaiUtil.removeJanPai(source, pai, 1);
                }
                resultList.add(ryanMen);
            }
        }
        return resultList;
    }
    
    /**
     * 順子リストを取得
     * 
     * @param source 取得元。取得分の牌が削除される。
     * @return 順子リスト。
     */
    public static List<MenTsu> getShunTsuList(final Map<JanPai, Integer> source) {
        final List<MenTsu> resultList = new ArrayList<MenTsu>();
        boolean foundShunTsu;
        do {
            foundShunTsu = false;
            final List<JanPai> keyList = new ArrayList<JanPai>(source.keySet());
            for (int i = 2; i < keyList.size(); i++) {
                final JanPai x = keyList.get(i - 2);
                final JanPai y = keyList.get(i - 1);
                final JanPai z = keyList.get(i);
                if (JanPaiUtil.isShunTsu(x, y, z)) {
                    final List<JanPai> shunTsu = Arrays.asList(x, y, z);
                    for (final JanPai pai : shunTsu) {
                        JanPaiUtil.removeJanPai(source, pai, 1);
                    }
                    resultList.add(new MenTsu(shunTsu));
                    
                    // 順子が見つかった場合、リスト再構築 (一盃口対策)
                    foundShunTsu = true;
                    break;
                }
            }
        }
        while (foundShunTsu && !source.isEmpty());
        return resultList;
    }
    
    /**
     * 指定牌を含む順子リストを取得
     * 
     * @param source 取得元。取得分の牌が削除される。
     * @param target 順子に含めたい牌。
     * @return 順子リスト。
     */
    public static List<MenTsu> getShunTsuListWith(final Map<JanPai, Integer> source, final JanPai target) {
        final List<MenTsu> resultList = new ArrayList<MenTsu>();
        boolean foundShunTsu;
        do {
            foundShunTsu = false;
            final List<JanPai> keyList = new ArrayList<JanPai>(source.keySet());
            for (int i = 2; i < keyList.size(); i++) {
                final JanPai x = keyList.get(i - 2);
                final JanPai y = keyList.get(i - 1);
                final JanPai z = keyList.get(i);
                if (x != target && y != target && z != target) {
                    continue;
                }
                if (JanPaiUtil.isShunTsu(x, y, z)) {
                    final List<JanPai> shunTsu = Arrays.asList(x, y, z);
                    for (final JanPai pai : shunTsu) {
                        JanPaiUtil.removeJanPai(source, pai, 1);
                    }
                    resultList.add(new MenTsu(shunTsu));
                    
                    // 順子が見つかった場合、リスト再構築 (一盃口対策)
                    foundShunTsu = true;
                    break;
                }
            }
        }
        while (foundShunTsu && !source.isEmpty());
        return resultList;
    }
    
}

