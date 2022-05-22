package com.getir.bookstore.module.statistics;

import com.getir.bookstore.common.model.Result;
import com.getir.bookstore.module.statistics.model.Statistics;
import com.getir.bookstore.module.statistics.service.StatisticsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/stats")
@Api("APIs related to resource : STATS")
public class StatisticsController {

  @Autowired
  private StatisticsService statsService;

  @GetMapping
  public ResponseEntity getStatistics(
      @RequestParam @NotNull Long customerId, @RequestParam Integer year) {
    Result<List<Statistics>> result = statsService.getCustomerStats(customerId, year);

    if (result.isHasError()) {
      return new ResponseEntity(result.getErrorMessage(), result.getStatus());
    }

    return ResponseEntity.ok(result.getObject());
  }

}
