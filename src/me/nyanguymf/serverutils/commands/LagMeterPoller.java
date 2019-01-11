/**
 * LagMeterPoller.java 2019-01-10
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands;

/**
 * @author nyanguymf
 *
 */
class LagMeterPoller implements Runnable {
    private long lastPoll   = System.currentTimeMillis() - 3000;
    @SuppressWarnings("unused")
    private long polls      = 0;
    private int  interval   = 40;

    @Override
    public void run() {
        long now        = System.currentTimeMillis();
        long timeSpent  = (now - lastPoll) / 1000;

        if (timeSpent == 0)
            timeSpent = 1;

        float tps = interval / timeSpent;

        TPSCommand.ticksPerSecond = tps;
        TPSCommand.history.add(tps);

        lastPoll = now;
        polls++;
    }

}