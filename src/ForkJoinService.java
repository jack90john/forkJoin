import java.util.concurrent.RecursiveTask;

/**
 * Description: ForkJoin接口
 * Designer: jack
 * Date: 2017/8/3
 * Version: 1.0.0
 */
public abstract class ForkJoinService<T> extends RecursiveTask<T>{
    @Override
    protected abstract T compute();
}
