package org.example.reportservice.events;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.example.common.events.delete.DeletedReportEvent;
import org.example.common.events.update.UpdatedOrderToReportEvent;
import org.example.common.events.update.UpdatedPaymentToReportEvent;
import org.example.reportservice.entity.Report;
import org.example.reportservice.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class ReportEventsHandler {
    private final ReportRepository reportRepository;
    @Autowired
    public ReportEventsHandler(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @EventHandler
    private void on(DeletedReportEvent event) {
        log.info("[@EventHandler] Handle <DeletedReportEvent> for Order Id: {}", event.getOrderId());

        Optional<Report> optReport = reportRepository.findByOrderId(event.getOrderId());
        if(optReport.isEmpty()) {
            log.info("Can't find Report info for Order Id: {}", event.getOrderId());
            return;
        }
        try {
            reportRepository.delete(optReport.get());
        } catch(Exception e) {
            log.error(e.getMessage());
        }
    }
    /*
    - 목적: Order데이터 변경 시 Update Report using CQRS패턴
    */
    @EventHandler
    private void on(UpdatedOrderToReportEvent event) {
        log.info("[@EventHandler] Handle <UpdatedOrderToReportEvent> for Order Id: {}", event.getOrderId());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Optional<Report> optReport = reportRepository.findByOrderId(event.getOrderId());
        if(optReport.isEmpty()) {
            log.info("Can't find Report info for Order Id: {}", event.getOrderId());
            return;
        }
        Report report = optReport.get();
        report.setOrderDatetime(event.getOrderDatetime());
        report.setTotalOrderAmt(event.getTotalOrderAmt());
        report.setOrderStatus(event.getOrderStatus());
        report.setOrderDetails(gson.toJson(event.getOrderDetails()));

        reportRepository.save(report);
    }

    /*
    - 목적: Payment 데이터 변경 시 Update Report using CQRS패턴
    */
    @EventHandler
    private void on(UpdatedPaymentToReportEvent event) {
        log.info("[@EventHandler] Handle <UpdatedPaymentToReportEvent> for Order Id: {}", event.getOrderId());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Optional<Report> optReport = reportRepository.findByOrderId(event.getOrderId());
        if(optReport.isEmpty()) {
            log.info("Can't find Report info for Order Id: {}", event.getOrderId());
            return;
        }
        Report report = optReport.get();
        report.setTotalPaymentAmt(event.getTotalPaymentAmt());
        report.setPaymentDetails(gson.toJson(event.getPaymentDetails()));
        report.setPaymentStatus(event.getPaymentStatus());
        reportRepository.save(report);
    }
}
