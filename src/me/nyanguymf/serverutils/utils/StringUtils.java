/**
 * StringUtils.java 2019-01-09
 * 
 * @author NyanGuyMF
 * 
 * @version 1.0
 */
package me.nyanguymf.serverutils.utils;

/**
 * @author nyanguymf
 *
 */
public class StringUtils {

    /**
     * <p>This method replaces all <i>&</i> symbols 
     * to standard Minecraft color symbol <i>\u00a7</i>.</p>
     *
     * @param toReplace <p>takes String to replace.</p>
     *
     * @return <p>returns the same string with colors.</p>
     */
    public static String replaceColors(String toReplace) {
        return toReplace.replace("&", "\u00a7");
    }

    /**
     * <p>This method replaces all <i>{n}</i> substrings
     * in transmitted String to corresponding var in vars array.</p>
     *
     * @param toReplace <p>Takes String to replace.</p>
     *
     * @param vars <p>Takes variables array to add in String.</p>
     *
     * @return <p>Returns String with add variables from vars array.</p>
     */
    public static String replaceVariables(String toReplace, String[] vars) {
        if (vars.length == 0) {
            return toReplace;
        }

        for (int i = 0; i < vars.length; i++) {
            toReplace = toReplace.replace("{" + i + "}", vars[i]);
        }

        return toReplace;
    }

    /**
     * <p>Replaces all {0} placeholders to given String.</p>
     *
     * @param toReplace String to replace.
     *
     * @param var Will replace all placeholders.
     *
     * @return Given String, where all {0} placeholders are replaced by
     * given argument.
     */
    public static String replaceVariable(String toReplace, String var) {
        if (var == null) {
            return "";
        }

        return toReplace.replace("{0}", var);
    }

    /**
     * Same method as {@link #replaceVarColored(String, String)},
     * but with all Bukkit colors repalced.
     */
    public static String replaceVarColored(String toReplace, String var) {
        return replaceColors(replaceVariable(toReplace, var));
    }
}
