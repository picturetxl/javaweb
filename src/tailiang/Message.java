
package tailiang;
/**
 * Message
 */
public class Message {

    private String mid;
    private String username;
    private String title;
    private String message;

    /**
     * @return the mid
     */
    public String getMid() {
        return mid;
    }
    /**
     * @param mid the mid to set
     */
    public void setMid(String mid) {
        this.mid = mid;
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    public void setMessage(String message)
    {
        this.message=message;
    }
}