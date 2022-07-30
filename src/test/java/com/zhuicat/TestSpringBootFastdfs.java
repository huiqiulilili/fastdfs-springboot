package com.zhuicat;

import com.github.tobato.fastdfs.domain.MataData;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhuicat
 * @since 2022/7/30 14:13
 */
@SpringBootTest
public class TestSpringBootFastdfs {

    @Autowired
    private FastFileStorageClient fastFileStorageClient; // storageClient

    /**
     * 上传文件
     */
    @Test
    public void testUpload() throws FileNotFoundException {
        // 参数1：文件输入流
        // 参数2：文件长度
        // 参数3：文件的后缀
        // 参数4：metaData
        Set<MataData> mataDatas = new HashSet<>();
        mataDatas.add(new MataData("originFileName","Redis2.md"));
        mataDatas.add(new MataData("upload_author","lyx"));
        File file = new File("H:/Redis2.md");
        FileInputStream fileInputStream = new FileInputStream(file);
        StorePath storePath = fastFileStorageClient.uploadFile(fileInputStream, file.length(), "md", mataDatas);


        System.out.println(storePath.getFullPath()); // group1/XXX
        System.out.println(storePath.getGroup());
        System.out.println(storePath.getPath());
    }

    /**
     * 下载文件
     * group1/M00/00/00/wKhYgGLkz6-AWuFFAAES_hbSQPA5586.md
     */
    @Test
    public void testdownloadFile() throws IOException {
        byte[] bytes = fastFileStorageClient.downloadFile("group1", "M00/00/00/wKhYgGLkz6-AWuFFAAES_hbSQPA5586.md", new DownloadByteArray());

        FileOutputStream fileOutputStream = new FileOutputStream(new File("J:/img/a.md"));
        fileOutputStream.write(bytes);
    }

    /**
     * 获取元数据信息
     */
    @Test
    public void testdownloadFile2() throws IOException {
        Set<MataData> mataDatas = fastFileStorageClient.getMetadata("group1", "M00/00/00/wKhYgGLkz6-AWuFFAAES_hbSQPA5586.md");
        for (MataData mataData : mataDatas) {
            System.out.println(mataData.getName()+"=>"+mataData.getValue());
        }
    }
}














