package codecluster.notificationservice.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) representing the result of an
 * email sending operation.
 */
public class SentMailDto {

    /** Email address of the sender. */
    @NotBlank
    private String from;

    /** Email address of the recipient. */
    @NotBlank
    private String to;

    /** Status message describing the email delivery result. */
    @NotBlank
    private String status;

    /** Date on which the email was sent. */
    @NotBlank
    private LocalDate sentAt;

    /** Indicates whether the email was sent successfully. */
    @NotBlank
    private boolean successStatus;

    /**
     * @return {@code true} if the email was sent successfully,
     * otherwise {@code false}
     */
    public boolean isSuccessStatus() {
        return successStatus;
    }

    /**
     * @param successStatus email delivery status
     */
    public void setSuccessStatus(boolean successStatus) {
        this.successStatus = successStatus;
    }

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
     * @return status of the email sending operation
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status status of the email sending operation
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return date when the email was sent
     */
    public LocalDate getSentAt() {
        return sentAt;
    }

    /**
     * @param sentAt date when the email was sent
     */
    public void setSentAt(LocalDate sentAt) {
        this.sentAt = sentAt;
    }

    /**
     * Returns a string representation of the email response.
     *
     * @return formatted string containing email delivery details
     */
    @Override
    public String toString() {
        return "SentMailDto{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", status='" + status + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }
}