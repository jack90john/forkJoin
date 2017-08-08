import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Description: Fork/Join执行类
 * Designer: jack
 * Date: 2017/8/3
 * Version: 1.0.0
 */
public class Test {

    public static void main(String args[]) throws ExecutionException, InterruptedException {

        String[] strings = {"a", "ah", "b", "ba", "ab", "ac", "sd", "fd", "ar", "te", "se", "te",
                "sdr", "gdf", "df", "fg", "gh", "oa", "ah", "qwe", "re", "ty", "ui"};
        List<String> stringList = new ArrayList<>(Arrays.asList(strings));

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinService<List<String>> forkJoinService = ForkJoinTest.getInstance(stringList, 20);
        // 提交可分解的ForkJoinTask任务
        ForkJoinTask<List<String>> future = pool.submit(forkJoinService);
        System.out.println(future.get());
        // 关闭线程池
        pool.shutdown();

    }

}
