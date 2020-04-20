package cn.dbdj1201.es.pojo;

import org.elasticsearch.index.engine.Engine;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author tyz1201
 * @datetime 2020-04-20 11:23
 **/
@Document(indexName = "web_page_items", type = "docs", shards = 1, replicas = 0)
public class WebPageItem implements Serializable {
    @Id
    @Field(store = true, type = FieldType.Integer)
    private Integer id;
    @Field(store = true, type = FieldType.Text, analyzer = "ik_max_word")
    private String title;
    @Field(store = true, type = FieldType.Text, analyzer = "ik_max_word")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WebPageItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
