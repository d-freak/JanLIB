/**
 * ChmCompleteInfo.java
 * 
 * @Author
 *   Masasutzu
 */

package wiz.project.jan;

import java.util.ArrayList;
import java.util.List;

import wiz.project.jan.yaku.ChmYaku;



/**
 * 和了情報 (不変オブジェクト)
 */
public final class ChmCompleteInfo {
    
    /**
     * コンストラクタ
     * 
     * @param yakuList 役リスト。
     * @param type 和了タイプ。
     */
    public ChmCompleteInfo(final List<ChmYaku> yakuList, final CompleteType type) {
        setYakuList(yakuList);
        setCompleteType(type);
    }
    
    /**
     * コピーコンストラクタ
     * 
     * @param source 複製元。
     */
    public ChmCompleteInfo(final ChmCompleteInfo source) {
        if (source != null) {
            _yakuList = deepCopyList(source._yakuList);
            _completeType = source._completeType;
        }
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
        if (!(target instanceof ChmCompleteInfo)) {
            return false;
        }
        
        final ChmCompleteInfo targetInfo = (ChmCompleteInfo)target;
        return _yakuList.equals(targetInfo._yakuList) &&
               (_completeType == targetInfo._completeType);
    }
    
    /**
     * 和了タイプを取得
     * 
     * @return 和了タイプ。
     */
    public CompleteType getCompleteType() {
        return _completeType;
    }
    
    /**
     * 役リストを取得
     * 
     * @return 役リスト。
     */
    public List<ChmYaku> getYakuList() {
        return deepCopyList(_yakuList);
    }
    
    /**
     * ハッシュコードを取得
     * 
     * @return ハッシュコード。
     */
    @Override
    public int hashCode() {
        return _yakuList.hashCode() + _completeType.hashCode();
    }
    
    /**
     * 文字列に変換
     * 
     * @return 変換結果。
     */
    @Override
    public String toString() {
        return _yakuList + "";
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
     * 和了タイプを設定
     * 
     * @param type 和了タイプ。
     */
    private void setCompleteType(final CompleteType type) {
        if (type != null) {
            _completeType = type;
        }
        else {
            _completeType = CompleteType.UNKNOWN;
        }
    }
    
    /**
     * 役リストを設定
     * 
     * @param yakuList 役リスト。
     */
    private void setYakuList(final List<ChmYaku> yakuList) {
        if (yakuList != null) {
            _yakuList = deepCopyList(yakuList);
        }
        else {
            _yakuList.clear();
        }
    }
    
    
    
    /**
     * 役リスト
     */
    private List<ChmYaku> _yakuList = new ArrayList<ChmYaku>();
    
    /**
     * 和了タイプ
     */
    private CompleteType _completeType = CompleteType.UNKNOWN;
    
}

