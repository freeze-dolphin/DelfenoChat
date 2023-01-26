package io.sn.delfenochat.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChatUtils {

    public static Map<String, String> moeDict = new HashMap<>();

    static {
        moeDict.put(":xz:", "(╯°Д°)╯︵ ┻━┻");
        moeDict.put(":ax:", "˗ˋˏ♡ˎˊ˗");
        moeDict.put(":f:", "꒰ঌ( ⌯' '⌯)໒꒱");
        moeDict.put(":lh:", "(//̀Д/́/)");
        moeDict.put(":my:", "✧*｡ (ˊᗜˋ*) ✧*｡");
        moeDict.put(":wk:", "(*＞◡❛)");
        moeDict.put(":qd:", "(⑅˃◡˂⑅)");
        moeDict.put(":hhh:", "(●′∀｀●)");
    }

    public static Component minides(String input) {
        return MiniMessage.miniMessage().deserialize(input);
    }

    public static Component concludeMoeDict() {
        Component rst = Component.empty();
        for (String key : moeDict.keySet()) {
            rst = rst.append(minides("        <white> " + key + " <gray>[<white>" + moeDict.get(key) + "<gray>]<newline>"));
        }
        return rst;
    }

    private static final HanyuPinyinOutputFormat pyformat = new HanyuPinyinOutputFormat();

    static {
        pyformat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        pyformat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        pyformat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
    }

    public static String matchChengYu(String firstChar) throws IOException, BadHanyuPinyinOutputFormatCombination {
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(ChatUtils.class.getClassLoader().getResourceAsStream("chengyu.txt"))));
        br.readLine();
        br.readLine();
        br.readLine();
        String linestr;
        while ((linestr = br.readLine()) != null) {
            if (PinyinHelper.toHanYuPinyinString(String.valueOf(linestr.charAt(0)), pyformat, "", false).equals(firstChar)) {
                break;
            }
        }
        br.close();
        return linestr;
    }

    public static String processMoe(String text) {
        for (String key : moeDict.keySet()) {
            text = text.replaceAll(key, moeDict.get(key));
        }
        return text;
    }

    public static String processChatMessage(String text) {
        if (text.startsWith("$")) {
            return MiniMessage.miniMessage().deserialize(
                    "<rainbow>" + text.substring(1) + "</rainbow>").toString();
        } else if (text.startsWith(",")) {
            return text.substring(1);
        } else {
            return "{#74ebd5>}" + text + "{#acb6e5<}";
        }
    }
}
