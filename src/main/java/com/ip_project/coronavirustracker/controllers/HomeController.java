package com.ip_project.coronavirustracker.controllers;

import com.ip_project.coronavirustracker.Services.CoronaVirusDataService;
import com.ip_project.coronavirustracker.models.LocationStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Locale;

@Controller
public class HomeController {
    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats= coronaVirusDataService.getAllStats();
        int Totalcases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int TotalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPreviousDay()).sum();
        model.addAttribute("locationStats",allStats );
        model.addAttribute("TotalReportedCases",Totalcases );
        model.addAttribute("TotalNewCases",TotalNewCases);
        return "home";

    }
}
