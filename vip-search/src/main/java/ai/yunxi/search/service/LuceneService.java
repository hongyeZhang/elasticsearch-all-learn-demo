package ai.yunxi.search.service;

import ai.yunxi.search.model.FileInfo;
import org.apache.lucene.document.Document;

import java.util.List;

public interface LuceneService {
    public List<FileInfo> getTopN(String field, String keyword, int num);

    public List<FileInfo> getDocs(String field, String keyword, int pageNum, int num);
}
