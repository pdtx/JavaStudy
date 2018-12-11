/**
 * @description: 开闭原则：最好不要对自己已经交付的类进行修改
 * comparable 自己和自己比，可以看作是自营性质的比较器
 * comparator 第三方比较器，可以看作是平台性质的比较器
 * @author: fancying
 * @create: 2018-12-11 21:31
 **/
package chapter6;

import java.util.Comparator;

public class SearchResultComparator implements Comparator<SearchResult> {


    @Override
    public int compare(SearchResult o1, SearchResult o2) {
        // 避免 if-else 嵌套过多使用卫语句来实现
        if (o1.relativeRatio != o2.relativeRatio) {
            return o1.relativeRatio > o2.relativeRatio ? 1 : -1;
        }

        if (o1.recentOrders != o2.recentOrders) {
            return o1.recentOrders > o2.recentOrders ? 1 : -1;
        }

        if (o1.count != o2.count) {
            return o1.count > o2.count ? 1 : -1 ;
        }

        return 0;
    }
}

class SearchResult implements Comparable<SearchResult> {

    int relativeRatio;
    long count;
    int recentOrders;

    @Override
    public int compareTo(SearchResult o) {
        // 先比较相关度
        if (this.relativeRatio != o.relativeRatio) {
            return this.relativeRatio > o.relativeRatio ? 1 : -1;
        }

        // 相关度相等时再比较浏览数
        if (this.count != o.count) {
            return this.count > o.count ? 1 : -1;
        }
        return 0;
    }

    public void setRecentOrders(int recentOrders) {
        this.recentOrders = recentOrders;
    }
}