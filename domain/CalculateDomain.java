package domain;


public class CalculateDomain {


    public void input(DateDomain dd, StartDomain sd, EndDomain ed, WorkMinutesDomain wd, FileOutInterface fileOutDatasource) {


        fileOutDatasource.kintaiOutPut(dd, sd, ed, wd);

    }

    public void total(DateDomain dd, FileOutInterface fileOutDatasource) {

        fileOutDatasource.totalOutPut(dd);

    }

}