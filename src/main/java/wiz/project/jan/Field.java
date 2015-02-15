/**
 * Field.java
 * 
 * @Author
 *   Yuki Kawata
 */

package wiz.project.jan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import wiz.project.jan.util.JanPaiUtil;



/**
 * 場
 */
public final class Field extends Observable {
    
    /**
     * コンストラクタを自分自身に限定許可
     */
    private Field() {
    }
    
    
    
    /**
     * インスタンスを取得
     * 
     * @return インスタンス。
     */
    public static Field getInstance() {
        return INSTANCE;
    }
    
    
    
    /**
     * 牌を捨てる
     * 
     * @param wind プレイヤーの風。
     * @param pai 捨て牌。
     */
    public void discardJanPai(final Wind wind, final JanPai pai) {
    	_river.get(wind).add(pai);
    	
    	setChanged();
    	notifyObservers(pai);
    }
    
    /**
     * ドラを取得
     * 
     * @return ドラ。
     */
    public JanPai getDora() {
    	synchronized (_DORA_PREV_LOCK) {
    		return _doraPrev.getNext();
    	}
    }
    
    /**
     * ドラ表示牌を取得
     * 
     * @return ドラ表示牌。
     */
    public JanPai getDoraPrev() {
    	synchronized (_DORA_PREV_LOCK) {
    		return _doraPrev;
    	}
    }
    
    /**
     * 雀牌を取得
     * 
     * @return 雀牌。
     */
    public JanPai getJanPai() {
    	// TODO 暫定実装
    	_remainTsumoCount--;
    	return getJanPaiCore();
    }
    
    /**
     * 残りツモ枚数を取得
     */
    public int getRemainTsumoCount() {
    	return _remainTsumoCount;
    }
    
    /**
     * 河を取得
     * 
     * @param wind プレイヤーの風。
     * @return 河。
     */
    public List<JanPai> getRiver(final Wind wind) {
    	return deepCopyList(_river.get(wind));
    }
    
    /**
     * サイコロの出目を取得
     * 
     * @return サイコロの出目。
     */
    public int getSai() {
    	return _sai;
    }
    
    /**
     * 王牌を取得
     * 
     * @return 王牌。
     */
    public JanPai getWanPai() {
    	// TODO 暫定実装
    	_remainTsumoCount--;
    	return getJanPai();
    }
    
    /**
     * 場風を取得
     * 
     * @return 場風。
     */
    public Wind getWind() {
        synchronized (_WIND_LOCK) {
            return _wind;
        }
    }
    
    /**
     * 山牌を初期化
     * 
     * @return 手牌。
     */
    public Map<Wind, List<JanPai>> initializeDeck() {
    	// 牌山生成
    	_deck.clear();
    	_deck.putAll(JanPaiUtil.createDeck());
    	
    	// 河の状態を初期化
    	_river.clear();
    	for (final Wind wind : Wind.values()) {
    		_river.put(wind, new ArrayList<JanPai>());
    	}
    	
    	// サイコロを振る
		_sai = RandomUtil.getInt(2, 12);
    	
    	// ドラ表字牌をめくる
    	synchronized (_DORA_PREV_LOCK) {
			_doraPrev = getJanPaiCore();
    	}
    	
    	// 手牌生成 (全員13牌から)
    	final Map<Wind, List<JanPai>> handTable = new LinkedHashMap<Wind, List<JanPai>>();
    	for (final Wind wind : Wind.values()) {
    		final List<JanPai> hand = new ArrayList<>();
    		for (int i = 0; i < 13; i++) {
    			hand.add(getJanPaiCore());
    		}
    		Collections.sort(hand);
    		handTable.put(wind, hand);
    	}
    	
    	// 残り枚数を設定
    	_remainTsumoCount = 70;
    	return handTable;
    }
    
    /**
     * 場風を設定
     * 
     * @param wind 場風。
     */
    public void setWind(final Wind wind) {
        synchronized (_WIND_LOCK) {
            if (wind != null) {
                _wind = wind;
            }
            else {
                _wind = Wind.TON;
            }
        }
    }
    
    
    
    /**
     * リストをディープコピー
     * 
     * @param sourceList 複製元。
     * @return 複製結果。
     */
    private <E> List<E> deepCopyList(final List<E> sourceList) {
    	return new ArrayList<>(sourceList);
    }
    
    /**
     * 雀牌取得の中核処理
     * 
     * @return 雀牌。
     */
    private JanPai getJanPaiCore() {
    	// TODO 暫定実装
    	for (final Wind wind : Wind.values()) {
    		final List<JanPai> janPaiList = _deck.get(wind);
    		if (!janPaiList.isEmpty()) {
    			return janPaiList.remove(0);
    		}
    	}
    	throw new IllegalStateException("Deck is empty.");
    }
    
    
    
    /**
     * 自分自身のインスタンス
     */
    private static final Field INSTANCE = new Field();
    
    
    
    /**
     * ロックオブジェクト (場風)
     */
    private final Object _WIND_LOCK = new Object();
    
    /**
     * ロックオブジェクト (ドラ表字牌)
     */
    private final Object _DORA_PREV_LOCK = new Object();
    
    
    
    /**
     * 場風
     */
    private Wind _wind = Wind.TON;
    
    /**
     * 牌山
     */
    private final Map<Wind, List<JanPai>> _deck =
    	Collections.synchronizedMap(new LinkedHashMap<Wind, List<JanPai>>());
    
    /**
     * 河
     */
    private final Map<Wind, List<JanPai>> _river =
    	Collections.synchronizedMap(new LinkedHashMap<Wind, List<JanPai>>());
    
    /**
     * 残りツモ枚数
     */
    private volatile int _remainTsumoCount = 0;
    
    /**
     * ドラ表示牌
     */
    private JanPai _doraPrev = JanPai.HAKU;
    
    /**
     * サイコロの出目
     */
    private volatile int _sai = 0;
    
}

