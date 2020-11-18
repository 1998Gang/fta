package cn.ezs.fta.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <h3>fta</h3>
 * <p>信息类</p>
 *
 * @author : 1998Gang
 * @date : 2020-08-10 17:38
 **/
public class Message {
    //信息id
    private int mid;
    //信息标题
    private String title;
    //信息内容
    private String content;
    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Massage{" +
                "mid=" + mid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
