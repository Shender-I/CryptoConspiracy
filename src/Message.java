public class Message {
    private final String originalText;
    private String processedText;

    public Message(String originalText) {
        this.originalText = originalText;
    }

    public String getOriginalText() { return originalText; }
    public void setProcessedText(String processedText) { this.processedText = processedText; }
    public String getProcessedText() { return processedText; }
}