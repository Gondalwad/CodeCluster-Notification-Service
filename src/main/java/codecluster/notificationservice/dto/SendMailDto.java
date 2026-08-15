package codecluster.notificationservice.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) used to carry email details
 * from the client to the notification service.
 */
public class SendMailDto {

    /** Email address of the sender. */
    @NotBlank
    private String from;

    /** Email address of the recipient. */
    @NotBlank
    private String to;

    /** Subject of the email. */
    @NotBlank
    private String subject;

    /** Title displayed within the email template. */
    @NotBlank
    private String title;

    /** Main content/body of the email. */
    @NotBlank
    private String content;

    /**
     * @return sender's email address
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from sender's email address
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return email body content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content email body content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return recipient's email address
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to recipient's email address
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return email subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject email subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return title displayed in the email template
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title title displayed in the email template
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns a string representation of the email request.
     *
     * @return formatted string containing email details
     */
    @Override
    public String toString() {
        return "SendMailDto{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}