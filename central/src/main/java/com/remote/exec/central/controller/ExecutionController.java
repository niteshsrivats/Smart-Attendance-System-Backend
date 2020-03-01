package com.remote.exec.central.controller;

import com.remote.exec.central.models.entities.Code;
import com.remote.exec.central.named.Endpoints;
import com.remote.exec.central.service.ExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
@CrossOrigin
public class ExecutionController {

    private final ExecutionService executionService;

    @Autowired
    public ExecutionController(ExecutionService executionService) {
        this.executionService = executionService;
    }

    @PostMapping(Endpoints.Execution.Base)
    public String executeFile(@RequestBody @NotBlank Code code) {
        return executionService.executeCode(code.getCompiler(), code.getCode());
    }
}
