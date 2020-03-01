package com.remote.exec.central.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

@Service
public class ExecutionService {

    private int number = 10000;

    private String format(Stream<String> stringStream) {
        ArrayList<String> lines = new ArrayList<>();
        stringStream.iterator().forEachRemaining(lines::add);
        return String.join("\n", lines);
    }


    public String executeCode(String compiler, String code) {
        ProcessBuilder builder = new ProcessBuilder();
        number += 1;
//        if (isWindows) {
//            builder.command("cmd.exe", "/c", "dir");
//        } else {
        builder.command("sh", "-c", "docker exec CodeExec python home/code/" + number + ".py");
//        }\
        builder.directory(new File(System.getProperty("user.dir") + "/code/" + compiler));
        try {
            Path p = Files.createFile(Paths.get(System.getProperty("user.dir") + "/code/" + compiler + "/" + number + ".py"));
            File file = new File(System.getProperty("user.dir") + "/code/" + compiler + "/" + number + ".py");
            file.createNewFile();
            Files.write(p, code.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Process process = null;
        try {
            process = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert process != null;
        String result = format(new BufferedReader(new InputStreamReader(process.getErrorStream())).lines());
        if (result.length() == 0) {
            result = format(new BufferedReader(new InputStreamReader(process.getInputStream())).lines());
        }

//        Executors.newSingleThreadExecutor().submit();
        int exitCode = 1;
        try {
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert exitCode == 0;
        return result;
    }
}
