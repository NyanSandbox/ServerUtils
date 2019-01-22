/**
 * TPSCommand.java 2019-01-09
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands.tps;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.nyanguymf.serverutils.ServerUtils;
import me.nyanguymf.serverutils.commands.Command;
import me.nyanguymf.serverutils.managers.MessagesManager;
import me.nyanguymf.serverutils.utils.StringUtils;

/**
 * @author nyanguymf
 *
 * @author DemmyDemon https://github.com/DemmyDemon/LagMeter
 */
public class TPSCommand extends Command {
    private float           ticksPerSecond;
    private boolean         useAverage;
    private int             averageLength;
    private int             interval;
    private LagMeterStack   history;
    private LagMeterPoller  poller;
    private ServerUtils     su;

    /** init scheduler for TPS */
    public TPSCommand(String permission, String command, ServerUtils plugin, MessagesManager mm) {
        super(permission, command, mm);

        ticksPerSecond  = 20;
        useAverage      = true;
        averageLength   = 10;
        interval        = 40;
        this.su         = plugin;
        history         = new LagMeterStack();
        poller          = new LagMeterPoller(this);

        getHistory().setMaxSize(averageLength);
        su.getServer().getScheduler().scheduleSyncRepeatingTask(su, poller, 0, interval);
    }

    /**
     * Shows TPS to command sender.
     *
     * A lot acknowledgements to DemmyDemon
     * for open source project. Origin
     * https://github.com/DemmyDemon/LagMeter
     */
    @Override
    public boolean execute(CommandSender sender, boolean permissionAll, String... args) {
        if (!super.execute(sender, permissionAll)) return false;

        String tps     = getTPS();
        String message = super.mm.getMessage("tps");

        sender.sendMessage(StringUtils.replaceVarColored(message, tps));

        return true;
    }


    private String getTPS() {
        String  lagMeter    = "";
        float   tps         = 0f;

        if (isUseAverage())
            tps = getHistory().getAverage();
        else
            tps = ticksPerSecond;

        if (tps < 21) {
            int looped = 0;

            while (looped++ < tps) {
                lagMeter += "#";
            }

            lagMeter += ChatColor.WHITE;

            while (looped++ <= 20)
                lagMeter += "_";

        } else {
            return "TPS meter just loaded, please wait for polling.";
        }

        ChatColor color;

        if (tps >= 20)
            color = ChatColor.GREEN;
        else if (tps >= 18)
            color = ChatColor.GREEN;
        else if (tps >= 15)
            color = ChatColor.YELLOW;
        else
            color = ChatColor.RED;

        return ChatColor.AQUA + "[" + color + lagMeter + ChatColor.AQUA + "] " + tps;
    }



    /**
     * @return the ticksPerSecond
     */
    protected float getTicksPerSecond() {
        return ticksPerSecond;
    }


    /**
     * @param ticksPerSecond the ticksPerSecond to set
     */
    protected void setTicksPerSecond(float ticksPerSecond) {
        this.ticksPerSecond = ticksPerSecond;
    }

    /**
     * @return the history
     */
    protected LagMeterStack getHistory() {
        return history;
    }

    /**
     * @param history the history to set
     */
    protected void setHistory(LagMeterStack history) {
        this.history = history;
    }

    /**
     * @return the useAverage
     */
    protected boolean isUseAverage() {
        return useAverage;
    }

    /**
     * @param useAverage the useAverage to set
     */
    protected void setUseAverage(boolean useAverage) {
        this.useAverage = useAverage;
    }
}
