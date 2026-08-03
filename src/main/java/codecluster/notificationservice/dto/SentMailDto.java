package codecluster.notificationservice.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class SentMailDto {

    @NotBlank
    private String from;
    @NotBlank
    private String to;
    @NotBlank
    private String status;
    @NotBlank
    private LocalDate sentAt;
    @NotBlank
    private boolean successStatus;

    public boolean isSuccessStatus() {
        return successStatus;
    }

    public void setSuccessStatus(boolean successStatus) {
        this.successStatus = successStatus;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDate sentAt) {
        this.sentAt = sentAt;
    }

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
