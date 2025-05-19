package fsa.cplminiprj.dto;

public class UserStatusStats {
    private int status;
    private int count;

    public UserStatusStats() {
    }

    public UserStatusStats(int status, int count) {
        this.status = status;
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "UserStatusStats{" +
                "status=" + status +
                ", count=" + count +
                '}';
    }
}
