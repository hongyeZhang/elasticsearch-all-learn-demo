package com.zhq.example;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Paths;

/**
 * @author ZHQ
 */
public class SearchTestDemo {



    public void searchIndex() throws Exception {
        // 索引存放路径
        Directory directory = FSDirectory.open(Paths.get("E:/Lucene/index"));
        // 创建IndexReader
        IndexReader indexReader = DirectoryReader.open(directory);
        // 创建IndexSearcher
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // 创建查询
        Query query = new TermQuery(new Term("content","考试"));
        // 获取搜索的结果，指定返回document返回的个数
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
