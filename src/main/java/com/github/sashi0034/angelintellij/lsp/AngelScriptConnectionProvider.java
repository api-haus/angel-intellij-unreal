package com.github.sashi0034.angelintellij.lsp;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.openapi.diagnostic.Logger;
import com.redhat.devtools.lsp4ij.server.OSProcessStreamConnectionProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AngelScriptConnectionProvider extends OSProcessStreamConnectionProvider {
    private static final Logger LOG = Logger.getInstance(AngelScriptConnectionProvider.class);
    
    public AngelScriptConnectionProvider() {
        LOG.info("Initializing AngelScript Language Server connection");
        
        InputStream input = getClass().getResourceAsStream("/js/angelscript-language-server.js");
        if (input == null) {
            String error = "Can't find /js/angelscript-language-server.js in resources";
            LOG.error(error);
            throw new IllegalStateException(error);
        }

        try {
            // Create a temporary file for the language server
            // deleteOnExit ensures it persists during the IDE session
            File tempJsFile = File.createTempFile("angelscript-language-server", ".js");
            tempJsFile.deleteOnExit();
            
            LOG.info("Created temporary file for LSP server: " + tempJsFile.getAbsolutePath());
            
            try (FileOutputStream fos = new FileOutputStream(tempJsFile)) {
                input.transferTo(fos);
                LOG.info("Successfully wrote LSP server to temporary file");
            } catch (IOException e) {
                String error = "Failed to write LSP server to temporary file";
                LOG.error(error, e);
                throw new IllegalStateException(error, e);
            }

            // Build the command line
            GeneralCommandLine commandLine = new GeneralCommandLine("node", tempJsFile.getAbsolutePath());
            
            // Set working directory to help with relative paths if needed
            // commandLine.setWorkDirectory(project.getBasePath());
            
            LOG.info("LSP server command: " + commandLine.getCommandLineString());
            
            super.setCommandLine(commandLine);
        } catch (IOException e) {
            String error = "Failed to create temporary file for LSP server";
            LOG.error(error, e);
            throw new IllegalStateException(error, e);
        }
        
        LOG.info("AngelScript Language Server connection initialized successfully");
    }
    
    @Override
    public void start() {
        LOG.info("Starting AngelScript Language Server process");
        try {
            super.start();
            LOG.info("AngelScript Language Server process started successfully");
        } catch (Exception e) {
            LOG.error("Failed to start AngelScript Language Server", e);
            throw new RuntimeException("Failed to start AngelScript Language Server", e);
        }
    }
    
    @Override
    public void stop() {
        LOG.info("Stopping AngelScript Language Server");
        super.stop();
        LOG.info("AngelScript Language Server stopped");
    }
}