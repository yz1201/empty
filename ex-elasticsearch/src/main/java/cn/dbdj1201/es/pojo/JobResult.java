package cn.dbdj1201.es.pojo;

import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-20 14:35
 **/
public class JobResult {

    private List<JobInfoItem> rows;
    private Integer pageTotal;

    public List<JobInfoItem> getRows() {
        return rows;
    }

    public void setRows(List<JobInfoItem> rows) {
        this.rows = rows;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    @Override
    public String toString() {
        return "JobResult{" +
                "rows=" + rows +
                ", pageTotal=" + pageTotal +
                '}';
    }
}
