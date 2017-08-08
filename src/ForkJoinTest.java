import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: ForkJoin基类
 * Designer: jack
 * Date: 2017/8/3
 * Version: 1.0.0
 */
public class ForkJoinTest extends ForkJoinService<List<String>> {

    private static ForkJoinTest forkJoinTest;
    private int threshold;  //阈值
    private List<String> list; //待拆分List

    private ForkJoinTest(List<String> list, int threshold) {
        this.list = list;
        this.threshold = threshold;
    }

    @Override
    protected List<String> compute() {
        // 当end与start之间的差小于阈值时，开始进行实际筛选
        if (list.size() < threshold) {
            return list.parallelStream().filter(s -> s.contains("a")).collect(Collectors.toList());
        } else {
            // 如果当end与start之间的差大于阈值时,将大任务分解成两个小任务。
            int middle = list.size() / 2;
            List<String> leftList = list.subList(0, middle);
            List<String> rightList = list.subList(middle, list.size());
            ForkJoinTest left = new ForkJoinTest(leftList, threshold);
            ForkJoinTest right = new ForkJoinTest(rightList, threshold);
            // 并行执行两个“小任务”
            left.fork();
            right.fork();
            // 把两个“小任务”的结果合并起来
            List<String> join = left.join();
            join.addAll(right.join());
            return join;
        }
    }

    /**
     * 获取ForkJoinTest实例
     * @param list  待处理List
     * @param threshold 阈值
     * @return ForkJoinTest实例
     */
    public static ForkJoinService<List<String>> getInstance(List<String> list, int threshold) {
        if (forkJoinTest == null) {
            synchronized (ForkJoinTest.class) {
                if (forkJoinTest == null) {
                    forkJoinTest = new ForkJoinTest(list, threshold);
                }
            }
        }
        return forkJoinTest;
    }
}
