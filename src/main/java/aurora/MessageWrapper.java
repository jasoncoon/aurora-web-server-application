package aurora;

public class MessageWrapper {
    private ScrollingTextMessage message;

    public MessageWrapper() {
    }

    public MessageWrapper(ScrollingTextMessage message) {
        this.message = message;
    }

    public ScrollingTextMessage getMessage() {
        return message;
    }

    public void setMessage(ScrollingTextMessage message) {
        this.message = message;
    }
}
