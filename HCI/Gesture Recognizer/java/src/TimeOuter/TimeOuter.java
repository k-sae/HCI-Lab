package TimeOuter;

/**
 * Created by kareem on 7/4/17.
 */
public  abstract class TimeOuter extends Thread {
//    public void
    private volatile int extend;
    private volatile boolean isWaiting;
    private volatile int Timeout = 20;
    @Override
    public void run() {
        while (true)
        {
            try {
                Thread.sleep(Timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setExtend(-1);
            if (extend <= 0)
            {
                onFinish();
                setWaiting(true);
                while (isWaiting);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void extend()
    {
        setWaiting(false);
        if (extend < 2)
            setExtend(1);
    }
    public abstract void onFinish();
    private synchronized void setExtend(int val)
    {

        extend += val;
    }

    private synchronized void setWaiting(boolean waiting) {
        this.isWaiting = waiting;
    }

    public int getTimeout() {
        return Timeout;
    }

    public synchronized void setTimeout(int timeout) {
        this.Timeout = timeout;
    }
}
