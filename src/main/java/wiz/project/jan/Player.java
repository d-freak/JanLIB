/**
 * Player.java
 */

package wiz.project.jan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;

import wiz.project.jan.util.HandCheckUtil;
import wiz.project.jan.util.JanPaiUtil;



/**
 * プレイヤー
 */
public class Player implements Observer {
	
	/**
	 * コンストラクタ
	 */
	public Player() {
	}
	
	/**
	 * コンストラクタ
	 * 
	 * @param name プレイヤー名。
	 */
	public Player(final String name) {
		_status.setName(name);
	}
	
	
	
	/**
	 * ツモ牌を手牌に加える
	 * 
	 * @param tsumo ツモ牌。
	 * @param discard 捨て牌。
	 */
	public void addJanPai(final JanPai tsumo, final JanPai discard) {
		final Hand hand = _status.getHand();
		hand.removeJanPai(discard);
		hand.addJanPai(tsumo);
		_status.setHand(hand);
	}
	
	/**
	 * アクションリストを消去
	 */
	public void clearActionList() {
		_actionList.clear();
	}
	
	/**
	 * アクションリストを取得
	 * 
	 * @return アクションリスト。
	 */
	public List<Action> getActionList() {
		return deepCopyList(_actionList);
	}
	
	/**
	 * ステータスを取得
	 * 
	 * @return ステータス。
	 */
	public PlayerStatus getStatus() {
		return _status.clone();
	}
	
	/**
	 * 各局の初期化処理
	 * 
	 * @param hand 配牌。
	 */
	public void initializeOnGame(final List<JanPai> hand) {
		if (hand == null) {
			throw new NullPointerException("Hand is null.");
		}
		_status.setHand(new Hand(hand));
	}
	
	/**
	 * フリテンか
	 * 
	 * @return 判定結果。
	 */
	public boolean isFuriten() {
		final Map<JanPai, Integer> hand = getCleanedHand();
		final List<JanPai> completableList = HandCheckUtil.getCompletableJanPaiList(hand);
		final List<JanPai> discardList = Field.getInstance().getRiver(_status.getWind());
		for (final JanPai completable : completableList) {
			if (discardList.contains(completable)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 次の風を設定
	 */
	public void setNextWind() {
		_status.setWind(_status.getWind().getNext());
	}
	
	/**
	 * ステータスを設定
	 * 
	 * @param status ステータス。
	 */
	public void setStatus(final PlayerStatus status) {
		if (status != null) {
			_status = status.clone();
		}
		else {
			_status = new PlayerStatus();
		}
	}
	
	/**
	 * 風を設定
	 * 
	 * @param wind 風。
	 */
	public void setWind(final Wind wind) {
		_status.setWind(wind);
	}
	
	/**
	 * 更新通知時の処理
	 * 
	 * @param target 監視対象。
	 * @param param 通知パラメータ。
	 */
	public void update(final Observable target, final Object param) {
		if (target == null) {
			throw new NullPointerException("Subject is null.");
		}
		if (!(param instanceof JanPai)) {
			// 何もしない
			return;
		}
		
		final Map<JanPai, Integer> hand = getCleanedHand();
		JanPaiUtil.addJanPai(hand, (JanPai)param, 1);
		if (HandCheckUtil.isComplete(hand)) {
			_actionList.add(Action.RON);
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
     * 整形済みの手牌を取得
     */
    private Map<JanPai, Integer> getCleanedHand() {
    	final Map<JanPai, Integer> hand = _status.getHand().getMenZenMap();
		JanPaiUtil.cleanJanPaiMap(hand);
		return hand;
    }
    
    
    
	/**
	 * プレイヤーの状態
	 */
	private PlayerStatus _status = new PlayerStatus();
	
    /**
     * 可能なアクション
     */
    private List<Action> _actionList = new CopyOnWriteArrayList<>();
    
}

