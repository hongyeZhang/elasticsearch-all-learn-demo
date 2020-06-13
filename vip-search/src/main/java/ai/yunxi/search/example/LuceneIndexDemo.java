package ai.yunxi.search.example;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class LuceneIndexDemo {
    // 创建索引
    public void createIndex() throws IOException {
        // 索引存放路径
        Directory directory = FSDirectory.open(Paths.get("D:/Lucene/index"));

        // 创建IKAnalzyer分析器
        IKAnalyzer analyzer = new IKAnalyzer();

        // 创建IndexWriterConfig
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        // 是否使用复合索引格式
        iwc.setUseCompoundFile(false);

        // 创建IndexWriter
        IndexWriter indexWriter = new IndexWriter(directory, iwc);

        // 原始文档的路径
        File path = new File("D:/Lucene/data");
        for (File file : path.listFiles()) {
            // 创建Document对象
            Document document = new Document();

            FieldType fieldType = new FieldType();
            fieldType.setIndexOptions(IndexOptions.NONE);
            fieldType.setStored(true);
            // fieldType.setStoreTermVectors(false);
            // fieldType.setStoreTermVectorPositions(false);

            // 文件名称
            document.add(new TextField("filename", file.getName(), Field.Store.YES));
            // 文件内容
            Field content = new TextField("content", FileUtils.readFileToString(file, "GBK"),
                    Field.Store.YES);
            document.add(content);
            // 文件路径
            document.add(new StoredField("path", file.getPath()));
            // 文件大小
            document.add(new NumericDocValuesField("size", FileUtils.sizeOf(file)));

            // 写入索引
            indexWriter.addDocument(document);
        }

        // 写入完毕，清理工作
        if (indexWriter != null) {
            indexWriter.close();
            indexWriter = null;
        }
    }

    // 搜索
    public void searchIndex() throws Exception {
        // 索引存放路径
        Directory directory = FSDirectory.open(Paths.get("D:/Lucene/index"));
        // 创建IndexReader
        IndexReader indexReader = DirectoryReader.open(directory);
        // 创建IndexSearcher
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // 查询解析器，可以指定查询的属性名和分词器
        // SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        // QueryParser parser = new QueryParser("content", analyzer);
        // Query query = parser.parse("全国考试");

        // 创建查询
        Query query = new TermQuery(new Term("content", "考试"));
        // 获取搜索的结果，指定返回document返回的个数
        ScoreDoc[] hits = indexSearcher.search(query, 10).scoreDocs;

        // 遍历ScoreDoc
        for (ScoreDoc scoreDoc : hits) {
            // 根据Document的id找到document对象
            Document doc = indexSearcher.doc(scoreDoc.doc);
            System.out.print(doc.get("filename") + ":    ");
            //System.out.println(doc.get("content"));
        }
        indexReader.close();
        directory.close();
    }

    // 删除索引
    public void deleteIndex() throws IOException {
        // 索引存放路径
        Directory directory = FSDirectory.open(Paths.get("D:/Lucene/index"));

        // 创建IKAnalzyer分析器
        IKAnalyzer analyzer = new IKAnalyzer();

        // 创建IndexWriterConfig
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

        // 创建IndexWriter
        IndexWriter indexWriter = new IndexWriter(directory, iwc);

        indexWriter.deleteDocuments(new Term("content", "考试"));
        long l = indexWriter.commit();
        indexWriter.close();
        System.out.println("删除完毕");
    }

    // 更新索引
    public void updateIndex() throws IOException {
        // 索引存放路径
        Directory directory = FSDirectory.open(Paths.get("D:/Lucene/index"));

        // 创建IKAnalzyer分析器
        IKAnalyzer analyzer = new IKAnalyzer();

        // 创建IndexWriterConfig
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);

        // 创建IndexWriter
        IndexWriter indexWriter = new IndexWriter(directory, iwc);

        Document document = new Document();

        // 文件名称
        document.add(new TextField("filename", "9999.txt", Field.Store.YES));
        // 文件内容
        document.add(new TextField("content", "全国范围学习", Field.Store.YES));
        // 文件路径
        document.add(new StoredField("path", "D:/Lucene/index/9999.txt"));
        // 文件大小
        document.add(new NumericDocValuesField("size", 5));

        indexWriter.updateDocument(new Term("考试"), document);
        long l = indexWriter.commit();
        indexWriter.close();
        System.out.println("更新完毕");
    }

    public static void main(String[] args) {
        LuceneIndexDemo lid = new LuceneIndexDemo();
        try {
            // lid.createIndex();
            // lid.updateIndex();
            // lid.deleteIndex();
            lid.searchIndex();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}