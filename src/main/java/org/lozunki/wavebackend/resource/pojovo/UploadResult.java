package org.lozunki.wavebackend.resource.pojovo;

import lombok.Data;

@Data
public class UploadResult {

    /**
     * 文件URL
     */
    private String url;
    /**
     * 文件大小
     */
    private long fileSize;
    /**
     * 文档MIME类型
     */
    private String contentType;
    /**
     * 文件名
     */
    private String fileName;

}
