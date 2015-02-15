/**
 * Hand.java
 * 
 * @Author
 *   Yuki Kawata
 */

package wiz.project.jan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import wiz.project.jan.util.JanPaiUtil;



/**
 * 手牌
 */
public final class Hand implements Cloneable {
    
    /**
     * コンストラクタ
     */
    public Hand() {
        initializeCore();
    }
    
    /**
     * コンストラクタ
     * 
     * @param menZen 面前手牌。
     */
    public Hand(final List<JanPai> menZen) {
        this();
        setJanPaiList(menZen);
    }
    
    /**
     * コンストラクタ
     * 
     * @param menZen 面前手牌。
     */
    public Hand(final Map<JanPai, Integer> menZen) {
        this();
        setJanPaiMap(menZen);
    }
    
    /**
     * コンストラクタ
     * 
     * @param menZen 面前手牌。
     * @param fixedMenTsuList 確定面子リスト。
     */
    public Hand(final List<JanPai> menZen, final List<MenTsu> fixedMenTsuList) {
        this(menZen);
        setFixedMenTsuList(fixedMenTsuList);
    }
    
    /**
     * コンストラクタ
     * 
     * @param menZen 面前手牌。
     * @param fixedMenTsuList 確定面子リスト。
     */
    public Hand(final Map<JanPai, Integer> menZen, final List<MenTsu> fixedMenTsuList) {
        this(menZen);
        setFixedMenTsuList(fixedMenTsuList);
    }
    
    /**
     * コピーコンストラクタ
     * 
     * @param source 複製元オブジェクト。
     */
    public Hand(final Hand source) {
        if (source != null) {
            _core = deepCopyMap(source._core);
            _fixedMenTsuList = deepCopyList(source._fixedMenTsuList);
        }
    }
    
    
    
    /**
     * 自分自身を複製 (ディープコピー)
     * 
     * @return 複製結果。
     */
    @Override
    public Hand clone() {
        return new Hand(this);
    }
    
    /**
     * 等価なオブジェクトか
     * 
     * @param target 比較対象。
     * @return 比較結果。
     */
    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (target == null) {
            return false;
        }
        if (!(target instanceof Hand)) {
            return false;
        }
        
