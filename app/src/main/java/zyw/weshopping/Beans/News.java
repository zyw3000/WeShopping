package zyw.weshopping.Beans;


public class News {
    private int Zx_ID;
    private String Zx_Mame;
    private String Zx_Tp;
    private String Zx_Content;
    private String Zx_Date;
    private Boolean Zx_Fb;

    public News() {
 /*       this.Zx_ID=Zx_ID;
        this.Zx_Mame=Zx_Name;
        this.Zx_Tp=Zx_Tp;
        this.Zx_Content=Zx_Content;
        this.Zx_Date=Zx_Date;
        this.Zx_Fb=Zx_Fb;*/
    }

    public int getZx_ID() {
        return Zx_ID;
    }

    public void setZx_ID(int Zx_ID) {
        this.Zx_ID = Zx_ID;
    }

    public String getZx_Mame() {
        return Zx_Mame;
    }

    public void setZx_Mame(String zx_Mame) {
        this.Zx_Mame = zx_Mame;
    }

    public String getZx_Tp() {
        return Zx_Tp;
    }

    public void setZx_Tp(String Zx_Tp) {
        this.Zx_Tp = Zx_Tp;
    }

    public String getZx_Content() {
        return Zx_Content;
    }

    public void setZx_Content(String Zx_Content) {
        this.Zx_Content = Zx_Content;
    }

    public String getZx_Date() {
        return Zx_Date;
    }

    public void setZx_Date(String Zx_Date) {
        this.Zx_Date = Zx_Date;
    }

    public Boolean getZx_Fb() {
        return Zx_Fb;
    }

    public void setZx_Fb(Boolean Zx_Fb) {
        this.Zx_Fb = Zx_Fb;
    }

    public String toString() {
        return "News{";
    }
}
