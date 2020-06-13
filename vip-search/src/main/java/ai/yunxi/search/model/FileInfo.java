package ai.yunxi.search.model;

public class FileInfo {
    // 文件名称
    private String filename;
    // 文件内容
    private String content;
    // 文件路径
    private String path;
    // 文件大小
    private int size;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
