package ai.yunxi.search.query;

import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

public class PrefixQueryDemo {
    public static void main(String[] args) throws IOException {
        // 索引存放路径
        Directory directory = FSDirectory.open(Paths.get("D:/Lucene/index"));
        // 创建IndexReader
        IndexReader indexReader = DirectoryReader.open(directory);
        // 创建IndexSearcher
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        Term term = new Term("content", "考试");
        PrefixQuery query = new PrefixQuery(term);
        ScoreDoc[] hits = indexSearcher.search(query, 10).scoreDocs;

        // 遍历ScoreDoc
        for (ScoreDoc scoreDoc : hits) {
            // 根据Document的id找到document对象
            Document doc = indexSearcher.doc(scoreDoc.doc);
            System.out.print(doc.get("filename") + ":    ");
            System.out.println(doc.get("path"));
        }
        indexReader.close();
        directory.close();
    }
}
