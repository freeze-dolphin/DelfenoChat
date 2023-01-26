package io.sn.delfenochat;

import io.sn.delfenochat.utils.ChatUtils;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.io.IOException;

public class MainTest {

    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination, IOException {
        System.out.println(ChatUtils.matchChengYu("yue"));
    }
}
