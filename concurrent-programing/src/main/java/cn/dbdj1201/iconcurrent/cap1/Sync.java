package cn.dbdj1201.iconcurrent.cap1;

import cn.dbdj1201.iconcurrent.utils.FileReader;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tyz1201
 * @datetime 2020-05-19 10:33
 **/
@Slf4j(topic = "c.Sync")
public class Sync {

    public static void main(String[] args) {
        FileReader.read("F:\\BaiduNetdiskDownload\\study\\userwords\\assets\\01348FC2.gif");
        log.debug("do other things ...");
    }
}
