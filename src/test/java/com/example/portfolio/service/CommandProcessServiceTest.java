package com.example.portfolio.service;

import com.example.portfolio.repository.FundRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommandProcessServiceTest {

    private FundRepository repo;
    private PortfolioService svc;
    private CommandProcessService cps;

    @BeforeEach
    void init() throws Exception {
        URL json = getClass().getClassLoader().getResource("stock_data.json");
        repo = new FundRepository(json);
        svc = new PortfolioService(repo);
        cps = new CommandProcessService(repo, svc);
    }

    @Test
    void processCommands_endToEnd_outputsCorrectly() throws Exception {

        Path tmp = Files.createTempFile("cmds", ".txt");
        List<String> lines = Arrays.asList(
                "CURRENT_PORTFOLIO AXIS_BLUECHIP ICICI_PRU_BLUECHIP",
                "CALCULATE_OVERLAP AXIS_BLUECHIP",
                "ADD_STOCK AXIS_BLUECHIP DUMMY_STOCK",
                "CALCULATE_OVERLAP AXIS_BLUECHIP"
        );
        Files.write(tmp, lines, StandardCharsets.UTF_8);


        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bout));

        cps.processCommands(tmp.toString());

        String out = bout.toString();
        assertTrue(out.contains("AXIS_BLUECHIP"), "result output");
        assertTrue(out.contains("DUMMY_STOCK") || !out.contains("FUND_NOT_FOUND"));
    }

    @Test
    void processCommands_invalidCommand_printsError() throws Exception {
        Path tmp = Files.createTempFile("cmds", ".txt");
        List<String> lines = Arrays.asList(
                "UNKNOWN_CMD X Y Z"
        );
        Files.write(tmp, lines, StandardCharsets.UTF_8);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> cps.processCommands(tmp.toString()),
                "throw IllegalArgumentException"
        );
        // check error msg
        assertTrue(ex.getMessage().contains("Unknown command"),
                "Exception message should mention unknown command");
    }

}