        final Hand targetHand = (Hand)target;
        return _core.equals(targetHand._core) &&
               _fixedMenTsuList.equals(targetHand._fixedMenTsuList);
    }
    
    /**
     * 確定面子を追加
     * 
     * @param menTsu 確定面子。
     */
    public void addFixedMenTsu(final MenTsu menTsu) {
        if (menTsu != null) {
            _fixedMenTsuList.add(menTsu);
        }
    }
    
    /**
     * 牌を追加
     * 
     * @param pai 追加する牌。
     */
    public void addJanPai(final JanPai pai) {
        if (pai == null) {
            return;
        }
        
        if (isLimitSize()) {
            return;
        }
        if (getJanPaiCount(pai) >= 4) {
            return;
        }
        JanPaiUtil.addJanPai(_core, pai, 1);
    }
    
    /**
     * フィールドを全消去
     */
    public void clear() {
        clearMenZenHand();
        clearFixedMenTsuList();
    }
    
    /**
     * 面前手牌を全消去
     */
    public void clearMenZenHand() {
        initializeCore();
    }
    
    /**
     * 確定面子を全消去
     */
    public void clearFixedMenTsuList() {
        _fixedMenTsuList.clear();
    }
    
    /**
     * 全ての牌マップを取得
     * 
     * @return 全ての牌マップ。
     */
    public Map<JanPai, Integer> getAllJanPaiMap() {
        final Map<JanPai, Integer> result = deepCopyMap(_core);
        for (final MenTsu menTsu : _fixedMenTsuList) {
            for (final JanPai pai : menTsu.getSource()) {
                JanPaiUtil.addJanPai(result, pai, 1);
            }
        }
        return result;
    }
    
    /**
     * 確定面子数を取得
     * 
     * @return 確定面子数。
     */
    public int getFixedMenTsuCount() {
        return _fixedMenTsuList.size();
    }
    
    /**
     * 確定面子リストを取得
     * 
     * @return 確定面子リスト。
     */
    public List<MenTsu> getFixedMenTsuList() {
        return deepCopyList(_fixedMenTsuList);
    }
    
    /**
     * 指定牌の所持数を取得
     * 
     * @param pai 検索対象。
     * @return 所持数。
     */
    public int getJanPaiCount(final JanPai pai) {
        int count = _core.get(pai);
        for (final MenTsu menTsu : _fixedMenTsuList) {
            count += menTsu.getJanPaiCount(pai);
        }
        return count;
    }
    
    /**
     * 面前手牌の上限枚数を取得
     * 
     * @return 面前手牌の上限枚数。
     */
    public int getLimitSize() {
        final int fixedCount = _fixedMenTsuList.size() * 3;
        return 14 - fixedCount;
    }
    
    /**
     * 指定牌の面前所持数を取得
     * 
     * @param pai 検索対象。
     * @return 所持数。
     */
    public int getMenZenJanPaiCount(final JanPai pai) {
        return _core.get(pai);
    }
    
    /**
     * 面前手牌リストを取得
     * 
     * @return 面前手牌リスト。
     */
    public List<JanPai> getMenZenList() {
        return JanPaiUtil.convertJanPaiMap(_core);
    }
    
    /**
     * 面前手牌マップを取得
     * 
     * @return 面前手牌マップ。
     */
    public Map<JanPai, Integer> getMenZenMap() {
        return deepCopyMap(_core);
    }
    
    /**
     * 面前手牌枚数を取得
     * 
     * @return 面前手牌枚数。
     */
    public int getMenZenSize() {
        return JanPaiUtil.getJanPaiTotalCount(_core);
    }
    
    /**
     * 空き枚数を取得
     * 
     * @return 空き枚数。
     */
    public int getUsableSize() {
        return 14 - ((_fixedMenTsuList.size() * 3) + getMenZenSize());
    }
    
    /**
     * ハッシュコードを取得
     * 
     * @return ハッシュコード。
     */
    @Override
    public int hashCode() {
        return _core.hashCode() + _fixedMenTsuList.hashCode();
    }
    
    /**
     * 面前手牌の枚数上限に達しているか
     * 
     * @return 判定結果。
     */
    public boolean isLimitSize() {
        final int menZenCount = JanPaiUtil.getJanPaiTotalCount(_core);
        return menZenCount >= getLimitSize();
    }
    
    /**
     * 確定面子を削除
     * 
     * @param index 面子インデックス。
     */
    public void removeFixedMenTsu(final int index) {
        if (index < 0) {
            return;
        }
        if (index >= _fixedMenTsuList.size()) {
            return;
        }
        _fixedMenTsuList.remove(index);
    }
    
    /**
     * 牌を削除
     * 
     * @param pai 削除する牌。
     */
    public void removeJanPai(final JanPai pai) {
        if (pai == null) {
            return;
        }
        
        final int count = _core.get(pai);
        if (count == 0) {
            return;
        }
        _core.put(pai, count - 1);
    }
    
    /**
     * 確定面子リストを設定
     * 
     * @param menTsuList 面子リスト。
     */
    public void setFixedMenTsuList(final List<MenTsu> menTsuList) {
        if (menTsuList != null) {
            _fixedMenTsuList = deepCopyList(menTsuList);
        }
        else {
            _fixedMenTsuList.clear();
        }
    }
    
    /**
     * 牌リストを設定
     * 
     * @param sourceList 牌リスト。
     */
    public void setJanPaiList(final List<JanPai> sourceList) {
        initializeCore();
        if (sourceList != null) {
            for (final JanPai source : sourceList) {
                JanPaiUtil.addJanPai(_core, source, 1);
            }
        }
    }
    
    /**
     * 牌マップを設定
     * 
     * @param source 牌マップ。
     */
    public void setJanPaiMap(final Map<JanPai, Integer> source) {
        initializeCore();
        if (source != null) {
            for (final Map.Entry<JanPai, Integer> entry : source.entrySet()) {
                JanPaiUtil.addJanPai(_core, entry.getKey(), entry.getValue());
            }
        }
    }
    
    /**
     * 雀牌リストに変換
     * 
     * @return 変換結果。
     */
    public List<JanPai> toList() {
        final List<JanPai> resultList = JanPaiUtil.convertJanPaiMap(_core);
        for (final MenTsu menTsu : _fixedMenTsuList) {
            resultList.addAll(menTsu.getSource());
        }
        return resultList;
    }
    
    /**
     * 文字列に変換
     * 
     * @return 変換結果。
     */
    @Override
    public String toString() {
        final StringBuilder buf = new StringBuilder();
        for (final JanPai pai : JanPaiUtil.convertJanPaiMap(_core)) {
            buf.append(pai);
        }
        for (final MenTsu menTsu : _fixedMenTsuList) {
            buf.append(" ");
            for (final JanPai pai : menTsu.getSource()) {
                buf.append(pai);
            }
        }
        return buf.toString();
    }
    
    
    
    /**
     * 牌マップを全消去
     */
    private void initializeCore() {
        _core.clear();
        for (final JanPai pai : JanPai.values()) {
            _core.put(pai, 0);
        }
    }
    
    /**
     * リストをディープコピー
     * 
     * @param sourceList 複製元リスト。
     * @return 複製結果。
     */
    private <E> List<E> deepCopyList(final List<E> sourceList) {
        return new ArrayList<E>(sourceList);
    }
    
    /**
     * マップをディープコピー
     * 
     * @param source 複製元マップ。
     * @return 複製結果。
     */
    private <S, T> Map<S, T> deepCopyMap(final Map<S, T> source) {
        return new TreeMap<S, T>(source);
    }
    
    
    
    /**
     * 牌マップ
     */
    private Map<JanPai, Integer> _core = new TreeMap<JanPai, Integer>();
    
    /**
     * 確定面子リスト
     */
    private List<MenTsu> _fixedMenTsuList = new ArrayList<MenTsu>();
    
}

