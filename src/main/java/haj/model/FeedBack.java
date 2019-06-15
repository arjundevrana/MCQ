package haj.model;

import javax.persistence.*;

@Entity
@Table(name = "USER_FEEDBACK")
public class FeedBack extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;
    @Column(name="username")
    private String username;
    @Column(name="WEB_FILE_NUMBER")
    private String webFileNumber;
    @Column(name="QUESTION_ID")
    private long questionId;
    @Column(name="ANSWER_ID")
    private String answerId;
    @Column(name="PTO_ID")
    private String ptoId;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebFileNumber() {
        return webFileNumber;
    }

    public void setWebFileNumber(String webFileNumber) {
        this.webFileNumber = webFileNumber;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getPtoId() {
        return ptoId;
    }

    public void setPtoId(String ptoId) {
        this.ptoId = ptoId;
    }


}
