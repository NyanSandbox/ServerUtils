/**
 * LagMeterPoller.java 2019-01-10
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands.tps;

/**
 * @author nyanguymf
 *
 */
class LagMeterPoller implements Runnable {
    private long        lastPoll;
    private int         interval;
    private TPSCommand  tpsCmd;

    @SuppressWarnings("unused")
    private long        polls;

    public LagMeterPoller(TPSCommand tpsCmd) {
        lastPoll    = System.currentTimeMillis() - 3000;
        polls       = 0;
        interval    = 40;
        this.tpsCmd = tpsCmd;
    }

    @Override
    public void run() {
        long now        = System.currentTimeMillis();
        long timeSpent  = (now - lastPoll) / 1000;

        if (timeSpent == 0)
            timeSpent = 1;

        float tps = interval / timeSpent;

        tpsCmd.setTicksPerSecond(tps);
        tpsCmd.getHistory().add(tps);

        lastPoll = now;
        polls++;
    }
}
