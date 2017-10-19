/**
 * Created by root on 8/22/17.
 */
public class CVAttr {
    String s;
    CVSplit.TYPE t;

    public CVAttr(String s, CVSplit.TYPE t) {
        this.s = s;
        this.t = t;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public CVSplit.TYPE getT() {
        return t;
    }

    public void setT(CVSplit.TYPE t) {
        this.t = t;
    }
}
