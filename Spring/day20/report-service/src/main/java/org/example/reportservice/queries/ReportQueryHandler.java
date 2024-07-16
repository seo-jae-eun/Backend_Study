package org.example.reportservice.queries;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.example.common.config.Constants;
import org.example.common.dto.ReportDTO;
import org.example.common.queries.GetReportId;
import org.example.reportservice.entity.Report;
import org.example.reportservice.repository.ReportRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class ReportQueryHandler {

    private final ReportRepository reportRepository;
    @Autowired
    public ReportQueryHandler(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @QueryHandler
    private String handle(GetReportId qry) {
        log.info("[@QueryHandler] Handle <GetReportId> for Order Id: {}", qry.getOrderId());
        Optional<Report> optReport = reportRepository.findByOrderId(qry.getOrderId());
        if(optReport.isPresent()) {
            return optReport.get().getReportId();
        } else {
            return "";
        }
    }

    @QueryHandler(queryName = Constants.QUERY_REPORT)
    private ReportDTO handle(String orderId) {
        log.info("[@QueryHandler] Handle <{}}> for Order Id: {}", Constants.QUERY_REPORT, orderId);
        Optional<Report> optReport = reportRepository.findByOrderId(orderId);
        if(optReport.isPresent()) {
            ReportDTO report = new ReportDTO();
            BeanUtils.copyProperties(optReport.get(), report);
            return report;
        } else {
            return null;
        }
    }
}
