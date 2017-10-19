import bean.User;
import dao.UserOperation;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.net.*;
import java.nio.channels.FileChannel;
import java.sql.Array;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Created by root on 4/28/17.
 */
public class Main {

    public static void main(String args[]) throws IOException, InvalidFormatException, BadHanyuPinyinOutputFormatCombination, ParseException {
        int TIKTOK = 24 * 60* 60 * 1000;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date t = f.parse("2017-08-12 23:59:59");
        Timestamp s = new Timestamp((t.getTime()  + TimeZone.getDefault().getOffset(0)) / TIKTOK * TIKTOK - TimeZone.getDefault().getOffset(0));
        Timestamp sx = new Timestamp((t.getTime()  + TimeZone.getDefault().getOffset(0)) / TIKTOK * TIKTOK + TIKTOK -1000 - TimeZone.getDefault().getOffset(0));
        System.out.println(s);
        System.out.println(sx);
    }
}
