/**
 * CpuCommand.java 2019-01-22
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.commands;

import java.lang.management.ManagementFactory;

import org.bukkit.command.CommandSender;

import com.sun.management.OperatingSystemMXBean;

import me.nyanguymf.serverutils.managers.MessagesManager;
import me.nyanguymf.serverutils.utils.StringUtils;

/**
 * @author nyanguymf
 *
 * @since 1.2
 */
public class CpuCommand extends Command {

    public CpuCommand(String permission, String command, MessagesManager mm) {
        super(permission, command, mm);
    }

    @Override
    public boolean execute(CommandSender sender, boolean permission, String... args) {
        if (!super.execute(sender, permission)) return false;

        OperatingSystemMXBean operatingSystem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        String cpuSystemUsage   = super.mm.getColoredMessage("cpu.system");
        String cpuJavaUsage     = super.mm.getColoredMessage("cpu.java");
        String cpuAverageUsage  = super.mm.getColoredMessage("cpu.average");

        double systemUsage  = Math.round(operatingSystem.getSystemCpuLoad() * 1000) / 10D;
        double javaUsage    = Math.round(operatingSystem.getProcessCpuLoad() * 1000) / 10D;
        double averageUsage = operatingSystem.getSystemLoadAverage(); 

        sender.sendMessage(new String[] {
                StringUtils.replaceVariable(cpuSystemUsage, String.valueOf(systemUsage)),
                StringUtils.replaceVariable(cpuJavaUsage, String.valueOf(javaUsage)),
                StringUtils.replaceVariable(cpuAverageUsage, String.valueOf(averageUsage))
        });

        return true;
    }
}
