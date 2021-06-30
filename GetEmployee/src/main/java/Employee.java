public class Employee {
    private int employeeId;
    private int threadId;
    private short id;
    private short albumId;
    private String title;
    private String url;
    private String thumbnailUrl;

    public Employee() { }

    public Employee(int employeeId, int threadId, short id, short albumId, String title, String url, String thumbnailUrl) {
        this.employeeId = employeeId;
        this.threadId = threadId;
        this.id = id;
        this.albumId = albumId;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getEmployeeId() { return employeeId; }

    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public short getId() { return id; }

    public void setId(short id) { this.id = id; }

    public short getAlbumId() { return albumId; }

    public void setAlbumId(short albumId) { this.albumId = albumId; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getThumbnailUrl() { return thumbnailUrl; }

    public int getThreadId() { return threadId; }

    public void setThreadId(int threadId) { this.threadId = threadId; }

    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }
}
