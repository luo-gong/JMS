package lg.cn.springbootjmsactivemqconsumer.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd mm:HH:ss")
    private Date date;

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

}
