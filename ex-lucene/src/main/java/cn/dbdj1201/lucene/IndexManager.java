package cn.dbdj1201.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author tyz1201
 * @datetime 2020-03-22 15:44
 **/
public class IndexManager {

    private IndexWriter indexWriter;
    private Directory directory;

    @Before
    public void init() throws Exception {
        //创建一个IndexWriter对象，需要使用IKAnalyzer作为分析器
        System.out.println("??");
        directory = FSDirectory.open(new File("F:\\temp\\index").toPath());
        indexWriter =
                new IndexWriter(directory,
                        new IndexWriterConfig(new IKAnalyzer(true)));
    }

    @Test
    public void addDocument() throws Exception {
        //创建一个IndexWriter对象，需要使用IKAnalyzer作为分析器
//        IndexWriter indexWriter =
//                new IndexWriter(FSDirectory.open(new File("F:\\temp\\index").toPath()),
//                        new IndexWriterConfig(new IKAnalyzer()));
        //创建一个Document对象
        Document document = new Document();
        //向document对象中添加域
        document.add(new TextField("name", "新添加的文件", Field.Store.YES));
        document.add(new TextField("content", "新添加的文件内容", Field.Store.NO));
        document.add(new StoredField("path", "F:/temp/halo"));
        // 把文档写入索引库
        indexWriter.addDocument(document);
        //关闭索引库
        indexWriter.close();
    }

    @Test
    public void deleteAllDocument() throws Exception {
        //删除全部文档
        indexWriter.deleteAll();
        //关闭索引库
        indexWriter.close();
    }

    @Test
    public void deleteDocumentByQuery() throws Exception {
        indexWriter.deleteDocuments(new Term("name", "apache"));
        indexWriter.close();
    }

    @Test
    public void updateDocument() throws Exception {
        //创建一个新的文档对象
        Document document = new Document();
        //向文档对象中添加域
        document.add(new TextField("name", "更新之后的文档111111", Field.Store.YES));
        document.add(new TextField("name1", "更新之后的文档222222", Field.Store.YES));
        document.add(new TextField("name2", "更新之后的文档333333", Field.Store.YES));
        //更新操作
        indexWriter.updateDocument(new Term("name", "更新"), document);
        //关闭索引库
        indexWriter.close();
    }

    @Test
    public void testSearch() throws IOException {
//        1、创建一个Director对象，指定索引库的位置

//        2、创建一个IndexReader对象
        IndexReader indexReader = DirectoryReader.open(directory);
//        3、创建一个IndexSearcher对象，构造方法中的参数indexReader对象。
        IndexSearcher searcher = new IndexSearcher(indexReader);
//        4、创建一个Query对象，TermQuery

        Query query = new TermQuery(new Term("name", "更新"));
//        5、执行查询，得到一个TopDocs对象
        TopDocs topDocs = searcher.search(query, 10);
//        6、取查询结果的总记录数
        long totals = topDocs.totalHits.value;
        System.out.println("total hits-->" + totals);
//        7、取文档列表
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        Arrays.stream(scoreDocs).forEach(scoreDoc -> {
            int docId = scoreDoc.doc;
            try {
                Document document = searcher.doc(docId);
                document.getFields().forEach(System.out::println);
                System.out.println("=========================");
//                System.out.println(document.get("name"));
//                System.out.println(document.get("content"));
//                System.out.println(document.get("path"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
//        8、打印文档中的内容
//        9、关闭IndexReader对象
        indexReader.close();
    }
}
