/**
 * TenpaiPattern.java
 * 
 * @Author
 *   Masasutzu
 */

package wiz.project.jan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



/**
 * 和了パターン (不変オブジェクト)
 */
public final class CompletePattern implements Serializable {

    /**
     * コンストラクタ
     * 
     * @param head 頭。
     * @param mentsuList 面子リスト。
     */
    public CompletePattern(final JanPai head, final List<MenTsu> mentsuList) {
        setHead(head);
        setMenTsuList(mentsuList);
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
        if (!(target instanceof CompletePattern)) {
            return false;
        }
        
        final CompletePattern targetPattern = (CompletePattern) target;
        return (_head == targetPattern._head) &&
               _mentsuList.equals(targetPattern._mentsuList);
    }
    
    /**
     * 頭を取得
     * 
     * @return 頭。
     */
    public JanPai getHead() {
        return _head;
    }
    
    /**
     * 面子リストを取得
     * 
     * @return 面子リスト。
     */
    public List<MenTsu> getMenTsuList() {
        return deepCopyList(_mentsuList);
    }
    
    /**
     * 順子の数を取得
     * 
     * @return 順子の数。
     */
    public int getShunTsuCount() {
        int shuntsuCount = 0;
        
        for (final MenTsu mentsu : _mentsuList) {
            if (mentsu.getMenTsuType().isShunTsu()) {
                shuntsuCount++;
            }
        }
        return shuntsuCount;
    }
    
    /**
     * ハッシュコードを取得
     * 
     * @return ハッシュコード。
     */
    @Override
    public int hashCode() {
        return _head.hashCode() + _mentsuList.hashCode();
    }
    
    /**
     * 文字列に変換
     * 
     * @return 変換結果。
     */
    @Override
    public String toString() {
        return _head + " , " + _mentsuList;
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
     * 頭を設定
     * 
     * @param pai 頭。
     */
    private void setHead(final JanPai pai) {
        if (pai != null) {
            _head = pai;
        }
        else {
            _head = JanPai.HAKU;
        }
    }
    
    /**
     * 面子リストを設定
     * 
     * @param mentsuList 面子リスト。
     */
    private void setMenTsuList(final List<MenTsu> mentsuList) {
        if (mentsuList != null) {
            _mentsuList = deepCopyList(mentsuList);
        }
        else {
            _mentsuList.clear();
        }
    }
    
    
    
    /**
     * シリアルバージョン
     */
    private static final long serialVersionUID = 1L;
    
    
    
    /**
     * 頭
     */
    private JanPai _head = JanPai.HAKU;
    
    /**
     * 面子リスト
     */
    private List<MenTsu> _mentsuList = new ArrayList<MenTsu>();
    
}

