package com.zhq.example;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

public class TestAnalyzer {
    private static final String cnStr = "佳县木头峪镇高李家沟村发生突发性黄土崩塌灾害";

    private static final String enStr = "SINA English is the English language destination for news and information about China";

    public static void main(String[] args) throws IOException {
        Analyzer analyzer = null;
        System.out.println("====> 标准分词");
        analyzer = new StandardAnalyzer();
        print(analyzer);

        System.out.println("====> 简单分词");
        analyzer = new SimpleAnalyzer();
        print(analyzer);

        System.out.println("====> 空格分词");
        analyzer = new WhitespaceAnalyzer();
        print(analyzer);

        System.out.println("====> 关键词分词");
        analyzer = new KeywordAnalyzer();
        print(analyzer);

        System.out.println("====> 二元分词");
        analyzer = new CJKAnalyzer();
        print(analyzer);

        System.out.println("====> 中文智能分词");
        analyzer = new SmartChineseAnalyzer();
        print(analyzer);

        System.out.println("====> IK分词");
        analyzer = new IKAnalyzer();
        print(analyzer);
    }

    private static void print(Analyzer analyzer) throws IOException {
        StringReader enReader = new StringReader(enStr);
        TokenStream enStream = analyzer.tokenStream(enStr, enReader);
        enStream.reset();
        CharTermAttribute attr = enStream.getAttribute(CharTermAttribute.class);
        while (enStream.incrementToken()) {
            System.out.print(attr.toString() + "|");
        }
        System.out.println();
        enStream.close();

        StringReader cnReader = new StringReader(cnStr);
        TokenStream cnStream = analyzer.tokenStream(cnStr, cnReader);
        cnStream.reset();
        attr = cnStream.getAttribute(CharTermAttribute.class);
        while (cnStream.incrementToken()) {
            System.out.print(attr.toString() + "|");
        }
        System.out.println("\n");
        analyzer.close();
    }
}
