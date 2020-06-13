package ai.yunxi.search.service;

import ai.yunxi.search.model.FileInfo;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class LuceneUtils {
    @Value("${lucene.index.dir}")
    private String indexDir;

    // 创建IndexSearcher
    private IndexSearcher indexSearcher;

    // 创建IndexReader
    private IndexReader indexReader;

    // 索引存放路径
    private Directory directory;

    public LuceneUtils() {
        try {
            // 索引存放路径
            this.directory = FSDirectory.open(Paths.get("D:/Lucene/index"));

            // 创建IndexReader
            this.indexReader = DirectoryReader.open(directory);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建IndexSearcher
        this.indexSearcher = new IndexSearcher(indexReader);
    }

    public List<FileInfo> searchIndex(String field, String keyword, int num) {
        List<FileInfo> list = new ArrayList<>();

        IKAnalyzer ikAnalyzer = new IKAnalyzer();
        // 创建查询
        Query query = null;
        // 查询字段解析
        QueryParser parser = new QueryParser(field, ikAnalyzer);

        try {
            // 解析关键字
            query = parser.parse(keyword);
            QueryScorer score = new QueryScorer(query, field);
            SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color=\"green\">",
                    "</font>");
            Highlighter highlighter = new Highlighter(formatter, score);
            // 获取搜索的结果，指定返回document返回的个数
            ScoreDoc[] hits = indexSearcher.search(query, num).scoreDocs;

            // 遍历ScoreDoc
            for (ScoreDoc scoreDoc : hits) {
                // 根据Document的id找到document对象
                Document doc = indexSearcher.doc(scoreDoc.doc);
                // 获取TokenStream
                TokenStream tokenStream = TokenSources.getAnyTokenStream(indexSearcher.getIndexReader(), scoreDoc.doc, field, ikAnalyzer);
                SimpleSpanFragmenter fragmenter = new SimpleSpanFragmenter(score);
                highlighter.setTextFragmenter(fragmenter);
                // 获取高亮
                String fragment = highlighter.getBestFragment(tokenStream, doc.get(field));
                FileInfo fi = new FileInfo();
                fi.setFilename(doc.get("filename"));
                fi.setContent(fragment);
                fi.setPath(doc.get("path"));
                fi.setSize(0);
                list.add(fi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<FileInfo> searchIndex(String field, String keyword, int pageNum, int size) {
        List<FileInfo> list = new ArrayList<>();

        IKAnalyzer ikAnalyzer = new IKAnalyzer();
        // 创建查询
        Query query = null;
        // 查询字段解析
        QueryParser parser = new QueryParser(field, ikAnalyzer);

        int num = (pageNum - 1) * size;

        try {
            query = parser.parse(keyword);
            // 查询本页之前的数据
            TopDocs topDocs = indexSearcher.search(query, num);
            if (topDocs.scoreDocs.length >= num) {
                // 获取到上一页最后一条
                ScoreDoc preScore = topDocs.scoreDocs[num - 1];

                // 解析关键字
                query = parser.parse(keyword);
                QueryScorer score = new QueryScorer(query, field);
                SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color=\"red\">",
                        "</font>");
                Highlighter highlighter = new Highlighter(formatter, score);
                // 获取搜索的结果，指定返回document返回的个数
                ScoreDoc[] hits = indexSearcher.searchAfter(preScore, query, size).scoreDocs;

                // 遍历ScoreDoc
                for (ScoreDoc scoreDoc : hits) {
                    // 根据Document的id找到document对象
                    Document doc = indexSearcher.doc(scoreDoc.doc);
                    // 获取TokenStream
                    TokenStream tokenStream = TokenSources.getAnyTokenStream(indexSearcher.getIndexReader(), scoreDoc.doc, field, ikAnalyzer);
                    SimpleSpanFragmenter fragmenter = new SimpleSpanFragmenter(score);
                    highlighter.setTextFragmenter(fragmenter);
                    // 获取高亮
                    String bestFragment = highlighter.getBestFragment(tokenStream, doc.get(field));
                    FileInfo fi = new FileInfo();
                    fi.setFilename(doc.get("filename"));
                    fi.setContent(bestFragment);
                    fi.setPath(doc.get("path"));
                    fi.setSize(0);
                    list.add(fi);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
