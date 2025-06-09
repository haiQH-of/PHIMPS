package Entity;

import java.sql.Date;

public class ShareReport {
    private Integer id;
    private String senderEmail;
    private String receiveEmail;
    private Date sendDate;

    public ShareReport() {

    }

    public ShareReport(Integer id, String senderEmail, String receiveEmail, Date sendDate) {
        this.id = id;
        this.senderEmail = senderEmail;
        this.receiveEmail = receiveEmail;
        this.sendDate = sendDate;
    }

    public Integer getUserId() {
        return id;
    }

    public void setid(Integer id) {
        this.id = id;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
}
