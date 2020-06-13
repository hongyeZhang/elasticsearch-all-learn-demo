package ai.yunxi.search.service;

import ai.yunxi.search.model.FileInfo;
import org.apache.lucene.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LuceneServiceImpl implements LuceneService {
    @Autowired
    LuceneUtils luceneUtils;

    @Override
    // 查找TopN
    public List<FileInfo> getTopN(String field, String keyword, int num) {
        return luceneUtils.searchIndex(field, keyword, num);
    }

    @Override
    // 分页查询
    public List<FileInfo> getDocs(String field, String keyword, int pageNum, int size) {
        return luceneUtils.searchIndex(field, keyword, pageNum, size);
    }
}
