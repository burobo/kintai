package domain;


public class WorkMinutesDomain {

    private final int workMinutes;
    private final int overWorkMinutes;
    private int tmpWorkMinutes;

    public WorkMinutesDomain(StartDomain sd, EndDomain ed) {

        this.tmpWorkMinutes = ed.getEndTotalM() - sd.getStartTotalM();
        if (ed.getEndH().getEndH() == 12) {
            this.tmpWorkMinutes -= ed.getEndM().getEndM();
        } else if (ed.getEndH().getEndH() >= 13) {
            this.tmpWorkMinutes -= 60;
        } else if  (ed.getEndH().getEndH() == 18) {
            this.tmpWorkMinutes -= ed.getEndM().getEndM();
        } else if (ed.getEndH().getEndH() >= 19) {
            this.tmpWorkMinutes -= 60;
        } else if  (ed.getEndH().getEndH() == 21) {
            this.tmpWorkMinutes -= ed.getEndM().getEndM();
        } else if (ed.getEndH().getEndH() >= 22) {
            this.tmpWorkMinutes -= 60;
        }
        this.workMinutes = this.tmpWorkMinutes;
        this.overWorkMinutes = Math.max(this.workMinutes - 8 * 60, 0);
    }


    public int getOverWorkMinutes() {
        return overWorkMinutes;
    }

    public int getWorkMinutes() {
        return workMinutes;
    }

}
