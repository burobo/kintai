package com.naosim.dddwork.kintai.api.feature.input;

import com.naosim.dddwork.kintai.api.request.RequestOperands;
import com.naosim.dddwork.kintai.api.request.protocol.RequestProcessor;
import com.naosim.dddwork.kintai.datasource.settings.DataSourceConfiguration;
import com.naosim.dddwork.kintai.datasource.timerecord.protocol.DataSourceFactory;
import com.naosim.dddwork.kintai.service.command.DailyWorkedTimeRegistrationService;


/**
 * ［指定日の勤怠登録］リクエストプロセッサー
 */
public class InputRequestProcessor implements RequestProcessor {

    final DailyWorkedTimeRegistrationService service;


    public InputRequestProcessor() {

        final DataSourceFactory dataSourceFactory = DataSourceConfiguration.DATA_STORE_POLICY.dataSourceFactory();
        service = new DailyWorkedTimeRegistrationService(dataSourceFactory.workedTimeRepository());
    }


    @Override
    public void execute(RequestOperands operands) {

        InputRequestOperandParser parser = InputRequestOperandParser.of(operands);
        DailyWorkedTimeRegistrationService.Parameter parameter = parser.serviceParameter();

        service.execute(parameter);
    }
}
